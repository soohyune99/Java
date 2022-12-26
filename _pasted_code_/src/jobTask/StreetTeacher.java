package jobTask;

import java.util.Scanner;

public class StreetTeacher {
   public static void main(String[] args) {
	   OakSellerTeacher seller = new OakSellerTeacher();
      Scanner sc = new Scanner(System.in);
      
      String msg = "1. 판매하기\n2. 승진하기\n3. 내 정보\n4. 나가기";
      int choice = 0, resultSold = 0;
      
      while(true) {
         System.out.println(msg);
         choice = sc.nextInt();
         if(choice == 4) {break;}										// 만약 4번을 선택하면 while문 나가기
         
         switch(choice) {												// msg에 있는 번호 
         case 1: 														// 1. 판매하기	선택 시
            resultSold = seller.sell();									// 내정보.sell()	
            
            switch(resultSold) {
            case 0 : 													//성공
               System.out.println("휴~ 옥장판 판매 성공!");
               break;
            case 1 : 													//대성공
               System.out.println("아자뵤~! 옥장판 판매 대성공!");
               break;
            case -1 :													//실패
               System.out.println("앗");
               if(seller.count == 3 && seller.positionNumber != 0) {	// 판매 개수가 3개이고 직급이 0이 아니면, 팀장과 다이어이면
                  seller.demote();										// 직급 강등
                  System.out.println(seller.table[seller.positionNumber] + "으로 좌천되었습니다...");
               }
               break;
            }
            System.out.println("내 통장 잔고 : " + seller.income + "만원");	// 수입 금액 공개
            
            break;
         case 2: 														// 2. 승진하기 선택 시
            if(seller.positionNumber == 2) {							// 만약 직급이 다이아이면
               System.out.println("승진하실 수 없습니다.");					// 승진 불가
               break;
            }
            if(seller.promote()) {										// 만약 승진이
               seller.count = 0;										// 판매 수량이 0일때, 실패가 없을때
               System.out.println(seller.table[seller.positionNumber] + "(으)로 승진하셨습니다!!!!");	
               System.out.println("내 통장 잔고 : " + seller.income + "만원");// 승진 시 지불
               break;
            }
            System.out.println("승진 실패....");
            System.out.println("내 통장 잔고 : " + seller.income + "만원");
            break;
         case 3: 														// 3. 내 정보 선택 시
            System.out.println("이름 : " + seller.name);					
            System.out.println("직급 : " + seller.table[seller.positionNumber]);	
            System.out.println("내 통장 잔고 : " + seller.income);
            System.out.println("판매 성공률 : " + seller.successRating + "%");
            break;
         }
      }
      
   }
}









