<%--
  Created by IntelliJ IDEA.
  User: 万顺开
  Date: 2019/10/10
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form  class="layui-form" action="/job?crud=list" id="query" method="get">
    <div class="layui-form-item" style="margin-top: 30px !important;">
        <div class="layui-inline" style="margin-top: 5px!important;">
            <label class="layui-form-label">职位名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name"  lay-verify="required"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <button type="button" class="layui-btn" id="btn" style="margin-left: 150px!important;" data-type="reload">查询</button>
    </div>
</form>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    </div>
</script>

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
            , url: '/job?crud=list'
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: []
            , title: '用户数据表'
            ,limit:10
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'name', title: '职位名称', width: 200,align:'center'}
                , {field: 'remark', title: '详细信息',align:'center'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150,hide:hi,align:'center'}
            ]]
            , page: true
            ,limit:5
            ,limits:[5,10,20]
            ,id: 'testReload'
        });
        var $ = layui.$, active = {
            reload: function(){
                var name = $("input[name='name']");
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                            name: name.val(),
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
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    $.get('/job?crud=delete',{id:data.id},function (data) {
                        /*alert(data);*/
                        obj.del();
                        /*window.location.reload();*/
                        layer.close(index);
                    });
                });
            } else if (obj.event === 'edit') {
                var active = {
                    notice: function () {
                        console.log(data)
                        $("#myform input[name='name']").val(data.name);
                        $("#myform textarea[name='remark']").html(data.remark);
                        /*var op = $("option");
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
                        });*/
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
                                    var user=$("#myform input[name='name']").val();
                                    var sta=$("#myform textarea[name='remark']").val();
                                    $.post("/job",{id:data.id,name:user,remark:sta},function (data) {
                                        alert(data)
                                        window.location.reload();
                                    });
                                    /*$("#hd").val(data.id);
                                    $("#myform").submit();*/

                                    $("#wai").css('display', 'none');
                                });
                                btn.find('.layui-layer-btn1').click(function () {
                                    $("#wai").css('display', 'none');
                                });
                            }
                        });
                    }
                }
                active['notice'].call(document.getElementsByClassName("edit")[0], $(".edit"))
            }
        });
    });

</script>
<div style="display: none" id="wai">
    <div id="rd" style="padding: 30px; line-height: 22px; background-color: white ; font-weight: 300; ">
        <form class="layui-form layui-form-pane" action="/job" method="post" id="myform">
            <div class="layui-form-item">
                <label class="layui-form-label">职位名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" required lay-verify="required"
                           placeholder="请输入" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label" style="width: 300px !important;">详细信息</label>
                <div class="layui-input-block" style="width: 300px !important;">
                    <textarea name="remark"  class="layui-textarea"></textarea>
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
