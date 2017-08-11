<%--
  Created by IntelliJ IDEA.
  User: guanhc
  Date: 2017/8/7
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="button" accept="application/vnd.ms-excel" onclick="exportExcel()"/>
</body>

<script type="text/javascript">
    function exportExcel(){
        location.href="exporyExcel?id="+1;
    }
</script>
</html>
