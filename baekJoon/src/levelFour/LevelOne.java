package levelFour;

import java.util.Arrays;
import java.util.Scanner;

public class LevelOne {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] ar = new int[N];
		
		for (int i = 0; i < N; i++) {
			ar[i] = sc.nextInt();
		}
		
		// 배열 정렬
		Arrays.sort(ar);
		int max = ar[ar.length-1];
		int min = ar[0];
		System.out.println(min + " " + max);
	}
}
