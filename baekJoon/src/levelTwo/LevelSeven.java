package levelTwo;

import java.util.Scanner;

public class LevelSeven {
	public static void main(String[] args) {
		int num1 = 0, num2 = 0, num3 = 0;
		
		Scanner sc = new Scanner(System.in);
		
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		num3 = sc.nextInt();
		
		if(num1 == num2 && num2 == num3) {
			System.out.println(10000 + num1*1000);
		}else if(num1 == num2 || num1 == num3) {
			System.out.println(1000 + num1*100);
		}else if(num2 == num3) {
			System.out.println(1000 + num2*100);
		}else {
			System.out.println(Math.max(Math.max(num1, num2), num3) * 100);
		}
	}

}
