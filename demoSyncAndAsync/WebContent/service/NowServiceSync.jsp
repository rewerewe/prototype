<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.idealful.util.CommonUtil"%>
<% 
	String pattern = request.getParameter("pattern");

	String result = new CommonUtil().getDateTime(pattern);
	request.setAttribute("result", result);

	String path = "/view/nowSync.jsp";
	RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
	requestDispatcher.forward(request, response);
%>