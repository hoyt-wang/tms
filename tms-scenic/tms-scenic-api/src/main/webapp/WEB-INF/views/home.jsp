<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-景区管理系统 | 首页</title>
    <%@include file="include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Blank page
                <small>it all starts here</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Examples</a></li>
                <li class="active">Blank page</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-body">
                    <c:if test="${not empty message}">
                        ${message}
                    </c:if>
                    <div id="countModal">

                            <label>今日客流量: 共计${not empty countCustomer ? countCustomer : 0}人</label>

                    </div>


                </div>
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="include/js.jsp"%>
<script>
    $(function () {
      /*  var scenicAccountId = <shiro:principal property="id"/>
        $.get("/ticket/count",{"scenicAccountId":scenicAccountId}).done(function (data) {
            if(data.state == 'success') {
                var count = data.data;

                var html = '<label>'+'今日共计'+count+'人入园参观'+'</label>';
                $("#countModal").append(html);
                /!*window.location.href = "/ticket/list";*!/
            } else {
                layer.msg(data.message);
            }
        }).error(function () {
            layer.msg("服务器忙，请稍后");
        });*/
    });
</script>
</body>
</html>
