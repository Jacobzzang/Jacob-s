package kinder2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class KinderDAO extends DAO{

	public String insertK(Kinder kinder) {	
		String sql = "";
		connect();
		
		try {
			
			sql = "insert into kinder_info values((SELECT NVL(MAX(kinder_id)+1,0) FROM kinder_info),?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kinder.getKinderName() );
			pstmt.setString(2, kinder.getKinderAge());
			pstmt.setString(3, kinder.getKinderClass());
			pstmt.setString(4, kinder.getParentPhone());
			pstmt.executeQuery();
			
			
			sql = "SELECT * FROM kinder_info WHERE kinder_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kinder.getKinderId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			System.out.println("원생번호>" + rs.getString("kinder_id"));
			System.out.println("입학일> " + rs.getString("admission_date"));
			}
			System.out.println("학원생 정보가 등록되었습니다.");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return sql;
	}
	public void insertT(Kinder teacher) {
		connect();
		try {
			String sql = "insert into teacher_info values((SELECT NVL(MAX(teacher_id)+1,0) FROM teacher_info),?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherName());
			pstmt.setString(2, teacher.getKinderClass());
//			pstmt.setString(3, teacher.getKinderName());
			pstmt.executeUpdate();
			
			sql = "select * from teacher_info where teacher_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			System.out.println("선생님번호> " + rs.getString("teacher_id"));
			System.out.println("입사일> " + rs.getString("entry_date"));
			
			}
			System.out.println("선생님 정보가 등록되었습니다.");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	public void deleteK(String kinderId) {
		String sql = "delete from kinder_info where kinder_id = ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kinderId);
			
			pstmt.executeQuery();
			System.out.println("학생정보 삭제완료.");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		
	}
	public void deleteT(String teacherId) {
		String sql = "delete from teacher_info where teacher_id = ?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacherId);
			
			pstmt.executeQuery();
			System.out.println("선생님정보 삭제완료.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		
	}
	public void updateK(Kinder kinder) {
		String sql = "update kinder_info set kinder_name=?, kinder_age=?, kinder_class=?, parent_phone=?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kinder.getKinderName());
			pstmt.setString(2, kinder.getKinderAge());
			pstmt.setString(3, kinder.getKinderClass());
			pstmt.setString(4, kinder.getParentPhone());
			
			pstmt.executeUpdate();
			System.out.println("원생정보 수정 완료.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	public void updateT(Kinder teacher) {
		String sql = "update teacher_info set teacher_name=?, kinder_class=?";
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherName());
			pstmt.setString(2, teacher.getKinderClass());
			
			pstmt.executeUpdate();
			System.out.println("선생님정보 수정 완료.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	public List<Kinder> serachKinder(Kinder kinder){
		List<Kinder> kinderList = new ArrayList<Kinder>();
		String sql = "select * from kinder_info";
		if (kinder.getKinderId() != null) {
			sql += " where kinder_id like '%'||?||'%'";
		} else if (kinder.getKinderName() != null) {
			sql += " where kinder_name like '%'||?||'%'";
		} else if (kinder.getKinderAge() != null) {
			sql += " where kinder_age like '%'||?||'%'";
		}
		connect();
		try {
			pstmt = conn.prepareStatement(sql);
			if (kinder.getKinderId() != null) { // 조회조건 : 저자명.
				pstmt.setString(1, kinder.getKinderId());
			} else if (kinder.getKinderName() != null) { // 조회조건 : 책제목
				pstmt.setString(1, kinder.getKinderName());
			} else if (kinder.getKinderAge() != null) { // 조회조건 : 출판사
				pstmt.setString(1, kinder.getKinderAge());
			}
			rs = pstmt.executeQuery(); // 조회결과 -> ResultSet
			while (rs.next()) { // 데이터가 있으면 계속 데이터를 하나씩 가져온다.
				Kinder kd = new Kinder();
				kd.setKinderId(rs.getString("kinder_id"));
				kd.setKinderName(rs.getString("kinder_name"));
				kd.setKinderAge(rs.getString("kinder_age"));
				kd.setKinderClass(rs.getString("kinder_class"));
				kd.setParentPhone(rs.getString("parent_phone"));

				kinderList.add(kd);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return kinderList; // 조회된 결과를 List컬렉션에 담아서 반환.
	}
}

