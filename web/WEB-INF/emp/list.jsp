<%--
  Created by IntelliJ IDEA.
  User: 万顺开
  Date: 2019/10/10
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/jquery/jquery-2.1.3.min.js"></script>
    <script src="../../static/layui/layui.js"></script>
    <style>
    </style>
<script>
    $(function () {
        /*$("#btn").click(function () {
            $.get('/user?crud=list',$("#query").serialize(),function (data) {
                    window.location.reload();
            })
        });*/
    });
</script>
</head>
<body>
<!-- 内容主体区域 -->
<form  class="layui-form" action="/user?crud=list" id="query" method="get">
    <div class="layui-form-item" style="margin-top: 30px !important;">
        <div class="layui-inline" style="margin-top: 5px!important;">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline" style="margin-right: 0!important;">
                <input type="text" name="name"  lay-verify="required"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline" style="margin-top: 5px!important;">
            <label class="layui-form-label">身份证号</label>
            <div class="layui-input-inline" style="margin-right: 0!important;">
                <input type="text" name="cardId"  lay-verify="required"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline" style="margin-top: 5px!important;">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-inline" style="margin-right: 0!important;">
                <input type="text" name="phone"  lay-verify="required"  autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
        <div class="layui-form-item">
        <div class="layui-inline" style="margin-top: 5px!important;">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block" style="width: 190px !important;" id="s1">
                <select name="sex" lay-filter="aihao">
                    <option value="-1" selected>请选择</option>
                    <option value="1" >男</option>
                    <option value="0" >女</option>
                </select>
            </div>
        </div>
        <div class="layui-inline" style="margin-top: 5px!important;">
            <label class="layui-form-label">职位</label>
            <div class="layui-input-block" style="width: 190px !important;" id="s2">
                <select name="jobId" lay-filter="aihao" >
                    <option value="-1" selected>请选择</option>
                    <c:forEach items="${jobs}" var="job">
                    <option value=${job.id}>${job.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline" style="margin-top: 5px!important;">
            <label class="layui-form-label">部门</label>
            <div class="layui-input-block" style="width: 190px !important;" id="s3">
                <select name="deptId" lay-filter="aihao" >
                    <option value="-1" selected>请选择</option>
                    <c:forEach items="${depts}" var="dept">
                        <option value=${dept.id}>${dept.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="button" class="layui-btn" id="btn" style="margin-left: 50px!important;" data-type="reload">查询</button>
    </div>
</form>
<table class="layui-hide" id="test" lay-filter="test"></table>
<%--<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    </div>
</script>--%>

<script type="text/html" id="barDemo">
    <button class="layui-btn layui-btn-xs edit" lay-event="edit" data-method="notice">编辑</button>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['table', 'layer', 'laypage'], function () {
        var table = layui.table;
        var layer = layui.layer;
        var laypage = layui.laypage;
        var hi=${USER_IN_SESSION.status==0}?false:true;
        table.render({
            elem: '#test'
            , url: '/emp?crud=list'
            , defaultToolbar: []
            , title: '用户数据表'
            ,limit:10
            , cols: [[
                {field: 'name', title: '姓名', width: 80,align:'center'}
                , {field: 'sexs', title: '性别', width: 60,align:'center'}
                , {field: 'phone', title: '手机号码',width: 120,align:'center'}
                , {field: 'email', title: '邮箱',width: 160,align:'center'}
                , {field: 'name', title: '职位',width: 100,align:'center',templet: function(d){return d.job?d.job.name:"";}}
                , {field: 'education', title: '学历',width: 70,align:'center'}
                , {field: 'cardId', title: '身份证',width: 180,align:'center'}
                , {field: 'name', title: '部门',width: 90,align:'center',templet: function(d){return d.dept?d.dept.name:"";}}
                , {field: 'address', width: 100,title: '地址',align:'center'}
                , {field: 'time', width: 120,title: '建档日期',align:'center'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 120,hide:hi,align:'center'}
            ]]
            , page: true
            ,limit:5
            ,limits:[5,10,20]
            ,id: 'testReload'
        });
        var $ = layui.$, active = {
            reload: function(){
                var name = $("#query input[name='name']");
                var cardId = $("#query input[name='cardId']");
                var phone = $("#query input[name='phone']");
                var sex=$("#query #s1 .layui-anim-upbit .layui-this");
                var jobId=$("#query #s2 .layui-anim-upbit .layui-this");
                var deptId=$("#query #s3 .layui-anim-upbit .layui-this");
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                            name: name.val(),
                            cardId: cardId.val(),
                            phone: phone.val(),
                            sex:sex.attr("lay-value"),
                            jobId:jobId.attr("lay-value"),
                            deptId:deptId.attr("lay-value")
                    }
                }, 'data');
            }
        };

        $('#btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    /*layer.alert(JSON.stringify(data));*/
                    layer.alert(JSON.stringify(data));
                    console.log(data[0].id)
                    break;
            }
            ;
        });
        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            console.log(data);
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    $.get('/user?crud=delete',{id:data.id},function (data) {
                        /*alert(data);*/
                        obj.del();
                        /*window.location.reload();*/
                        layer.close(index);
                    });
                });
            } else if (obj.event === 'edit') {
                var active = {
                    notice: function () {
                        window.location.href="/forword?tohtml=empadd&id="+data.id;
               /*         $("input[name='username']").val(data.username);
                var op = $("option");
                op.each(function () {
                    $(this).val() == data.status ? $(this).attr("selected", true) : '';
                });
                data.status==0?$("#myform .layui-select-title .layui-unselect").val('超级管理员'):$("#myform .layui-select-title .layui-unselect").val('普通员工')
                $(".layui-anim-upbit dd").each(function () {
                    if(data.status==$(this).attr("lay-value")){
                        $(this).addClass("layui-this")
                    }else {
                        $(this).removeClass("layui-this")
                    }
                });
                $("#wai").css('display', 'block');
                //示范一个公告层
                layer.open({
                    type: 1
                    ,
                    title: false //不显示标题栏
                    ,
                    closeBtn: false
                    ,
                    area: ['400px', '300px']
                    ,
                    shade: 0
                    ,
                    id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,
                    btn: ['提交', '关闭']
                    ,
                    btnAlign: 'c'
                    ,
                    moveType: 0 //拖拽模式，0或者1
                    ,
                    content: $("#rd"),
                    success: function (layero) {
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').click(function () {
                            var user=$("#myform input[name='username']").val();
                            var sta=$("#myform .layui-anim-upbit .layui-this").attr("lay-value");
                            $.post("/user",{id:data.id,username:user,status:sta},function (data) {
                                alert(data)
                                window.location.reload();
                            });
                            /!*$("#hd").val(data.id);
                            $("#myform").submit();*!/

                            $("#wai").css('display', 'none');
                        });
                        btn.find('.layui-layer-btn1').click(function () {
                            $("#wai").css('display', 'none');
                        });
                    }
                });*/
            }
                }
                active['notice'].call(document.getElementsByClassName("edit")[0], $(".edit"))
            }
        });
    });

</script>
<div style="display: none" id="wai">
    <div id="rd" style="padding: 30px; line-height: 22px; background-color: white ; font-weight: 300; ">
        <form class="layui-form layui-form-pane" action="/user" method="post" id="myform">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" required lay-verify="required"
                           placeholder="请输入" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block" style="width: 190px !important;">
                    <select name="status" lay-filter="aihao">
                        <option value="0">超级管理员</option>
                        <option value="1">普通用户</option>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
</script>
</body>

</html>
