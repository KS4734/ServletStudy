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
import sevlet_study.com.dto.EmpDto;



@WebServlet("/crudEmpInsert.do") // "/" 없거나 , 중복된 이름이 있으면 서버 오류!
public class L04CRUDEmpInsert extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String empno_str=req.getParameter("empno");
		String ename=req.getParameter("ename");
		String job=req.getParameter("job");
		String mgr_str=req.getParameter("mgr");
		String sal_str=req.getParameter("sal");
		String comm_str=req.getParameter("comm");
		String deptno_str=req.getParameter("deptno");
		Connection conn=null;
		PreparedStatement pstmt=null;
		int insert=0; //dml은 실행하면 성공한 수가 반환
		String sql="INSERT INTO emp (empno,ename,job,mgr,sal,comm,deptno,hiredate) "
							+ "VALUES (?,?,?,?,?,?,?,now())";
		try {
			EmpDto emp=new EmpDto();
			emp.setEmpno(Integer.parseInt(empno_str));
			emp.setEname(ename);
			emp.setJob(job);
			emp.setMgr(Integer.parseInt(mgr_str));
			emp.setSal(Float.parseFloat(sal_str));
			emp.setComm(Float.parseFloat(comm_str));
			emp.setDeptno(Integer.parseInt(deptno_str));
			System.out.println(emp);
			conn=ScottDBConn.getConn();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setFloat(5, emp.getSal());
			pstmt.setFloat(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptno());
			insert=pstmt.executeUpdate();//dml 실행하는 함수
			System.out.println("등록성공 :"+insert);
			resp.getWriter().append("insert :"+insert);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("등록 실패");
		}
		//data 를 처리하는 Post 페이지는 보여지는 리소스가 없이 다른 페이지로 이동하는 경향이 있다. 
		//성공시 empList 로 이동
		//실패시 form 으로 이동
		if(insert>0) {
			resp.sendRedirect("crudEmpList.do");			
		}else {
			resp.sendRedirect("L04CRUDCreateForm.html");			
		}
		
	}
}