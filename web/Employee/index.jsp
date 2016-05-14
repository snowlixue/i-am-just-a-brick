<%@page contentType="text/html;charset=GBK" %>
<%@ page session="true" %>
<%
if (session.getAttribute("employeeNo")==null || session.getAttribute("employeeNo")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>超市信息管理系统</title>
		<LINK href="../css/style.css" type="text/css" rel="stylesheet">
		<script src="../script/App.js"></script>
	
	</HEAD>
	<body onmousemove="hiddenMenu();">
		<form id="Form1" method="post" runat="server">
			<table align="center" cellSpacing="0" cellPadding="0" width="760" border="0" ID="Table5">
				<TBODY>
					<tr>
						<td>
							<!--   /菜单层   -->
							<table class="tbl" id="Table2" cellSpacing="0" cellPadding="0" width="760" border="0">
								<tr>
									<td>
										<!--   导航超链接   -->
											<jsp:include page="EmployeeHead.jsp" flush="true"/>
										<!--   /导航主菜单   --></td>
								</tr>
								<tr>
									<td align="center"></td>
								</tr>
							</table>
							<!-- END PAGE HEADER MODULE -->
							<!--   内容层   -->
							<table class="lrb" align="center" cellSpacing="0" cellPadding="0" width="760" border="0"
								ID="Table3">
								<tr>
									<td bgcolor="#d6ebff" style="height: 400px">
									<iframe style="height: 450px; width: 760px;" frameborder="0" name="ContentFrame" scrolling="no" src="../desk.htm" width="760"></IFRAME>
									</td>
								</tr>
							</table>
							<!--   /内容层   -->
						</td>
					</tr>
					<tr><td><jsp:include page="../foot.jsp" flush="true"/></td></tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>
