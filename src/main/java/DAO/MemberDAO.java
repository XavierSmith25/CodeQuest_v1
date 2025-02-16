package DAO;

import java.util.List;

import DTO.MemberDTO;
import enums.GENDER;

public interface MemberDAO {
	List<MemberDTO> selectAll() throws Exception;
	MemberDTO selectById(int id) throws Exception;
	int insert(MemberDTO dto) throws Exception;
	MemberDTO login(String id, String pw) throws Exception;
	boolean idVali(String id) throws Exception;
	int update(MemberDTO dto) throws Exception;
	int deleteById(int id) throws Exception;
	
	List<MemberDTO> selectByGender(GENDER gender) throws Exception;   // 성별별로 유저를 검색
	List<MemberDTO> selectByAgeRange(int startAge, int endAge) throws Exception;	// 성별별로 유저를 검색
	boolean isAdmin(String role) throws Exception;	// 관리자인지 확인 
}
