package sevlet_study.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/invalidateSession.do")
public class L12InvalidateSession extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션을 만료해서 삭제하기
		HttpSession session = req.getSession();
		session.invalidate();  // 접속하고 30분이 되지 않았지만 만료시키기
		// 세션은 바로 삭제되지 않고 응답이 완료되어야 만료시간이 지정되면서 삭제된다.
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.append("<h1>세션 만료 성공</h1>");
		out.append("<h2>세션아이디 : "+session.getId()+"</h2>");
		out.append("<h2><a href='getSession.do'>세션객체 불러오기</a></h2>");
		
	}
}
