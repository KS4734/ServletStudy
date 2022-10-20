package sevlet_study.com.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sevlet_study.com.dto.EmpDto;


@WebServlet("/jdbcEmpList2.do")
public class L03JdbcEmpListFindDept extends HttpServlet { 
	private static String url="jdbc:mysql://localhost:3306/scott";
	private static String user="root";
	private static String pw="mysql";	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터==path이기 때문에 servlet을 생성하면서 파라미터 정의한다.
		
		resp.setContentType("text/html; charset=UTF-8");
		String deptno_str=req.getParameter("deptno");
		StringBuffer sb=new StringBuffer();
		sb.append("<h1>"+deptno_str+"부서 사원 리스트</h1>");//문자열 더하기 연산과 같다.
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM EMP WHERE deptno=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, pw);
			pstmt=conn.prepareStatement(sql);
			int deptno=Integer.parseInt(deptno_str); //null "11dd"
			pstmt.setInt(1, deptno);
			rs=pstmt.executeQuery();
			List<EmpDto> empList=new ArrayList<EmpDto>();
			while(rs.next()) {
				EmpDto emp=new EmpDto();
				emp.setEmpno(rs.getInt("EMPNO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setJob(rs.getString("JOB"));
				emp.setMgr(rs.getInt("MGR"));
				emp.setHiredate(rs.getDate("HIREDATE"));
				emp.setSal(rs.getFloat("SAL"));
				emp.setComm(rs.getFloat("COMM"));
				emp.setDeptno(rs.getInt("DEPTNO"));
				empList.add(emp);
			}
			sb.append("<table border='1'>");
			sb.append("<tr><th>사번</th><th>이름</th><th>직책</th><th>상사</th><th>입사일</th><th>급여</th><th>커미션</th><th>부서번호</th></tr>");
			empList.stream().forEach((e)->{
				sb.append("<tr>");
				sb.append("<td>"+e.getEmpno()+"</td>");
				sb.append("<td>"+e.getEname()+"</td>");
				sb.append("<td>"+e.getJob()+"</td>");
				sb.append("<td>"+e.getMgr()+"</td>");
				sb.append("<td>"+e.getHiredate()+"</td>");
				sb.append("<td>"+e.getSal()+"</td>");
				sb.append("<td>"+e.getComm()+"</td>");
				sb.append("<td>"+e.getDeptno()+"</td>");
				sb.append("</tr>");
			});
			sb.append("</table>");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		resp.getWriter().write(sb.toString());
	}
}