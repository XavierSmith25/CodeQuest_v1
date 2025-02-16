package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DAO.MemberDAO;
import DTO.MemberDTO;
import enums.GENDER;

public enum MemberDAOImpl implements MemberDAO {
	INSTANCE;
	
	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public List<MemberDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	//dao.insert(new MemberDTO(id,pw,name,ssn,email,phone,postcode,address1,address2,null)); //role은 정해진게없어서 null
	
	@Override
	public int insert(MemberDTO dto) throws Exception {
		String sql = "insert into users "
				+ "(member_id, user_id, password, name, ssn, email, "
				+ "phone, zip_code, address, detail_address, role, reg_date)values "
				+ "(member_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getSsn());
			pstat.setString(5, dto.getEmail());
			pstat.setString(6, dto.getPhone());
			pstat.setInt(7, dto.getZipCode());
			pstat.setString(8, dto.getAddress());
			pstat.setString(9, dto.getDetailAddress());
			pstat.setString(10, dto.getRole());

			return pstat.executeUpdate();
		}
	}
	
	public boolean idVali(String id) throws Exception {// ID검증
		String sql = "select user_id from users where user_id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, id);
			try (ResultSet rs = pstat.executeQuery()) {
				return rs.next();
			}
		}
	}
	
	@Override
	public MemberDTO login(String inputId, String inputPw) throws Exception {
		String sql = "select * from users where user_id = ? and password = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, inputId);
			pstat.setString(2, inputPw);
			try (ResultSet rs = pstat.executeQuery()) {
				if (rs.next()) {
					String id = rs.getString("user_id");
					String name = rs.getString("name");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");
					
					MemberDTO member = new MemberDTO(id,name,ssn,email,phone,postcode,address,detail_address,role,date);
					return member;

			}
		}
		return null;
		}
	}

	@Override
	public int update(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberDTO> selectByGender(GENDER gender) throws Exception {
		// TODO Auto-generated method stub
		int genderFactor = gender.getGenderFactor();
		
		String sql = "?";
		
		return null;
	}

	@Override
	public List<MemberDTO> selectByAgeRange(int startAge, int endAge) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdmin(String role) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
