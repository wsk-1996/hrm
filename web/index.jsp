<%--
  Created by IntelliJ IDEA.
  User: 万顺开
  Date: 2019/10/8
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登陆</title>
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <script src="static/jquery/jquery-2.1.3.min.js"></script>
    <script src="static/layui/layui.all.js"></script>
    <script src="static/jquery-validation/jquery.validate.js"></script>
    <style>
      *{
        padding: 0;
        margin: 0;
      }
      body{
        /* background-color: #1E9FFF;*/
        background: url("static/imgs/简约.jpg") no-repeat ;
        background-size: cover;
      }
      #maind{
        height: 400px;
        width: 600px;
        margin-left: 450px;
        margin-top: 150px;
      }
      #font{
        font-size: 50px;
        color: white;
        padding-left: 50px;
      }
      .layui-input-inline{
        width: 266px !important;
      }
      /* .layui-form-item{
           overflow: visible !important;
       }*/
      .layui-form-label{
        color: white;
        font-size: 20px;
      }
      .layui-btn-primary{
        background-color: #39bb26;
        margin-left: 140px;
      }
      #rand{
        width: 166px !important;
      }
    </style>
    <script>
      $(function () {
        $("#img").click(function () {
          $(this).attr("src","/randomCode?time="+new Date());
        });

      });
    </script>
  </head>
  <body>
  <div id="maind">
    <p id="font">人事管理系统</p><br/><br/>
    <p style="width: 400px;height: 20px;text-align: center;color: red">${msg}</p>
    <form class="layui-form" action="/login" method="post">
      <div class="layui-form-item">
        <label class="layui-form-label">登&nbsp;陆&nbsp;名</label>
        <div class="layui-input-inline">
          <input type="text" name="loginname" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
        <!--<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>-->
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
        <div class="layui-input-inline">
          <input type="password" name="password" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">验&nbsp;证&nbsp;码</label>
        <div class="layui-input-inline" id="rand">
          <input type="text"  name="randomCode" required lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
        <img src="/randomCode" id="img">
      </div>
      <br/><br/>
      <button type="submit" class="layui-btn layui-btn-primary">登陆</button>
      <button type="reset" class="layui-btn layui-btn-normal">重置</button>
    </form>
  </div>
  </body>
</html>
