package com.cnm_inf.model;

import java.util.*;
import java.sql.*;

public class Cnm_infJDBCDAO implements Cnm_infDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO cnm_inf (CNM_DT,CNM_TEL,CNM_EM,CNM_LC,CNM_TRP) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT CNM_INF_ID,CNM_DT,CNM_TEL,CNM_EM,CNM_LC,CNM_TRP FROM cnm_inf order by CNM_INF_ID";
	private static final String GET_ONE_STMT = 
		"SELECT CNM_INF_ID,CNM_DT,CNM_TEL,CNM_EM,CNM_LC,CNM_TRP FROM cnm_inf where CNM_INF_ID = ?";
	private static final String DELETE = 
		"DELETE FROM cnm_inf where CNM_INF_ID = ?";
	private static final String UPDATE = 
		"UPDATE cnm_inf set CNM_DT=?, CNM_TEL=?, CNM_EM=?, CNM_LC=?, CNM_TRP=? where CNM_INF_ID = ?";

	@Override
	public void insert(Cnm_infVO cnm_infVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cnm_infVO.getCNM_DT());
			pstmt.setString(2, cnm_infVO.getCNM_TEL());
			pstmt.setString(3, cnm_infVO.getCNM_EM());
			pstmt.setString(4, cnm_infVO.getCNM_LC());
			pstmt.setString(5, cnm_infVO.getCNM_TRP());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Cnm_infVO cnm_infVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, cnm_infVO.getCNM_DT());
			pstmt.setString(2, cnm_infVO.getCNM_TEL());
			pstmt.setString(3, cnm_infVO.getCNM_EM());
			pstmt.setString(4, cnm_infVO.getCNM_LC());
			pstmt.setString(5, cnm_infVO.getCNM_TRP());
			pstmt.setInt(6, cnm_infVO.getCNM_INF_ID());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer CNM_INF_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, CNM_INF_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Cnm_infVO findByPrimaryKey(Integer CNM_INF_ID) {

		Cnm_infVO cnm_infVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, CNM_INF_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ????????? Domain objects
				cnm_infVO = new Cnm_infVO();
				cnm_infVO.setCNM_INF_ID(rs.getInt("CNM_INF_ID"));
				cnm_infVO.setCNM_DT(rs.getString("CNM_DT"));
				cnm_infVO.setCNM_TEL(rs.getString("CNM_TEL"));
				cnm_infVO.setCNM_EM(rs.getString("CNM_EM"));
				cnm_infVO.setCNM_LC(rs.getString("CNM_LC"));
				cnm_infVO.setCNM_TRP(rs.getString("CNM_TRP"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return cnm_infVO;
	}

	@Override
	public List<Cnm_infVO> getAll() {
		List<Cnm_infVO> list = new ArrayList<Cnm_infVO>();
		Cnm_infVO cnm_infVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cnm_infVO ????????? Domain objects
				cnm_infVO = new Cnm_infVO();
				cnm_infVO.setCNM_INF_ID(rs.getInt("CNM_INF_ID"));
				cnm_infVO.setCNM_DT(rs.getString("CNM_DT"));
				cnm_infVO.setCNM_TEL(rs.getString("CNM_TEL"));
				cnm_infVO.setCNM_EM(rs.getString("CNM_EM"));
				cnm_infVO.setCNM_LC(rs.getString("CNM_LC"));
				cnm_infVO.setCNM_TRP(rs.getString("CNM_TRP"));
				list.add(cnm_infVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		Cnm_infJDBCDAO dao = new Cnm_infJDBCDAO();

		// ??????
		Cnm_infVO cnm_infVO1 = new Cnm_infVO();
		cnm_infVO1.setCNM_DT("testtesttesttest");
		cnm_infVO1.setCNM_TEL("03-1234567");
		cnm_infVO1.setCNM_EM("123@123.com.tw");
		cnm_infVO1.setCNM_LC("??????????????????????????????981???");
		cnm_infVO1.setCNM_TRP("??????: AA???B?????????; ??????: ABC/DEF???????????????????????????");
		dao.insert(cnm_infVO1);

		// ??????
		Cnm_infVO cnm_infVO2 = new Cnm_infVO();
		cnm_infVO2.setCNM_INF_ID(new Integer(1));
		cnm_infVO2.setCNM_DT("??????5????????????2?????????????????????2???IMAX???????????????1???????????????????????????????????????854????????????\r\n"
					+ "		????????????????????????4K????????????????????????????????????????????????\r\n"
					+ "		?????????????????????????????? 7.1 ????????????????????????????????????4???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\r\n"
					+ "		?????????VIP??????????????????????????????IMAX??????????????????????????????????????????????????????????????????????????????????????????????????????");
		cnm_infVO2.setCNM_TEL("02-9876543");
		cnm_infVO2.setCNM_EM("staff_01@hireme.com");
		cnm_infVO2.setCNM_LC("??????????????????????????????10???");
		cnm_infVO2.setCNM_TRP("111111");
		dao.update(cnm_infVO2);

		// ??????
//		dao.delete(new Integer(2));

		// ??????
		Cnm_infVO cnm_infVO3 = dao.findByPrimaryKey(new Integer(1));
		System.out.print(cnm_infVO3.getCNM_INF_ID() + ",");
		System.out.print(cnm_infVO3.getCNM_DT() + ",");
		System.out.print(cnm_infVO3.getCNM_TEL() + ",");
		System.out.print(cnm_infVO3.getCNM_EM() + ",");
		System.out.print(cnm_infVO3.getCNM_LC() + ",");
		System.out.println(cnm_infVO3.getCNM_TRP() + ",");
		System.out.println("---------------------");

		// ??????
		List<Cnm_infVO> list = dao.getAll();
		for (Cnm_infVO aCnm_inf : list) {
			System.out.print(aCnm_inf.getCNM_INF_ID() + ",");
			System.out.print(aCnm_inf.getCNM_DT() + ",");
			System.out.print(aCnm_inf.getCNM_TEL() + ",");
			System.out.print(aCnm_inf.getCNM_EM() + ",");
			System.out.print(aCnm_inf.getCNM_LC() + ",");
			System.out.print(aCnm_inf.getCNM_TRP() + ",");
			System.out.println();
		}
	}
}