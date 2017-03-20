<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
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
        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">设置密码</h3>
                </div>
                <div class="box-body">
                    <form method="post" id="changePasswordForm">
                        <div class="form-group">
                            <label>旧密码</label>
                            <input type="password" class="form-control" name="oldPassword" id="oldPassword">
                        </div>
                        <div class="form-group">
                            <label>新密码</label>
                            <input type="password" class="form-control" name="newPassword" id="newPassword">
                        </div>
                        <div class="form-group">
                            <label>确认密码</label>
                            <input type="password" class="form-control" name="replyPassword">
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button id="saveBtn" class="btn btn-primary pull-right">保存</button>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <%@include file="../include/footer.jsp" %>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.0 -->
<%@include file="../include/js.jsp" %>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function () {

        $("#changePasswordForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                oldPassword: {
                    required: true,
                },
                newPassword: {
                    required: true,
                    rangelength: [5, 18]
                },
                replyPassword: {
                    required: true,
                    rangelength: [5, 18],
                    equalTo: "#newPassword"
                }
            },
            messages: {
                oldPassword: {
                    required: "请输入旧密码",
                },
                newPassword: {
                    required: "请输入新密码",
                    rangelength: "密码长度5~18位"
                },
                replyPassword: {
                    required: "请输入确认密码",
                    rangelength: "密码长度5~18位",
                    equalTo: "两次密码不一致"
                }
            },
            submitHandler: function (form) {
                var oldPassword = $("#oldPassword").val();
                var newPassword = $("#newPassword").val();
                $.post("/user/password", {
                    "oldPassword": oldPassword,
                    "newPassword": newPassword
                }).done(function (data) {
                    if (data.status == 'success') {
                        alert("密码修改成功，点击确定重新登录");
                        window.location.href = "/";
                    } else {
                        alert(data.message);
                    }
                }).fail(function () {
                    alert("服务器异常");
                });
            }
        });


        $("#saveBtn").click(function () {
            $("#changePasswordForm").submit();
        });
    });
</script>
</body>
</html>
