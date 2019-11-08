<%--
  Created by IntelliJ IDEA.
  User: 万顺开
  Date: 2019/10/10
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/jquery/jquery-2.1.3.min.js"></script>
    <script src="../../static/layui/layui.js"></script>
    <script src="../../static/jquery-validation/jquery.validate.js"></script>
    <script>
        $(function () {
            layui.use(['form','layer'], function() {
                var form = layui.form;
                var layer = layui.layer;
                $().ready(function() {
                    // validate the comment form when it is submitted

                    // validate signup form on keyup and submit
                    $("#myform").validate({
                        errorElement:'div',
                        errorPlacement: function(error, element) {
                            error.appendTo(element.parent('div').next('div'));
                        },
                        submitHandler: function(form) {
                                $.post('/user',$("#myform").serialize(),function (data) {
                                    layer.confirm(data,function () {
                                        window.location.reload();
                                    })
                            });
                        },
                        rules: {
                            username: {
                                required: true,
                                minlength: 2,

                            },
                            password: {
                                required: true,
                                minlength: 5,
                                maxlength:16
                            },
                            loginname: {
                                required: true,
                                minlength: 2,
                                maxlength:16
                            },
                        },
                        messages: {
                            username: {
                                required: "请填写",
                                minlength: "最少两个字符",
                            },
                            password: {
                                required: "请填写",
                                minlength: "最少5个字符",
                                maxlength: "最多16个字符"
                            },
                            loginname: {
                                required: "请填写",
                                minlength: "最少两个字符",
                                maxlength: "最多16个字符"
                            },
                        }
                    });
                });

            });


        })
    </script>
</head>
<body>
<div style="padding-left:100px;padding-top: 50px;" >
    <p style="font-size: 20px;font-family: 隶书;margin-left: 350px">添加用户</p><br/><br/>
    <form  class="layui-form layui-form-pane" action="/user" id="myform" method="post">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" style="width: 100px;color: red !important;"></div>
            </div>
            <div class="layui-inline" style="margin-left: 50px !important;">
                <label class="layui-form-label">登陆名</label>
                <div class="layui-input-inline">
                    <input type="text" name="loginname" required lay-verify="required" placeholder="请输入登陆名" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" style="color: red !important;"></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" style="width: 100px;color: red !important;"></div>
            </div>
            <div class="layui-inline" style="margin-left: 50px !important;">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block" style="width: 190px !important;">
                    <select name="status" lay-filter="aihao">
                        <option value="0">超级管理员</option>
                        <option value="1">普通用户</option>
                    </select>
                </div>
            </div>
        </div>
        <br/><br/>
        <div style="margin-left: 325px">
            <button type="submit" class="layui-btn" id="btn">提交</button>
            <button type="reset" class="layui-btn layui-btn-normal">重置</button>
        </div>
    </form>
</div>
<script>
  /*  layui.use(['form','layer'], function() {
        var form = layui.form;
        var layer = layui.layer;

        $("#myform").on('submit',function () {
            alert(123)
            $.post('/user',$(this).serialize(),function (data) {
                alert(data);
                window.location.reload();

            });

        });
    });
*/
</script>
</body>
</html>
