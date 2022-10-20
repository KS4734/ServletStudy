package sevlet_study.com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sevlet_study.com.dao.ScottDBConn;
import sevlet_study.com.dto.EmpDto;
@WebServlet("/crudEmpList.do")
public class L04CRUDEmpList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sql="SELECT * FROM EMP";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	try {
		conn=ScottDBConn.getConn();
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		List<EmpDto> empList=new ArrayList<EmpDto>();
		while(rs.next()) {
			EmpDto emp=new EmpDto();
			emp.setEmpno(rs.getInt("empno"));
			emp.setDeptno((Integer)rs.getObject("deptno"));
			emp.setMgr((Integer)rs.getObject("mgr"));
			
			emp.setEname(rs.getString("ename"));
			emp.setJob(rs.getString("job"));
			
			emp.setSal((Float)rs.getObject("sal"));
			emp.setComm((Float)rs.getObject("comm"));
			emp.setHiredate(rs.getDate("hiredate"));
			
			empList.add(emp);
			// depton 는 null일수 있는 데이터인데 Null->0으로 반환되는 중(오류)
			// mysql의 모든 데이터 타입은 null을 참조할 수 있다.
			// java는 참조형 데이터 타입만 null을 참조할 수 있다.
			// int a=null;
		}
		System.out.println(empList);
		resp.setContentType("text/html;charset=UTF-8");
		String a="1" + "2";  // "1", "2" 객체를 만들고 "12" 객체를 생성
		StringBuffer sb=new StringBuffer();  // 하나의 sb 객체 안에 문자열을 더하는 방식(메모리 낭비가 없고 연산속도가 빠르다.)
		sb.append("<html>");
		sb.append("<h1>CRUD Read 사원 리스트 입니다. (L04CRUDEmpList.java)</h1>");
		sb.append("<table>");
		sb.append("<tr><th>사번</th><th>이름</th><th>직첵</th><th>부서번호</th><th>급여</th><th>커미션</th></tr>");
		for (EmpDto e : empList) {
			sb.append("<tr>");
			sb.append("<td><a href='crudEmpDetail.do?empno="+e.getEmpno()+"'>"+e.getEmpno()+"</a></td>");
			sb.append("<td>"+e.getEname()+"</td>");
			sb.append("<td>"+e.getJob()+"</td>");
			sb.append("<td>"+e.getDeptno()+"</td>");
			sb.append("<td>"+e.getSal()+"</td>");
			sb.append("<td>"+e.getComm()+"</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("</html>");
		resp.getWriter().append(sb.toString());
		
	} catch (Exception e) {
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
}
}