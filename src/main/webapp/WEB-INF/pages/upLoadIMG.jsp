<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>图片上传</title>

</head>

<body bgcolor="#DC8349  ">
	<div align="center">

		<h3>文件上传</h3>
		
		<font size="10" color="red">
		
		<%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %>
		
		</font>
		
		<form enctype="multipart/form-data" method="post"
			action="/user/upload.do">
			
			文件描述：<input type="text" name="text" /><br /> 
			
			选择文件：<input type="file"name="file1" /><br /> 
			
			选择文件：<input type="file" name="file2" /><br />
			
			<input type="submit" value="上传" />
			
		</form>

		<br /> <a href="/user/toUserCenter.do">》》》回到首頁》》》</a>
		
	</div>

</body>


</html>