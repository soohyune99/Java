package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import vo.UserVO;

public class UserDAO {
	
	// 1. 쿼리 작성
	// 2. 쿼리 객체로 저장
	// 3. 쿼리 객체로 쿼리 실행
	// 4. 실행 결과 받기 - 2차원 배열, 행 먼저 받고 그 다음에 열
	
	private final int KEY = 3;
	
	// 3가지 객체
	public Connection connection; //연결 객체
	public PreparedStatement preparedStatement; //쿼리(SQL문) 객체
	public ResultSet resultSet; //결과 테이블 객체 -  2차원 배열, 행 먼저 받고 그 다음에 열
	
	// 아이디 중복검사
	public boolean checkId(String id) {
		// USER_ID 컬럼에서 사용자가 입력한 id 검사
		// 아이디 중복검사를 COUNT()를 이용해 결과 값이 0이면 중복이 없는것이고, 결과 값이 1이면 중복이 있다. 
		// ? : PreparedStatment 문법, 변수가 들어올 자리에 ?로 채워주면 된다.
		String query = "SELECT COUNT(USER_ID) FROM TBL_USER WHERE USER_ID = ?";
		
		boolean check = false;
		try {
			// 연결 객체 가져오기
			connection = DBConnecter.getConnection();
			// 작성한 쿼리문을 preparedStatement에 전달
			preparedStatement = connection.prepareStatement(query);
			// ? 채우기(좌에서 우로 1부터 1씩 증가)
			// ? 인덱스는 배열(배열의 인덱스는 0부터 시작)과 다르게 1부터 시작
			preparedStatement.setString(1, id);
			// executeQuery는 쿼리 실행
			// SELECT문은 executeQuery을 사용, 그러면 resultSet으로 결과값 리턴
			// SELECT문이 아닌 UPDATE, INSERT, DELETE는 결과값이 없기때문에 executeQuery()가 아닌 executeUpdate()사용
			resultSet = preparedStatement.executeQuery();
			// 결과는 2차원 배열, 행을 먼저 가져와야 한다.
			// resultSet.next()를 사용, 행이 여러개면 반복해서 쓰기
			resultSet.next();
			// 결과 첫번째 행에서 첫번째 열을 가져오려먼 result.getInt(컬럼의 인덱스 또는 컬럼명)
			// 1이라면 사용자가 입력한 아이디가 1개 조회된 것이기 때문에 중복된 아이디이다.
			// true면 아이디 중복이 있다, false면 아이디 중복이 없다. 
			check = resultSet.getInt(1) == 1;
			
		} catch (SQLException e) {
			System.out.println("checkId()에서 쿼리문 오류");
		} finally {
			try {
				// 연결 객체 모두 닫기
				// 항상 닫을때 열었던 순서 반대로 닫기
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
				// 예외 존재하면 직접 날리기
				throw new RuntimeException(e.getMessage());
			}
		}
		return check;
	}
	
