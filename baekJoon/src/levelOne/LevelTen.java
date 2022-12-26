package levelOne;

import java.util.Scanner;

public class LevelTen {
	public static void main(String[] args) {
		int king = 0, queen = 0, rook = 0, bishop = 0, knight = 0, pawn = 0;
		int result1 = 0, result2 = 0, result3 = 0, result4 = 0, result5 = 0, result6 = 0;
		
		Scanner sc = new Scanner(System.in);
		
		king = sc.nextInt();
		queen = sc.nextInt();
		rook = sc.nextInt();
		bishop = sc.nextInt();
		knight = sc.nextInt();
		pawn = sc.nextInt();
		
		result1 = 1 - king;
		result2 = 1 - queen;
		result3 = 2 - rook;
		result4 = 2 - bishop;
		result5 = 2 - knight;
		result6 = 8 - pawn;
		
		System.out.print(result1 + " ");
		System.out.print(result2 + " ");
		System.out.print(result3 + " ");
		System.out.print(result4 + " ");
		System.out.print(result5 + " ");
		System.out.print(result6);
		
		
		
	}
}
