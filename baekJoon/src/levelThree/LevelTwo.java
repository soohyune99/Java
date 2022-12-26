package levelThree;

import java.util.Scanner;

public class LevelTwo {
	public static void main(String[] args) {
		
		int testCase = 0, A = 0, B = 0;
		
		Scanner sc = new Scanner(System.in);
		testCase = sc.nextInt();
		
		for (int i = 0; i < testCase; i++) {
			A = sc.nextInt();
			B = sc.nextInt();
			System.out.println(A + B);
		}
	}
}
