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

            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">登录日志列表</h3>
                        </div>
                        <div class="box-body">
                            <table class="table table-bordered table-hover" id="logTable">
                                <thead>
                                <tr>
                                    <th>登录时间</th>
                                    <th>登录IP</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
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
<%@include file="../include/dateTables.jsp" %>
<script>
    $(function(){

        var dateTable = $("#logTable").DataTable({
            searching:false,
            serverSide:true,
            ajax:"/user/log/load",
            ordering:false,
            "autoWidth": false,
            columns:[
                {"data":"logintime"},
                {"data":"loginip"}
            ],
            "language": { //定义中文
                "search": "请输入书籍名称:",
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
    });
</script>
</body>
</html>
