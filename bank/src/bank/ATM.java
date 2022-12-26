package bank;

import java.util.Random;
import java.util.Scanner;

public class ATM {
   public static void main(String[] args) {
//      은행 선택
      String msg ="1. 신한은행\n2. 국민은행\n3. 동석은행\n4. 나가기";
      
      /*
       * 1. 계좌개설
       * - 13자리 랜덤 계좌번호
       * - 기존 고객의 계좌번호와 중복이 없는 번호로 개설해야 한다.
       * - 핸드폰 번호는 숫자만 입력하도록 하고, 문자가 포함되면 안되고 0~9사이의 정수여야 한다.
       * - 비밀번호 4자리로만 입력하도록 한다.
       * 
       * 2. 입금하기
       * - 계좌를 개설한 은행에서만 입금 가능
       * 
       * 3. 출금하기
       * 4. 잔액조회
       * 5. 계좌번호 찾기
       * 6. 나가기
       */
      
      String menu = "1. 계좌개설\n2. 입금하기\n3. 출금하기\n4. 잔액조회\n5. 계좌번호 찾기\n6. 은행선택메뉴로 이동";
      String name = null, account = "", phoneNumber = null, password = "";
      int money = 0, bankNumber = 0, choice = 0;
      // 고객의 은행 번호로 은행이름을 알아내기 위함.
      String[] bankName = {"신한은행", "국민은행", "동석은행"};
      
      Scanner sc = new Scanner(System.in);
      Random r = new Random();
      
      // 검사한 객체를 담을 저장공간
      Bank bank = null;	
      
      
      while(true) {
         System.out.println(msg);		// 은행 선택하라는 메시지 출력
         bankNumber = sc.nextInt();		// bankNumber가 입력을 담는다
         if(bankNumber == 4) {break;}	// 만약 bankNumber가 4번, 나가기를 선택하면 조건문 나가기
         
         while(true) {
            System.out.println(menu);	// 메뉴 메시지 출력
            choice = sc.nextInt();		// choice가 입력을 담는다.
            if(choice == 6) {break;}	// 만약 choice가 6번, 은행선택메뉴로 이동을 선택하면 조건문 종료후, 은행 선택하라는 메시지 다시출력
            
            switch(choice) {			// choice
            case 1: //계좌개설
            	// 계좌 개설시 어떤 은행을 선택했는지는 알지만,  각 은행객체 규칙성이 없기때문에
            	// 규칙성을 부여하고자 배열에 담아준다.
               Bank[] arBank = {new ShBank(), new KbBank(), new HanaBank()};

               while(true) {
                  account = "";					
                  for (int i = 0; i < 12; i++) {		// 계좌를 개설한 은행에서만 입금 가능하게 해주기 위해서 13자리가 아닌 12자리로 범위 설정
                     account += r.nextInt(10);			// 계좌번호 랜덤으로 0~ 10까지 account에 담아준다.
                  }
                  // 쓸데없이 객체화를 해야 할 때에는 빨리 static으로 전환한다!
                  // Bank bank = new Bank();
                  //  bank.checkAccount()
                  if(Bank.checkAccount(account) == null) {break;}
               }
               // 계좌를 개설한 은행에서만 입금 가능하게 하려면 
               // 계좌번호 13자리중 은행 번호를 맨앞에 추가해준다.
               account = bankNumber - 1 + account;
               
               arBank[bankNumber - 1].setAccount(account);		// 선택한 은행에 계좌를 설정
               
               System.out.print("예금주 : ");			
               name = sc.next();
               
               arBank[bankNumber - 1].setName(name);			// 선택한 은행에 이름을 설정
               
               while(true) {
                  System.out.print("핸드폰 번호['-' 제외] : ");
                  phoneNumber = sc.next();
                  
                  // 숫자만 있는지 확인
                  int i = 0;
                  for (i = 0; i < phoneNumber.length(); i++) {
                     char c = phoneNumber.charAt(i);				
                     if(c < 48 || c > 57) {					// 사용자가 입력한 각 문자 c가 48(문자'0')보다 작고 57(문자'9')보다 크면
                        break;								// 조건문 탈출
                     }
                  }
                  
                  // 위의 for문에서 강제로 break를 만나서 탈출하면 i는 phone.length()까지 
                  // 절대로 증가할 수 없다.
                  // 따라서 정상적으로 증가하여 i가  phone.length()가 되었다면 
                  // 사용자가 전화번호를 특수문자없이 숫자로 잘 입력했다는 것이다.
                  
                  if(i != phoneNumber.length()) {					// i가 phone.length()가 아니라면
                     System.out.println("숫자만 입력해주세요.");			// 특수문자가 포함되었다라는 의미이다.
                     continue;										// 건너뛴다.
                  }
                  
                  // 입력한 문자열 값이 11자리인지			
                  if(phoneNumber.length() != 11) {					
                     System.out.println("핸드폰 번호를 입력해주세요.");
                     continue;
                  }
                  // 핸드폰 번호가 중복되었는 지
                  if(Bank.checkPhoneNumber(phoneNumber) != null) {
                     System.out.println("중복된 핸드폰 번호입니다.");
                     continue;
                  }
                  // 010으로 시작하는 지
                  if(!phoneNumber.startsWith("010")) {
                     System.out.println("핸드폰 번호를 입력해주세요.");
                     continue;
                  }
                  break;
               }
               
               arBank[bankNumber - 1].setPhoneNumber(phoneNumber);		// 선택한 은행에 전화번호를 설정
               
               password = "";
               while (password.length() != 4) {							// 사용자가 입력한 비밀번호가 4자리가 아니면 계속 반복
                  System.out.print("비밀번호 : ");	
                  password = sc.next();
               }
               
//               do~while문을 사용하였을때의 코드, 
//               대신 28번째 줄에 password를 null 바꿔줘야한다.
//               do {
//                  System.out.print("비밀번호 : ");
//                  password = sc.next();
//               } while (password.length() != 4);
               
               arBank[bankNumber - 1].setPassword(password);			// 선택한 은행에 비밀번호를 설정
               
                  
               // 사용자가 선택한 은행(bankNumber - 1)에
               // arCount[bankNumber - 1]번째 고객으로
               // arBank[bankNumber - 1] 신규고객 저장
               Bank.arrBank[bankNumber - 1][Bank.arCount[bankNumber - 1]] = arBank[bankNumber - 1];
               // 사용자가 선택한 은행의 고객 수 1 증가
               Bank.arCount[bankNumber - 1]++;
               
               System.out.println("🎉🎉🎊🎊🎉🎉🎊🎊🎉🎉🎊🎊🎉🎉🎊🎊🎉🎉🎊🎊🎉🎉🎊🎊");
               System.out.println("축하합니다. 감사합니다. 사랑합니다.");
               System.out.println("더 노력하는 " + bankName[bankNumber - 1] + "이 되겠습니다!");
               System.out.println("고객님의 계좌번호는 " + account + "입니다.");
               System.out.println("분실 시 계좌번호 찾기 서비스를 이용해주세요~!");
               System.out.println("🎉🎉🎊🎊🎉🎉🎊🎊🎉🎉🎊🎊🎉🎉🎊🎊🎉🎉🎊🎊🎉🎉🎊🎊");
               break;
               
            case 2: //입금하기
               System.out.print("계좌번호 : ");
               account = sc.next();
               
               // 계좌를 개설한 은행에서만 입금 가능
               // 계좌번호의 맨 앞부분이 (1=49, 2=50, 3=51) 48을 빼서 선택한 은행의 숫자와 같지않으면
               if(account.charAt(0) - 48 != bankNumber - 1) {		
            	   System.out.println("계좌를 개설한 은행에서만 입금 서비스를 이용할 수 있습니다.");
            	   break;
               }
               System.out.println("비밀번호 : ");
               password = sc.next();
               
               
               bank = Bank.login(account, password);
               if(bank != null) {														// bank가 비어있지 않으면, 계좌번호와 비밀번호 입력 성공
                  System.out.print("입금액 : ");
                  money = sc.nextInt();
                  if(money < 0) {System.out.println("금액이 잘못 입력되었습니다^^"); break;}	// 입력한 돈이 음수이면 다시 입력
                  bank.deposit(money);
                  break;
               }
               System.out.println("계좌번호나 비밀번호를 다시 확인해주세요.");
               break;
               
            case 3: //출금하기
               System.out.print("계좌번호 : ");
               account = sc.next();
               System.out.println("비밀번호 : ");
               password = sc.next();
               
               bank = Bank.login(account, password);
               if(bank != null) {														// bank가 비어있지 않으면, 계좌번호와 비밀번호 입력 성공
                  System.out.print("출금액 : ");
                  money = sc.nextInt();
                  if(money < 0) {System.out.println("금액이 잘못 입력되었습니다^^"); break;}	// 출금할 돈이 음수이면 다시 입력
                  // (국민은행에서의 계좌가 맞으면 수수료 1.5, 국민은행 계좌가 아니면 그냥 출금) 0보다 작으면
                  if(bank.showBalance() - (bank instanceof KbBank ? money * 1.5 : money) < 0) {
                     System.out.println("잔액이 부족합니다"); 
                     break;
                  }
                  bank.withdraw(money);
                  break;
               }
               System.out.println("계좌번호나 비밀번호를 다시 확인해주세요.");
               break;
               
            case 4: //잔액조회
               System.out.print("계좌번호 : ");
               account = sc.next();
               System.out.println("비밀번호 : ");
               password = sc.next();
               
               bank = Bank.login(account, password);
               if(bank != null) {
                  System.out.println("현재 잔액 : " + bank.showBalance() + "원");
               }
               break;
               
            case 5: //계좌번호 찾기
               System.out.print("전화번호 : ");
               phoneNumber = sc.next();
               
               bank = Bank.checkPhoneNumber(phoneNumber);
               
               // 전화번호 입력 후 
               if(bank != null) {
                  System.out.print("비밀번호 : ");
                  password = sc.next();
                  // 객체안에 있는 비밀번호와 지금 입력한 비밀번호를 비교해서 같으면 계좌 랜덤으로 다시 개설
                  if(bank.getPassword().equals(password)) {
                     while(true) {
                        account = "";
                        for (int i = 0; i < 13; i++) {
                           account += r.nextInt(10);
                        }
                        if(Bank.checkAccount(account) == null) {break;}
                     }
                     bank.setAccount(account);
                     System.out.println("고객님의 계좌번호를 새롭게 발급해드렸습니다.");
                     System.out.println("고객님의 새로운 계좌번호는 " + account + "입니다.");
                     System.out.println("분실 시 계좌번호 찾기 서비스를 다시 이용해주세요~!");
                  }
               }
               break;
            }
            
         }
         
      }
      
   }
}
















