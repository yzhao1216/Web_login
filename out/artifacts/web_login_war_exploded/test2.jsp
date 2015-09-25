<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<html>
    <head></head>
    <body>
        hello
        <img src="checkcode" border="1" id="img1">
        <a href="javascript:;" onclick="document.getElementById('img1').src='checkcode?'+Math.random();">换一个</a>
    </body>
</html>