<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 左侧菜单栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="${param.menu == 'home' ? 'active' : ''}"><a href="/home"><i
                    class="fa fa-home"></i> <span>首页</span></a></li>
            <li class="header">库存管理系统</li>
            <!-- 客户管理 -->
            <li class="${fn:startsWith(param.menu, 'new') ? 'active' : ''}">
                <a href="/ticket/new">
                    <i class="fa fa-address-book-o"></i> <span>年票入库</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>
            <!-- 工作记录 -->
            <li class="treeview ${fn:startsWith(param.menu, 'out') ? 'active' : ''}">
                <a href="/ticket/out">
                    <i class="fa fa-bars"></i> <span>年票下发</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>
            <!-- 待办事项 -->
            <li class="${param.menu == 'in' ? 'active' : ''}"><a href="/ticket/invalid"><i class="fa fa-calendar"></i> <span>年票作废</span></a></li>
            <!-- 统计报表 -->
            <li class="treeview ${fn:startsWith(param.menu, 'list') ? 'active' : ''}">
                <a href="/ticket/list">
                    <i class="fa fa-pie-chart"></i> <span>盘点统计</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>

                <li class="header">系统管理</li>
                <!-- 部门员工管理 -->
                <li class="${param.menu == 'employee' ? 'active' : ''}"><a href="/employee"><i class="fa fa-users"></i>
                    <span>系统管理入口</span></a>
                </li>
            <!--<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>