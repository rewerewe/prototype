<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>Now</title>
</head>
<body>
	<%@ include file="/templates/top.jsp"%>
	<div id="result">${result}</div>
	<script type="text/javascript">
		function reqNowServiceSync() {
			location.href = contextPath + '/service/NowServiceSync.jsp';
		}
	
		function init() {
			setInterval( () => { reqNowServiceSync(); }, 1000 * 1);
		}

		init();
	</script>
	<%@ include file="/templates/bottom.jsp"%>
</body>
</html>