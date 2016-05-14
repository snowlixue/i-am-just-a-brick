package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.GoodCartDAO;
import com.shuangyulin.javabean.GoodCart;

/*关于员工销售商品时购物车处理的控制层*/
public class GoodCartManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("add")) { /*向购物车中加入商品*/
			/*
		     对不起,此功能被屏蔽,有需要的朋友可以联系作者
		     姓名: 汪建林 生日:1985年1月7日(农历)
		     星座: 双鱼座(故名双鱼林) 血型: 0
		     毕业学校: 2007年于成都理工大学
		     家乡地址: 四川渠县望溪乡包山村5组
		     联系QQ: 287307421 联系电话: 13558690869
		     联系Email: wangjianlin1985@126.com
		     双鱼林电脑工作室淘宝店: http://shop34864101.taobao.com
		     双鱼林电脑工作室拍拍店: http://287307421.paipai.com
	          如果朋友们觉得我的设计还看得,可以联系我设计,我熟ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver提供技术指导!
	       */ 
		} else if(action.equals("del")) { /*删除购物车中某个销售信息*/
			/*
		     对不起,此功能被屏蔽,有需要的朋友可以联系作者
		     姓名: 汪建林 生日:1985年1月7日(农历)
		     星座: 双鱼座(故名双鱼林) 血型: 0
		     毕业学校: 2007年于成都理工大学
		     家乡地址: 四川渠县望溪乡包山村5组
		     联系QQ: 287307421 联系电话: 13558690869
		     联系Email: wangjianlin1985@126.com
		     双鱼林电脑工作室淘宝店: http://shop34864101.taobao.com
		     双鱼林电脑工作室拍拍店: http://287307421.paipai.com
	          如果朋友们觉得我的设计还看得,可以联系我设计,我熟ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver提供技术指导!
	       */ 
		} else if(action.equals("finishSell")) { /*完成销售*/
			/*
		     对不起,此功能被屏蔽,有需要的朋友可以联系作者
		     姓名: 汪建林 生日:1985年1月7日(农历)
		     星座: 双鱼座(故名双鱼林) 血型: 0
		     毕业学校: 2007年于成都理工大学
		     家乡地址: 四川渠县望溪乡包山村5组
		     联系QQ: 287307421 联系电话: 13558690869
		     联系Email: wangjianlin1985@126.com
		     双鱼林电脑工作室淘宝店: http://shop34864101.taobao.com
		     双鱼林电脑工作室拍拍店: http://287307421.paipai.com
	          如果朋友们觉得我的设计还看得,可以联系我设计,我熟ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver提供技术指导!
	       */ 
		}
	}

}
