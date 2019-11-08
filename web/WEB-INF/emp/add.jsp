<%--
  Created by IntelliJ IDEA.
  User: 万顺开
  Date: 2019/10/10
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加用户</title>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/jquery/jquery-2.1.3.min.js"></script>
    <script src="../../static/layui/layui.js"></script>
    <script src="../../static/jquery-validation/jquery.validate.js"></script>
    <style>
        .layui-form-item,.layui-form-item .layui-inline,.layui-form-item .layui-inline .layui-input-inline,.layui-form-item .layui-inline .layui-input-inline input{
            height: 30px!important;
        }
        .layui-form-select input{
            height: 30px!important;
        }
        .layui-form-item label{
            height: 20px!important;
            padding-top: 5px;
        }
        .layui-form-select .layui-anim-upbit{
            top:30px
        }
        #myform .layui-word-aux{
            width: 90px;
            height: 15px;
            color: red !important;
        }
    </style>
    <script>
        $(function () {
            layui.use(['form', 'layedit', 'laydate'], function(){
                var form = layui.form;
                var layer = layui.layer;
                var laydate = layui.laydate;
                laydate.render({
                    elem: '#date1',
                    max:0,
                    format: 'yyyy-MM-dd',
                    value:${emp.time==null}?new Date():'${emp.time}'
                });
                $().ready(function() {
                    // validate the comment form when it is submitted

                    // validate signup form on keyup and submit
                    $("#myform").validate({
                        errorElement:'div',
                        errorPlacement: function(error, element) {
                            error.appendTo(element.parent('div').next('div'));
                        },
                        submitHandler: function(form) {
                            $.post('/emp',$("#myform").serialize(),function (data) {
                                layer.confirm(data,function () {
                                    window.location.reload();
                                })
                            });
                        },
                        rules: {
                            name: {
                                required: true,
                                minlength: 2,
                                maxlength:10
                            },
                            cardId:{
                                required: true,
                                minlength:13,
                                maxlength:13
                            },
                            email:{
                                required: true,
                                email:true
                            },
                            qqNum:{
                                required:true,
                                digits:true,
                                min:0,
                                minlength:5
                            },
                            phone:{
                                required:true,
                                digits:true,
                                minlength:11,
                                maxlength:11,
                                min:0
                            },
                            tel:{
                                digits:true,
                                minlength:7,
                                min:0
                            },
                            race:{
                                required:true
                            },
                            address:{
                                required:true
                            },
                            speciality:{
                                required:true
                            }
                        },
                        messages: {
                            name: {
                                required: "请填写",
                                minlength: "最少两个字符",
                                maxlength: "最多两个字符"
                            },
                            cardId: {
                                required: "请填写",
                                minlength: "请输入13位身份证号",
                                maxlength: "请输入13位身份证号"
                            },
                            email:{
                                required: "请填写",
                                email:"请输入正确的邮箱格式"
                            },
                            qqNum:{
                                required: "请填写",
                                digits:"请输入正确的QQ号码",
                                min:"请输入正确的QQ号码",
                                minlength:"请输入正确的QQ号码"
                            },
                            phone:{
                                required: "请填写",
                                digits:"请输入正确的手机号码",
                                minlength:"请输入正确的手机号码",
                                maxlength:"请输入正确的手机号码",
                                min:"请输入正确的手机号码"
                            },
                            tel:{
                                digits:"请输入正确的电话号码",
                                minlength:"请输入正确的电话号码",
                                min:"请输入正确的电话号码"
                            },
                            race:{
                                required:"请填写"
                            },
                            address:{
                                required:"请填写"
                            },
                            speciality:{
                                required:"请填写"
                            }
                        }
                    });
                });

            });


        })
    </script>
