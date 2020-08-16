<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 입출력 테스트</title>
</head>
<body>


<div id = "file form">
	<form method="post" enctype="multipart/form-data" action="/file/upload">
	<input type="file" name="files" id="sourceFile">
	<input type="submit">
	</form>
</div>

</body>
</html>