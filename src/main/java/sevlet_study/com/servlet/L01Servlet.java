package sevlet_study.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@ 어노테이션: 컴파일될 때 동작
@WebServlet("/servlet.do") //동적 리소스의 주소를 지정하는 어노테이션
public class L01Servlet extends HttpServlet{
//브라우저가 Url 요청하는 방식을 GET이라 부른다.
//GET 방식의 요청을 처리하기 위해서는 HttpServlet.doGet(req,resp)를 재정의 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//HttpServletRequest req : url과 같은 요청정보가 포함됨 
		//HttpServletResponse resp : 응답할 내역을 정의하면 톰캣이 응답해줌
		System.out.println(req.getRequestURL());
		// 파라미터 : 동적 리소스 (servlet)의 내용을 바꾸기
		// url : 서버주소+path  naver.com(서버주소)/shopping?page=1&cate=식품(path)
		// path(리소스의 위치) : 리소스+파라미터 shopping(리소스)?page=1&cate=식품(파라미터)
		String a_Str=req.getParameter("a");
		String b_Str=req.getParameter("b");
		String c_Str=req.getParameter("c");
		int a=Integer.parseInt(a_Str);
		int b=Integer.parseInt(b_Str);
		int c=Integer.parseInt(c_Str);
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().append("<h1>hello servlet.do.doGet()</h1>")
		.append("<h2>입력받은 파라미터 목록</h2>")
		.append("<h3>get 방식 통신은 리소스를 요청하는 것으로 파라미터가 path에 포함 되어있다. (동적리소스는 파라미터에 의해 내용이 바뀜)</h3>")
		.append("<h4>url에 길이 제한이 있어서 파라미터의 길이나 양에 제한이 있다.</h4>")
		.append("<h4>파라미터가 포함된 url을 공유하면 같은 리소스를 참조할 수 있다.</h4>")
		.append("<h4>get방식이 post 보다 통신 속도가 빠르다.</h4>")
		
		.append("<ul>")
		.append("<li>a:"+a_Str+"</li>")
		.append("<li>b:"+b_Str+"</li>")
		.append("<li>c:"+c_Str+"</li>")
		.append("<li>a+b+c:"+(a+b+c)+"</li>")
		.append("<li>a*b*c:"+(a*b*c)+"</li>")
		.append("</ul>");
		//dbserver에 접속하고 그 내역을 table로 출력
	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html; charset=UTF-8");
			int a=Integer.parseInt(req.getParameter("a"));
			PrintWriter out=resp.getWriter();
			out.write("<h1>Servlet.do.doPost()</h1>");
			out.write("<h2>입력받은 파라미터 목록</h2>");
			out.write("<h3>post 방식의 통신은 파라미터를 path에 포함하지 않는다.</h3>");
			out.write("<h3>post 방식의 통신은 data를 처리하기 위해 존재하기 때문에 파라미터가 많고 바뀌기 쉽다.</h3>");
			
			out.write("<ul>");
			out.write("<li>a:"+a+"</li>");
			out.write("</ul>");
			// a+b+c 를 출력
		}
}
