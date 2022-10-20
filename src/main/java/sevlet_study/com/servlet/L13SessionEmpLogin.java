package sevlet_study.com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sevlet_study.com.dao.ScottDBConn;
import sevlet_study.com.dto.EmpDto;

@WebServlet("/sessionEmpLogin.do")
public class L13SessionEmpLogin extends HttpServlet{
	public String sql = "SELECT * FROM EMP WHERE empno=? AND ename=?";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String ename = req.getParameter("ename");
		String empno_str = req.getParameter("empno");
		EmpDto emp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// DB 접속후 ename과 empno 가 동일한 사원이 있으면 로그인 성공, 없으면 실패
		
		try {
			conn = ScottDBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(empno_str));
			pstmt.setString(2, ename);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				emp=new EmpDto();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setComm((Float)rs.getObject("Comm"));
				emp.setSal((Float)rs.getObject("sal"));
				emp.setMgr((Integer)rs.getObject("mgr"));
				emp.setDeptno((Integer)rs.getObject("deptno"));
				emp.setHiredate(rs.getDate("hiredate"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(emp);
		HttpSession session = req.getSession();
		if(emp!=null) {
			// 로그인 성공 : 세션에 emp 객체 저장하고 성공 페이지로 이동
			System.out.println("로그인 성공");
			session.setAttribute("loginEmp", emp);
			resp.sendRedirect("L13EmpLoginSuccess.jsp");
		}else {
			// 로그인 실패 : 실패 메세지와 로그인 폼 페이지로 이동
			System.out.println("로그인 실패");
			session.removeAttribute("loginEmp");
			resp.sendRedirect("L13EmpLoginFailed.jsp");
		}
	}
}
