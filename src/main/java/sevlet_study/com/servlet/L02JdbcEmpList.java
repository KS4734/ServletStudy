package sevlet_study.com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.mysql.cj.jdbc.Driver;
@WebServlet("/jdbcEmpList.do")
public class L02JdbcEmpList extends HttpServlet{
	private static String url = "jdbc:mysql://localhost:3306/scott";
	private static String user = "root";
	private static String pw = "mysql";
	private static String empList = "SELECT * FROM EMP";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8"); //반환하는 문서의 형식
		Connection conn = null;
		//Statement stmt = null; //접속하면 쿼리를 실행할 수 있는 객체
		PreparedStatement pstmt = null; // 쿼리에 파라미터를 준비 시킬수 있는 객체 (sqlInjection, type parsing)
		ResultSet rs = null; // 자료구조(Map.key(칼럼) = set
		
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection(url,user,pw);
				pstmt=conn.prepareStatement(empList);
				rs = pstmt.executeQuery();
				//문자열 + 는 매번 문자열 객체를 만들고 + 연산을 한 결과물을 만들기 때문에 메모리 낭비가 심하다.
				StringBuffer sb = new StringBuffer();//문자열 객체에 버퍼(임시저장공간)를 만들고 문자열을 더하는 구조 
				//StringBuild
				
				String html="<table border='1'>";
				html+="<tr><th>사번</th><th>이름</th><th>직책</th><th>상사</th><th>입사일</th><th>급여</th><th>커미션</th><th>부서번호</th></tr>";
				while(rs.next()) {
					html +="<tr>";
					int empno = rs.getInt(1);
					String ename = rs.getString(2);
					String job = rs.getString(3);
					int mgr = rs.getInt(4);
					String hiredate = rs.getString(5);
					float sal = rs.getFloat(6);
					float comm = rs.getFloat(7);
					int deptno = rs.getInt(8);
					
					html+="<td>"+empno+"</td>";
					html+="<td>"+ename+"</td>";
					html+="<td>"+job+"</td>";
					html+="<td>"+mgr+"</td>";
					html+="<td>"+hiredate+"</td>";
					html+="<td>"+sal+"</td>";
					html+="<td>"+comm+"</td>";
					html+="<td>"+deptno+"</td>";
					html +="</tr>";
				}
				html+="</table>";
				resp.getWriter().write(html);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		//dto 작성
		//emp_dept table 출력
		//사번으로 검색 페이지 작성
	}
}
