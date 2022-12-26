package levelThree;

import java.util.Scanner;

public class LevelThirteen {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int sameN = N;
		int count = 0;
		
		while(true) {
			N = ((N%10) * 10) + (((N/10)+(N%10)) % 10);
			count ++;
			
			if(N == sameN) {
				break;
			}
		}
		System.out.println(count);
	}
}
