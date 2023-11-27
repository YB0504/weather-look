<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<%
	String[] address = {"지역선택","서울","인천","경기도","강원","충북","충남","대전","세종","전북","전남","광주","경북","경남","대구","울산","부산","제주"};

	request.setAttribute("address", address);
%>