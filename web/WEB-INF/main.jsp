<%--
  Created by IntelliJ IDEA.
  User: 万顺开
  Date: 2019/10/9
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>main</title>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
    <script src="../static/jquery/jquery-2.1.3.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <style>
        .layui-header, .layui-bg-black {
            background: url('../static/imgs/简约.jpg') no-repeat !important;
            background-size: cover !important;
        }

        .a:hover {
            background-color: #6e94be !important;
        }

        .layui-logo {
            font-family: 隶书;
            font-size: 30px !important;
            color: white !important;
            margin-left: 50px;
        }

        .layui-layout-right, .layui-nav-tree {
            background-color: rgba(255, 255, 255, 0) !important;
        }
    </style>


</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">人事管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">

        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${USER_IN_SESSION.username}
                </a>
                <!--<dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>-->
            </li>
            <li class="layui-nav-item"><a href="/logout">退出登录</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black" style="width: 150px!important;">
        <div class="layui-side-scroll" style="width: 170px!important;">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test" style="width: 150px!important;">
                <li class="layui-nav-item">
                    <a class="a" href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" if="userlist">用户查询</a></dd>
                        <c:if test="${USER_IN_SESSION.status==0}">
                            <dd><a href="javascript:;" if="useradd">添加用户</a></dd>
                        </c:if>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="a" href="javascript:;">部门管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" if="deptlist">部门查询</a></dd>
                        <c:if test="${USER_IN_SESSION.status==0}">
                            <dd><a href="javascript:;" if="deptadd">添加部门</a></dd>
                        </c:if>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="a" href="javascript:;">职位管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" if="joblist">职位查询</a></dd>
                        <c:if test="${USER_IN_SESSION.status==0}">
                            <dd><a href="javascript:;" if="jobadd">添加职位</a></dd>
                        </c:if>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="a" href="javascript:;">员工管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" if="emplist">员工查询</a></dd>
                        <c:if test="${USER_IN_SESSION.status==0}">
                            <dd><a href="javascript:;" if="empadd">添加员工</a></dd>
                        </c:if>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="a" href="javascript:;">下载中心</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" if="doclist">文档查询</a></dd>
                        <c:if test="${USER_IN_SESSION.status==0}">
                            <dd><a href="javascript:;" if="docadd">上传文档</a></dd>
                        </c:if>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body" style="margin-left: -50px!important;">

        <iframe id="fr" src="" width="100%" height="98%" frameborder="0" marginheight="0">

        </iframe>
        <%--<div style="display: none" id="list"><jsp:include page="user/list.jsp"></jsp:include></div>--%>
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
        $(".layui-nav-child a").click(function () {
            var i = $(this).attr("if");
            $("#fr").attr("src", "/forword?tohtml=" + i);
        });

    });
</script>

</body>
</html>
