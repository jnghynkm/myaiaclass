package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHomework {

	public static void main(String[] args) {
		
//		1.EMP 테이블에 새로운 사원 정보를 입력하는 프로그램을 작성해보자.
		
		Connection conn = null;
		
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("ORACLE DRIVER LOAD--------");
			
			// 2. DB 연결
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			
			conn = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("데이터베이스에 접속---------");
			
			
			// 3. Statement 인스턴스 생성
			Statement stmt = conn.createStatement();
			
			// 입력 
			String sqlInsert = "insert into emp values (7935,'BILL','CLERK',7566,sysdate,800,null,40)";
			
			int resultCnt = stmt.executeUpdate(sqlInsert);
			
			if(resultCnt>0) {
				System.out.println("데이터가 정상적으로 입력되었습니다.");
			}
			
			// 4. Sql 실행 : 직원 리스트 출력
			String sql = "select * from emp order by empno";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			// 5. ResultSet 을 이용해서 결과 출력
						while(rs.next()) {
							int empno= rs.getInt("empno");
							String ename = rs.getString("ename");
							String job = rs.getString("job");
							int mgr = rs.getInt("mgr");
							int hiredate = rs.getInt("hiredate");
							
							System.out.println(empno+"\t"+ename+"\t"+job+"\t"+mgr+"\t"+hiredate);
						}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver 로드 실패");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		2.EMP 테이블의 모든 데이터를 출력하는 프로그램을 작성해보자.
//		3. EMP 테이블에 서 “SCOTT” 사원의 급여(sal) 정보를 1000으로 바꾸는 프로그램을 작성해보자.
//		4.EMP 테이블에 서 “SCOTT” 이름으로 검색한 결과를 출력하는 프로그램을 작성해보자.
//		5.모든 사원정보를 출력하되 부서정보를 함께 출력하는 프로그램을 작성해보자.
		

	}

}
