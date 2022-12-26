package levelThree;

import java.util.Scanner;

public class LevelOne {
	public static void main(String[] args) {
		
		int wantNumber = 0, multiple = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("n을 입력하세요: ");
		wantNumber = sc.nextInt();
		
		for (int i = 1; i < 10; i++) {
			multiple = wantNumber * i;
			System.out.println(wantNumber + " * " + i + " = " + multiple);
		}
	}

}
