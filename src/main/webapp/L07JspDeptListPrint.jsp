<%@page import="sevlet_study.com.dto.DeptDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jspDeptList.do과 포워드한 페이지 (L07JspDeptListPrint.jsp)</h1>
	<p>서블릿에서 특정페이지를 포워드하면 요청이 종료되지 않은 상태로 다른페이지를 호출하기 때문에 url이 바뀌지 않는다. (두 페이지가 한페이지 처럼 동작)</p>
	<%-- <%=request.getAttribute("deptList") %> --%>
	<p><a href="L08DeptInsertForm.jsp">부서 등록 폼</a></p>
	<table>
  		<tr><th>부서번호</th><th>부서이름</th><th>부서위치</th></tr>
	<%
		Object deptList_obj=request.getAttribute("deptList");
		if(deptList_obj!=null){
			ArrayList<DeptDto> deptList=(ArrayList<DeptDto>)deptList_obj;			
			for(DeptDto dept: deptList){
		%>
				<tr>
					<td><%=dept.getDeptno()%></td>
					<td><%=dept.getDname()%></td>
					<td><%=dept.getLoc()%></td>				
				</tr>
		<%
			}
		}
		
	%>
	</table>
</body>
</html>