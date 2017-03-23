<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Left side column. contains tde sidebar -->
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
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>