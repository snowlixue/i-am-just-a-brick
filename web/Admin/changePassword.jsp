<%@page contentType="text/html;charset=GBK" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>密码修改</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script language="javascript">
      function checkForm() {
        var newPassword,newPassAgain;
        newPassword = document.changePasswordForm.NewPassword.value;
        newPassAgain = document.changePasswordForm.NewPassAgain.value;
        if( newPassword== "") {
          alert('新密码不能为空!');
          document.changePasswordForm.NewPassword.focus();
          return false;
        }
        if(newPassword != newPassAgain) {
          alert('两次密码输入不一致!');
          document.changePasswordForm.NewPassword.value = "";
          document.changePasswordForm.NewPassAgain.value = "";
          document.changePasswordForm.NewPassword.focus();
          return false;
        }
        return true;
      }
    </script>
</head>
<body>
<c:if test="${!empty requestScope.result}">
   <script>alert('<c:out value="${requestScope.result}"></c:out>');</script>
 </c:if>
<form name="changePasswordForm" action="ChangePasswordServlet?identify=admin" onsubmit="return checkForm();" method="post">
   <table width="80%" border="0" cellpadding="0" cellspacing="2" align="center">
<tr> 
    <td height="21" bgcolor="magenta">&nbsp;<img src="../images/ico29.gif" width="32" height="32" hspace="2" vspace="2" align="absmiddle"><font size="+1"><strong>系统安全密码设置</strong></font></td>
  </tr>
</table>
<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
	  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center" class="TableMenu">
	
      <tr>
        <td  class="a3">重新设置密码</td>
		    <td>
		      <input type=password name=NewPassword size=20>
              
				</td>
      </tr>
		  <tr>
		    <td>再次确认新密码:</td>
			  <td>
			    <input type=password name=NewPassAgain size=20>
                </td>
			</tr>
      <tr bgcolor="#ffffff">
        <td height="30" colspan="4" align="center">
            <input type=hidden name=adminUsername value='<%=session.getAttribute("adminUsername").toString() %>'>
            &nbsp;<input type=submit value="修改密码">
            <br /></td>
      </tr> 
	  </table>
	</td>
  </tr>	  	    
</table>
 </form>
</body>
</html>
