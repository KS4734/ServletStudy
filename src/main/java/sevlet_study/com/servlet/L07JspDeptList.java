package sevlet_study.com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sevlet_study.com.dao.ScottDBConn;
import sevlet_study.com.dto.DeptDto;

@WebServlet("/jspDeptList.do")
public class L07JspDeptList extends HttpServlet{
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private String sql="SELECT * FROM DEPT";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//db만 접속하고 jsp에 출력을 위임
		String forwardPage="L07JspDeptListPrint.jsp";		
		List<DeptDto> deptList=new ArrayList<DeptDto>();
		try {
			conn=ScottDBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				DeptDto dept=new DeptDto();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				deptList.add(dept);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(deptList);
		req.setAttribute("deptList", deptList); 
		//요청 객체에 포워드할 페이지에 전달할 객체를 저장
		//setvlet과 forward 되는 페이지는 하나처럼 동작한다.
		req.getRequestDispatcher(forwardPage).forward(req, resp);
	
		
	}
}
