package sevlet_study.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/createSession.do")
public class L09HttpSession extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		System.out.println(session.getId());  // HttpSession : 서버가 
		// 로그인을 했다는 전제로 Id 객체를 저장
		String loginId="gildong";
		String loginName="홍길동";
		session.setAttribute("loginId", loginId);
		session.setAttribute("loginName", loginName);
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append("<h1>세션 객체에 아이디와 이름 저장 완료!</h1>");
		resp.getWriter().append("<h2><a href='getSession.do'>로그인 한 객체 불러오기~</a></h2>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		HttpSession session = req.getSession();
		session.setAttribute("loginId", id);
		session.setAttribute("loginName", name);
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		out.append("<h1>세션 객체에 아이디 이름 저장 완료! POST</h1>");
		out.append("<h2><a href='getSession.do'>로그인 한 객체 불러오기~</a></h2>");
		
	}
}
