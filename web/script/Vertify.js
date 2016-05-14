  function checkEmployeeAddForm(empForm) {
       if(empForm.employeeNo.value == "") {
         alert('员工编号输入不能为空!');
         empForm.employeeNo.focus();
         return false;
       }
       if(empForm.employeeName.value == "") {
         alert('员工姓名输入不能为空!');
         empForm.employeeName.focus();
         return false;
       }
       if(empForm.employeeBirthday.value == "") {
         alert('请选择员工的生日!');
         empForm.employeeBirthday.focus();
         return false;
       }
       
       /*验证家庭电话*/
       var employeeHomeTel=empForm.employeeHomeTel.value;
       if(employeeHomeTel != "") {
         var p1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
         var me = false;
         if (p1.test(employeeHomeTel))me=true;
         if (!me){
           empForm.employeeHomeTel.value='';
           alert('对不起，您输入的电话号码有错误。区号和电话号码之间请用-分割');
           empForm.employeeHomeTel.focus();
           return false;
         }
       }
       
       /*验证移动电话*/
       var employeeMobile = empForm.employeeMobile.value;
       if(employeeMobile != "") {
         var reg0 = /^13\d{5,9}$/;
         var reg1 = /^153\d{4,8}$/;
         var reg2 = /^159\d{4,8}$/;
         var reg3 = /^0\d{10,11}$/;
         var my = false;
         if (reg0.test(employeeMobile))my=true;
         if (reg1.test(employeeMobile))my=true;
         if (reg2.test(employeeMobile))my=true;
         if (reg3.test(employeeMobile))my=true;
         if (!my){
             empForm.employeeMobile.value='';
             alert('对不起，您输入的手机或小灵通号码有错误。');
             empForm.employeeMobile.focus();
             return false;
         }
       }
       
       /*验证邮箱地址*/
       var employeeEmail = empForm.employeeEmail.value;
       if(employeeEmail != "") {
         if(!(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(employeeEmail))) {
           empForm.employeeEmail.select();
           empForm.employeeEmail.focus();
           alert('你输入的Email不正确!');
           return false;
         }
       }
       
        /*验证身份证件*/
        var employeeCard = empForm.employeeCard.value; 
   	    var reg = /^([0-9]{15}|[0-9]{18})$/; 
        if(!reg.test(employeeCard)) {
          empForm.employeeCard.select();
          empForm.employeeCard.focus();
          alert('身份证信息输入不正确!');
          return false;
        }
       
       return true;
 }
 
 
 
 /*验证进货信息的form*/
function checkBuyInfoAddForm(buyInfoAddForm) {
    var Price = buyInfoAddForm.Price.value;
    var Number = buyInfoAddForm.Number.value;
    var BuyDate = buyInfoAddForm.BuyDate.value;
    if(Price == "") {
    	alert("价格不能为空!");
    	buyInfoAddForm.Price.focus();
    	return false;
    }
    if(Number=="") {
    	alert("进货数量不能为空!");
    	buyInfoAddForm.Number.focus();
    	return false;
    }
    if(BuyDate=="") {
    	alert("请选择进货日期!");
    	return false;
    }

    /*判断价格(正浮点数)的正则表达式
    //var Price_Reg = /^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	//var Price_Reg = /^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
	var Price_Reg = /[^0123456789.]{1,}/;
	//var Number_Reg = /^[0-9]*[1-9][0-9]*$/; //判断数量(正整数)的正则表达式
	var Number_Reg = /^[1-9]\d*$/ 
	if(!Price_Reg.test(Price)) {
	   alert("请输入正确的进货价格!");
	   buyInfoAddForm.Price.select();
	   buyInfoAddForm.Price.focus();
	   return false;
	}
	if(!Number_Reg.test(Number)) {
	   alert("请输入正确的进货数量!");
	   buyInfoAddForm.Number.select();
	   buyInfoAddForm.Number.focus();
	   return false;
	}
	Price = parseFloat(Price);
	Number = parsetInt(Number);
	var TotalPrice = Price * Number;
	buyInfoAddForm.TotalPrice.value = TotalPrice;*/
	return true;
}

/*计算机总的价格*/
function CalculateTotalPrice() {
	var Price = buyInfoAddForm.Price.value;
    var Number = buyInfoAddForm.Number.value;
    /*
    /*判断价格(正浮点数)的正则表达式
   //var Price_Reg = /^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	//var Price_Reg = /^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
	var Price_Reg = /[^0123456789.]{1,}/;
	//var Number_Reg = /^[0-9]*[1-9][0-9]*$/; //判断数量(正整数)的正则表达式
	var Number_Reg = /^[1-9]\d*$/ 
	if(!Price_Reg.test(Price)) {
	   alert("请输入正确的进货价格!");
	   buyInfoAddForm.Price.select();
	   buyInfoAddForm.Price.focus();
	   return false;
	}
	if(!Number_Reg.test(Number)) {
	   alert("请输入正确的进货数量!");
	   buyInfoAddForm.Number.select();
	   buyInfoAddForm.Number.focus();
	   return false;
	}*/
	//Price = parseFloat(Price);
	//Number = parsetInt(Number);
	var TotalPrice = Price * Number;
	buyInfoAddForm.TotalPrice.value = TotalPrice;
}

 function checkPrice(obj)    //验证输入的商品价格
 {   
 	pta=/[^0123456789.]{1,}/;   
 	if(pta.exec(obj))
 		document.buyInfoAddForm.Price.value=obj.substr(0,obj.length-1);   
 }  
  
  