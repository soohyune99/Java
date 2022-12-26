package test;

import java.util.ArrayList;
import java.util.Scanner;

import dao.UserDAO;
import vo.UserVO;

public class Test {
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		
//		아이디 검사
//		System.out.println(userDAO.checkId("hds"));
		
//		회원가입
		UserVO userVO = new UserVO();
		ArrayList<UserVO> users = null;
		Scanner sc = new Scanner(System.in);
		// 57번째 줄의 입력받는 아이디와 비밀번호(로그인)
		String userId = null, userPassword = null;
		int i = 0, status = 0;
		
		// 회원가입 
		userVO.setUserId("lss");
		userVO.setUserName("이순신");
		userVO.setUserPassword("8888");
		userVO.setUserPhoneNumber("01012341234");
		userVO.setUserBirth("2008-12-04");
		
		// 성공 시
		if(userDAO.insert(userVO)) {
			System.out.println("성공, 로그인페이지로 이동");
		
		// 실패 시
		}else {
			System.out.println("실패");
			// 회원가입 실패 시 회원 전체 정보 목록 가져오기
			users = userDAO.findUsersByUserPhoneNumber(userVO.getUserPhoneNumber());
			for(UserVO user : users) {
				// 전체 목록
				System.out.print(++i + ". " + user.toString() + ", ");
				// 만약에 UserStatus가 DELETED_USER_CODE = 삭제된 회원이라면 복구하기 출력, 아니라면 로그인하기 출력
				System.out.println(user.getUserStatus() == UserDAO.DELETED_USER_CODE ? "복구하기" : "로그인하기");
			}
			
			// 복구할껀지 로그인할꺼지 물어본다.
			System.out.println("다음단계로 진행할 번호를 선택하세요.");
			// 전체 목록의 i는 이제 더이상 밑에서 필요없기 때문에 i 또 사용 가능
			// i는 users.get(i)의 인덱스번호(0부터시작)이기때문에 입력받은 숫자에서 - 1을 해야한다.
			i = sc.nextInt() - 1;
			// 만약 사용자가 선택한 회원의 Status가 DELETED_USER_CODE= 탈퇴한 계정이면
			if(users.get(i).getUserStatus() == UserDAO.DELETED_USER_CODE) {
				// 탈퇴된 계정이라면 복구하기
				userDAO.restore(users.get(i).getUserNumber());
			}
			// 탈퇴한 계정이 아니면 로그인 
			System.out.print("아이디 : ");
			userId = sc.next();
			System.out.print("비밀번호 : ");
			userPassword = sc.next();
			
			// 입력받은 아이디와 비밀번호를 status에 담는다
			status = userDAO.login(userId, userPassword);
			
			// 회원가입이 되었고,
			if(status != 0) {
				// status가 탈퇴한 계정이라면
				if(status == UserDAO.DELETED_USER_CODE) {
					System.out.println("탈퇴된 계정입니다.");
				}else {
					System.out.println("로그인 성공");
				}
			}else {
				System.out.println("로그인 실패");
			}
		}
		
		
//		탈퇴 하기
//		int userNumber = userDAO.login("hds", "1234");
//		if(userNumber > 0) {
//			userDAO.delete(userNumber);
//			System.out.println("회원 탈퇴 성공");
//		}
		
//		회원 번호로 회원 정보 전체 조회
//		System.out.println(userDAO.selectUser(1));
		
//		회원정보 수정
//		UserVO userVO = userDAO.selectUser(userDAO.login("hgd", "1111"));
//		userVO.setUserName("길동st");
//		userDAO.update(userVO);
//		System.out.println("수정 성공");
		
//		아이디 찾기
//		userDAO.findId("01012341234").forEach(System.out::println);
		
	}
}












