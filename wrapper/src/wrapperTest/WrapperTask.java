package wrapperTest;

import java.util.Iterator;

public class WrapperTask {
	public static void main(String[] args) {
		// 1, 12.5, 86.6F, 'Z', "Hi", false
		//위 6개의 값을 6칸 배열에 담기
		
		// auto boxing
		// 모든 클래스는 자동으로 Object를 상속 받는다.
		// datas 라는 변수에 Object의 배열을 담는다.
		Object[] datas = {1, 12.5, 86.6F, 'Z', "Hi", false};
		
		for (int i = 0; i < datas.length; i++) {
			System.out.println(datas[i]);
		}
		

	}
}


