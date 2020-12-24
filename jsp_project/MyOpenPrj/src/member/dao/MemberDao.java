package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.Member;

public class MemberDao {
	
	// 싱글톤 패턴
	private MemberDao() {}
	
	private static MemberDao dao = new MemberDao();
	
	public static MemberDao getInstance() {
		return dao;
	}
	
	// Member 테이블의 Data를 CRUD
	
	// 데이터 입력
	public int insertMember(Connection conn,
							Member member) {
		int resultCnt = 0;
		
		PreparedStatement pstmt = null;
		
		String sqlInsert = "INSERT INTO member (memberid, password, membername) VALUES (?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getUserName());
			
			resultCnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultCnt;
	}
	

							

}
