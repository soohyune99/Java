package collectionTest;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListTest {
	public static void main(String[] args) {
		
//		<?> : 제네릭(이름이 없는) 
//		객체화 시 타입을 지정하는 기법.
//		설계할 때에는 타입을 지정할 수 없기 때문에 임시로 타입을 선언할 때 사용한다.
//		따로 다운 캐스팅을 할 필요가 없다.
//		지정할 타입에 제한을 줄 수 있다.
		
		ArrayList<Integer> datas = new ArrayList<>();
		
		datas.add(10);
		datas.add(20);
		datas.add(40);
		datas.add(50);
		datas.add(90);
		datas.add(80);
		datas.add(70);
		datas.add(60);
		
		// 배열의 length()와 비슷한 size()
//		System.out.println(datas.size());	// 결과값은 8
		
		// datas의 모든 값 출력
		for (int i = 0; i < datas.size(); i++) {
//			System.out.println(datas.get(i));
		}
		
		// [ 10, 20, 40, 50, 90, 80, 70, 60] 출력
//		System.out.println(datas);
		
		
		// 오름차순 정렬, upcasting
		Collections.sort(datas);
//		System.out.println(datas);
		
		// 내림차순 정렬이 없어서 reverse()를 써줘야한다.
		Collections.reverse(datas);
//		System.out.println(datas);
		
		// 섞는다.
		Collections.shuffle(datas);
//		System.out.println(datas);
		
		// 추가(삽입)
		// 50 뒤에 500 삽입
		if(datas.contains(50)) {
			datas.add(datas.indexOf(50) + 1 , 500);	// 50뒤에 삽입되어야해서 +1
		}
		System.out.println(datas);
		// 수정
		// 90을 9로 수정
		if(datas.contains(90)) {
			System.out.println(datas.set(datas.indexOf(90), 9) + "이 9로 변경되었습니다.");
		}
		System.out.println(datas);
		
		// 삭제
		// 80 삭제
		// 1. 인덱스로 삭제
		if(datas.contains(80)) {
			System.out.println(datas.remove(datas.indexOf(80)) + "삭제" );
		}
		System.out.println(datas);
		
		// 2. 값으로 삭제
		// int인 80을 Object인 Integer로 바꾸기 위해서는 boxing이 필요하다
		if(datas.remove(Integer.valueOf(80))) {
			System.out.println("삭제 완료");
		}
	}
	
}








