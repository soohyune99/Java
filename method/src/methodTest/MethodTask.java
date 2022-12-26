package methodTest;

public class MethodTask {
	
//	1~10까지 println()으로 출력하는 메소드
	void printFrom1To10() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i + 1);
		}
	}
	
//	"홍길동"을 n번 println()으로 출력하는 메소드
	
	void printHong(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println("홍길동");
		}
	}
	
//	이름을 n번 println()으로 출력하는 메소드
	void printName(String name, int n) {
		for (int i = 0; i < n; i++) {
			System.out.println(name);
		}
	}
	
//	세 정수의 뺄셈을 해주는 메소드
	int minus(int num1, int num2, int num3) {
		int result= num1 - num2 - num3;
		return result;
	}
	
//	두정수의 나눗셈을 해주는 메소드(몫과 나머지 둘다)
	int[] divide(int num1, int num2) {
		int [] results = null;
		if(num2 != 0) {
			results = new int[2];
			
			results[0]= num1 / num2;
			results[1] = num1 % num2;
		}
		return results;
	}
	
//  1~n까지의 합을 구해주는 메소드
	int numberPlus(int n) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += i + 1 ;
		}
		return sum;
	}
	
//  홀수를 짝수로, 짝수를 홀수로 바꿔주는 메소드
	int changeNumber(int number) {
		return ++number;
	}
	
//  문자열을 입력받고 소문자는 대문자로, 대문자는 소문자로 바꿔주는 메소드
	String changeAlpha(String word) {
		String result = "";
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if((c >= 65 && c <= 90)) {				  
				result += (char)(c + 32);			  
			}else if((c >= 97 && c <= 122)) {		 
				result += (char)(c - 32);			  
			}else {
				result += c;
			}
		}return result;
	}
	
//  문자열을 입력받고 원하는 문자의 개수를 구해주는 메소드
	int countWord(String word, char c) {		// 원하는 문자를 char c
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == c) {
				count++;
			}
		}
		return count;
	}
	
//  5개의 정수를 입력받은 후 원하는 번째 값을 구해주는 메소드
	int fiveNumber(int [] arData, int wantNum) {
		return arData[wantNum - 1];
	}
	
//  한글을 정수로 바꿔주는 메소드
	int changeHangel(String Korean) {
		String hangel = "공일이삼사오육칠팔구";
		String result = "";
		
		for (int i = 0; i < Korean.length(); i++) {
			result += hangel.indexOf(Korean.charAt(i));		
		}
		return Integer.parseInt(result);
	}
	
//  5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드
	int[] getMaxAndMin(int[] nums) {
		int[] results = new int[2];
		results[0] = nums[0];		// 최대값
		results[1] = nums[0];		// 최소값
		
		for (int i = 1; i < nums.length; i++) {
			if(results[0] < nums[i]) {results[0] = nums[i];}
			if(results[1] > nums[i]) {results[1] = nums[i];}
		}
		return results;
	}
	
//  어려움★★★★★
//  5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드(void로 하되, 출력 없이 사용하는 부분에 결과를 전달한다)
//  매개변수는 2개 전달해야 한다.
//  예) getMaxAndMin();
//     여기서부터 최대값, 최소값 사용 가능
	
	void getMaxAnaMin(int[] nums, int[] results) {
		results[0] = nums[0];		// 최대값
		results[1] = nums[0];		// 최소값
		
		for (int i = 1; i < nums.length; i++) {
			if(results[0] < nums[i]) {results[0] = nums[i];}
			if(results[1] > nums[i]) {results[1] = nums[i];}
		}
	}

	
	public static void main(String[] args) {
		MethodTask mt = new MethodTask();
		
//		mt.printFrom1To10();
		
//		mt.printHong(10);
		
//		mt.printName("홍길동", 10);
		
//		System.out.println(mt.minus(5, 4, 2));
		
//		int[] results = mt.divide(10, 0);
//		String msg = null;
//		msg = results == null ? "0으로 나눌 수 없습니다." : "몫 : " + results[0] + "\n나머지 : " + results[1];
//		System.out.println(msg);

//  1~n까지의 합을 구해주는 메소드
//		int numberPlus(int n) 
//		System.out.println(mt.numberPlus(10));
		
//      홀수를 짝수로, 짝수를 홀수로 바꿔주는 메소드
//      int change(int number)
//      boolean result = mt.change(11) % 2 == 0;
//      System.out.println(result ? "홀수에서 짝수로 바뀌었습니다." : "짝수에서 홀수로 바뀌었습니다.");
      
//      문자열을 입력받고 소문자는 대문자로, 대문자는 소문자로 바꿔주는 메소드
//      String changeString(String string)
//      System.out.println(mt.changeString("aPplE1234!@#$"));
   
//      문자열을 입력받고 원하는 문자의 개수를 구해주는 메소드
//      int getCount(String string, char c)
//      System.out.println(mt.getCount("apple", 'p') + "개");
      
//      5개의 정수를 입력받은 후 원하는 번째 값을 구해주는 메소드
//      int getValue(int[] nums, int wantedNumber)
//      int[] arData = {1, 2, 5, 7, 8};
//      System.out.println(mt.getValue(arData, 3));
      
//      한글을 정수로 바꿔주는 메소드
//      int changeToInteger(String hangle)
//      int result = mt.changeToInteger("삼공오이사");
//      System.out.println(result - 3);
      
//      5개의 정수를 입력받고 최대값과 최소값을 구해주는 메소드
//      void getMaxAndMin(int[] nums, int[] results)
//      int[] arData = {1, 2, 5, 7, 8};
//      int[] results = new int[2];
//      
//      mt.getMaxAndMin(arData, results);
//      
//      System.out.println("최대값 : " + results[0]);
//      System.out.println("최소값 : " + results[1]);

	}
}




















