<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<html>
<head>
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" name=supplierManageForm action="SupplierManage?action=add">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>系统管理--&gt;供应商信息管理</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          <br />
          <table>
            <tr class="title"><td>供应商公司名称</td><td>法人代表</td><td>电话</td><td>地址</td></tr>
            <%
               ArrayList<Supplier> supplierList = SupplierDAO.QueryAllSupplier();
               for(int i=0;i<supplierList.size();i++) {
            	   Supplier supplier = supplierList.get(i);
            %>
            <tr>
              <td><%=supplier.getSupplierName() %></td>
              <td><%=supplier.getSupplierLawyer() %></td>
              <td><%=supplier.getSupplierTelephone() %></td>
              <td><%=supplier.getSupplierAddress() %></td>
            </tr>
            <%
               }
            %>
          </table>
      </td>
	</tr>
          <tr>
              <td style="height: 24px">
                  <br />
                  新的供应商信息添加:<br />
                  <br />
                  供应商公司名称：
                  <input type=text name=SupplierName size=20>
                  <br>
                  供应商法人代表：
                  <input type=text name=SupplierLawyer size=20>
                  <br>
                  供应商电话： &nbsp;&nbsp;&nbsp;
                  <input type=text name=SupplierTelephone size=20><br>
                  供应商地址： &nbsp;&nbsp;&nbsp;
                  <input type=text name=SupplierAddress size=20>
                  <br />
                  <br />
                  <input type=submit value="保存">
                </td>
          </tr>
          <tr>
              <td style="height: 24px">
              </td>
          </tr>
         
      </table>
       &nbsp;&nbsp;&nbsp;
    </form>
</body>
</html>

