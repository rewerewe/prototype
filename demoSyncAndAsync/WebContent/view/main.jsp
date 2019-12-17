<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>Main</title>
</head>
<body>
	<%@ include file="/templates/top.jsp"%>
	<div>
		<button type="button" id="btnSync">synchronization</button>
		<button type="button" id="btnAsync">asynchronization</button>
	</div>
	<script type="text/javascript">
		const BTN_SYNCHRONIZATION = document.querySelector('#btnSync');
		const BTN_ASYNCHRONIZATION = document.querySelector('#btnAsync');
	
		function reqSync() {
			// location.href = '${contextPath}/view/nowSync.jsp';
			reqByForm('${contextPath}/view/nowSync.jsp', 'get', '_blank');
		}
		
		function reqAsync() {
			// location.href = '${contextPath}/view/nowAsync.jsp';
			reqByForm('${contextPath}/view/nowAsync.jsp', 'get', '_blank');
		}

		function init() {
			BTN_SYNCHRONIZATION.addEventListener('click', reqSync);
			BTN_ASYNCHRONIZATION.addEventListener('click', reqAsync);
		}

		init();
	</script>
	<%@ include file="/templates/bottom.jsp"%>
</body>
</html>