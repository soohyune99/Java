package levelFour;

import java.util.Scanner;

public class LevelFive {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		
		for (int i = 0; i < size; i++) {
			String[] ar = sc.next().split("");
			int count = 0, total = 0;
			
			for (String str : ar) {
				if(str.equals("O")) {
					count++;
					total += count;
				}else if(str.equals("X")) {
					count = 0;
				}
			}
			System.out.println(total);
		}

	}
}