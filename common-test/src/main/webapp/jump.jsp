<%--
  Created by IntelliJ IDEA.
  User: guanhc
  Date: 2017/7/24
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p style="text-indent: 2em; margin-top: 30px;">
    系统将在 <span id="time">5</span> 秒钟后自动跳转至新网址，如果未能跳转，<a href="http://localhost:8080/test/" title="点击访问">请点击</a>。</p>
<script type="text/javascript">
    delayURL();
    function delayURL() {
        var delay = document.getElementById("time").innerHTML;
        var t = setTimeout("delayURL()", 1000);
        if (delay > 0) {
            delay--;
            document.getElementById("time").innerHTML = delay;
        } else {
            clearTimeout(t);
            window.location.href = "http://localhost:8080/test/";
        }
    }
</script>
</body>
</html>
