<%@page contentType="text/html;charset=GBK"%>
<%
    //String baseUrl = "http://" + request.getRemoteHost() + ":" + request.getRemotePort() + "/SuperMarket";
%>
<!--BEGIN HEADER MODULE-->
<table id="Table2" cellSpacing="0" cellPadding="0" border="0">
    <tr>
        <td>
            <div id="outer">

                <ul id="menu">
                    <li class="sub">商品信息管理
                        <ul>
                            <li><a href="../GoodInfo/GoodClassManage.jsp"  target='ContentFrame'>商品类别管理</a></li>
                            <li><a href="../GoodInfo/GoodInfoAdd.jsp"  target='ContentFrame'>添加商品信息</a></li>
                            <li><a href="../GoodInfo/GoodInfoManage?action=query" target='ContentFrame'>商品信息维护</a></li>
                        </ul>
                    </li>
                    <li class="sub">员工信息管理
                        <ul>
                            <li><a href="EmployeeInfoAdd.jsp"  target='ContentFrame'>添加员工信息</a></li>
                            <li><a href="EmployeeInfoManage?action=query"  target='ContentFrame'>员工信息维护</a></li>
                        </ul>
                    </li>
                    <li class="sub">商品进货管理
                        <ul>
                            <li><a href="../BuyInfo/BuyInfoAdd.jsp"  target='ContentFrame'>商品进货登记</a></li>
                            <li><a href="../BuyInfo/BuyInfoManage?action=query"  target='ContentFrame'>商品进货查询</a></li>
                            <li><a href="../BuyBackInfo/BuyBackInfoAdd.jsp"  target='ContentFrame'>进货退货登记</a></li>
                            <li><a href="../BuyBackInfo/BuyBackInfoManage?action=query"  target='ContentFrame'>进货退货查询</a></li>
                        </ul>
                    </li>
                    <li class="sub">商品销售管理
                        <ul>
                            <li><a href="../SellInfo/SellInfoQuery.jsp"  target='ContentFrame'>商品销售查询</a></li>
                            <li><a href="../SellInfo/EmployeeSellResult.jsp"  target='ContentFrame'>员工业绩查询</a></li>
                            <li><a href="../SellBackInfo/SellBackInfoAdd.jsp"  target='ContentFrame'>顾客退货办理</a></li>
                            <li><a href="../SellBackInfo/SellBackInfoQuery.jsp"  target='ContentFrame'>商品退货查询</a></li>
                        </ul>
                    </li>
                    <li class="sub">商品库存管理
                        <ul>
                            <li><a href="../GoodStockInfo/GoodStockQuery.jsp"  target='ContentFrame'>商品库存查询</a></li>
                            <li><a href="../GoodStockInfo/GoodStockManage?action=warning"  target='ContentFrame'>商品库存报警</a></li>
                        </ul>
                    </li>
                    <li class="sub">系统管理
                        <ul>
                            <li><a href="SupplierManage.jsp"  target='ContentFrame'>供应商管理</a></li>
                            <li><a href="changePassword.jsp"  target='ContentFrame'>修改密码</a></li>
                            <li><a href="../about.html"  target='ContentFrame'>关于系统</a></li>
                            <li><a href="../logout.jsp"  target='ContentFrame'>退出</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
        </td>
    </tr>
</table>
