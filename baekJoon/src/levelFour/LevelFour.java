package levelFour;

import java.util.Arrays;
import java.util.Scanner;

public class LevelFour {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int subjectCount = sc.nextInt();
		double[] scoreAr = new double[subjectCount];
		double[] avg = new double[subjectCount];
		double avgTotal = 0.0;
		
		for (int i = 0; i < subjectCount; i++) {
			scoreAr[i] = sc.nextDouble();
//			System.out.println(scoreAr[i]);
		}
		
		Arrays.sort(scoreAr);
		double maxScore = scoreAr[scoreAr.length-1];
//		System.out.println(maxScore);
		
		for (int i = 0; i < subjectCount; i++) {
			avg[i] = ((scoreAr[i] / maxScore) * 100);
			avgTotal += avg[i];
//			System.out.println(avgTotal);
		}
		System.out.println(avgTotal / subjectCount);
		
		
		
	}
}
