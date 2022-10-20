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

import sevlet_study.com.dao.ScottDBConn;
import sevlet_study.com.dto.DeptDto;

@WebServlet("/deptDetailJson.do")
public class L08DeptDetailJSON extends HttpServlet{
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static String sql="SELECT * FROM DEPT WHERE deptno=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptno_str=req.getParameter("deptno");
		DeptDto dept=null;
		try {
			conn=ScottDBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(deptno_str));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dept=new DeptDto();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("deptno"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//detp가 있는지 검사 { "isDept" : true or false, dept : {deptno:1111,...} }
		String json="";
		if(dept==null) {
			json="{\"isDept\" : false }";
		}else {
			json="{\"isDept\" : true,"+dept.toString()+" }";

		}
		resp.setContentType("application/json;charset=UTF-8");
		resp.getWriter().write(json);
	}
}
