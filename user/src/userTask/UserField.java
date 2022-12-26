package userTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class UserField {
   private final int KEY = 3;

//   - 회원 정보를 담을 DB를 ArrayList로 선언
   public static ArrayList<User> users = new ArrayList<User>();

//   - 아이디 중복검사
   public User checkId(String id) {
      User user = null;
      
      // forEach()문 사용
      for (User temp : users) {					// 내가 입력한 id와 temp에 있었던 id를 비교해 아이디 중복 검사
    	  if(temp.getId().equals(id)) {
    		  user = temp;
    	  }
      }
      return user;								// null 또는 해당 회원 리턴
   }
   
      // DB에 있는 회원 수 만큼
//      for (int i = 0; i < users.size(); i++) {
         // 회원별 아이디 검사
//         if (users.get(i).getId().equals(id)) {
           // 일치하는 아이디가 있다면 user에 저장
//            user = users.get(i);
//            break;
//         }
//      }
  

   // 회원가입
   public void join(User user) {
      user.setPassword(encrypt(user.getPassword()));	// 암호화
      users.add(user);									// DB에 저장
   }

   // 로그인
   public User login(String id, String pw) {
   // 아이디 검사
      User user = checkId(id);
      if (user != null) { 									// 아이디가 있다면
         if (user.getPassword().equals(encrypt(pw))) {		// 비밀번호 검사
            return user;									// 로그인 성공(성공 시 로그인된 회원의 정보 리턴)
         }
      }
      return null;											// 로그인 실패
   }

   // 암호화
   public String encrypt(String password) {
      String decryptedPassword = "";

      for (int i = 0; i < password.length(); i++) {
         char c = password.charAt(i);						// 입력받은 비밀번호를 아스키코드로 바꾼다.
         decryptedPassword += (char) (c * KEY);				// 문자열 c를 13번째 줄에 선언한 KEY = 3 이므로 곱한다.
      }	
      return decryptedPassword;								// 암호화된 비밀번호를 리턴
   }

   // 비밀번호 변경(비밀번호 찾기 서비스)
   public void changePassword(User user) {
      User userOriginal = checkId(user.getId());				// 외부에서 사용자가 입력한 정보 중, 아이디로 전체 정보 조회
      userOriginal.setPassword(encrypt(user.getPassword()));	// 해당 회원의 비밀번호를 새로운 비밀번호로 변경
   }

   	// 인증번호 전송
   	// 인증번호 전송 시 전송된 인증번호가 화면에서 필요하다.
   	// 따라서 전송한 인증번호를 사용한 화면쪽으로 리턴해준다.
   public String sendSms(String phoneNumber) {
      String api_key = "NCSBGR1K2TNLV4TT";
      String api_secret = "#ENTER_YOUR_OWN#";
      Message coolsms = new Message(api_key, api_secret);
      String certificationNumber = getCertificationNumber();	// 만든 인증번호 6자리

      HashMap<String, String> params = new HashMap<String, String>();
      params.put("to", phoneNumber);		// 사용자가 입력한 핸드폰 번호로
      params.put("from", "01000000000");
      params.put("type", "SMS");			// 인증번호 발송
      params.put("text", certificationNumber);
      params.put("app_version", "test app 1.2"); // application name and version

      try {
         JSONObject obj = (JSONObject) coolsms.send(params);
         System.out.println(obj.toString());
      } catch (CoolsmsException e) {
         System.out.println(e.getMessage());
         System.out.println(e.getCode());
      }
      return certificationNumber;				// 화면쪽에 발송한 인증번호를 리턴
   }
   
   // 인증번호 6자리 만들기
   private String getCertificationNumber() {
      Random r = new Random();
      String number = "";
      number = r.nextInt(1000000) + "";		// 000000~999999  // 앞에 0이 붙으면 정수이기 때문에 생략된다.
      // 생략된 0만큼 앞에 직접 연결시켜주어야 한다. 예를 들어 000010 이면 10으로 나오기때문에 생략된 4개를 직접 연결
      for (int i = 0; i < 6 - number.length(); i++) {
         number = "0" + number;
      }
      return number;
   }
}



















