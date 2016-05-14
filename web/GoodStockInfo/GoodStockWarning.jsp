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
      <table width=700 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/list.gif" width=14px height=14px>商品库存管理--&gt;商品库存报警</td>
        </tr>
        <tr>
          <td style="height: 37px">
              商品库存报警功能：当商品库存过多(本系统设置为200)时以<font color=yellow>黄色</font>字体显示，过少(低于20)时以<font color=red>红色</font>字体显示。<br />
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
        		   int goodCount = goodStock.getGoodCount();
        		   String color = "yellow";
        		   if(goodCount < 20) color = "red";
        %>
        <tr>
          <td><%=goodStock.getGoodNo() %></td>
          <td><%=good.getGoodName() %></td>
          <td><%=goodClassName %>
          <td><%=good.getGoodModel() %></td>
          <td><%=good.getGoodSpecs() %></td>
          <td style='color:<%=color %>;'><%=goodCount %></td>
          <td><%=good.getGoodUnit() %></td>
        </tr>
        <%
        	   }
           }
        %>
    </table>  
        </td>
      </tr>
    </table>
</body>
</html>
