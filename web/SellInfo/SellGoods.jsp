<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("employeeNo")==null || session.getAttribute("employeeNo")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
    <script src="../script/ajax.js"></script>
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" name=sellInfoForm action="GoodCartManage?action=add">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>商品销售管理--&gt;商品销售</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          <br />
          你好，当前的商品销售信息如下：<br />
          <br />
            <table width=600 border=0 cellpadding=2 align="center">
             <tr class="title">
               <td>商品编号</td><td>商品名称</td><td>商品售价</td><td>商品数量</td><td>操作</td>
             </tr>
             <tr>
             <td colspan=5>
              &nbsp; 对不起,此功能被屏蔽,有需要的朋友可以联系作者
            <br />
            &nbsp; 姓名: 汪建林 生日:1985年1月7日(农历)
            <br />
            &nbsp; 星座: 双鱼座(故名双鱼林) 血型: 0
            <br />
            &nbsp; 毕业学校: 2007年于成都理工大学
            <br />
            &nbsp; 家乡地址: 四川渠县望溪乡包山村5组
            <br />
            &nbsp; 联系QQ: 287307421 联系电话: 13558690869
            <br />
            &nbsp; 联系Email: wangjianlin1985@126.com
            <br />
            &nbsp; 双鱼林电脑工作室淘宝店: <a href='http://shop34864101.taobao.com' target='_blank'>http://shop34864101.taobao.com</a>
            <br />
            &nbsp; 双鱼林电脑工作室拍拍店: <a href='http://287307421.paipai.com' target='_blank'>http://287307421.paipai.com</a>
            <br />
            &nbsp; 如果朋友们觉得我的设计还看得,可以联系我设计,我熟ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver提供技术指导!
             
             </td>
             </tr>
             </table>
    </form>
</body>
</html>

