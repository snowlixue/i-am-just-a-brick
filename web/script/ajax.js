var XMLHttpReq;
 //创建XMLHttpRequest对象     
 function createXMLHttpRequest() {
      if(window.XMLHttpRequest) { //Mozilla 浏览器
       XMLHttpReq = new XMLHttpRequest();
      }
      else if (window.ActiveXObject) { // IE浏览器
       try {
        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
       } catch (e) {
        try {
          XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {}
       }
      }
 }
 //发送请求函数
 function sendRequest(goodNo) {
  createXMLHttpRequest();
  var url = "GetGoodInfo?goodNo=" + goodNo;
  XMLHttpReq.open("GET", url, true);
  XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
  XMLHttpReq.send(null);  // 发送请求
 }
 // 处理返回信息函数
    function processResponse() {
     if (XMLHttpReq.readyState == 4) { // 判断对象状态
         if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
          DisplayGoodInfo();
          //setTimeout("sendRequest()", 1000);
         } else { //页面不正常
            window.alert("您所请求的页面有异常。");
           }
         }
    }
    function DisplayGoodInfo() {
    
     var GoodName = XMLHttpReq.responseXML.getElementsByTagName("GoodName")[0].firstChild.nodeValue;
     var GoodModel = XMLHttpReq.responseXML.getElementsByTagName("GoodModel")[0].firstChild.nodeValue;
     var GoodSpecs = XMLHttpReq.responseXML.getElementsByTagName("GoodSpecs")[0].firstChild.nodeValue;
     var GoodPlace = XMLHttpReq.responseXML.getElementsByTagName("GoodPlace")[0].firstChild.nodeValue;
     
     document.getElementById("GoodName").innerHTML = GoodName;
     document.getElementById("GoodModel").innerHTML = GoodModel;
     document.getElementById("GoodSpecs").innerHTML = GoodSpecs;
     document.getElementById("GoodPlace").innerHTML = GoodPlace;
     document.getElementById("GoodInfo").style.display = "";
   }
