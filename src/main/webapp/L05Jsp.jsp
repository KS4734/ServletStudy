<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Jsp</title>
</head>
<body>
<%
// java 문서를 작성하겠다. (doGet(request, response){내부}) 
// 만약 Post 호출을 하면 다시 doPost에서 다시 doGet을 실행
String a_str=request.getParameter("a");
String b_str=request.getParameter("b");
//out.append(a_str+b_str);  // PrintWrite out=reponse.getWriter();
%>

	<h1>html 처럼 보이는 동적 페이지</h1>
	<ul>
		<li>파라미터 a : <%out.append(a_str);%></li>
		<li>파라미터 b : <%=b_str%></li>
		<li>a + b = <%=a_str+b_str%></li>
	</ul>
	<h2>jsp 는 컴파일되지 않고 인터프리터에 의해 실행됨.</h2>
	<ul>
		<li>jsp는 doGet() 함수 내부인데 java 코드와 html이 반대로 작성됨</li>
		<li>문서이름이 setvlet 경로가 된다. (서블릿 추가시 톰캣을 재시작할 필요가 없다.)</li>
		<li>컴파일되지 않기 때문에 수정하면 바로 적용된다. (컴파일 오류를 이클립스가 알려준다.)</li>		
		<li>jsp내부에서 java를 작성하려면 JSP Scriptlet Tag를 작성한다. &lt;% %&gt;</li>
		<li>매개변수로 HttpServeltRequest request와 HttpServeltRequest response가 있다.</li>
		<li>PrintWrite out=reponse.getWriter()가 구현되어 있다. %= % 문법으로 append를 작성할 수 았다.</li>
		<li>java에서 html로 출력하려면 &lt;% out.append(10+20); %&gt;를 작성하면 되는데 JspExpression을 사용하면 간편하게 작성 할 수 있다. &lt;%=10+20%&gt;</li>
		<li>Jsp Scriptlet Tag의 주석 : &lt;%-- --%&gt;</li>		
		<li>JSP Directive : import, mata, taglib ... page에 변화를 주는 정보 정의 %@page import=%</li>
		<% Connection conn=null;%>
	</ul>	
	<h2>jsp를 사용하는 이유</h2>
	<ol>
		<li>front(html, css, js)와 back(java)를 구분하기 위해 (프론트엔드 개발자의 작업을 더 쉽게 하기 위해, 코드 재사용)</li>
		<li>스크립트 문서는 컴파일 되지 않기 때문에 작업 속도가 빠르다.</li>
		<li>view에서만 사용하는 라이브러리를 사용할 수 있게된다. taglib</li>
		
	</ol>
	<h2>**지금 Jsp를 보안 문제로 사용하지 않는다!! (정적리소스 위치에 동적리소스가 존재해서)</h2>
	
</body>
</html>