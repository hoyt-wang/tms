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
            <li class="header">系统功能</li>
            <!-- 客刷卡信息校验 -->
            <li class="treeview ${fn:startsWith(param.menu, 'customer_') ? 'active' : ''}">
                <a href="/ticket/validate">
                    <i class="fa fa-address-book-o"></i> <span>刷卡信息校验</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
            </li>


                <!-- 参数配置 -->
                <li class="${param.menu == 'employee' ? 'active' : ''}"><a href="/employee"><i class="fa fa-users"></i>
                    <span>参数配置</span></a>
                </li>

        </ul>
    </section>
</aside>