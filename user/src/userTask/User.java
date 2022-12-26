package userTask;

public class User {
//	 - 이름, 아이디, 비밀번호, 휴대폰번호
//	 - private으로 선언
   private String name;
   private String id;
   private String password;
   private String phoneNumber;
   
//  - 기본 생성자만 선언
   public User() {;}
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getPhoneNumber() {
      return phoneNumber;
   }
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
   
   // 비밀번호 복호화(해독 = decrypt)
   private String decrypt(String password) {
      String decryptedPassword = "";
      for (int i = 0; i < password.length(); i++) {
    	  // 비밀번호를 암호화 했을때 *3을 했으므로 그것을 풀때 /3을 한다.
         decryptedPassword += (char)(password.charAt(i) / 3);
      }
      return decryptedPassword;
   }
   
   // toString( ) 재정의
   @Override
   public String toString() {
      String str = name + "," + id + "," + phoneNumber + "," + decrypt(password);
      return str;
   }
}















