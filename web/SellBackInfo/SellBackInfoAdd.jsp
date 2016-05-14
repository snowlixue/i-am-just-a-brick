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
<head id="Head1" runat="server">
    <title>无标题页</title>
   <LINK href="../css/style.css" type="text/css" rel="stylesheet">
    <script src="../script/calendar.js"></script>
    <script src="../script/ajax.js"></script>
        <script language="javascript">
/*验证销售退货信息的form*/
function checkSellBackInfoAddForm(sellBackInfoAddForm) {
    var Price = sellBackInfoAddForm.Price.value;
    var Number = sellBackInfoAddForm.Number.value;
    if(Price == "") {
    	alert("价格不能为空!");
    	sellBackInfoAddForm.Price.focus();
    	return false;
    }
    if(Number=="") {
    	alert("进货数量不能为空!");
    	sellBackInfoAddForm.Number.focus();
    	return false;
    }
	return true;
}

 function checkPrice(obj)    //验证输入的商品价格
 {   
 	pta=/[^0123456789.]{1,}/;   
 	if(pta.exec(obj))
 		document.sellBackInfoAddForm.Price.value=obj.substr(0,obj.length-1);   
 }  
    </script>
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
    <form method="post" name=sellBackInfoAddForm action="SellBackInfoManage?action=add" onsubmit="return checkSellBackInfoAddForm(this);">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>商品销售管理--&gt;顾客退货登记</td>
        </tr>
            <tr><td>
                <br />
                销售单据：
                <input type=text name=SellNo size=20>
                <br />
                <br />
                商品编号：
                <input type=text name=GoodNo size=10>
                &nbsp;<input type=button onclick="javascript:sendRequest(document.sellBackInfoAddForm.GoodNo.value);" value="获取商品信息"/><br />
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
                退货单价：
                <input type=text name=Price size=5 onpropertyChange="checkPrice(this.value);">
                元<br />
                <br />
                退货数目：
                <input type=text name=Number size=5 onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" >
                <br /><br>
                退货原因：
                <input type=text name=SellBackReason size=20><br><br>
                商品是否完好：
                <select name=IsGood>
                  <option value="yes">完好</option>
                  <option value="no">已坏</option>
                </select>
                <br />
                <br />
                <input type=submit value="退货登记"/>
                </td></tr>
       &nbsp;&nbsp;&nbsp;
    </form>
</body>
</html>


