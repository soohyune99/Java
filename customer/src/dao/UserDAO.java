package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.UserVO;

public class UserDAO {
	// 288번쨰 줄 참고, 실무에서는 상수로 선언해서 무슨의미인지 제대로 알 수 있게 한다.
	// 상수 여러개 선언 시 각 숫자 겹치지 않게 선언
	// 회원 탈퇴
	public static final int DELETED_USER_CODE = -1;
	// 회원 복구
	public static final int RESTORED_USER_CODE = 0;
	// 중복된 아이디 코드
	public static final int DUPLICATED_ID_CODE = 1;
	// 중복이 안된 아이디 코드
	public static final int ENABLED_ID_CODE = 2;

	// 3가지 객체
	public Connection connection; //연결 객체
	public PreparedStatement preparedStatement; //쿼리(SQL문) 객체
	public ResultSet resultSet; //결과 테이블 객체 -  2차원 배열, 행 먼저 받고 그 다음에 열

	/**
	 * 
	 * @param userId
	 * @return
	 * 	RESTORED_USER_CODE = 0;<br>
		DELETED_USER_CODE = 1;<br>
		DUPLICATED_ID_CODE = 2;<br>
		ENABLED_ID_CODE = 3;<br>
	 */
	
	// 아이디 중복검사	
	// 회원가입 버튼을 누르기 전에 아이디 중복검사를 해야한다.
	// 보통 탈퇴된 계정인지 검사하는것은 아이디 중복검사에서 한다.
	// 아이디를 전달하면 아이디 상태(userStatus)에 대해서 리턴한다.
	public int checkId(String userId) {
		// 아이디 찾기가 아니기 때문에, userStatus 결과유무에 따라서 충분히 우리가 판단 가능해서 userId까지는 필요하지 않다.
		String query = "select userStatus from tbl_user where userId = ?";
		
		int code = 0;
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery(); 
			// resultSet.next()를 안 가져올수도 있어서 if문 사용
			// resultSet.next()를 가져왔다면 true, 아이디가 있는데 일단 중복이 되었다고 생각하고 들어간다.
			if(resultSet.next()) {
				// code는 중복이 되었다.
				code = DUPLICATED_ID_CODE;
				// 만약에 resultSet.getInt(1) = 첫번째의 userStatus가 DELETED_USER_CODE이면
				if(resultSet.getInt(1) == DELETED_USER_CODE) {
					// code는 DELETED_USER_CODE, 즉 탈퇴된 계정이다.
					code = DELETED_USER_CODE;
				}
			}else {
				// 중복이 된 아이디 코드가 아니다.
				code = ENABLED_ID_CODE;
			}
		} catch (SQLException e) {
			System.out.println("checkId()에서 쿼리문 오류");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return code;
	}

