package levelThree;

import java.util.Scanner;

public class LevelTen {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int X = sc.nextInt();
		int[] ar = new int[N];
		
		for (int i = 0; i < N; i++) {
			// 10개의 정수를 받음
			ar[i] = sc.nextInt();
		}
		// 10개의 정수 x와 비교
		for (int i = 0; i < N; i++) {
			if(ar[i] < X) {
				System.out.print(ar[i] + " ");
			}
		}
	
	}

}
