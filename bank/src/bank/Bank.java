package bank;

public class Bank {
	// 모든 Bank객체에서 공유하는 static변수 사용
   public static Bank[][] arrBank = new Bank[3][100];	// 3개의 은행, 각 은행별 고객 수 100명씩 유치 가능
   public static int[] arCount = new int[3];			// 각 은행별 가입 회원 수, 은행별 계좌가 개설된 고객 수
   		
   private String name;			// 이름
   private String account;		// 계좌 번호
   private String phoneNumber;	// 전화 번호
   private String password;		// 비밀 번호
   private int money;			// 잔액
   
   public Bank() {;}

   public Bank(String name, String account, String phoneNumber, String password, int money) {
      this.name = name;
      this.account = account;
      this.phoneNumber = phoneNumber;
      this.password = password;
      this.money = money;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAccount() {
      return account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public int getMoney() {
      return money;
   }

   public void setMoney(int money) {
      this.money = money;
   }
   
   // static을 붙여서 선언한 메소드
   // 객체화 없이 아용해야 할때가 있기 때문
   // 객체화 없이 사용하면 편하기 때문
   
   // 계좌번호 중복검사
   // 외부에서 생성된 계좌번호를 전달받고 전체 사용자의 계좌번호와 비교한다.
   public static Bank checkAccount(String account) {
      // 중복이 없다면 bank에 null이 담기고, 중복이 있다면 해당 객체가 bank에 담긴다.
	   Bank bank = null;
      for (int i = 0; i < arrBank.length; i++) {			// 은행 수만큼 반복
         int j = 0;											// for문 영역 밖에서 사용하기 위해 이곳에 선언
         for (j = 0; j < arCount[i]; j++) {					// 각 은행별 가입된 회원 수 만큼 반복
            if(arrBank[i][j].account.equals(account)) {		// 각 회원들의 계좌번호와 생성된 계좌번호 비교
            	bank = arrBank[i][j];						// 만약 일치하는 계좌번호가 있다면 해당 객체를 bank에 담아준다.
            	break;										// 더이상 반복을 진행할 필요가 없다.
            }
         }
         //위에서 강제로 break 했다면 j가 arCount[i]까지 증가하지 못하기 때문에
         // 위에서 break 후 밖에 있는 for문도 break 해준다.
         if(j != arCount[i]) {break;}
      }
      return bank;
   }
   
   // 핸드폰 번호 검사
   public static Bank checkPhoneNumber(String phoneNumber) {
	// 중복이 없다면 bank에 null이 담기고, 중복이 있다면 해당 객체가 bank에 담긴다.
      Bank bank = null;
      for (int i = 0; i < arrBank.length; i++) {					// 은행 수만큼 반복
         int j = 0;													// for문 영역 밖에서 사용하기 위해 이곳에 선언	
         for (j = 0; j < arCount[i]; j++) {							// 각 은행별 가입된 회원 수 만큼 반복
            if(arrBank[i][j].phoneNumber.equals(phoneNumber)) {		// 각 회원들의 전화번호와 생성된 전화번호 비교
               bank = arrBank[i][j];								// 만약 일치하는 계좌번호가 있다면 해당 객체를 bank에 담아준다.
               break;												// 더이상 반복을 진행할 필요가 없다.
            }
         }
         //위에서 강제로 break 했다면 j가 arCount[i]까지 증가하지 못하기 때문에
         // 위에서 break 후 밖에 있는 for문도 break 해준다.
         if(j != arCount[i]) {break;}
      }
      return bank;
   }
   
   // 로그인
   // 외부에서 입력받은 계좌번호와 비밀번호를 전달받는다.
   public static Bank login(String account, String password) {
	   // 계좌번호는 checkAccount(account)에 전달한다.
	   // 1) 계좌가 있다면 해당 회원의 전체 정보를 가져온다.
	   // 2) 계좌가 없다면 null을 가져온다.
      Bank bank = checkAccount(account);
      
      if(bank != null) {								// bank가 null이 아닐때(계좌번호가 존재)
         if(bank.password.equals(password)) {			// 해당 객체의 비밀번호와 입력받은 비밀번호가 같은지 비교
            return bank;								// 비밀번호가 같다면 bank객체에는 비밀번호가 담겨있다.
         }
      }
      return null;										// 비밀번호가 같지않다면 null이 담겨있다.
   }
   
//   입금
   public void deposit(int money) {
      this.money += money;								
   }
//   출금
   public void withdraw(int money) {
      this.money -= money;
   }
//   잔액조회
   public int showBalance() {
      return money;
   }
   
}