	// 회원가입
	public boolean insert(UserVO userVO) {
		// 전화번호가 3개가 있다면 회원가입을 할 수 없다. -> 회원가입 실패, false로 리턴
		// 368번째 check()메소드 사용
		// 드라이버를 열지도 않았기 때문에 close() 필요 없다.
		if(!check(userVO.getUserPhoneNumber())) { return false; }
		
		// userStatus는 default 0이기 때문에 굳이 넣어줄 필요 없고, 
		// userNumber는 auto_increment 자동으로 늘어나기 때문에 넣어줄 필요 없다.
		String query = "insert into tbl_user "
				+ "(userId, userPassword, userName, userAge, userPhoneNumber, userBirth) " 
				+ "values(?, ?, ?, ?, ?, ?)";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userVO.getUserId());
			preparedStatement.setString(2, userVO.getUserPassword());
			preparedStatement.setString(3, userVO.getUserName());
			preparedStatement.setInt(4, userVO.getUserAge());
			preparedStatement.setString(5, userVO.getUserPhoneNumber());
			preparedStatement.setString(6, userVO.getUserBirth());
			// insert 사용시 executeUpdate()를 사용해야한다.
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("insert()에서 쿼리문 오류");

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		// 회원가입이 성공했으므로 true 리턴
		return true;
	}

	// 로그인
	// userId, userPassword를 받아온다.
	public int login(String userId, String userPassword) {
		// userNumber를 가져온 이유는 누가 로그인 되어있는지 유지하기 위해서 갖고왔다.
		String query = "select userNumber, userStatus from tbl_user where userId = ? and userPassword =?";

		int userNumber = 0;

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userPassword);
			resultSet = preparedStatement.executeQuery();
			
			// 로그인 성공 시
			if (resultSet.next()) {
				userNumber = resultSet.getInt("userNumber");
				// userStatus가 DELETED_USER_CODE이면 탈퇴된 계정이면
				if(resultSet.getInt(2) == DELETED_USER_CODE) {
					userNumber = DELETED_USER_CODE;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("login() 쿼리문 오류");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		// userNumber는 1이 나오기 때문에 DELETED_USER_CODE를 1로 선언하게 되면 둘이 똑같아서 오류 발생
		return userNumber;
	}

	// 아이디 찾기 - 전화번호로 찾을 수 있게 한다.
	public ArrayList<UserVO> findId(String userPhoneNumber) {

		String query = "select userNumber, userId, userPhoneNumber from tbl_user where userPhoneNumber = ?";
		// 아이디 최대 3개, 전화번호 1개당 아이디가 여러개니까 ArrayList<> 사용
		// 아이디 찾기 다음에 비밀번호 찾기도 하기 위해서 회원 전체 정보를 받는다.
		ArrayList<UserVO> users = new ArrayList<UserVO>();

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhoneNumber);
			resultSet = preparedStatement.executeQuery();
			
			// 반복문
			while (resultSet.next()) {
				UserVO userVO = new UserVO();
				userVO.setUserNumber(Integer.valueOf(resultSet.getString(1)));	// 회원정보 받고
				userVO.setUserId(resultSet.getString(2));						// 아이디 받고
				userVO.setUserPhoneNumber(resultSet.getString(3));				// 전화번호 받아서
				
				// 합치기
				users.add(userVO);
			}

		} catch (SQLException e) {
			System.out.println("findId() 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return users;
	}

	// 회원 정보 전체 수정 - 새로운 정보가 들어온다.
	public void update(UserVO userVO) {
		String query = "update tbl_user " 
						+ "set userName=?, userPassword=?, userPhoneNumber=?, userAge=?, userBirth=? "
				+ "WHERE userNumber = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, userVO.getUserName());
			preparedStatement.setString(2, userVO.getUserPassword());
			preparedStatement.setString(3, userVO.getUserPhoneNumber());
			preparedStatement.setInt(4, userVO.getUserAge());
			preparedStatement.setString(5, userVO.getUserBirth());
			preparedStatement.setInt(6, userVO.getUserNumber());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("update() 쿼리 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	// 회원번호로 회원 전체정보 조회
	public UserVO selectUser(int userNumber) {
		String query = "select userNumber, userId, userPassword, userName, userAge, userPhoneNumber, userStatus, userBirth from tbl_user "
				+ "where userNumber=?";
		
		// 252번줄의 getInt(칼럼의 인덱스), 칼럼의 인덱스를 i로 표현
		int i = 0;
		// resultSet에 담은 결과들을 userVo객체에 담는다.
		UserVO userVO = new UserVO();
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				userVO.setUserNumber(resultSet.getInt(++i));
				userVO.setUserId((resultSet.getString(++i)));
				userVO.setUserPassword(resultSet.getString(++i));
				userVO.setUserName(resultSet.getString(++i));
				userVO.setUserAge(resultSet.getInt(++i));
				userVO.setUserPhoneNumber(resultSet.getString(++i));
				userVO.setUserStatus(resultSet.getInt(++i));
				userVO.setUserBirth(resultSet.getString(++i));
			}
			
		} catch (SQLException e) {
			System.out.println("updateUser() 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return userVO;
	}

	// 회원탈퇴
	public void delete(int userNumber) {
		// tbl_user 테이블에서 userStatus를 1로 바꾸겠다.
		// String query = "update tbl_user set userStatus=1 where userNumber= ?"; 도 가능한데 
		// 실무에서는 DELETED_USER_CODE로 상수 선언하여 무슨 의미인지 알게 한다.
		String query = "update tbl_user set userStatus=? where userNumber= ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, DELETED_USER_CODE);
			preparedStatement.setInt(2, userNumber);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("delete() 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	// 회원탈퇴 복구 : 회원 탈퇴와 비슷하다.
	// 로그인을 해서 탈퇴된 회원을 복구
	public void restore(int userNumber) {
		String query = "update tbl_user set userStatus=? where userNumber = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, RESTORED_USER_CODE);
			preparedStatement.setInt(2, userNumber);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("restore() 쿼리문 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	// 회원가입에서 동일한 핸드폰 번호로 3번(탈퇴된 계정 포함)까지 가입가능 메소드
	// 보통 탈퇴된 계정인지 검사하는것은 아이디 중복검사에서 한다.
	// 아이디 중복검사까지 하고 회원가입버튼을 눌러야 동일번호로 몇개 가입했는지 알 수 있다.
	// 그래서 이 메소드는 회원가입 안에 들어가야 한다.
	private boolean check(String userPhoneNumber) {
		boolean check = false;
		// 사용자가 전달한 전화번호를 가지고 조회를 한다.
		// 전화번호를 가지고 tbl_user테이블에서 전화번호를 카운팅한다.
		// 전화번호를 카운팅 = result 
		String query = "select count(userPhoneNumber) result from tbl_user where userPhoneNumber = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhoneNumber);
			resultSet = preparedStatement.executeQuery();
			
			// 만약에 전화번호가 있다면
			if (resultSet.next()) { 
				// 전화번호의 수가 3보다 작으면 회원가입 가능하면 true로 리턴
				check = resultSet.getInt("result") < 3;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("login() 쿼리문 오류");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return check;
	}
	
	// 핸드폰번호로 여러개 가입한 전체 회원 정보 조회
	// 회원가입이 false가 리턴 되었을때, 이 페이지로 이동되어야한다.
	public ArrayList<UserVO> findUsersByUserPhoneNumber(String userPhoneNumber) {
		// 회원의 모든 정보를 가져와야 한다.
		String query = "select userNumber, userId, userName, userAge, userPhoneNumber, userBirth, userStatus from tbl_user "
				+ "where userPhoneNumber = ?";
		ArrayList<UserVO> users = new ArrayList<UserVO>();
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userPhoneNumber);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				UserVO userVO = new UserVO();
				userVO.setUserNumber(resultSet.getInt(1));
				userVO.setUserId(resultSet.getString(2));
				userVO.setUserName(resultSet.getString(3));
				userVO.setUserAge(resultSet.getInt(4));
				userVO.setUserPhoneNumber(resultSet.getString(5));
				userVO.setUserBirth(resultSet.getString(6));
				userVO.setUserStatus(resultSet.getInt(7));
				users.add(userVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return users;
	}
}























