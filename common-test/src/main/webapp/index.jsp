<%--
  Created by IntelliJ IDEA.
  User: guanhc
  Date: 2017/7/17
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta charset="gb2312">
    <title>官红诚的个人博客――江南墨卷</title>
    <meta name="keywords" content="个人博客模板古典系列之――江南墨卷"/>
    <meta name="description" content="个人博客模板古典系列之――江南墨卷"/>
    <link href="<%=path %>/css/base.css" rel="stylesheet">
    <link href="<%=path %>/css/index.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="<%=path %>/js/modernizr.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${basePath}/js/jquery.js"></script>
</head>
<body>
<div class="topnav">
    <a href="http://www.yangqq.com/download/div/2017-07-16/785.html" target="_blank">官红诚的个人博客――江南墨卷</a>――作品来自<a
        href="http://www.yangqq.com" target="_blank">官红诚个人博客网站</a>
</div>
<div id="wrapper">
    <header>
        <div class="headtop"></div>
        <div class="contenttop">
            <div class="logo f_l">官红诚的个人博客――江南墨卷</div>
            <div class="search f_r">
                <form action="/e/search/index.php" method="post" name="searchform" id="searchform">
                    <input name="keyboard" id="keyboard" class="input_text" value="请输入关键字"
                           style="color: rgb(153, 153, 153);"
                           onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}"
                           onblur="if(value==''){this.style.color='#999';value='请输入关键字'}" type="text">
                    <input name="show" value="title" type="hidden">
                    <input name="tempid" value="1" type="hidden">
                    <input name="tbname" value="news" type="hidden">
                    <input name="Submit" class="input_submit" value="搜索" type="submit">
                </form>
            </div>
            <div class="blank"></div>
            <nav>
                <div class="navigation">
                    <ul class="menu">
                        <li><a href="index.html">网站首页</a></li>
                        <li><a href="#">关于我</a>
                            <ul>
                                <li><a href="about.html">个人简介</a></li>
                                <li><a href="listpic.html">个人相册</a></li>
                            </ul>
                        </li>
                        <li><a href="#">我的日记</a>
                            <ul>
                                <li><a href="newslistpic.html">个人日记</a></li>
                                <li><a href="newslistpic.html">学习笔记</a></li>
                            </ul>
                        </li>
                        <li><a href="newslistpic.html">技术文章</a></li>
                        <li><a href="#">给我留言</a></li>
                    </ul>
                </div>
            </nav>
            <SCRIPT type=text/javascript>
                // Navigation Menu
                $(function () {
                    $(".menu ul").css({display: "none"}); // Opera Fix
                    $(".menu li").hover(function () {
                        $(this).find('ul:first').css({visibility: "visible", display: "none"}).slideDown("normal");
                    }, function () {
                        $(this).find('ul:first').css({visibility: "hidden"});
                    });
                });
            </SCRIPT>
        </div>
    </header>
    <div class="jztop"></div>
    <div class="container">
        <div class="bloglist f_l">
            <h3><a href="/jstt/bj/2017-07-13/784.html">【心路历程】请不要在设计这条路上徘徊啦</a></h3>
            <figure><img src="images/img_1.jpg" alt="【心路历程】请不要在设计这条路上徘徊啦"></figure>
            <ul>
                <p> 我整理了一下网友给我的来信，如果你还在踌躇不前，不妨来看看，到底要不要坚持下去！我也欢迎大家给我来信，希望能帮到更多人。</p>
                <a title="【心路历程】请不要在设计这条路上徘徊啦" href="/jstt/bj/2017-07-13/784.html" target="_blank" class="readmore">阅读全文&gt;&gt;</a>
            </ul>
            <p class="dateview"><span>2017-07-13</span><span>作者：</span><span>个人博客：[<a href="/jstt/bj/">心得笔记</a>]</span>
            </p>
            <h3><a href="/jstt/bj/2015-01-09/740.html">【匆匆那些年】总结个人博客经历的这四年…</a></h3>
            <figure><img src="images/img_2.jpg" alt="【匆匆那些年】总结个人博客经历的这四年…"></figure>
            <ul>
                <p>博客从最初的域名购买，到上线已经有四年的时间了，这四年的时间，有笑过，有怨过，有悔过，有执着过，也有放弃过…但最后还是坚持了下来，时间如此匆匆，等再回过头已来不及去弥补</p>
                <a title="【匆匆那些年】总结个人博客经历的这四年…" href="/jstt/bj/2015-01-09/740.html" target="_blank" class="readmore">阅读全文&gt;&gt;</a>
            </ul>
            <p class="dateview"><span>2015-01-09</span><span>作者：杨青</span><span>个人博客：[<a
                    href="/jstt/bj/">心得笔记</a>]</span></p>
            <h3><a href="/jstt/bj/2014-11-06/732.html">分享我的个人博客访问量如何做到IP从10到600的(图文)</a></h3>
            <figure><img src="images/img_3.jpg" alt="分享我的个人博客访问量如何做到IP从10到600的(图文)"></figure>
            <ul>
                <p>
                    我的个人博客总共展示了三个版本，界面也经历了由“简单”到“复杂”再到“简单”，颜色从“色泽单一”到“五彩斑斓”再到“局部点缀”的过程。原来一年一个版本！而每次改版的契机都是被百度惩罚！界面不要频繁更换！好好检查代码，有没有冗余、结构有没有不合理的地方。</p>
                <a title="分享我的个人博客访问量如何做到IP从10到600的(图文)" href="/jstt/bj/2014-11-06/732.html" target="_blank"
                   class="readmore">阅读全文&gt;&gt;</a>
            </ul>
            <p class="dateview"><span>2014-11-06</span><span>作者：杨青</span><span>个人博客：[<a
                    href="/jstt/bj/">心得笔记</a>]</span></p>
            <h3><a href="/jstt/bj/2014-10-18/731.html">帝国cms常用标签调用方法总结（不得不收藏哦）</a></h3>
            <figure><img src="images/img_4.jpg" alt="帝国cms常用标签调用方法总结（不得不收藏哦）"></figure>
            <ul>
                <p>整理了一些常用的帝国cms调用，灵动标签和万能标签的调用方法举例。幻灯片、标题、一级栏目、二级栏目、带模版的友情链接（下拉菜单）、判断内容页字段为空时是如何调用的等等...新手可以借鉴学习。</p>
                <a title="帝国cms常用标签调用方法总结（不得不收藏哦）" href="/jstt/bj/2014-10-18/731.html" target="_blank" class="readmore">阅读全文&gt;&gt;</a>
            </ul>
            <p class="dateview"><span>2015-01-09</span><span>作者：杨青</span><span>个人博客：[<a
                    href="/jstt/bj/">心得笔记</a>]</span></p>
            <h3><a href="/jstt/bj/2017-07-13/784.html">【心路历程】请不要在设计这条路上徘徊啦</a></h3>
            <figure><img src="images/img_1.jpg" alt="【心路历程】请不要在设计这条路上徘徊啦"></figure>
            <ul>
                <p> 我整理了一下网友给我的来信，如果你还在踌躇不前，不妨来看看，到底要不要坚持下去！我也欢迎大家给我来信，希望能帮到更多人。</p>
                <a title="【心路历程】请不要在设计这条路上徘徊啦" href="/jstt/bj/2017-07-13/784.html" target="_blank" class="readmore">阅读全文&gt;&gt;</a>
            </ul>
            <p class="dateview"><span>2017-07-13</span><span>作者：</span><span>个人博客：[<a href="/jstt/bj/">心得笔记</a>]</span>
            </p>
            <h3><a href="/jstt/bj/2015-01-09/740.html">【匆匆那些年】总结个人博客经历的这四年…</a></h3>
            <figure><img src="images/img_2.jpg" alt="【匆匆那些年】总结个人博客经历的这四年…"></figure>
            <ul>
                <p>博客从最初的域名购买，到上线已经有四年的时间了，这四年的时间，有笑过，有怨过，有悔过，有执着过，也有放弃过…但最后还是坚持了下来，时间如此匆匆，等再回过头已来不及去弥补</p>
                <a title="【匆匆那些年】总结个人博客经历的这四年…" href="/jstt/bj/2015-01-09/740.html" target="_blank" class="readmore">阅读全文&gt;&gt;</a>
            </ul>
            <p class="dateview"><span>2015-01-09</span><span>作者：杨青</span><span>个人博客：[<a
                    href="/jstt/bj/">心得笔记</a>]</span></p>
        </div>
        <div class="r_box f_r">
            <div class="tit01">
                <h3 class="tit">关注我</h3>
                <div class="gzwm">
                    <ul>
                        <li><a class="email" href="#" target="_blank">我的电话</a></li>
                        <li><a class="qq" href="#mailto:admin@admin.com" target="_blank">我的邮箱</a></li>
                        <li><a class="tel" href="#" target="_blank">我的QQ</a></li>
                        <li><a class="prize" href="#">个人奖项</a></li>
                    </ul>
                </div>
            </div>
            <!--tit01 end-->

            <div class="tuwen">
                <h3 class="tit">图文推荐</h3>
                <ul>
                    <li><a href="/"><img src="images/01.jpg"><b>住在手机里的朋友</b></a>
                        <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
                    </li>
                    <li><a href="/"><img src="images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
                        <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
                    </li>
                    <li><a href="/" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src="images/03.jpg"><b>手机的16个惊人小秘密，据说...</b></a>
                        <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
                    </li>
                    <li><a href="/"><img src="images/06.jpg"><b>住在手机里的朋友</b></a>
                        <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
                    </li>
                    <li><a href="/"><img src="images/04.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
                        <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
                    </li>
                    <li><a href="/"><img src="images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
                        <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
                    </li>
                    <li><a href="/" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src="images/03.jpg"><b>手机的16个惊人小秘密，据说...</b></a>
                        <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
                    </li>
                </ul>
            </div>
            <div class="ph">
                <h3 class="tit">点击排行</h3>
                <ul class="rank">
                    <li><a href="/jstt/bj/2017-07-13/784.html" title="【心路历程】请不要在设计这条路上徘徊啦" target="_blank">【心路历程】请不要在设计这条路上徘徊啦</a>
                    </li>
                    <li><a href="/news/s/2016-05-20/751.html" title="IP要突破2000+了" target="_blank">IP要突破2000+了</a></li>
                    <li><a href="/jstt/web/2015-07-03/749.html" title="帝国cms首页、自定义页面如何实现分页" target="_blank">帝国cms首页、自定义页面如何实现分页</a>
                    </li>
                    <li><a href="/jstt/web/2015-02-25/745.html" title="【已评选】给我模板PSD源文件，我给你设计HTML！" target="_blank">【已评选】给我模板PSD源文件，我给你设计HTML！</a>
                    </li>
                    <li><a href="/jstt/bj/2015-02-14/744.html" title="【郑重申明】本站只提供静态模板下载！" target="_blank">【郑重申明】本站只提供静态模板下载！</a>
                    </li>
                    <li><a href="/news/s/2015-01-23/741.html" title="【孕期日记】生活本该如此" target="_blank">【孕期日记】生活本该如此</a></li>
                    <li><a href="/jstt/bj/2015-01-09/740.html" title="【匆匆那些年】总结个人博客经历的这四年…" target="_blank">【匆匆那些年】总结个人博客经历的这四年…</a>
                    </li>
                    <li><a href="/jstt/web/2015-01-01/739.html" title=" 2014年度优秀个人博客排名公布" target="_blank">
                        2014年度优秀个人博客排名公布</a></li>
                    <li><a href="/jstt/web/2014-12-18/736.html" title="2014年度优秀个人博客评选活动" target="_blank">2014年度优秀个人博客评选活动</a>
                    </li>
                    <li><a href="/jstt/css3/2014-12-09/734.html" title="使用CSS3制作文字、图片倒影"
                           target="_blank">使用CSS3制作文字、图片倒影</a></li>
                    <li><a href="/jstt/css3/2014-11-18/733.html" title="【分享】css3侧边栏导航不同方向划出效果" target="_blank">【分享】css3侧边栏导航不同方向划出效果</a>
                    </li>
                    <li><a href="/jstt/bj/2014-11-06/732.html" title="分享我的个人博客访问量如何做到IP从10到600的(图文)" target="_blank">分享我的个人博客访问量如何做到IP从10到600的(图文)</a>
                    </li>
                </ul>
            </div>
            <div class="ad"><img src="images/03.jpg"></div>
        </div>
    </div>
    <!-- container代码 结束 -->
    <div class="jzend"></div>
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
