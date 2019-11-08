<%--
  Created by IntelliJ IDEA.
  User: 万顺开
  Date: 2019/10/12
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/jquery/jquery-2.1.3.min.js"></script>
    <script src="../../static/layui/layui.js"></script>
    <script src="../../static/jquery-validation/jquery.validate.js"></script>
    <script>
        $(function () {
            layui.use('upload', function () {
                var upload = layui.upload;
                var uploadInst = upload.render({
                    elem: '#test3' //绑定元素
                    , url: '/doc' ,//上传接口
                    accept: 'file',
                    exts: 'doc|docx',
                    auto: false,
                    bindAction: "#btn1",
                    /*done:function(data){
                        alert(data.msg);
                    },*/
                    error:function () {

                    }
                });

                $("#myform").validate({
                    errorElement:'div',
                    errorPlacement: function(error, element) {
                        error.appendTo(element.parent('div').next('div'));
                    },
                    submitHandler: function(form) {
                        var title = $('#myform input[name="title"]').val();
                        var remark = $('#myform textarea[name="remark"]').val();
                        var file = $('#myform input[name="file"]')[0].files[0];

                        var formData = new FormData();
                        formData.append("files", file);
                        formData.append("title", title);
                        formData.append("remark", remark);
                        $.ajax({
                            url: '/doc',
                            dataType: 'json',
                            type: 'POST',
                            async: false,
                            data: formData,
                            processData: false, // 使数据不做处理
                            contentType: false, // 不要设置Content-Type请求头
                            success: function (data) {
                                alert(data.msg)
                                if(data.msg=="上传成功"){
                                    window.location.href='forword?tohtml=docadd';
                                }

                            }
                        });
                    },
                    rules: {
                        title: {
                            required: true,
                        }
                    },
                    messages: {
                        title: {
                            required: "请填写"
                        }
                    }
                });
            });
        })
    </script>
</head>
<body>
<div style="margin: 0 auto;width: 500px">
<form  class="layui-form " action="/doc" id="myform" method="post" enctype="multipart/form-data">
    <p style="margin-left: 200px;margin-top: 20px;font-size: 20px;font-family: 隶书">上传文件</p>
    <div class="layui-form-item" style="margin-top: 15px">
        <div class="layui-inline" >
            <label class="layui-form-label">文档标题</label>
            <div class="layui-input-inline" >
                <input type="text" name="title"  required  autocomplete="off" class="layui-input" >
            </div>
            <div class="layui-form-mid layui-word-aux" ></div>
        </div>
        <div class="layui-form-item layui-form-text" style="">
            <label class="layui-form-label" style="">详细信息</label>
            <div class="layui-input-block" style="width: 400px !important;">
                <textarea name="remark"  class="layui-textarea" style="height: 300px!important;"></textarea>
            </div>
        </div>
    </div>
    <div style="margin-left: 110px">
        <button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>上传文件</button>
        &nbsp;&nbsp;<span>word文档</span>
    </div>
    <div style="margin-left: 300px;margin-top: 50px">
        <input type="submit" class="layui-btn layui-btn-normal" id="btn" value="上传"/>
        <button  class="layui-btn layui-btn-normal" id="btn1" style="display: none">上传</button>
    </div>
</form>
</div>
</body>
</html>
