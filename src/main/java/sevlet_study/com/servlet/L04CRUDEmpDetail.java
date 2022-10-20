package sevlet_study.com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sevlet_study.com.dao.ScottDBConn;
import sevlet_study.com.dto.EmpDto;
@WebServlet("/crudEmpDetail.do")
public class L04CRUDEmpDetail extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empno_str=req.getParameter("empno");
		
		String sql="SELECT * FROM EMP WHERE empno=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		EmpDto emp=null;
		try {
			conn=ScottDBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(empno_str));
			rs=pstmt.executeQuery();
			if (rs.next()) {
				emp = new EmpDto();
				emp.setEmpno(rs.getInt("empno"));
				emp.setDeptno((Integer)rs.getObject("deptno"));
				emp.setMgr((Integer)rs.getObject("mgr"));
				
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				
				emp.setSal((Float)rs.getObject("sal"));
				emp.setComm((Float)rs.getObject("comm"));
				emp.setHiredate(rs.getDate("hiredate"));
			}
		} catch (Exception e) {
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
		System.out.println("EmpDetail.do");
		// 예외 처리와 인터페이스
		if (emp!=null) {  // 통신에러가 없고, 파라미터가 정상적, 검색과 동시에 삭제되지 않은 데이터
			resp.setContentType("text/html; charset=UTF-8");
			StringBuffer sb=new StringBuffer();
			sb.append("<html>");
			sb.append("<h1>사원("+empno_str+")상세,수정 페이지</h1>");
			sb.append("<form action='crudEmpUpdate.do' method='post'>");
			sb.append("<p>사번 : <input name='empno' readonly value='"+emp.getEmpno()+"'></p>");
			sb.append("<p>이름 : <input name='ename' value='"+emp.getEname()+"'></p>");
			sb.append("<p>급여 : <input name='sal' value='"+emp.getSal()+"'></p>");
			sb.append("<p>커미션 : <input name='comm' value='"+(emp.getComm()!=null?emp.getComm():"")+"'></p>");
			sb.append("<p>직책 : <input name='job' value='"+emp.getJob()+"'></p>");
			sb.append("<p>상사 : <input name='mgr' value='"+(emp.getMgr()!=null?emp.getMgr():"")+"'></p>");
			sb.append("<p>부서 : <input name='deptno' value='"+(emp.getDeptno()!=null?emp.getDeptno():"")+"'></p>");
			sb.append("<p>입사일 : <input name='hiredate' readonly value='"+emp.getHiredate()+"'></p>");
			sb.append("<p><button>제출</button>&nbsp;&nbsp;");
			sb.append("&nbsp;<a href='crudEmpDelete.do?empno="+emp.getEmpno()+"'>삭제</a></p>");
			sb.append("</form>");
			sb.append("</html>");
			resp.getWriter().append(sb.toString());
		}else {  // 통신에러, 파라미터가 잘못됨, 검색과 동시에 삭제됨
			resp.sendRedirect("crudEmpList.do");
		}
	}
}
