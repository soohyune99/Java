package levelThree;

import java.util.Scanner;

//End of File의 약자로, 데이터 소스로부터 더 이상 읽을 수 있는 데이터가 없음
// 데이터가 없음을 어떻게 알려줄까?
// hasNext() 메소드를 사용
// 입력된 토큰이 있으면 true를 반환하고, 그렇지 않을 경우 false를 반환
public class LevelTwelve {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextInt()) {
			
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			System.out.println(A + B);
		}
		sc.close();
	}
}


