package collectionTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
	public static void main(String[] args) {
		HashSet<String> bloodTypes = new HashSet<String>();
		ArrayList<String> bloodTypeList = null;

		// HashSet에서 add는 사용할수 없는데 어떻게 사용되었을까
		// add를 사용하면 HashSet 타입이 아닌 iterator 타입으로 변경되어서 사용가능
		bloodTypes.add("A");
		bloodTypes.add("B");
		bloodTypes.add("O");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		bloodTypes.add("AB");
		
		      
		System.out.println(bloodTypes.toString());			// 위에 혈액형 AB를 많이 추가해도 중복되는 값 무시한다.
		
		bloodTypeList = new ArrayList<String>(bloodTypes);
		System.out.println(bloodTypeList.get(0));			// ArrayList<String>에 담은 bloodTypeList

		Iterator<String> iter =  bloodTypes.iterator();		// Iterator 순서부여
		
		while(iter.hasNext()) {								// hasNext()에서 다음값이 있다면 true 없다면 false
			System.out.println(iter.next());
		}
	}
}









