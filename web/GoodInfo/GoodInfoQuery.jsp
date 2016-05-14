<%@page contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shuangyulin.javabean.GoodClass"%>
<%@page import="com.shuangyulin.javabean.Good"%>
<%@page import="com.shuangyulin.dao.GoodClassDAO"%>
<html>
<head>
    <title>无标题页</title>
<LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
		<%
		String GoodNo = (String)request.getAttribute("GoodNo");
		String GoodName = (String)request.getAttribute("GoodName");
		int GoodClassId = (Integer)request.getAttribute("GoodClassId");
		int recordCount = (Integer)request.getAttribute("recordCount");
		//int pageSize = (Integer)request.getAttribute("pageSize");
		int currentPage = (Integer)request.getAttribute("currentPage");
		int totalPage = (Integer)request.getAttribute("totalPage");
		String pageFootStr = "";  
		int next, prev;  
		prev=currentPage-1;  
		next=currentPage+1; 
		String actionPath = "GoodInfoManage?action=query&GoodNo=" + GoodNo + "&GoodName=" + GoodName + "&GoodClassId=" + GoodClassId;
		pageFootStr+="<form aciont='" + actionPath + "' method=post>";
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
		<form method="post" id="frmAnnounce"
			action="GoodInfoManage?action=query">
			<table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
				<tr style="color:blue;font-size:14px;">
					<td style="height: 14px">
						<img src="../images/list.gif" width=14px height=14px />
						商品信息管理--&gt;商品信息查询
					</td>
				</tr>
				<tr>
					<td style="height: 37px">
						商品编号:
						<input type=text name=GoodNo size=12 value='<%=GoodNo %>' />
						&nbsp; 商品名称:
						<input type=text name=GoodName value='<%=GoodName %>' size=12 />
						&nbsp; 商品类别:
						<select name="GoodClassId">
							<option value="0">
								请选择商品类别
							</option>
							<%
											              /*调用业务层查询所有的商品类别信息*/
											              ArrayList goodClassList = GoodClassDAO.QueryAllGoodClassInfo();
											              for(int i=0;i<goodClassList.size();i++) {
											   		      	GoodClass goodClass = (GoodClass)goodClassList.get(i);
											   		      	String selected = "";
											   		      	if(goodClass.getGoodClassId() == GoodClassId) selected = "selected";
							%>
							<option value="<%=goodClass.getGoodClassId()%>" <%=selected %>>
								<%=goodClass.getGoodClassName()%>
							</option>
							<%
							}
							%>
						</select>
						&nbsp;
						<input type=submit value="查询" />
					</td>
				</tr>
			</table>
		</form>
		<table width=600 border=0 cellpadding=2 cellspacing=0 align="center">
			<tr class="title">
				<td>
					商品编号
				</td>
				<td>
					商品名称
				</td>
				<td>
					商品类别
				</td>
				<td>
					商品单位
				</td>
				<td>
					商品售价
				</td>
			</tr>
			<%
					          	//从request域中取得要显示的某页商品信息
					          	ArrayList GoodList = (ArrayList)request.getAttribute("goodList");
					          	/*遍历每个商品的信息进行显示*/
					            for(int i=0;i<GoodList.size();i++){
					            	Good good = (Good)GoodList.get(i);
			%>
			<tr onmouseover="this.style.backgroundColor='#227711';" onmouseOut="this.style.backgroundColor='';">
				<td>
					<%=good.getGoodNo()%>
				</td>
				<td>
					<%=good.getGoodName()%>
				</td>
				<td>
					<%=GoodClassDAO.GetGoodClassNameById(good.getGoodClassId())%>
				</td>
				<td>
					<%=good.getGoodUnit()%>
				</td>
				<td>
					<%=good.getGoodPrice()%>
				</td>
			</tr>
			<%
			}
			%>
			<tr><td colspan=6>
		<%=pageFootStr %>
		&nbsp;
		</td>
		</tr>
		</table>
	</body>
</html>
