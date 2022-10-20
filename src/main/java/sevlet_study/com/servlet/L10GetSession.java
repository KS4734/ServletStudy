package sevlet_study.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/getSession.do")
public class L10GetSession extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object id_str=session.getAttribute("loginId");
		Object name_str=session.getAttribute("loginName");
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append("<h1>로그인한 아이디와 이름?</h1>");
		resp.getWriter().append("<h2>loginId: "+session.getId()+"</h2>");
		resp.getWriter().append("<p>loginId: "+id_str+"</p>");
		resp.getWriter().append("<p>loginName: "+name_str+"</p>");
	}
}
