<%@page contentType="text/html;charset=GBK" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList" %>
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
     <script src="../script/calendar.js"></script>
     <script src="../script/Vertify.js"></script>
     <script src="../script/ajax.js"></script>
</head>
<body onload="javascript:sendRequest(document.buyInfoAddForm.GoodNo.value);">
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" name="buyInfoAddForm" action="BuyInfoManage?action=add" onsubmit="return checkBuyInfoAddForm(this);">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>商品进货管理--&gt;商品进货登记</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          选择供应商:
          <select name=SupplierName>
            <%
				ArrayList<Supplier> supplierList = SupplierDAO.QueryAllSupplier();
	  			for(int i=0;i<supplierList.size();i++) {
	  				Supplier supplier = supplierList.get(i);
	  	    %>
	  	         <option value=<%=supplier.getSupplierName() %>><%=supplier.getSupplierName() %></option>
	  	    <%
	  			}
            %>
          </select>
          <br />
          <br />
          选择商品:
          <select name=GoodNo onchange="javascript:sendRequest(this.value);">
          <%
            ArrayList<Good> goodList = GoodDAO.QueryAllGoodInfo();
            for(int i=0;i<goodList.size();i++) {
            	Good good = goodList.get(i);
          %>
              <option value=<%=good.getGoodNo() %>><%=good.getGoodNo() %>----<%=good.getGoodName() %></option>
          <%
            }
          %>
          </select>
         <br /><br/>
          <div id="GoodInfo" style="display:none;color:red">
              商品名称:
              <span id=GoodName></span> <br>
              商品型号:
              <span id=GoodModel></span> <br>
              商品规格:
              <span id=GoodSpecs></span> <br>
              商品产地:
              <span id=GoodPlace></span> <br>
          </div>
          <br />
          进货价格:
          <input type=text name=Price size=5 onpropertyChange="checkPrice(this.value);" onBlur="CalculateTotalPrice();">元
           &nbsp; &nbsp;&nbsp; 进货数量:
          <input type=text name=Number size=5  onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" onBlur="CalculateTotalPrice();"><br />
          <br />
          进货总价:
          <input type=text name=TotalPrice readonly='true'  size=8>元
          <br />
          <br />
          进货日期:
          <input type=text name="BuyDate" size=12 readOnly/>
          <input class="submit" name="Button" onclick="setDay(document.buyInfoAddForm.BuyDate);" style="width: 30px" type="button" value="选择">
           <br />
          <br />
      </td>
	</tr>
          <tr>
              <td style="height: 24px">
                <input type=submit value="进货登记"/>&nbsp;
                <input type=button value="取消" onclick="javascript:location.href='BuyInfoAdd.jsp';"/>
              </td>
          </tr>
         
      </table>
    </form>
</body>
</html>

