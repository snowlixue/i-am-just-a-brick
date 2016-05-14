<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head runat="server">
    <title>无标题页</title>
<LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<%
		String EmployeeNo = (String)request.getAttribute("EmployeeeNo");
        if(EmployeeNo == null) EmployeeNo = "";
		String EmployeeName = (String)request.getAttribute("EmployeeName");
		if(EmployeeName == null) EmployeeName = "";
		int recordCount = (Integer)request.getAttribute("recordCount");
		//int pageSize = (Integer)request.getAttribute("pageSize");
		int currentPage = (Integer)request.getAttribute("currentPage");
		int totalPage = (Integer)request.getAttribute("totalPage");
		String pageFootStr = "";  
		int next, prev;  
		prev=currentPage-1;  
		next=currentPage+1; 
		String actionPath = "EmployeeInfoManage?action=query&method=get&EmployeeNo=" + EmployeeNo + "&EmployeeName=" + EmployeeName;
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
    <form method="post" id="frmAnnounce" action="EmployeeInfoManage?action=query">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	      <td style="height: 14px">
            <img src="../images/list.gif" width=14px height=14px />员工信息管理--&gt;员工信息维护
          </td>
        </tr>
        <tr>
          <td style="height: 37px">
             员工编号:<input type=text name="EmployeeNo" size=10/>
             员工姓名:<input type=text name="EmployeeName" size=10>&nbsp;
            <input type="submit" value="查询"/>
           </td>
         </tr>
       </table>
       <table width=600 border=0 cellpadding=2 align="center">
         <tr class="title">
           <td>员工编号</td><td>员工姓名</td><td>员工性别</td><td>员工生日</td><td>员工电话</td><td>编辑</td>
         </tr>
         <%
            ArrayList<Employee> employeeList = (ArrayList<Employee>)request.getAttribute("employeeList");
            for(int i=0;i<employeeList.size();i++) {
            	Employee employee = (Employee)employeeList.get(i);
           
         %>
         <tr onmouseover="this.style.backgroundColor='#227711';" onmouseOut="this.style.backgroundColor='';">
           <td><%=employee.getEmployeeNo() %></td>
           <td><%=employee.getEmployeeName() %></td>
           <td><%=employee.getEmployeeSex() %></td>
           <td><%=employee.getEmployeeBirthday() %></td>
           <td><%=employee.getEmployeeMobile() %></td>
           <td><a href="EmployeeInfoUpdate.jsp?EmployeeNo=<%=employee.getEmployeeNo() %>">编辑</a></td>
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
  </form>
</body>
</html>
