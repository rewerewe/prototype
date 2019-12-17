<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.idealful.util.CommonUtil"%>
<%@ page import="com.fasterxml.jackson.*" %>
<% 
	String pattern = request.getParameter("pattern");

	String result = new CommonUtil().getDateTime(pattern);
	
	ObjectMapper objectMapper = new ObjectMapper();
	String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
	out.write(json);
%>