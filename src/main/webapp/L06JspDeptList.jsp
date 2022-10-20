<%@page import="sevlet_study.com.dao.ScottDBConn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! // 전역변수 선언 법
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static String sql="SELECT * FROM DEPT";
%>
<html>
<head>
<meta charset="UTF-8">
<title>dept List</title>
</head>
<body>
	<h1>JAVA Servlet VS JAVA Servlet Page</h1>
	<h2>jsp 단점과 문제점</h2>
	<ul>
		<li>정적페이지에 있는 동적 페이지가 db를 접속하는 것은 위험하다.</li>
		<li>보통 정적페이지 경로는 오픈되어 있어 업로드가 자유롭고 정적페이지의 리소스를 다운로드할 수 있다.</li>
		<li>jsp에 java코드가 너무 많으면 프론트앤드 개발 시 오류를 생성할 수 있다.</li>
	</ul>
	<table>
		<thead>
			<tr><th>부서 번호</th><th>부서 이름</th><th>부서 위치</th></tr>
		</thead>
	<% 
		try {
			conn=ScottDBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
	%>
		<tr>
			<td><%=rs.getInt("deptno")%></td>
			<td><%=rs.getString("dname")%></td>
			<td><%=rs.getString("loc")%></td>
		</tr>
	<%
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			}catch (Exception e2){
				e2.printStackTrace();
			}
		}
	%>
	</table>
</body>
</html>