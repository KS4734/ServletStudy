<%@page import="sevlet_study.com.dto.EmpDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//HttpSession session = request.getSession(); // out객체처럼 미리 구현되어 있음
Object loginEmp_obj = session.getAttribute("loginEmp");
if(loginEmp_obj != null){
	EmpDto loginEmp = (EmpDto)loginEmp_obj;
%>	
	<h1>로그인 성공</h1>
	<h2><%=loginEmp.getEname() %>님 로그인 중</h2>
	<ul>
		<li>사번 : <%=loginEmp.getEname() %></li>
		<li>직책 : <%=loginEmp.getJob() %></li>
		<li>급여 : <%=loginEmp.getSal() %></li>
		<li>커미션 : <%=loginEmp.getComm() %></li>
		<li>직속상사 : <%=loginEmp.getMgr() %></li>
		<li>부서번호 : <%=loginEmp.getDeptno() %></li>
	</ul>
	
<%}else{
	response.sendRedirect("");
%>	
<h1>잘못된 접근 (로그인 성공시 접근 가능)</h1>
<%} %>

</body>
</html>