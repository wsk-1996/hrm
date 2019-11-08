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
                                $.post('/job',$("#myform").serialize(),function (data) {
                                    layer.confirm(data,function () {
                                        window.location.reload();
                                    })
                            });
                        },
                        rules: {
                            name: {
                                required: true,
                                minlength: 2,

                            },


                        },
                        messages: {
                            name: {
                                required: "请填写",
                                minlength: "最少两个字符",
                            }
                        }
                    });
                });

            });


        })
    </script>
</head>
<body>
<div style="padding-left:100px;padding-top: 50px;" >
    <div style="width: 320px;margin: 0 auto;text-align: center">
    <p style="font-size: 20px;font-family: 隶书;">添加职位</p><br/><br/>
    <form  class="layui-form layui-form-pane" action="/job" id="myform" >
        <div class="layui-form-item" >
            <div class="layui-inline">
                <label class="layui-form-label">职位名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" required lay-verify="required"  autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" style="width: 100px;height: 15px;color: red !important;float: right!important;"></div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text" style="margin: 0 auto!important;">
            <label class="layui-form-label" style="width: 300px !important;">详细信息</label>
            <div class="layui-input-block" style="width: 300px !important;">
                <textarea name="remark"  class="layui-textarea"></textarea>
            </div>
        </div>
        <br/>
        <div>
            <button type="submit" class="layui-btn" id="btn">提交</button>
            <button type="reset" class="layui-btn layui-btn-normal">重置</button>
        </div>
    </form>
    </div>
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
