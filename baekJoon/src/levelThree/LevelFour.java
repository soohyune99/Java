package levelThree;

import java.util.Scanner;

public class LevelFour {
	public static void main(String[] args) {
		int receiptAllCount = 0, receiptProductCount = 0;
		
		int price = 0, cnt = 0, total = 0;
		
		Scanner sc = new Scanner(System.in);
		
		receiptAllCount = sc.nextInt();
		receiptProductCount = sc.nextInt();
		
		for (int i = 0; i < receiptProductCount; i++) {
			price = sc.nextInt();
			cnt = sc.nextInt();
			total += price * cnt;
		}
		
		if(total == receiptAllCount) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		
	}

}
