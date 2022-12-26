package levelFour;

import java.util.HashSet;
import java.util.Scanner;

// HashSet : 중복을 허용하지 않는다.

public class LevelThree {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		HashSet<Integer> set =  new HashSet<Integer>();
		
		for (int i = 0; i < 10; i++) {
			// 이미 여기서 중복 제거
			set.add(sc.nextInt() % 42);
		}
		System.out.println(set.size());
	}
}
