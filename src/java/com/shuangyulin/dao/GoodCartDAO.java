package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.GoodCart;
import com.shuangyulin.javabean.SellInfo;

/*关于购物车的业务处理*/
public class GoodCartDAO {
	private String errMessage;

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
	/*根据员工编号返回该员工对应的购物车信息，一个购物车服务于一个顾客对象*/
	public static ArrayList<GoodCart> GetGoodCartByEmployeeNo(String employeeNo) {
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
		return null;
	}
	

    /*根据商品购物车记录编号和要修改的销售数目执行更新操作*/
    public boolean UpdateGoodCartInfo(int goodCartId, int goodCount)
    {
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
        return true;
    }
    /*根据商品销售购物车编号实现该记录的删除操作*/
    public boolean DeleteGoodCartInfo(int goodCartId)
    {
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
        return true;
    }
    /*传入商品销售购物车信息模型对象，将商品销售信息加入到系统中*/
    public boolean AddGoodCartInfo(GoodCart goodCart)
    {
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
        return true;
    }
    /*根据员工编号得到购物车中总的商品数量*/
    public static int GetTotalGoodCountInCart(String employeeNo)
    {
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
        return 0;
    }
    /*根据员工编号得到购物车中商品的总价格*/
    public static float GetTotalPriceInCart(String employeeNo)
    {
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
       return 0.0f;
    }
    /*根据传递过来的销售小票号和员工编号实现对应购物车中商品销售信息的登记,然后清空购物车*/
    public static boolean AddGoodSellInfoInCart(String sellNo, String employeeNo)
    {
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
        return true;
    }
}
