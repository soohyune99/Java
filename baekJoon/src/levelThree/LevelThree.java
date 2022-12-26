package levelThree;

import java.util.Scanner;

public class LevelThree {
	public static void main(String[] args) {
		
		int wantNumber = 0, total = 0;
		
		Scanner sc = new Scanner(System.in);
		wantNumber = sc.nextInt();
		
		for (int i = 0; i < wantNumber; i++) {
			total += i + 1 ;
		}
		System.out.println(total);
	}

}
