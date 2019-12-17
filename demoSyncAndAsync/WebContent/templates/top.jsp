<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.idealful.util.CommonUtil"%>
<%
	request.getSession().setAttribute("contextPath", CommonUtil.CONTEXT_PATH);
	String contextPath = request.getSession().getAttribute("contextPath").toString();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>top</title>
</head>
<body>
	<script type="text/javascript" src="${contextPath}/static/js/service.js"></script>
	<script type="text/javascript">
		contextPath = '${contextPath}';
	</script>
</body>
</html>