	// 회원가입
	// DB에 추가만 하면 되므로 화면에 리턴 할 값이 없다, void 타입이다. 
	public void insert(UserVO userVO) {
		String query = "INSERT INTO TBL_USER "
				+ "(USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB)"
				+ "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVO.getUserId());
			preparedStatement.setString(2, userVO.getUserName());
			preparedStatement.setString(3, userVO.getUserPassword());
			preparedStatement.setString(4, userVO.getUserPhone());
			preparedStatement.setString(5, userVO.getUserNickname());
			preparedStatement.setString(6, userVO.getUserEmail());
			preparedStatement.setString(7, userVO.getUserAddress());
			preparedStatement.setString(8, userVO.getUserBirthDate());
			preparedStatement.setString(9, userVO.getUserGender());
			preparedStatement.setString(10, userVO.getUserRecommenderId());
			preparedStatement.setString(11, userVO.getUserJob());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("insert()에서 쿼리문 오류");
			
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
	// 로그인을 했으면 그다음부터 로그인 할떄 회원의 정보가 유지되야하니까 
	// PK값인 회원번호를 담아서 리턴해야되기 때문에 int로 설정
	public int login(String userId, String userPassword) {
		String query = "SELECT USER_NUMBER FROM TBL_USER WHERE USER_ID = ? AND USER_PASSWORD = ?";
		int userNumber = 0;
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// 첫번째 ?에 userId 전달
			preparedStatement.setString(1, userId);
			// 두번째 ?에 userPassword 전달
			preparedStatement.setString(2, userPassword);
			resultSet = preparedStatement.executeQuery();
			
			// resultSet.next()는 행부터 접근
			// 어차피 이건 중복이 없기 때문에 무조건 결과 행은 한개가 나온다
			// 그러니까 한번만 돌리면 된다, 반복문 쓸 필요 없다.
			// 행이 있어야 userNumber가 접근이 가능해야 하므로 조건문 if문 사용 -> 행이 있을때만 열을 갖고와라
			// 행이 없으면 userNumber 0으로 리턴
			if(resultSet.next()) {
				userNumber = resultSet.getInt("USER_NUMBER");
				// userNumber = resultSet.getInt(1);로 써도 가능
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("login() 쿼리문 오류");
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
		return userNumber;
	}
	
	
	// 암호화
	public String encrypt(String password) {
		// 암호화된 비밀번호를 담을곳
		String encryptedPassword = "";
		
		for (int i = 0; i < password.length(); i++) {
			// 입력받은 password를 한문자씩 KEY(3)을 곱해주고,
			// 곱한것을 다시 char로 형변환하여 encryptedPassword에 하나씩 담는다.
			encryptedPassword += (char)(password.charAt(i) * KEY);
		}
		return encryptedPassword;
	}
	
	// 회원탈퇴
	// DB에 삭제만 하면 되므로 화면에 리턴 할 값이 없다, void 타입이다. 
	public void delete(int userNumber) {
		String query = "DELETE FROM TBL_USER WHERE USER_NUMBER = ?";
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// int타입이므로 setInt로 받는다.
			preparedStatement.setInt(1, userNumber);
			// SELECT문이 아닌 UPDATE, INSERT, DELETE는 결과값이 없기때문에 executeQuery()가 아닌 executeUpdate()사용
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("delete() 쿼리문 오류");
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
	
	// 아이디 찾기 : 전화번호 찾기
	// USER_PHONE은 중복이 없는 UK여서 아이디 찾기 가능하다.
	public String findId(String userPhone) {
		String userId = null;
		String query = "SELECT USER_ID FROM TBL_USER WHERE USER_PHONE = ?";
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhone);
			resultSet = preparedStatement.executeQuery();
			
			// 중복이 없는 전화번호이므로 행은 1개 나온다.
			// 그러므로 반복문 while문 사용하지않고 조건문 if문 사용
			if(resultSet.next()) {
				userId = resultSet.getString(1);
			}
			
		} catch (SQLException e) {
			System.out.println("findId() 쿼리문 오류");
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
		return userId;
	}
	
	// 비밀번호 변경
	// 변경하기 전 페이지에서 아이디, 전화번호 인증을 한 후, 
	// 비밀번호 변경 페이지 안에 변경하려는 사용자의 id와 새로 변경할 비밀번호를 매개변수로 받아온다. 
	public void updateUserPassword(String userId, String userPassword) {
		String query = "UPDATE TBL_USER SET USER_PASSWORD = ? WHERE USER_ID = ?";
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPassword);
			preparedStatement.setString(2, userId);
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
	public void update(UserVO userVO) {
		String query = "UPDATE TBL_USER "
				+ "SET USER_NAME=?, USER_PASSWORD=?, USER_PHONE=?, USER_NICKNAME=?, USER_ADDRESS=?, USER_BIRTH_DATE=?, USER_GENDER=? , USER_RECOMMENDER_ID=?, USER_JOB=?"
				+ "WHERE USER_NUMBER = ?";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVO.getUserName());
			preparedStatement.setString(2, userVO.getUserPassword());
			preparedStatement.setString(3, userVO.getUserPhone());
			preparedStatement.setString(4, userVO.getUserNickname());
			preparedStatement.setString(5, userVO.getUserAddress());
			preparedStatement.setString(6, userVO.getUserBirthDate());
			preparedStatement.setString(7, userVO.getUserGender());
			preparedStatement.setString(8, userVO.getUserRecommenderId());
			preparedStatement.setString(9, userVO.getUserJob());
			preparedStatement.setInt(10, userVO.getUserNumber());
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
	// 정보를 조회라기 위해 회원번호가 필요
	public UserVO selectUser(int userNumber) {
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB FROM TBL_USER "
				+ "WHERE USER_NUMBER = ?";
		// 생년월일로 지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// 331번줄의 getInt(칼럼의 인덱스), 칼럼의 인덱스를 i로 표현
		int i = 0;
		// resultSet에 담은 결과들을 userVo객체에 담는다. 
		UserVO userVO = new UserVO();
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();
			// 회원번호 각 하나이기 때문에 행도 하나, next()는 한번만 써도 된다.
			resultSet.next();
			
			userVO.setUserNumber(resultSet.getInt(++i));
			userVO.setUserId(resultSet.getString(++i));
			userVO.setUserName(resultSet.getString(++i));
			userVO.setUserPassword(resultSet.getString(++i));
			userVO.setUserPhone(resultSet.getString(++i));
			userVO.setUserNickname(resultSet.getString(++i));
			userVO.setUserEmail(resultSet.getString(++i));
			userVO.setUserAddress(resultSet.getString(++i));
			// resultSet.getString(++i)은 string타입이므로 parse를 사용하여 date타입으로 변경
			// setUserBirthDate string타입이므로 sdf.parse(resultSet.getString(++i)를 format을 사용하여 String타입으로 변경
			try {userVO.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));} catch (Exception e) {;}
			userVO.setUserGender(resultSet.getString(++i));
			userVO.setUserRecommenderId(resultSet.getString(++i));
			userVO.setUserJob(resultSet.getString(++i));
			
		} catch (SQLException e) {
			System.out.println("selectUser() 쿼리문 오류");
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
		return userVO;
	}
	
	
	// 추천수
	// 추천수를 알고 싶은 회원의 아이디를 받아온다.
	public int getTotalOfRecommender(String userId) {
		String query = "SELECT COUNT(USER_RECOMMENDER_ID) FROM TBL_USER WHERE USER_RECOMMENDER_ID = ?";
		int count = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			// 집계함수를 사용, 결과는 하나이다. 그러므로 반복문 while()없이 사용
			if(resultSet.next()) {
				count = resultSet.getInt(1);
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
		return count;
	}
	
	// 나를 추천한 사람
	// 나를 추천한 사람은 여러명이므로 ArrayList<> 사용
	public ArrayList<UserVO> selectRecommenders(String userId){
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB FROM TBL_USER "
				+ "WHERE USER_RECOMMENDER_ID = ?";
		// 리턴할 UserVO를 담을 ArrayList recommenders 선언
		ArrayList<UserVO> recommenders = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		int i = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			// 행의 여러개이므로 while()문 사용
			while(resultSet.next()) {
				// 초기화, 여러명이 나올수있어서
				i = 0;
				// 모든 정보들을 한번에 담을 객체인 userVO 선언										
				UserVO userVO = new UserVO();	
				userVO.setUserNumber(resultSet.getInt(++i));
				userVO.setUserId(resultSet.getString(++i));
				userVO.setUserName(resultSet.getString(++i));
				userVO.setUserPassword(resultSet.getString(++i));
				userVO.setUserPhone(resultSet.getString(++i));
				userVO.setUserNickname(resultSet.getString(++i));
				userVO.setUserEmail(resultSet.getString(++i));
				userVO.setUserAddress(resultSet.getString(++i));
				try {userVO.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));} catch (Exception e) {;}
				userVO.setUserGender(resultSet.getString(++i));
				userVO.setUserRecommenderId(resultSet.getString(++i));
				userVO.setUserJob(resultSet.getString(++i));
				
				// 모든 정보를 담은 userVO를 recommenders인 ArrayList에 담는다.
				recommenders.add(userVO);
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
	
	// 내가 추천한 사람
	public UserVO getMyRecommender(String userId) {
		//  USER_ID인 내가 추천한 사람의 모든 정보를 조회 
		String query = "SELECT USER_NUMBER, USER_ID, USER_NAME, USER_PASSWORD, USER_PHONE, USER_NICKNAME, USER_EMAIL, USER_ADDRESS, USER_BIRTH_DATE, USER_GENDER, USER_RECOMMENDER_ID, USER_JOB " +
				"FROM TBL_USER WHERE USER_ID = " + 
				"(" +
					"SELECT USER_RECOMMENDER_ID FROM TBL_USER " + 
					"WHERE USER_ID = ?" +
				")";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVO = new UserVO();

		int i = 0;
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				userVO.setUserNumber(resultSet.getInt(++i));
				userVO.setUserId(resultSet.getString(++i));
				userVO.setUserName(resultSet.getString(++i));
				userVO.setUserPassword(resultSet.getString(++i));
				userVO.setUserPhone(resultSet.getString(++i));
				userVO.setUserNickname(resultSet.getString(++i));
				userVO.setUserEmail(resultSet.getString(++i));
				userVO.setUserAddress(resultSet.getString(++i));
				try {userVO.setUserBirthDate(sdf.format(sdf.parse(resultSet.getString(++i))));} catch (Exception e) {;}
				userVO.setUserGender(resultSet.getString(++i));
				userVO.setUserRecommenderId(resultSet.getString(++i));
				userVO.setUserJob(resultSet.getString(++i));
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
		return userVO;
	}
}



















