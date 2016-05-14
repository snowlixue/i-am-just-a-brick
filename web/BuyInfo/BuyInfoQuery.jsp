<%@page contentType="text/html;charset=GBK" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList" %>
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
</head>
<body>
<%
	String GoodNo = (String)request.getAttribute("GoodNo");
	if(null == GoodNo) GoodNo = "";
	String GoodName = (String)request.getAttribute("GoodName");
	if(null == GoodName) GoodName = "";
	int GoodClassId = (Integer)request.getAttribute("GoodClassId");
	String StartDate = (String)request.getAttribute("StartDate");
	if(null == StartDate) StartDate = "";
	String EndDate = (String)request.getAttribute("EndDate");
	if(null == EndDate) EndDate = "";
	float TotalPrice = (Float)request.getAttribute("TotalPrice");
	
	int recordCount = (Integer)request.getAttribute("recordCount");
	int currentPage = (Integer)request.getAttribute("currentPage");
	int totalPage = (Integer)request.getAttribute("totalPage");
	String pageFootStr = "";  
	int next, prev;  
	prev=currentPage-1;  
	next=currentPage+1; 
	String actionPath = "BuyInfoManage?action=query&method=get&GoodNo=" + GoodNo + "&GoodName=" + GoodName + "&GoodClassId=" + GoodClassId + "&StartDate=" + StartDate + "&EndDate=" + EndDate;
	pageFootStr+="<form action='" + actionPath + "' method=post>";
	pageFootStr += "查询到<span>"+recordCount+"</span>条记录"+"    共<span>"+totalPage+"</span>页";  
	pageFootStr +=" 第<span>"+currentPage+"</span>页 ";  
	if(currentPage>1) 
		pageFootStr += " <A href=" + actionPath + "&currentPage=1"+">首页</A> ";  
	else 
		pageFootStr += " 首页 ";  
	if(currentPage>1)
		pageFootStr += " <A href=" + actionPath + "&currentPage=" + prev + ">上一页</A> ";  
	else
		pageFootStr += " 上一页 ";  
	if(currentPage<totalPage)  
		pageFootStr += " <A href=" + actionPath + "&currentPage=" + next + ">下一页</A> ";  
	else 
		pageFootStr += " 下一页 ";  
	if(totalPage>1&&currentPage!=totalPage)  
		pageFootStr += " <A href=" + actionPath + "&currentPage=" + totalPage + ">尾页</A>";  
	else
		pageFootStr += " 尾页 ";  
	pageFootStr+="第<input name=currentPage type=text size=2>页 <input type=submit value=GO></form>" ;
	%>
    <form method="post" name=buyInfoQueryForm action="BuyInfoManage?action=query">
      <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/list.gif" width=14px height=14px>商品进货管理--&gt;进货信息查询</td>
        </tr>
        <tr>
          <td style="height: 37px">
        商品编号:<input type=text size=10 name=GoodNo>&nbsp;
       商品名称:<input type=text size=10 name=GoodName>&nbsp;
        商品类别:
        <select name=GoodClassId>
          <option value="0">请选择</option>
        <%
           ArrayList goodClassList = (new GoodClassDAO()).QueryAllGoodClassInfo();
           for(int i=0;i<goodClassList.size();i++) {
        	   GoodClass goodClass = (GoodClass)goodClassList.get(i);
        %>
          <option value="<%=goodClass.getGoodClassId() %>"><%=goodClass.getGoodClassName() %></option>
        <%
           }
        %>
        </select>
        <br/>
          开始日期:
          <input type=text name=StartDate size=10/>
          <input class="submit" name="Button" onclick="setDay(document.buyInfoQueryForm.StartDate);" style="width: 30px" type="button" value="选择">
          结束日期:
          <input type=text name=EndDate size=10/>
          <input class="submit" name="Button" onclick="setDay(document.buyInfoQueryForm.EndDate);" style="width: 30px" type="button" value="选择">
       <input type=submit value="查询"/>    
        </td>
      </tr>
    </table>
    </form>
    <table width=700 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>商品编号</td><td>商品名称</td><td>商品类别</td><td>供应商</td><td>进货单价</td><td>进货数量</td><td>进货总价</td><td>进货日期</td>
         </tr>
         <%
            ArrayList<BuyInfo> buyInfoList = (ArrayList<BuyInfo>)request.getAttribute("buyInfoList");
            for(int i=0;i<buyInfoList.size();i++) {
            	BuyInfo buyInfo = buyInfoList.get(i);
            	Good good = (new GoodDAO()).GetGoodInfoByGoodNo(buyInfo.getGoodNo());
         %>
         <tr onmouseover="this.style.backgroundColor='#227711';" onmouseOut="this.style.backgroundColor='';">
           <td><%=buyInfo.getGoodNo() %></td>
           <td><%=good.getGoodName() %></td>
           <td><%=(new GoodClassDAO()).GetGoodClassNameById(good.getGoodClassId()) %></td>
           <td><%=buyInfo.getSupplierName() %></td>
           <td><%=buyInfo.getPrice() %></td>
           <td><%=buyInfo.getNumber() %></td>
           <td><%=buyInfo.getTotalPrice() %></td>
           <td><%=buyInfo.getBuyDate() %></td>
         </tr>
         <%
            }
         %>
         <tr><td colspan=8>
		<%=pageFootStr %>
		&nbsp;
		</td>
		</tr>
    </table>
    <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
    <tr>
    <td align=right style="height: 12px">
        当前查询条件下进货总金额为：<%=TotalPrice %>元</td></tr>
    </table>
   
</body>
</html>
