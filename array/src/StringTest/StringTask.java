package StringTest;

import java.util.Scanner;

public class StringTask {
	public static void main(String[] args) {
//		사용자에게 입력받은 문자열 중 소문자는 모두 대문자로,
//		대문자는 모두 소문자로 변경한다.
		
//		String message = null;  // 문자열 배열
//		String result = "";		// 바뀐 결과, 누적연결(+=)을 사용해야 할때 "" 사용
//		
//		System.out.println("문자를 입력해주세요.: ");
//		Scanner sc = new Scanner(System.in);	// 입력받을 준비
//		message = sc.next();					// message로 입력받음
//		
//		for (int i = 0; i < message.length(); i++) {  // 문자열의 길이만큼 반복을 돌릴 준비
//			char c  = message.charAt(i);			  // i번째있는 문자릉 char c에 담을 준비
//			if((c >= 65 && c <= 90)) {				  // c가 65이상 90이하면 -> 대문자
//				result += (char)(c + 32);			  // c를 32더해서 소문자로 변경
//			}else if((c >= 97 && c <= 122)) {		  // c가 97이상 122이하면 -> 소문자
//				result += (char)(c - 32);			  // c를 32빼서 대문자로 변경
//			}else {
//				result += c;
//			}
//		}System.out.println(result);
		
//		정수를 한글로 변경
//		예) 1024
//		     -> 일공이사
		
		String hangle = "공일이삼사오육칠팔구";
		int num = 0;							// 입력받을 숫자
		String result = "";
		
		System.out.println("숫자를 입력해주세요: ");
		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		
		while(num != 0) {							// 0이 아니면 조건식 실행
			result = hangle.charAt(num % 10) + result;		// num의 나머지
			num /= 10;								// 윗줄의 num의 나머지를 없애기 위해 나누기 10을 하여  
		}
		System.out.println(result);					// 
		
		
//		  for (int i = 0; i < number.length(); i++) {
//		         result += hangle.charAt(number.charAt(i) - 48);
//		   }
//		      
//		   System.out.println(result);

		
	}
}
