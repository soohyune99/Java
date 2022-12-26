package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import oracle.net.aso.p;
import vo.UserVO;
import vo.UserVOme;

public class UserDAOMe {

	// 1. 쿼리 작성
	// 2. 쿼리 객체로 저장
	// 3. 쿼리 객체로 쿼리 실행
	// 4. 실행 결과 받기 - 2차원 배열, 행 먼저 받고 그 다음에 열
	
	private final int KEY = 3;
	
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	// 아이디 중복검사
	public boolean checkId(String id) {
		String query = "SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_ID = ?";
		boolean check = false;
		
		try {
			connection = DBConnecterMe.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			check = resultSet.getInt(1) == 1;
			
		} catch (SQLException e) {
			System.out.println("checkId()에서 쿼리문 오류");
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return check;
	}
	
	// 회원가입
	public void insert(UserVOme userVome) {
		String query = "INSERT INTO TBL_USER "
				+ "(USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB)"
				+ "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DBConnecterMe.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVome.getUserId());
			preparedStatement.setString(2, userVome.getUserName());
			preparedStatement.setString(3, userVome.getUserPassword());
			preparedStatement.setString(4, userVome.getUserPhone());
			preparedStatement.setString(5, userVome.getUserNickname());
			preparedStatement.setString(6, userVome.getUserEmail());
			preparedStatement.setString(7, userVome.getUserAddress());
			preparedStatement.setString(8, userVome.getUserBirthDate());
			preparedStatement.setString(9, userVome.getUserGender());
			preparedStatement.setString(10, userVome.getUserRecommenderId());
			preparedStatement.setString(11, userVome.getUserJob());
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("insert()에서 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 로그인
	public int login(String userId, String userPassword) {
		String query = "SELECT USER_NUMBER FROM TBL_USER WHERE USER_ID = ? AND USER_PASSWORD = ?";
		int userNumber = 0;
		
		try {
			connection = DBConnecterMe.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userPassword);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				userNumber = resultSet.getInt("USER_NUMBER");
			}
				
			
		} catch (SQLException e) {
			System.out.println("login()에서 쿼리문 오류");
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userNumber;
		
	
	}
	
	
	// 암호화
	public String encrypt(String password) {
		String encryptPassword = "";
		
		for (int i = 0; i < password.length(); i++) {
			encryptPassword += (char)(password.charAt(i)* KEY);
		}
		return encryptPassword;
	}
	
	// 회원 탈퇴
	public void delet(int userNumber) {
		String query = "DELETE FROM TBL_USER WHERE USER_NUMBER = ?";
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("delete()에서 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 아이디 찾기
	public String findId(String userPhone) {
		String query = "SELECT USER_ID FROM TBL_USER WHERE USER_PHONE = ?";
		String userId = null;
		
		try {
			connection = DBConnecterMe.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhone);
			resultSet =  preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				userId = resultSet.getString(1);
				
			}
		} catch (SQLException e) {
			System.out.println("login()에서 쿼리문 오류");
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userId;
	}
	

	// 비밀번호 변경
	public void updatePassword(String userId, String userPassword) {
		String query = "UPDATE TBL_USER SET USER_PASSWORD = ? WHERE USER_ID = ?";
		try {
			connection = DBConnecterMe.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPassword);
			preparedStatement.setString(2, userPassword);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateUserPassword() 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 회원정보 수정
	public void update(UserVOme userVome) {
		String query = "UPDATE TBL_USER "
				+ "SET USER_NAME=?, USER_PASSWORD=?, USER_PHONE=?, USER_NICKNAME=?, USER_ADDRESS=?, USER_BIRTH_DATE=?, USER_GENDER=? , USER_RECOMMENDER_ID=?, USER_JOB=?"
				+ "WHERE USER_NUMBER = ?";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVome.getUserName());
			preparedStatement.setString(2, userVome.getUserPassword());
			preparedStatement.setString(3, userVome.getUserPhone());
			preparedStatement.setString(4, userVome.getUserNickname());
			preparedStatement.setString(5, userVome.getUserAddress());
			preparedStatement.setString(6, userVome.getUserBirthDate());
			preparedStatement.setString(7, userVome.getUserGender());
			preparedStatement.setString(8, userVome.getUserRecommenderId());
			preparedStatement.setString(9, userVome.getUserJob());
			preparedStatement.setInt(10, userVome.getUserNumber());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("update() 쿼리 오류");
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 회원정보 조회
	public void selectUser(int userNumber) {
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB FROM TBL_USER "
				+ "WHERE USER_NUMBER = ?";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		UserVOme userVome = new UserVOme();
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();
			// 회원번호 각 하나이기 때문에 행도 하나, next()는 한번만 써도 된다.
			resultSet.next();
			
			userVome.setUserNumber(resultSet.getInt(++i));
			userVome.setUserId(resultSet.getString(++i));
			userVome.setUserName(resultSet.getString(++i));
			userVome.setUserPassword(resultSet.getString(++i));
			userVome.setUserPhone(resultSet.getString(++i));
			userVome.setUserNickname(resultSet.getString(++i));
			userVome.setUserEmail(resultSet.getString(++i));
			userVome.setUserAddress(resultSet.getString(++i));
			// resultSet.getString(++i)은 string타입이므로 parse를 사용하여 date타입으로 변경
			// setUserBirthDate string타입이므로 sdf.parse(resultSet.getString(++i)를 format을 사용하여 String타입으로 변경
			try {userVome.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));} catch (Exception e) {;}
			userVome.setUserGender(resultSet.getString(++i));
			userVome.setUserRecommenderId(resultSet.getString(++i));
			userVome.setUserJob(resultSet.getString(++i));
			
		} catch (SQLException e) {
			System.out.println("selectUser() 쿼리 오류");
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	// 추천수
	public int getTotalRecommender(String userId) {
		String query = "SELECT COUNT(USER_RECOMMENDER_ID) FROM TBL_USER WHERE USER_RECOMMENDER_ID = ?";
		int count = 0;
		
		try {
			connection = DBConnecterMe.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("getTotalRecommender() 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return count;
	}
	
	// 나를 추천한 사람
	public ArrayList<UserVOme> selectRecommenders(String userId){
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB FROM TBL_USER "
				+ "WHERE USER_RECOMMENDER_ID = ?";
		
		ArrayList<UserVOme> recommenders = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				i = 0;
				UserVOme UserVOme = new UserVOme();	
				UserVOme.setUserNumber(resultSet.getInt(++i));
				UserVOme.setUserId(resultSet.getString(++i));
				UserVOme.setUserName(resultSet.getString(++i));
				UserVOme.setUserPassword(resultSet.getString(++i));
				UserVOme.setUserPhone(resultSet.getString(++i));
				UserVOme.setUserNickname(resultSet.getString(++i));
				UserVOme.setUserEmail(resultSet.getString(++i));
				UserVOme.setUserAddress(resultSet.getString(++i));
				try {UserVOme.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));} catch (Exception e) {;}
				UserVOme.setUserGender(resultSet.getString(++i));
				UserVOme.setUserRecommenderId(resultSet.getString(++i));
				UserVOme.setUserJob(resultSet.getString(++i));
				
				recommenders.add(UserVOme);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return recommenders;
	}
	
	// 내가 추천한 사람 - 한명이므로 ArrayList, while()반복문 필요없다.
	public UserVOme getMyRecommender(String userId) {
		//  USER_ID인 내가 추천한 사람의 모든 정보를 조회 
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB " +
				"FROM TBL_USER WHERE USER_ID = " + 
				"(" +
					"SELECT USER_RECOMMENDER_ID FROM TBL_USER " + 
					"WHERE USER_ID = ?" +
				")";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVOme UserVOme = new UserVOme();

		int i = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				UserVOme.setUserNumber(resultSet.getInt(++i));
				UserVOme.setUserId(resultSet.getString(++i));
				UserVOme.setUserName(resultSet.getString(++i));
				UserVOme.setUserPassword(resultSet.getString(++i));
				UserVOme.setUserPhone(resultSet.getString(++i));
				UserVOme.setUserNickname(resultSet.getString(++i));
				UserVOme.setUserEmail(resultSet.getString(++i));
				UserVOme.setUserAddress(resultSet.getString(++i));
				try {UserVOme.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));} catch (Exception e) {;}
				UserVOme.setUserGender(resultSet.getString(++i));
				UserVOme.setUserRecommenderId(resultSet.getString(++i));
				UserVOme.setUserJob(resultSet.getString(++i));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return UserVOme;
	}
	
}



