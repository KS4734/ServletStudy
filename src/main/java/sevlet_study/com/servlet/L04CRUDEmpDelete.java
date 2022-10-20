package sevlet_study.com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sevlet_study.com.dao.ScottDBConn;

@WebServlet("/crudEmpDelete.do")
public class L04CRUDEmpDelete extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empno_str=req.getParameter("empno");
		System.out.println(empno_str);
		String redirectPage="crudEmpList.do";
		String errorPage="crudEmpDelete.do?empno="+empno_str;
		String sql="DELETE FROM EMP WHERE empno=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int delete=0;
		
		try {
			conn=ScottDBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(empno_str));
			delete=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(delete>0) { 
			resp.sendRedirect(redirectPage);
		} else {
			resp.sendRedirect(errorPage);
		}
	}
}
