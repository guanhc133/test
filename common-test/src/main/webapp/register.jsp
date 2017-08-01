<%--
  Created by IntelliJ IDEA.
  User: guanhc
  Date: 2017/6/26
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>注册 - 知乎 - Thousands Find</title>
    <meta author="zrong.me 曾荣">
    <link rel="stylesheet" type="text/css" href="style/register-login.css">
</head>
<body>
<div id="box"></div>
<div class="cent-box register-box">
    <div class="cent-box-header">
        <h1 class="main-title hide">知乎</h1>
        <h2 class="sub-title">生活热爱分享 - Thousands Find</h2>
    </div>
    <form action="user/regist" method="post" id="myForm" style="align-items: center">
        <div class="cont-main clearfix">
            <div class="index-tab">
                <div class="index-slide-nav">
                    <a href="login.jsp">登录</a>
                    <a href="register.jsp" class="active">注册</a>
                    <div class="slide-bar slide-bar1"></div>
                </div>
            </div>

            <div class="login form">
                <div class="group">
                    <div class="group-ipt email">
                        <input type="email" name="email" id="email" class="ipt" placeholder="邮箱地址" required="true">
                    </div>
                    <div class="group-ipt user">
                        <input type="text" name="userName" id="userName" class="ipt" placeholder="选择一个用户名"
                               onblur="validateUser()" required="true">
                        <span style="color: #ff110e" id="inner"></span>
                    </div>
                    <div class="group-ipt password">
                        <input type="password" name="password" id="password" class="ipt" placeholder="设置登录密码" required="true">
                    </div>
                    <div class="group-ipt password1">
                        <input type="password" name="password1" id="password1" class="ipt" placeholder="重复密码"
                               onblur="validatePassword()" required>
                    </div>
                    <div class="group-ipt verify">
                        <input type="text" class="ipt" name="captcha" id="captcha" maxlength="4" placeholder="输入验证码"
                               onblur="validateCaptcha()" required="true"/>
                        <img src="servlet/ImageCaptchaServlet" id="imageRandom" title="看不清，请点击图片刷新"
                             onclick="changeImage()" class="imgcode"/>
                        <span style="color: blue" id="cap"></span>
                    </div>
                </div>
            </div>

            <div class="button">
                <button type="submit" class="login-btn register-btn" id="button">注册</button>
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
<script>
    function changeImage() {
        document.getElementById("imageRandom").src = document.getElementById("imageRandom").src + '?';
    }

    $(".login-btn").click(function () {
        var email = $("#email").val();
        var password = $("#password").val();
        var verify = $("#captcha").val();
    })

    $("#remember-me").click(function () {
        var n = document.getElementById("remember-me").checked;
        if (n) {
            $(".zt").show();
        } else {
            $(".zt").hide();
        }
    })

    function validatePassword() {
        var password = $("#password").val();
        var password1 = $("#password1").val();
        if (password != password1) {
            alert("两次密码输入不一致，请重新输入！")
        }
    }

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
                } else {
                    document.getElementById("button").disabled = false;
                }
            },
            error: function (data) {
                alert("系统繁忙，请稍后再试。。。");
            }
        })
    }


    function validateUser() {
        $.ajax({
            url: "user/queryUserInfo",
            type: 'post',
            dataType: 'json',
            data: {userName: $("#userName").val()},
            cache: false,
            success: function (data) {
                alert(data.success);
                if (data.success) {
                    document.getElementById("inner").innerHTML = "用户名已被注册";
                } else {
                    document.getElementById("inner").innerHTML = "用户名可以使用";
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        })
    }

//    function validateUserName() {
//        var userNam = $("#userName").val();
//        sendRequest("user/queryUserInfo?userName=" + userNam);
//    }
//
//    function sendRequest(url) {
//        createXMLHttpRequest();
//        XMLHttpReq.open("POST", url, true);
//        XMLHttpReq.onreadystatechange = processResponse;
//        XMLHttpReq.send(null);
//    }
//
//    var XMLHttpReq = false;
//
//    function createXMLHttpRequest() {
//        if (window.XMLHttpRequest) {
//            XMLHttpReq = new XMLHttpRequest();
//        } else if (window.ActiveXObject) {
//            try {
//                XMLHttpReq = new ActiveXObject("MSXML2.XMLHTTP");
//            } catch (e) {
//                try {
//                    XMLHttpReq = new ActiveXObject("Mircsoft.XMLHTTP");
//                } catch (e1) {
//                }
//            }
//        }
//    }
//
//    function processResponse() {
//        if (XMLHttpReq.readyState == 4) {
//            if (XMLHttpReq.status == 200) {
//                var res = XMLHttpReq.responseXML;
//                alert(res);
//                document.getElementById("inner").innerHTML = "用户名可以使用";
//            } else {
//                var res = XMLHttpReq.responseXML.getElementsByTagName("isSuccess");
//                alert(res);
//                document.getElementById("inner").innerHTML = "用户已注册";
//            }
//        }
//    }

</script>
</body>
</html>