</head>
<body>
<div style="padding-left:100px;padding-top: 5px;" >
    <div style="width:1000px;margin: 0 auto;">
        <p style="font-size: 20px;font-family: 隶书;margin-left: 355px">${emp!=null?'修改':'添加'}员工</p><br/><br/>
        <form  class="layui-form " action="/emp" id="myform" >
            <input type="hidden" name="id" value="${emp.id}"/>
            <div class="layui-form-item">
                <div class="layui-inline" >
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline" >
                        <input type="text" name="name"  lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.name}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
                <div class="layui-inline" style="">
                    <label class="layui-form-label">身份证号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="cardId"  lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.cardId}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline" style="margin-left: 50px!important;">
                    <input type="radio" name="sex" value="1" title="男" ${emp.sex==1||emp.sex==null?'checked':''}>
                    <input type="radio" name="sex" value="0" title="女" ${emp.sex==0?'checked':''}>
                </div>
                <div class="layui-inline" style="margin-left: 64px">
                    <label class="layui-form-label">学历</label>
                    <div class="layui-input-inline" style="width: 190px !important;" >
                        <select name="education" lay-filter="aihao" id="se">
                                <option value="小学以下">小学以下</option>
                                <option value="小学">小学</option>
                                <option value="初中">初中</option>
                                <option value="高中">高中</option>
                                <option value="大专">大专</option>
                                <option value="本科">本科</option>
                                <option value="硕士">硕士</option>
                                <option value="博士">博士</option>
                                <option value="博士以上">博士以上</option>
                        </select>
                    </div>
                </div>
                <script>
                    var data='${emp.education}';
                    $("#se option").each(function () {
                        console.log(data+"111111")
                        if(data!=null&&$(this).val()==data){
                            $(this).attr("selected",true);
                        }
                    });
                </script>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline" >
                    <label class="layui-form-label">职位</label>
                    <div class="layui-input-inline" style="width: 190px !important;" >
                        <select name="jobId" lay-filter="aihao" >
                            <c:forEach items="${jobs}" var="job">
                                <option value="${job.id}" ${job.id==emp.job.id?'selected':''}>${job.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-inline" style="margin-left: 100px">
                    <label class="layui-form-label">部门</label>
                    <div class="layui-input-inline" style="width: 190px !important;">
                        <select name="deptId" lay-filter="aihao" >
                            <c:forEach items="${depts}" var="dept">
                                <option value="${dept.id}" ${dept.id==emp.dept.id?'selected':''}>${dept.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" lay-verify="required" autocomplete="off" class="layui-input" value="${emp.email}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
                <div class="layui-inline" >
                    <label class="layui-form-label">QQ号码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="qqNum" lay-verify="required" autocomplete="off" class="layui-input" value="${emp.qqNum}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">手机</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone"  autocomplete="off" class="layui-input" value="${emp.phone}">
                    </div>
                    <div class="layui-form-mid layui-word-aux"></div>
                </div>
                <div class="layui-inline" >
                    <label class="layui-form-label">电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tel" lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.tel}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">民族</label>
                    <div class="layui-input-inline">
                        <input type="text" name="race"  lay-verify="required" autocomplete="off" class="layui-input" value="${emp.race}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">政治面貌</label>
                    <div class="layui-input-inline">
                        <input type="text" name="party" lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.party}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">联系地址</label>
                    <div class="layui-input-inline">
                        <input type="text" name="address" lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.address}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">邮政编码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="postCode"  lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.postCode}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">出生日期</label>
                    <div class="layui-input-inline">
                        <input type="text" name="birth" id="date1" autocomplete="off" class="layui-input" >
                    </div>
                </div>
                <div class="layui-inline" style="margin-left: 100px">
                    <label class="layui-form-label">所学专业</label>
                    <div class="layui-input-inline">
                        <input type="text" name="speciality"  lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.speciality}">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ></div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">爱好</label>
                    <div class="layui-input-inline">
                        <input type="text" name="hobby"  lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.hobby}">
                    </div>
                    <div class="layui-form-mid layui-word-aux"></div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-inline">
                        <input type="text" name="remark"  lay-verify="required"  autocomplete="off" class="layui-input" value="${emp.remark}">
                    </div>
                    <div class="layui-form-mid layui-word-aux"></div>
                </div>
            </div>
            <br/>
            <div  style="margin-left: 330px">
                <button type="submit" class="layui-btn" id="btn">提交</button>
                <button type="reset" class="layui-btn layui-btn-normal">重置</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
