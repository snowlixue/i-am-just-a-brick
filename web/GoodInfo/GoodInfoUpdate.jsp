<%@page contentType="text/html;charset=GBK" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList;" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<%
  /*取得商品编号，根据该商品编号调用业务层取得该商品的详细信息*/
  String goodNo = request.getParameter("goodNo");
  Good good = GoodDAO.GetGoodInfoByGoodNo(goodNo);
  /*调用业务层查询所有的商品类别信息*/
  ArrayList goodClassList = GoodClassDAO.QueryAllGoodClassInfo();
%>
<html>
<head>
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
 <c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" id="frmAnnounce" action="GoodInfoManage?action=update&goodNo=<%=goodNo %>">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px/>商品信息管理--&gt;商品信息更新</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          商品编号:
          <%=goodNo %>
          <br />
          <br />
          商品类别:
          <select name=goodClassId>
            <%
               for(int i=0;i<goodClassList.size();i++) {
            	   GoodClass goodClass = (GoodClass)goodClassList.get(i);
            	   if(goodClass.getGoodClassId() == good.getGoodClassId()) {
            %>
            		 <option selected value=<%=goodClass.getGoodClassId()%>><%=goodClass.getGoodClassName() %></option>
           <%
            	   } else {
           %>
                     <option value=<%=goodClass.getGoodClassId() %>><%=goodClass.getGoodClassName() %></option>
           <%
            	   }
           %>
            <%
               }
            %>
          </select>
          <br />
          <br />
          商品名称:
          <input type=text size=20 name=goodName value=<%=good.getGoodName() %>>
          <br />
          <br />
          商品单位:
          <input type=text size=4 name=goodUnit value=<%=good.getGoodUnit() %>>
          <br />
          <br />
          商品型号:
          <input type=text size=20 name=goodModel value=<%=good.getGoodModel() %>>
          <br />
          <br />
          商品规格:
          <input type=text size=20 name=goodSpecs value=<%=good.getGoodSpecs() %>>
          <br />
          <br />
          商品售价:
          <input type=text size=5 name=goodPrice value=<%=good.getGoodPrice() %>>
          元&nbsp;
          <br />
          <br />
          商品产地:
          <input type=text size=30 name=goodPlace value=<%=good.getGoodPlace() %>>
          <br />
          <br />
          附加信息:
 		  <textarea name="goodMemo" cols=40 rows=5><%=good.getGoodMemo() %></textarea><br />
      </td>
	</tr>
          <tr>
              <td style="height: 25px">
                  <input type=submit value="更新" />&nbsp;
                  <input type=button value='返回' onclick="javascript:location.href='GoodInfoManage?action=query';" />
                  </td>
          </tr>
         
      </table>
       &nbsp;&nbsp;&nbsp;&nbsp;
    </form>
</body>
</html>
