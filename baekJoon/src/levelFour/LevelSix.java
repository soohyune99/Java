package levelFour;

import java.util.Scanner;

public class LevelSix {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		int sum = 0, avg = 0;
		
		for (int i = 0; i < size; i++) {
			int student = sc.nextInt();
			int[] score = new int[student];
			
			for (int j = 0; j < student; j++) {
				score[i] = sc.nextInt();
				sum += score[i];
				avg = sum / student;
			}
			
		}
	}

}
