package levelFour;

import java.util.Scanner;

public class LevelTwo {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] ar = new int[9];
		
		int count = 0;
		int max = 0;
		int index = 0;
		
		for (int i = 0; i < 9; i++) {
			ar[i] = sc.nextInt();
			count++;
			
			if(ar[i] > max) {
				max = ar[i];
				index = count;
			}
		}
		System.out.println(max + "\n" + index);
	}
}
