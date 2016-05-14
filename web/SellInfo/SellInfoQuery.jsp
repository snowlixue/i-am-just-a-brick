<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<html>
<head>
    <title>无标题页</title>
<LINK href="../css/style.css" type="text/css" rel="stylesheet">
<script src="../script/calendar.js"></script>
</head>
<body>
<%
   String EmployeeNo = (String)request.getAttribute("EmployeeNo");
   if(null == EmployeeNo) EmployeeNo = "";
   String SellNo = (String)request.getAttribute("SellNo");
   if(null == SellNo) SellNo = "";
   String StartDate = (String)request.getAttribute("StartDate");
   if(null == StartDate) StartDate = "";
   String EndDate = (String)request.getAttribute("EndDate");
   if(null == EndDate) EndDate = "";
   
%>
     <form method="post" name=sellInfoQueryForm action="SellInfoManage?action=sellInfoQuery">
      <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px; width: 605px;">
          <img src="../images/list.gif" width=14px height=14px>商品销售管理--&gt;销售信息查询</td>
        </tr>
        <tr>
          <td style="height: 37px; width: 605px;">
              员工编号:<input type=text name=EmployeeNo value='<%=EmployeeNo %>' size=10/>
              单据号:<input type=text name=SellNo value='<%=SellNo %>' size=12/>
             <br />
              开始日期: <input type=text name=StartDate value='<%=StartDate %>' size=10/>
          <input class="submit" name="Button" onclick="setDay(document.sellInfoQueryForm.StartDate);" style="width: 30px" type="button" value="选择">
              结束日期<input type=text name=EndDate value='<%=EndDate %>' size=10/>
          <input class="submit" name="Button" onclick="setDay(document.sellInfoQueryForm.EndDate);" style="width: 30px" type="button" value="选择">
      <input type=submit value="查询"/><br />
       <table width=700 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>单据号</td><td>商品编号</td><td>商品名称</td><td>销售价格</td><td>销售数量</td><td>销售时间</td>
         </tr>
          <%
             float sellTotalPrice = 0.0f;
             ArrayList<SellInfo> sellInfoList = (ArrayList<SellInfo>)request.getAttribute("sellInfoList");
             if(null != sellInfoList) {
            	 for(int i=0;i<sellInfoList.size();i++) {
            		 SellInfo sellInfo = sellInfoList.get(i);
            		 sellTotalPrice += sellInfo.getTotalPrice();
            		 Good good = (new GoodDAO()).GetGoodInfoByGoodNo(sellInfo.getGoodNo());
          %>
          <tr>
          <td><%=sellInfo.getSellNo() %></td>
          <td><%=sellInfo.getGoodNo() %></td>
          <td><%=good.getGoodName() %></td>
          <td><%=sellInfo.getPrice() %></td>
          <td><%=sellInfo.getNumber() %></td>
          <td><%=sellInfo.getSellTime() %></td>
          </tr>
          <%
            	 }
             }
          %>    
        </table>
    <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
    <tr>
    <td align=right style="height: 12px">
        当前查询条件下销售总金额为：<%=sellTotalPrice %>元</td></tr>
    </table>
  </form>
</body>
</html>
