package levelThree;

import java.util.Scanner;

public class LevelNine {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int starCount = sc.nextInt();
		
		for (int i = 0; i <= starCount; i++) {
			for (int j = 0; j <= starCount - i ; j++) {
				System.out.print(" ");
			}
			for (int k = 0; k < i; k++) {
				System.out.print("*");
				
			}
			System.out.println();
		}
	}

}
