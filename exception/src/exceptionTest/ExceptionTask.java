package exceptionTest;

import java.util.Scanner;

public class ExceptionTask {

	// 5개의 정수만 입력받기
	// - 무한 입력상태로 구현
	// - q 입력시 나가기
	// 5개의 정수는 배열에 담기
	// if문은 딱 한번 사용하기
	
		public static void main(String[] args) {
			int[] arData = new int[5];					// 5개의 정수를 배열에 담기
			String msg = "번째 정수 입력: ", temp = null;
			int index = 0;
			
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				System.out.println(++index + msg);		// 몇번째인지 알려주는 출력문
				temp = sc.next();						// 입력받는 변수
				if(temp.equals("q")) {break;}			// 만약 q를 입력하면 조건문 종료
				
				try {
					// arData[index - 1]인 이유는 22번째줄에서 index를 증가시켜서 index -1을 해야한다.
					arData[index - 1] = Integer.parseInt(temp);		// 문자를 int로 바꿔준다.	
				}catch(NumberFormatException e){					// 정수를 입력하지 않았을때의 예외 처리
					System.out.println("정수만 입력하세요.");
					index--;										//	잘 못 입력했을때 그 순서 넘어가지 않도록 
				}catch(ArrayIndexOutOfBoundsException e) {			// 5개 이상의 정수를 입력할때의 예외 처리
					System.out.println("5개만 입력 가능합니다.");
					for (int j = 0; j < arData.length; j++) {		// 그동안 입력받은 정수들 출력
						System.out.print(arData[j] + "");
						break;
					}
				
				}catch(Exception e) {								// 예측할 수 없는 오류의 예외 처리
					System.out.println("?");
					index--;
				}
			}
		}
}
