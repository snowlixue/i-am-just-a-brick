<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
 <c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
      <table width=600 border=0 cellpadding=2 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;"  valign="top">
	  <td style="height: 14px" colspan=3>
          <img src="../images/ADD.gif" width=14px height=14px>商品信息管理--&gt;商品类别管理</td>
        </tr>
            <tr class="title"><td>商品类别编号</td><td>商品类别名称</td><td>操作</td></tr>
        </table>
        <table width=600 border=0 cellpadding=2 align="center">
            <%
               /*调用业务层查询所有的商品类别信息*/
               ArrayList goodClassList = GoodClassDAO.QueryAllGoodClassInfo();
               for(int i=0;i<goodClassList.size();i++) {
    		   GoodClass goodClass = (GoodClass)goodClassList.get(i);%><tr onmouseover="this.style.backgroundColor='#227711';" onmouseOut="this.style.backgroundColor='';">
                    <td><%=goodClass.getGoodClassId()%></td>
                    <td><%=goodClass.getGoodClassName()%></td>
                    <td><a href="GoodClassManage?action=del&goodClassId=<%=goodClass.getGoodClassId()%>" onclick="return confirm('确认删除吗?');">删除</a></td>
                </tr>  <%} %>
          </table>
	<form action="GoodClassManage?action=add" method="post">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
          <tr>
              <td style="height: 24px">
                  &nbsp;
                  商品类别名称：
                  <input type=text name="goodClassName" size="20"/>
                  &nbsp;
                  <input type="submit" value="添加"/></td>
          </tr>
        </table>
     </form>
</body>
</html>

