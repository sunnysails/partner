<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="treeview ${param.menu == 'user' ? 'active' : ''}">
                <a href="/user">
                    <i class="fa fa-address-card"></i>
                    <span>账户列表</span>
                </a>
            </li>
            <li class="treeview ${fn:startsWith(param.menu,'setting_') ? 'active' : ''}">
                <a href="#">
                    <i class="fa fa-user-circle"></i>
                    <span>账号管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.menu == 'setting_password' ? 'active' : ''}"><a href="/setting/password"><i
                            class="fa fa-cog"></i>密码修改</a></li>
                    <li class="${param.menu == 'setting_login' ? 'active' : ''}"><a href="/log/login"><i
                            class="fa fa-history"></i>登陆日志</a></li>
                </ul>
            </li>

            <li class="treeview ${param.menu == 'task' ? 'active' : ''}">
                <a href="/task">
                    <i class="fa fa-calendar"></i>
                    <span>待办事项</span>
                </a>
            </li>
            <li class="treeview ${param.menu == 'document' ? 'active' : ''}">
                <a href="/doc">
                    <i class="fa fa-folder"></i>
                    <span>公司网盘</span>
                </a>
            </li>
            <li class="treeview">
                <a href="/logout">
                    <i class="fa fa-sign-out"></i>
                    <span>安全退出</span>
                </a>
            </li>



            <li class="treeview">
                <a href="#">
                    <i class="fa fa-address-card"></i>
                    <span>工作流模块</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li <%--class="${param.menu == 'process_apply' ? 'active' : ''}"--%>><a href="/process/apply"><i
                            class="fa fa-circle-o"></i> 发起流程</a></li>
                    <li <%--class="${param.menu == 'process_list' ? 'active' : ''}"--%>><a href="/process/task/list"><i
                            class="fa fa-circle-o"></i> 我的待办</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>财务报表</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/finance/day"><i class="fa fa-circle-o"></i> 日报</a></li>
                    <li><a href="/finance/month"><i class="fa fa-circle-o"></i> 月报</a></li>
                    <li><a href="/finance/year"><i class="fa fa-circle-o"></i> 年报</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>业务</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> 设备租赁<i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="/business/rent"><i class="fa fa-circle-o"></i> 业务流水</a></li>
                            <li><a href="/business/rent/new"><i class="fa fa-plus"></i> 新增流水 </a></li>
                        </ul>
                    </li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> 设备管理<i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="/business/device"><i class="fa fa-circle-o"></i> 设备库存</a></li>
                            <li><a href="/business/device/new"><i class="fa fa-plus"></i> 新增设备</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-circle-o"></i> 劳务人员 <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="/business/worker"><i class="fa fa-circle-o"></i> 工种清单</a></li>
                            <li><a href="/business/worker/new"><i class="fa fa-plus"></i> 新增工种</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-circle-o"></i> 劳务外包 <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="/business/out"><i class="fa fa-circle-o"></i> 业务流水</a></li>
                            <li><a href="/business/out/new"><i class="fa fa-plus"></i> 新增流水</a></li>
                        </ul>
                    </li>
                </ul>
            </li>

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <%--<li class="header">HEADER</li>--%>
            <!-- Optionally, you can add icons to the links -->
            <shiro:hasAnyRoles name="经理,员工">
                <li class="${param.menu == 'home' ? 'active' : ''}"><a href="/home"><i class="fa fa-home"></i> <span>首页</span></a></li>
                <li class="${param.menu == 'notice' ? 'active' : ''}"><a href="/notice"><i class="fa fa-bullhorn"></i> <span>公告</span></a></li>
                <li class="${param.menu == 'sales' ? 'active' : ''}"><a href="/sales"><i class="fa fa-building-o"></i> <span>销售机会</span></a></li>
                <li class="${param.menu == 'customer' ? 'active' : ''}"><a href="/customer"><i class="fa fa-users"></i> <span>客户管理</span></a></li>
                <li class="${param.menu == 'chart' ? 'active' : ''}"><a href="/chart"><i class="fa fa-bar-chart"></i> <span>统计</span></a></li>
                <li class="${param.menu == 'task' ? 'active' : ''}"><a href="/task"><i class="fa fa-calendar-check-o"></i> <span>待办事项</span></a></li>
                <li class="${param.menu == 'document' ? 'active' : ''}"><a href="/doc"><i class="fa fa-file-text"></i> <span>文档管理</span></a></li>
            </shiro:hasAnyRoles>
            <shiro:hasRole name="管理员">
                <li class="treeview">
                    <a href="javascript:;"><i class="fa fa-cogs"></i> <span>系统管理</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="/admin/users">员工管理</a></li>
                        <li><a href="#">系统设置</a></li>
                    </ul>
                </li>
            </shiro:hasRole>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>