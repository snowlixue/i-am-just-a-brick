<%@page contentType="text/html;charset=GBK" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
    <title>无标题页</title>
 <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<%
   String GoodNo = (String)request.getAttribute("GoodNo");
   if(null == GoodNo) GoodNo = "";
   String GoodName = (String)request.getAttribute("GoodName");
   if(null == GoodName) GoodName = "";
%>
    <form method="post" name=goodStockQueryForm action="GoodStockManage?action=query">
      <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/list.gif" width=14px height=14px>商品库存管理--&gt;商品库存查询</td>
        </tr>
        <tr>
          <td style="height: 37px">
        商品编号: <input type=text name=GoodNo value='<%=GoodNo %>' size=10>
        商品名称: <input type=text name=GoodName value='<%=GoodName %>' size=10>
        <input type=submit value="查询"/><br />
         </td>
         </tr>
        </table>
        <table width=700 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>商品编号</td><td>商品名称</td><td>商品类别</td><td>商品型号</td><td>商品规格<td>商品库存</td><td>商品单位</td>
         </tr>
        <%
           ArrayList<GoodStock> goodStockList = (ArrayList<GoodStock>)request.getAttribute("goodStockList");
           if(null != goodStockList) {
        	   for(int i=0;i<goodStockList.size();i++) {
        		   GoodStock goodStock = goodStockList.get(i);
        		   Good good = (new GoodDAO()).GetGoodInfoByGoodNo(goodStock.getGoodNo());
        		   String goodClassName = GoodClassDAO.GetGoodClassNameById(good.getGoodClassId());
        %>
        <tr>
          <td><%=goodStock.getGoodNo() %></td>
          <td><%=good.getGoodName() %></td>
          <td><%=goodClassName %>
          <td><%=good.getGoodModel() %></td>
          <td><%=good.getGoodSpecs() %></td>
          <td><%=goodStock.getGoodCount() %></td>
          <td><%=good.getGoodUnit() %></td>
        </tr>
        <%
        	   }
           }
        %>
    </table>
  </form>
</body>
</html>
