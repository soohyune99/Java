package jobTask;

import java.util.Random;

public class OakSellerTeacher {

   String[] table = {"사원", "팀장", "다이아"};		// 직급 배열 0 = 사원, 1 = 팀장, 2 = 다이아
   int[] pay = {0, 10_000, 100_000};			// 승진시 지불해야하는 금액
   int[] incomes = {1_000, 3_000, 9_000};		// 수입
   
   String name;				// 이름	
   int positionNumber;		// 승진 숫자 
   int successRating;		// 성공 확률
   int income;				// 수익금액
   int count;				// 실패 할때 실패 횟수
   
   Random r = new Random();		// 랜덤을 돌리기 위한
   
   public OakSellerTeacher() {
      this("한동석", 0);					// 초기값, (이름, 통장 잔고)
   }
   
   public OakSellerTeacher(String name, int positionNumber) {		
      this.name = name;
      this.positionNumber = positionNumber;						// 이름과 승진 숫자
      this.successRating = 50;									// 성공 확률을 50%로
   }
   
   int sell() { 											//성공 : 0, 대성공 : 1, 실패 : -1
      if(rate(successRating)) { 							//성공
         count = 0;											// 성공하면 다시 실패 횟수 0으로 초기화
         if(rate(20)) {										// 성공 확률인 20%이면 대성공,
            income += incomes[positionNumber] * 3;			// 대성공시 수입이 성공시 받을 금액의 3배
            successRating += 5;								// 성공 확률 5% 증가
            return 1;										// 1을 반환한다.
         }
         income += incomes[positionNumber];					// 성공 시 수입금액 합산
         successRating -= 2;								// 성공 확률이 2% 씩 감소
         return 0;											// 0을 반환한다.
      }
      														//실패
      count++;												// 실패 시 판매 수량 증가
      income -= 2_000;										// 수입 금액 2000만원씩 감소
      return -1;											// -1을 반환
   }
   
   void demote() {											// 강등 
      positionNumber--;										// 승진 숫자 감소
   }
   
   boolean promote() {										// 승진
      income -= pay[positionNumber+1];						// 갖고 있는 직급에서 하나 승진하면 수입금액에서 승진 시 지불해야하는 금액 차감
      if(positionNumber == 0 ? rate(30) : rate(10)) {		// 만약 직급이 사원이라면 승진할 수 있는 비율이 30% 사원이 아니면 10% 승진
         positionNumber++;									
         return true;										// 반환을 true
      }	
      return false;											// 직급이 사원이 아니면 반환을 false
   }
   
   boolean rate(int rating) {							
      int[] percent = new int[100];							
      for (int i = 0; i < rating; i++) {percent[i] = 1;}
      
      return percent[r.nextInt(percent.length)] == 1;
   }
}















