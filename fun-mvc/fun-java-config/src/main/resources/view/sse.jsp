<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SSE</title>
</head>
<body>
<h1>SSE</h1>
<div id="msgFromPush" ></div>
<script type="text/javascript" src="../assert/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    if(!!window.EventSource){
        console.log("start");
        var source=new EventSource("push");
        s="";
        source.addEventListener("message", function(e){
            console.log(e);
            s+=e.data+"<br/>";
            $("#msgFromPush").html(s);
        });
        source.addEventListener("open", function(e){
            console.log("链接打开")
        });
        source.addEventListener("error", function(e){
            if(e.readyState == EventSource.CLOSED){
                console.log("链接关闭")
            }else{
                console.log(JSON.stringify(e));
                console.log(e.readyState);
            }
        },false);
    }else{
        console.log("浏览器不支持sse")
    }


</script>

</body>
</html>