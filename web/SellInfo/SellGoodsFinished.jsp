<%@page contentType="text/html;charset=GBK" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.shuangyulin.javabean.*" %>
<%@page import="com.shuangyulin.dao.*" %>
<%@ page session="true" %>
<%
if (session.getAttribute("employeeNo")==null || session.getAttribute("employeeNo")==""){
	response.sendRedirect("/SuperMarket/login.jsp");
} %>
<html>
<head>
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
    <script language=javascript>
function printSellInfo() { 
bdhtml=window.document.body.innerHTML; 
sprnstr="<!--startprint-->"; 
eprnstr="<!--endprint-->"; 
prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); 
prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
window.document.body.innerHTML=prnhtml; 
window.print(); 
window.document.body.innerHTML=bdhtml; 
return true;
         }
 function Calculate() {
    if(document.sellFinishForm.RealGiveMoney.value == "") {
       alert('请输入实付金额');
       document.sellFinishForm.RealGiveMoney.focus();
       return ;
    }
    document.sellFinishForm.GiveBackMoney.value = document.sellFinishForm.RealGiveMoney.value - document.sellFinishForm.ShouldGiveMoney.value
 }
 
 function checkPrice(obj)    //验证输入的商品价格
 {   
 	pta=/[^0123456789.]{1,}/;   
 	if(pta.exec(obj))
 		document.sellFinishForm.RealGiveMoney.value=obj.substr(0,obj.length-1);   
 }  
</script>
</head>
<body>

<%
   String employeeNo = session.getAttribute("employeeNo").toString();
   java.text.SimpleDateFormat bartDateFormat = new java.text.SimpleDateFormat("yyMMddHHmmSS");
   java.util.Date date = new java.util.Date();
   String nowTimeString = bartDateFormat.format(date);
   /*生成单据号: 员工编号+当前时间*/
   String SellNo = employeeNo + nowTimeString; 
   
%>
   <form method="post" name="sellFinishForm" action="GoodCartManage?action=finishSell">
   <!--startprint-->
   　　<table width=550 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px">
          <img src="../images/ADD.gif" width=14px height=14px>商品销售管理--&gt;商品销售结帐</td>
        </tr>
        </table><br />
      <table width=550 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px;"><center><font size=4 color=red>欢迎光临双鱼林超市</font></center><br />
          你的小票号是<font color=red><%=SellNo %></font>,请妥善保管，7天内凭此办理退货.</td>
        </tr><tr>
	  <td style="height: 26px; width: 426px;">
          <br />
         <table width=600 border=0 cellpadding=2 align="center">
           <tr>
           <td>
            &nbsp; 对不起,此功能被屏蔽,有需要的朋友可以联系作者
            <br />
            &nbsp; 姓名: 汪建林 生日:1985年1月7日(农历)
            <br />
            &nbsp; 星座: 双鱼座(故名双鱼林) 血型: 0
            <br />
            &nbsp; 毕业学校: 2007年于成都理工大学
            <br />
            &nbsp; 家乡地址: 四川渠县望溪乡包山村5组
            <br />
            &nbsp; 联系QQ: 287307421 联系电话: 13558690869
            <br />
            &nbsp; 联系Email: wangjianlin1985@126.com
            <br />
            &nbsp; 双鱼林电脑工作室淘宝店: <a href='http://shop34864101.taobao.com' target='_blank'>http://shop34864101.taobao.com</a>
            <br />
            &nbsp; 双鱼林电脑工作室拍拍店: <a href='http://287307421.paipai.com' target='_blank'>http://287307421.paipai.com</a>
            <br />
            &nbsp; 如果朋友们觉得我的设计还看得,可以联系我设计,我熟ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver提供技术指导!
           
           </td>
           </tr>  
       </table>
       </td>
       </tr>
       </table>
    </form>
</body>
</html>


