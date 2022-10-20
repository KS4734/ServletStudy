package sevlet_study.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/removeSession.do")
public class L11RemoveSession extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션은 그대로고 저장된 객체만 삭제
		HttpSession session = req.getSession();
		session.removeAttribute("loginId");
		session.removeAttribute("loginName");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.append("<h1>세션객체 삭제 성공</h1>");
		out.append("<h2>세션아이디 : "+session.getId()+"</h2>");
		out.append("<h2><a href='getSession.do'>세션객체 불러오기</a></h2>");
		
	}
}
