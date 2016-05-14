<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.GoodClass" %>
<%@page import="com.shuangyulin.dao.GoodClassDAO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="Head1" runat="server">
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" id="frmAnnounce" action="GoodInfoManage?action=add">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px/>商品信息管理--&gt;商品信息添加</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          商品编号:
          <input type="text" name="GoodNo" size=20 /><br/>
          商品类别:
           <select name="GoodClassId">
           <%
               /*调用业务层查询所有的商品类别信息*/
               ArrayList goodClassList = GoodClassDAO.QueryAllGoodClassInfo();
               for(int i=0;i<goodClassList.size();i++) {
    		   GoodClass goodClass = (GoodClass)goodClassList.get(i);%>
    		   <option value="<%=goodClass.getGoodClassId() %>"><%=goodClass.getGoodClassName() %></option>
 		   <%} %>
 		   </select><br/>
          <!--  
          <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" url="jdbc:sqlserver://127.0.0.1;databaseName=SuperMarketInfo;user=sa;password=123" var="db" scope="request"/>
          <sql:query var="query" dataSource="${db}">select goodClassName,goodClassId from goodClassInfo</sql:query>
          <select name="goodClassId">
            <c:forEach var="goodClass" items="${query.rows}">
              <option value="${goodClass.goodClassId}">${goodClass.goodClassName}</option>
            </c:forEach>
          </select><br/>
          -->
          商品名称:
          <input type=text name="GoodName" size=20/>
          <br/>
          商品单位:
          <input type=text name="GoodUnit" size=5/>
          <br />
          商品型号:
          <input type=text name="GoodModel" size=10/>
          <br />
          商品规格:
          <input type="text" name="GoodSpecs" size=10/>
          <br />
          商品售价:
          <input type="text" name="GoodPrice" size=5/>元&nbsp;
         <br />
          商品产地:
          <input type="text" name="GoodPlace" size=10/>
          <br />
          附加信息:
          <textarea name="GoodMemo" cols=40 rows=5></textarea>
          <br />
      </td>
	</tr>
          <tr>
              <td style="height: 25px">
                   <input type=submit value='添加'/>
                  <input type=button value='取消' onclick="javascript:location.href='GoodInfoAdd.jsp';" />
                  </td>
          </tr>
         
      </table>
    </form>
</body>
</html>

