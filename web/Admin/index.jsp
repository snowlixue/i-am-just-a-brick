<%@page contentType="text/html;charset=GBK" %>
<%@ page session="true" %>
<%
    if (session.getAttribute("adminUsername") == null || session.getAttribute("adminUsername") == "") {
        response.sendRedirect("/SuperMarket/login.jsp");
    }%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
    <HEAD>
        <title>超市信息管理系统</title>
        <LINK href="../css/style.css" type="text/css" rel="stylesheet">
        <script src="../script/App.js"></script>
        <style>
            #outer { position:relative; background: no-repeat 0px 160px;}
            #outer #content {clear:left; position:absolute;  z-index:10;}
            #menu {list-style-type:none; padding:0; margin:0; width:125px; position:absolute; left:0; border:1px solid #fff; border-width:0 1px 1px; z-index:100;}
            #menu ul {list-style-type:none; padding:0; margin:0; width:125px;}
            #menu li {float:left; background:#657; position:relative; border-top:1px solid #fff;}
            #menu li.sub {background:#d30;}
            #menu li, #menu li a {display:block; color:#fff; font-family:arial, sans-serif; font-size:11px; line-height:24px; width:125px; text-decoration:none;  cursor:pointer; font-weight:bold; text-indent:5px;}
            #menu ul,
            #menu li.click ul ul,
            #menu li.click ul li.hover ul ul {display:none;}
            #menu li.hover {color:#ff0; z-index:500;}
            #menu li.click {color:#ff0;}
            #menu li.click ul {display:block;}
            #menu li.click ul li.hover ul,
            #menu li.click ul li.hover ul li.hover ul {display:block; position:absolute; left:110px; top:-1px; border:1px solid #fff; border-width:0 1px 1px;}
            #menu li.click ul li.fly {background: #657 url(/jscode/demoimg/200907/frog_arrow.gif) no-repeat top right;}
            #menu li.click ul li.hover {background:#98a;}
            #menu li.click ul li.hover ul li {background:#c60;}
            #menu li.click ul li.hover ul li.hover ul li {background:#780; z-index:500;}
            #menu li.click ul li.hover ul li.fly {background: #c60 url(/jscode/demoimg/200907/frog_arrow.gif) no-repeat top right;}
            #menu li.click ul li.hover ul li.hover {z-index:500; background:#fa4;}
            #menu li.click ul li.hover ul li.hover a {color:#000;}
            #menu li.click ul li.hover ul li.hover ul li.hover {background:#aa0;}
            #menu li.click ul li.hover ul li.hover ul li.hover a {color:#fff;}
            #outer img {display:block; float:right;}
            #outer p {margin:0px; padding:17px 0 0 0; color:#000; font-size:12px; font-family:arial, sans-serif; text-align:justify;}
        </style>
    </HEAD>
    <body onload="clickMenu('menu')">
        <form id="Form1" method="post" runat="server">
            <table align="center"  class="tbl" cellSpacing="0" cellPadding="0" width="1024" border="0" ID="Table5">
                <tr>
                    <td colspan="2" valign="center" style="background:#2fb2f6;color:white;font-size: 35;  width: 200px;">
                        <CENTER>Too Young Too Simple SuperMarket</CENTER>
                    </td>
                </tr>
                <tr>
                    <td width="20%" valign="top" >
                         <jsp:include page="AdminHead.jsp"  flush="true"/>
                    </td>
                    <td  width="80%" valign="center">
                         <iframe frameborder="0" name="ContentFrame" scrolling="no" width="800" height="600"></IFRAME>
                    </td>
                </tr>
               
            </table>
        </form>
    </body>
</HTML>
