package userTask;

import java.util.Scanner;

public class Page {
   public static void main(String[] args) {
      User user = new User();
      UserField userField = new UserField();
      Scanner sc = new Scanner(System.in);
      String number = null;
      
//      입력받은 정보
      user.setId("hds1234");
      user.setName("한동석");
      user.setPassword("1234");
      user.setPhoneNumber("01012341234");
      
//      아이디 중복검사 테스트      
      if(userField.checkId(user.getId()) == null) {
//      회원가입 테스트
         userField.join(user);
//       향상된 for문, 빠른 for문, forEach문
         for (User member : UserField.users) {
            System.out.println(member);
         }
//         for (int i = 0; i < UserField.users.size(); i++) {
//            System.out.println(userField.users.get(i));
//         }
//       userField.users.forEach(System.out::println);
         
      }
   
//      로그인 테스트
//      user = userField.login("hds1234", "1234");
//      if(user != null) {
//         System.out.println(user);
//      }else {
//         System.out.println("로그인 실패");
//      }
      
      user = userField.checkId("hds1234");
      if(user != null) {
         number = userField.sendSms(user.getPhoneNumber());
         System.out.println(number);
         
         System.out.print("인증번호 : ");
         if(number.equals(sc.next())) {
            System.out.print("비밀번호 : ");
            user.setPassword(sc.next());
            userField.changePassword(user);
            System.out.println(UserField.users.get(0));
         }
      }
      
      if(userField.login("hds1234", "5555") != null) {
         System.out.println("로그인 성공");
         
      }else {
         System.out.println("로그인 실패");
      }
      
   }
}















