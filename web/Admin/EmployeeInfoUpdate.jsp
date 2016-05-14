<%@page contentType="text/html;charset=gb2312" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="java.util.ArrayList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<%
   Boolean adminFlag = (Boolean)session.getAttribute("adminFlag");
   if(adminFlag == null || adminFlag != true) 
	   response.sendRedirect("../login.jsp");
   String EmployeeNo = request.getParameter("EmployeeNo");
   Employee employee = EmployeeDAO.GetEmployeeInfoByNo(EmployeeNo);
%>
<html>
<head runat="server">
    <title>无标题页</title>
 <LINK href="../css/style.css" type="text/css" rel="stylesheet">
  <script src="../script/calendar.js"></script>
</head>
<body>
 <c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
    <form method="post" name="empForm" action="EmployeeInfoManage?action=update">
       <input type=hidden name=EmployeeNo value=<%=EmployeeNo %>>
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/Edit.gif" width=14px height=14px>员工信息管理--&gt;员工信息更新</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          <br />
          员工编号:<%=employee.getEmployeeNo() %>
          &nbsp; &nbsp;&nbsp;
          员工姓名:<input type=text name=EmployeeName value=<%=employee.getEmployeeName() %>>&nbsp;&nbsp;
          &nbsp; &nbsp;&nbsp;<br />
          <br />
          性别:
          <%
             String employeeSex = employee.getEmployeeSex();
             if(employeeSex.equals("男")) {
          %>
            <select name=EmployeeSex>
              <option value="男">男</option>
              <option value="女">女</option>
            </select>
          <% 
             } else {
          %>
            <select name=EmployeeSex>
              <option value="女">女</option>
              <option value="男">男</option>
            </select>
          <%
             }
          %>&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; 
          &nbsp; &nbsp; &nbsp;&nbsp;
          学历层次:
          <select name="EmployeeEducationId">
            <%
               ArrayList<Education> educationList = (ArrayList<Education>)EducationDAO.QueryAllEducation();
               for(int i=0;i<educationList.size();i++) {
            	   Education education = educationList.get(i);
            	   if(education.getEducationId() == employee.getEmployeeEducationId()) {
           %>
               <option selected value=<%=education.getEducationId() %>><%=education.getEducationName() %></option> 
           <% 
            	   } else {
           %>
           
              <option value=<%=education.getEducationId() %>><%=education.getEducationName() %></option>
           <%		   
            	   }  
               }
            %>
          </select>&nbsp;<br />
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<br />
          生日:<input type=text name="EmployeeBirthday" value='<%=employee.getEmployeeBirthday() %>' size=12 readOnly/>
          <input class="submit" name="Button" onclick="setDay(document.empForm.EmployeeBirthday);" style="width: 30px" type="button" value="选择">
          &nbsp; &nbsp; &nbsp;&nbsp;
          登陆密码:<input type=text name="EmployeePassword" value='<%=employee.getEmployeePassword() %>' size=20/><br />
          <br />
          家庭电话:<input type=text name="EmployeeHomeTel" value='<%=employee.getEmployeeHomeTel() %>' size=20/>
          &nbsp; &nbsp; 手机:<input type=text name="EmployeeMobile" value='<%=employee.getEmployeeMobile() %>' size=20/><br />
          <br />
          身份证件:<input type=text name="EmployeeCard" value='<%=employee.getEmployeeCard() %>' size=30/>
          邮件地址:<input type=text name="EmployeeEmail" value='<%=employee.getEmployeeEmail() %>' size=20/><br />
          <br />
          居住地址:<input type=text name="EmployeeAddress" value='<%=employee.getEmployeeAddress() %>'/></td>
	</tr>
          <tr>
              <td style="height: 26px" align="center">
                 <input type=submit value='更新'/>
                 &nbsp;
                 <input type=button value="取消" onclick="javascript:location.href='EmployeeInfoUpdate.jsp?EmployeeNo=<%=EmployeeNo %>';">
           </tr>
         
      </table>
        &nbsp;&nbsp;
    </form>
</body>
</html>
