<%@ page import="com.finance.test.msg.send.util.util.GetSession" %>
<%--
  Created by IntelliJ IDEA.
  User: guanhc
  Date: 2017/6/26
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta author="guanhc133@163.com">
    <title>登录 - 知了 - Thousands Find</title>
    <link rel="stylesheet" type="text/css" href="style/register-login.css">
</head>
<body>
<div id="box"></div>
<div class="cent-box">
    <div class="cent-box-header">
        <h1 class="main-title hide">知了</h1>
        <h2 class="sub-title">生活热爱分享 - Thousands Find</h2>
    </div>
    <form action="user/login" method="post" id="myForm" style="align-items: center">
        <div class="cont-main clearfix">
            <div class="index-tab">
                <div class="index-slide-nav">
                    <a href="login.jsp" class="active">登录</a>
                    <a href="register.jsp">注册</a>
                    <div class="slide-bar"></div>
                </div>
            </div>

            <div class="login form">
                <div class="group">
                    <div class="group-ipt email">
                        <input type="text" name="userName" id="userName" class="ipt" placeholder="请输入用户名" required>
                    </div>
                    <div class="group-ipt password">
                        <input type="password" name="password" id="password" class="ipt" placeholder="输入您的登录密码"
                               required>
                    </div>
                    <div class="group-ipt verify">
                        <input type="text" class="ipt" name="captcha" id="captcha" maxlength="4" placeholder="输入验证码"
                               onblur="validateCaptcha()" required/>
                        <img src="servlet/ImageCaptchaServlet" id="imageRandom" title="看不清，请点击图片刷新"
                             onclick="changeImage()" class="imgcode"/>
                        <span style="color: red" id="cap"></span>
                    </div>
                </div>
            </div>

            <div class="button">
                <input type="button" class="login-btn register-btn" id="button" onclick="login()" value="登陆"/>
                <span style="color: red" id="log"></span>
            </div>

            <div class="remember clearfix">
                <label class="rememberMe"><span class="icon"><span class="zt"></span></span><input type="checkbox"
                                                                                                   name="rememberMe"
                                                                                                   id="rememberMe"
                                                                                                   class="remember-mecheck"
                                                                                                   checked>记住我</label>
                <label class="forgot-password">
                    <a href="#">忘记密码？</a>
                </label>
            </div>
        </div>
    </form>
</div>

<div class="footer">
    <p>知乎 - Thousands Find</p>
    <p>Designed By GuanHc & <a href="www.baidu.com">zhile.net</a> 2017</p>
</div>

<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/jquery.min.js' type="text/javascript"></script>
<script src='js/layer/layer.js' type="text/javascript"></script>
<script src='js/index.js' type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script type="text/javascript">
    function changeImage() {
        document.getElementById("imageRandom").src = document.getElementById("imageRandom").src + '?';
    }

    $("#rememberMe").click(function () {
        var n = document.getElementById("rememberMe").checked;
        if (n) {
            $(".zt").show();
        } else {
            $(".zt").hide();
        }
    });
    /**
     * 校验验证码
     */
    function validateCaptcha() {
        $.ajax({
            url: "captchaValidate/captchaValidate",
            type: 'post',
            dataType: 'json',
            data: {captcha: $("#captcha").val()},
            cache: false,
            success: function (data) {
                if (!data.success) {
                    document.getElementById("cap").innerHTML = "验证码错误，请刷新重试";
                    document.getElementById("button").disabled = true;
                }
            },
            error: function (data) {
                alert("系统繁忙，请稍后再试。。。");
            }
        })

        function login(){
            alert("sss");
            $.ajax({
                url: "user/login",
                type: 'post',
                dataType: 'json',
                data: {userName: $("#userName").val(),password:$("#password").val()},
                cache: false,
                success: function (data) {
                    if (!data.success) {
                        document.getElementById("log").innerHTML = "用户名或密码错误，请重新输入";
                        document.getElementById("button").disabled = true;
                    }
                },
                error: function (data) {
                    alert("系统繁忙，请稍后再试。。。");
                }
            })
        }
    }
</script>
</body>
</html>
