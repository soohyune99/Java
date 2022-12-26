package lambdaTest;

import lamda.LambdaInter;

public class LambdaTest {
	public static void main(String[] args) {
		// 람다식 1번
		LambdaInter lambdaInter1 = (number) -> number % 10 == 0;
		boolean result = lambdaInter1.checkMultipleOf10(11);
		System.out.println(result);
		
		// 람다식 2번
		LambdaInter lambdaInter2 = (n) -> {
			System.out.println("10의 배수 검사");
			return n % 10 == 0;
		};
		boolean result2= lambdaInter2.checkMultipleOf10(20);
		System.out.println(result2);
	}
}



