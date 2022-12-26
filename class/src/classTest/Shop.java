package classTest;

import java.util.Scanner;

class SuperCar{
//	브랜드, 색상, 가격
	String brand;
	String color;
	int price;
	
	boolean check;		// check의 초기값은 false
	
	public SuperCar() {;}	// 생성자	
		
	public SuperCar(String brand, String color, int price) {		// 브랜드, 색깔, 가격 생성자
		this.brand = brand;		
		this.color = color;
		this.price = price;
}

//  시동 켜기
//  시동의 상태를 확인하고 
//  시동이 꺼져있다면, "시동 켜기" 출력
//  이미 시동이 켜져있다면, "시동이 이미 켜져있습니다" 출력
	boolean engineStart() {			
		if(!check) {			// check가 false가 아니면, check가 true면 (시동이 켜져있으면)
			check = true;		// 시동을 true로 해서 시동을 킨다.
			return true;		// true값으로 리턴한다.
		}
		return false;			// check가 true이면 false값으로 리턴한다.
	}
  
//  시동 끄기
//  시동의 상태를 확인하고 
//  시동이 켜져있다면, "시동 끄기" 출력
//  이미 시동이 꺼져있다면, "시동이 이미 꺼져있습니다" 출력
	boolean engineStop() {
		if(check) {				// check가 false면 (시동이 꺼져있으면)
			check = false;		// 시동은 false
			return true;		// true값으로 리턴한다.
		}
		return false;			// check가 false이면 false값으로 리턴한다.
	}


}

public class Shop {
	   public static void main(String[] args) {
	      SuperCar ferrari = new SuperCar();		// 페라리로 객체화
	      
	      String msg = "1.시동켜기\n2.시동끄기";			// 사용자에게 자동차의 상태를 묻는 msg
	      Scanner sc = new Scanner(System.in);		// 입력
	      int choice = 0;							// 입력을 받기위한 choice 변수 선언
	      
	      // do ~ while문 : 무조건 한번은 실행해야 할때
	      do {
	         System.out.println(msg);				// msg 출력
	         choice = sc.nextInt();					// 입력받는 choice
	         
	         
	         // switch문: 하나의 변수에 여러 개의 값이 담길 수 있고, 각 값이 같은지를 비교할 때 사용
	         switch(choice) {						
	         case 1: 								// 시동 켜기
	            if(ferrari.engineStart()) {			// 페라리가 엔진스타트를 했을때 
	               System.out.println("시동 켜짐");	// 시동이 켜짐이 출력
	               break;							// 조건문을 나간다.
	            }
	            System.out.println("이미 시동이 켜져있습니다.");	// 이미 시동이 켜져있음이 출력
	            break;										// 조건문을 나간다.
	            
	         case 2:								// 시동 끄기
	            if(ferrari.engineStop()) {			// 페라리가 엔진스탑를 했을때 
	               System.out.println("시동 꺼짐");	// 시동이 꺼짐이 출력
	               break;							// 조건문을 나간다.
	            }
	            
	            System.out.println("이미 시동이 꺼져있습니다.");	// 이미 시동이 껴져있음이 출력	
	            break;										// 조건문 
	         }	
	      }while(ferrari.check);
	   }
	}




