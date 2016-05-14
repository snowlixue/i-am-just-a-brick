<%@page contentType="text/html; charset=GBK" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>超市信息管理系统</title>
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<script src="script/App.js"></script>
	</HEAD>
	<body>
	<% if(request.getAttribute("errMessage")!= null) {%>
	    <script>
	         alert('<%=request.getAttribute("errMessage")%>');
	    </script> 
	<% } %>
		<form id="Form1" method="post" runat="server" action="CheckLogin">
			<TABLE id="Table1" width="80%" border="0" align=center>
				<tr>
					<td style="height: 14px" align="center">
                        <img src="images/title.jpg" /></td>
				</tr>
				<br />
				<TR>
					<TD vAlign="middle" align="center">
						<TABLE class="tbl" id="Table2" cellSpacing="0" cellPadding="4" width="280" align="center"
							bgColor="#d6ebff" border="0">
							<TR>
								<TD class="bottom" align="center" bgColor="#52beef" height="35" colspan=2>
                                    超市管理系统登陆</TD>
							</TR>
							<TR>
								<TD class="br" style="HEIGHT: 33px" align="right" width="30%">用户名：</TD>
								<TD class="bottom" style="HEIGHT: 33px" align="left" width="70%">
								 <input type=text name="txtName" size=15/>
								</TD>
							</TR>
							<TR>
								<TD class="br" style="HEIGHT: 33px" align="right" width="30%">密码：</td>
								<TD class="bottom" style="HEIGHT: 33px" align="left" width="70%">
								  <input type=password name="txtPwd" size=15/>
								</TD>
							</TR>
							<tr>
							     <td class="br" style="HEIGHT: 33px" align="right" width="30%">验证码:</td>
							     <TD class="bottom" style="HEIGHT: 33px" align="left" width="70%">
							       <input name="code" type="text" id="code" value="" size="10">
          						   <img style="CURSOR: pointer" onclick=this.src=this.src; src="code.jsp">
          						 </td>
		  					</tr>
							<TR>
								<TD class="br" style="HEIGHT: 33px" align="right" width="30%">身份：</td>
								<TD class="bottom" style="HEIGHT: 33px" align="left" width="70%">
								    <select name="identify">
								      <option value="admin">管理员</option>
								      <option value="employee">员工</option>
								    </select>
                                </TD>
							</TR>
							
							<TR>
								<TD align="center" colSpan="2" height="40">&nbsp;
									<input type=submit class="searchButton" value="登陆">
									<input class="searchButton" id="btnExit" onclick="window.close();" type="button" value="退出"
										name="btnExit">
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
				<br />
				
			</TABLE>
		</form>
	</body>
</HTML>
