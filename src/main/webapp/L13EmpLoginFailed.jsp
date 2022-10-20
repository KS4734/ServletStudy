<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 실패 페이지</h1>
	<h2>사번이나 사원이름을 확인하세요!</h2>	
	<form action="sessionEmpLogin.do" method="post">
		<p>
			<label>
				이름 : 
				<input name="ename" type="text" value="king"> 
			</label>
		</p>
		<p>
			<label>
				사번 :
				<input name="empno" type="password" value="7839"> 
			</label>
		</p>
		<p>
			<button>로그인</button>
		</p>
	</form>

</body>
</html>