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
		function reqNowServiceAsync() {
			const RESULT = document.querySelector('#result');
			const url = contextPath + '/service/NowServiceAsync.jsp';
			
			fetch(url, {
				method: 'get'
			})
			.then(response => {
				if(response.ok) {
					return response.json();
				} else {
					console.error('response is not ok');
				}
			})
			.then(result => {
				RESULT.innerText = result;
			})
			.catch(response => {
				console.error(response);
			})
			;
		}

		function init() {
			reqNowServiceAsync();
			setInterval( () => { reqNowServiceAsync(); }, 1000 * 1);
		}

		init();
	</script>
	<%@ include file="/templates/bottom.jsp"%>
</body>
</html>