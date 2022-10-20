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
import sevlet_study.com.dto.DeptDto;

@WebServlet("/jspDeptInsert.do")
public class L08JspDeptInsert extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String deptno_str=req.getParameter("deptno");
		String dname=req.getParameter("dname");
		String loc=req.getParameter("loc");
		Connection conn=null;
		PreparedStatement pstmt=null;
		int insert=0;
		String sql="INSERT INTO dept (deptno, dname, loc) "
				+ "VALUES (?,?,?)";
		try {
			DeptDto dept=new DeptDto();
			dept.setDeptno(Integer.parseInt(deptno_str));
			dept.setDname(dname);
			dept.setLoc(loc);
			System.out.println(dept);
			conn=ScottDBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());
			insert=pstmt.executeUpdate();
			System.out.println("등록성공 :"+insert);
			resp.getWriter().append("insert :"+insert);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("등록 실패");
		}
		if(insert>0) {
			resp.sendRedirect("jspDeptList.do");			
		}else {
			resp.sendRedirect("L08DeptInsertForm.jsp");			
		}
	}
}
