<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%
if (session.getAttribute("adminUsername")==null || session.getAttribute("adminUsername")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<html>
<head>
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
  <script src="../script/Vertify.js"></script>
  <script src="../script/calendar.js"></script>
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
   <form method="post" name="empForm" onsubmit="return checkEmployeeAddForm(this);" action="EmployeeInfoManage?action=add">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>员工信息管理--&gt;员工信息添加</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          <br />
          员工编号:<input type=text name="employeeNo" size=20 />&nbsp;&nbsp;
          &nbsp; &nbsp;&nbsp;
          员工姓名:<input type=text name="employeeName" size=20/>&nbsp;&nbsp;
          &nbsp; &nbsp;&nbsp;<br />
          <br />
          性别:<select name="employeeSex">
               <option value='男'>男</option>
               <option value='女'>女</option>
             </select>&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; 
          &nbsp; &nbsp; &nbsp;&nbsp; 学历层次:
             <select name="educationId">
             <%
                ArrayList educationList = EducationDAO.QueryAllEducation();
                for(int i=0;i<educationList.size();i++) {
                	Education education = (Education)educationList.get(i);
             %>
                    <option value=<%=education.getEducationId() %>><%=education.getEducationName() %></option>
             <%
                }
             %>
             </select>
           &nbsp;<br />
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<br />
           生日:<input type=text name="employeeBirthday" size=12/>
          <input class="submit" name="Button" onclick="setDay(document.empForm.employeeBirthday);" style="width: 30px" type="button" value="选择">
          &nbsp; &nbsp; &nbsp;&nbsp;
          登陆密码:<input type=text name="employeePassword" size=20/><br/>
          <br />
          家庭电话:<input type=text name="employeeHomeTel" size=20/>
          &nbsp; &nbsp; 手机:<input type=text name="employeeMobile" size=20/><br />
          <br />
          身份证件:<input type=text name="employeeCard" size=30/>
          邮件地址:<input type=text name="employeeEmail" size=20/><br />
          <br />
          居住地址:<input type=text name="employeeAddress"/></td>
	</tr>
          <tr>
              <td style="height: 26px" align="center">
                  <input type=submit value="添加" />&nbsp;
                  <input type=reset value="取消" /></td>
          </tr>
         
      </table>
       &nbsp;&nbsp;
    </form>
</body>
</html>
