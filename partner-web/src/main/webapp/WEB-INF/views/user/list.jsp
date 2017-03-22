<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2017/3/17
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <%@include file="../include/sider.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                员工管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">员工列表</h3>
                    <div class="box-tools pull-right">
                        <a href="javascript:;" id="newBtn" class="btn btn-xs btn-success"><i class="fa fa-plus"></i> 新增</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="userTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>账号</th>
                            <th>员工姓名</th>
                            <th>微信号</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->


<div class="modal fade" id="newModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增用户</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <div class="form-group">
                        <label>账号(用于系统登录)</label>
                        <input type="text" class="form-control" name="userName">
                    </div>
                    <div class="form-group">
                        <label>员工姓名(真实姓名)</label>
                        <input type="text" class="form-control" name="realName">
                    </div>
                    <div class="form-group">
                        <label>密码(默认 000000)</label>
                        <input type="text" class="form-control" name="password" value="000000">
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input type="text" class="form-control" name="weiXin">
                    </div>
                    <div class="form-group">
                        <label>角色</label>
                        <select class="form-control" name="role.id">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑用户</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="hidden" name="id" id="edit_user_id">
                    <div class="form-group">
                        <label>账号(用于系统登录)</label>
                        <input type="text" class="form-control" disabled name="userName" id="edit_user_username">
                    </div>
                    <div class="form-group">
                        <label>员工姓名(真实姓名)</label>
                        <input type="text" class="form-control" name="realName" id="edit_user_realname">
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input type="text" class="form-control" name="weiXin" id="edit_user_weixin">
                    </div>
                    <div class="form-group">
                        <label>角色</label>
                        <select class="form-control" name="role.id" id="edit_user_roleid">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>状态</label>
                        <select class="form-control" name="enable" id="edit_user_enable">
                            <option value="1">正常</option>
                            <option value="0">禁用</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="editBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    <%@include file="../include/footer.jsp" %>
</div><!-- /.modal -->


<!-- REQUIRED JS SCRIPTS -->

<%@include file="../include/js.jsp" %>
<%@include file="../include/dateTables.jsp" %>
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function () {

        var dataTable = $("#userTable").DataTable({
            serverSide: true,
            ajax: "/user/load/user",
            ordering: false,
            "autoWidth": false,
//            "searching": false,
            columns: [
                {"data": "id"},
                {"data": "userName"},
                {"data": "realName"},
                {"data": "weiXin"},
                {"data": "role.roleName"},
                {
                    "data": function (row) {
                        if (row.enable == 1) {
                            return "<span class='label label-success'>正常</span>";
                        } else {
                            return "<span class='label label-danger'>禁用</span>";
                        }
                    }
                },
                {
                    "data": function (row) {
                        var timestamp = row.createtime;
                        var day = moment(timestamp);
                        return day.format("YYYY-MM-DD HH:mm");
                    }
                },
                {
                    "data": function (row) {
                        if (row.role.roleName == '管理员') {
                            return "";
                        } else {
                            return "<a href='javascript:;' class='resetPwd' rel='" + row.id + "'>重置密码</a> &nbsp;&nbsp;" +
                                "<a href='javascript:;' class='edit' rel='" + row.id + "'>编辑</a>";
                        }
                    }
                }
            ],
            "language": { //定义中文
                "search": "请输入员工姓名或登录账号:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });

        //新增用户

        $("#newForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                userName: {
                    required: true,
                    rangelength: [3, 20],
                    remote: "/user/isuse"
                },
                realName: {
                    required: true,
                    rangelength: [2, 20]
                },
                password: {
                    required: true,
                    rangelength: [5, 18]
                },
                weiXin: {
                    required: true
                }
            },
            messages: {
                userName: {
                    required: "请输入用户名",
                    rangelength: "用户名的长度3~20位",
                    remote: "该用户名已被占用"
                },
                realName: {
                    required: "请输入真实姓名",
                    rangelength: "真实姓名长度2~20位"
                },
                password: {
                    required: "请输入密码",
                    rangelength: "密码长度6~18位"
                },
                weiXin: {
                    required: "请输入微信号码"
                }
            },
            submitHandler: function (form) {
                $.post("/user/new", $(form).serialize()).done(function (data) {
                    if (data.status == "success") {
                        $("#newModal").modal('hide');
                        dataTable.ajax.reload();
                    }
                }).fail(function () {
                    alert("服务器异常");
                });
            }
        });

        $("#newBtn").click(function () {
            $("#newForm")[0].reset();
            $("#newModal").modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        });

        $("#saveBtn").click(function () {
            $("#newForm").submit();
        });

        //重置密码
        $(document).delegate(".resetPwd", "click", function () {
            var id = $(this).attr("rel");
            if (confirm("确认将密码重置为：000000？")) {
                $.post("/user/resetpassword", {"id": id}).done(function (data) {
                    if (data == 'success') {
                        alert("密码重置成功");
                    }
                }).fail(function () {
                    alert("服务器异常");
                });
            }
        })

        //编辑

        $("#editForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                realName: {
                    required: true,
                    rangelength: [2, 20]
                },
                weixin: {
                    required: true
                }
            },
            messages: {
                realName: {
                    required: "请输入真实姓名",
                    rangelength: "真实姓名长度2~20位"
                },
                weiXin: {
                    required: "请输入微信号码"
                }
            },
            submitHandler: function (form) {
                $.post("/user/edit", $(form).serialize()).done(function (data) {
                    if (data.status == "success") {
                        $("#editModal").modal('hide');
                        dataTable.ajax.reload();
                    }
                }).fail(function () {
                    alert("服务器异常");
                });
            }
        });

        $(document).delegate(".edit", "click", function () {
            var id = $(this).attr("rel");
            $.get("/user/" + id + ".json").done(function (result) {
                if (result.status == "success") {
                    $("#edit_user_id").val(result.data.id);
                    $("#edit_user_username").val(result.data.userName);
                    $("#edit_user_realname").val(result.data.realName);
                    $("#edit_user_weixin").val(result.data.weiXin);
                    $("#edit_user_roleid").val(result.data.role.id);
                    console.log(result.data.enable);
                    if (result.data.enable == 1) {
                        $("#edit_user_enable").val("1");
                    } else {
                        $("#edit_user_enable").val("0");
                    }

                    $("#editModal").modal({
                        show: true,
                        dropback: 'static',
                        keyboard: false
                    });
                } else {
                    alert(result.message);
                }
            }).fail(function () {
                alert("服务器异常");
            });
        });

        $("#editBtn").click(function () {
            $("#editForm").submit();
        });
    });
</script>
</body>
</html>
