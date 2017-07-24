<%--
  Created by IntelliJ IDEA.
  User: guanhc
  Date: 2017/7/21
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
    %>

    <meta charset="gb2312">
    <title>个人博客模板古典系列之――江南墨卷</title>
    <meta name="keywords" content="个人博客模板古典系列之――江南墨卷" />
    <meta name="description" content="个人博客模板古典系列之――江南墨卷" />
    <link href="<%=path%>/css/base.css" rel="stylesheet">
    <link href="<%=path%>/css/main.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="<%=path%>/js/modernizr.js"></script>
    <![endif]-->
    <script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<body>
<div id="wrapper">
    <header>
        <div class="headtop"></div>
        <div class="contenttop">
            <div class="logo f_l">个人博客模板古典系列之――江南墨卷</div>
            <div class="search f_r">
                <form action="/e/search/index.php" method="post" name="searchform" id="searchform">
                    <input name="keyboard" id="keyboard" class="input_text" value="请输入关键字" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
                    <input name="show" value="title" type="hidden">
                    <input name="tempid" value="1" type="hidden">
                    <input name="tbname" value="news" type="hidden">
                    <input name="Submit" class="input_submit" value="搜索" type="submit">
                </form>
            </div>
            <div class="blank"></div>
            <nav>
                <div  class="navigation">
                    <ul class="menu">
                        <li><a href="<%=path%>/index.jsp">网站首页</a></li>
                        <li><a href="#">关于我</a>
                            <ul>
                                <li><a href="<%=path%>/about.jsp">个人简介</a></li>
                                <li><a href="<%=path%>/listpic.jsp">个人相册</a></li>
                            </ul>
                        </li>
                        <li><a href="<%=path%>/newslistpic.jsp">我的日记</a>
                            <ul>
                                <li><a href="<%=path%>/newslistpic.jsp">个人日记</a></li>
                                <li><a href="<%=path%>/newslistpic.jsp">学习笔记</a></li>
                            </ul>
                        </li>
                        <li><a href="<%=path%>/newslistpic.jsp">技术文章</a> </li>
                        <li><a href="#">给我留言</a> </li>
                    </ul>
                </div>
            </nav>
            <SCRIPT type=text/javascript>
                // Navigation Menu
                $(function() {
                    $(".menu ul").css({display: "none"}); // Opera Fix
                    $(".menu li").hover(function(){
                        $(this).find('ul:first').css({visibility: "visible",display: "none"}).slideDown("normal");
                    },function(){
                        $(this).find('ul:first').css({visibility: "hidden"});
                    });
                });
            </SCRIPT>
        </div>
    </header>
    <div class="container">
        <div class="con_content">
            <div class="about_box">
                <h2 class="nh1"><span>您现在的位置是：<a href="/" target="_blank">网站首页</a>>><a href="#" target="_blank">信息浏览</a></span><b>个人简介</b></h2>
                <div class="f_box">
                    <p class="a_title">个人简介</p>
                    <p class="p_title"></p>
                    <!--  <p class="box_p"><span>发布时间：2017-07-07 15:12:42</span><span>作者：唐孝文</span><span>来源：稽查支队</span><span>点击：111056</span></p>-->
                    <!-- 可用于内容模板 -->
                </div>
                <ul class="about_content">
                    <p> 人生就是一个得与失的过程，而我却是一个幸运者，得到的永远比失去的多。生活的压力迫使我放弃了轻松的前台接待，放弃了体面的编辑，换来虽有些蓬头垢面的工作，但是我仍然很享受那些熬得只剩下黑眼圈的日子，因为我在学习使用Photoshop、Flash、Dreamweaver、ASP、PHP、JSP...中激发了兴趣，然后越走越远....</p>
                    <p><img src="<%=path%>/images/01.jpg"></p>
                    <p>“冥冥中该来则来，无处可逃”。 </p>
                </ul>
            </div>
        </div>
        <div class="blank"></div>
    </div>
    <!-- container代码 结束 -->
    <footer>
        <div class="footer">
            <div class="f_l">
                <p>All Rights Reserved 版权所有：<a href="http://www.yangqq.com">杨青个人博客</a> 备案号：蜀ICP备00000000号</p>
            </div>
            <div class="f_r textr">
                <p>Design by DanceSmile</p>
            </div>
        </div>
    </footer>
</div>
</body>
</html>
