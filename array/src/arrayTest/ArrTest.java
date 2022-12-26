package arrayTest;

public class ArrTest {
	public static void main(String[] args) {
		int[][] arrData = {{3, 4, 5, 6}, {8, 9, 0, 9},{1, 2, 3, 4}};
		
		System.out.println(arrData.length);		// 3 출력
		System.out.println(arrData[0].length);	// 4 출력
		
		// 전체 길이 구하는 방법
		int length = arrData.length * arrData[0].length;	
		for (int i = 0; i < length; i++) {
			System.out.println(arrData[i / 4][i % 4]);
		}
		
//		이중 for문
//		for (int i = 0; i < arrData.length; i++) {
//			for (int j = 0; j < arrData[i].length; j++) {
//				System.out.println(arrData[i][j]);
//			}
//		}
		
	}

}
