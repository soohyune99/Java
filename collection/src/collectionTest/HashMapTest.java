package collectionTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class HashMapTest{
	public static void main(String[] args) {
		// String클래스의 KEY와 Object 클래스의 VALUE를 저장
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", "hds1234");
		userMap.put("pw", "1234");
		userMap.put("name", "한동석");
		userMap.put("age", 20);
		
		System.out.println(userMap.size());		// 4 출력
		System.out.println(userMap);			// {pw=1234, name=한동석, id=hds1234, age=20} 출력
		System.out.println(userMap.get("id"));	// 검색
		
		Iterator<Entry<String, Object>> iter =  userMap.entrySet().iterator();
		
		while(iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		// KEY값만 나오게 
		Iterator<String> iter2 = userMap.keySet().iterator();
		      
		System.out.println("=====================");
		      
		while(iter2.hasNext()) {
			System.out.println(iter2.next());
			}
		
		// Value값만 나오게
		System.out.println("=====================");
		for (Object value : userMap.values()) {
			System.out.println(value);
			}

		
	}
}



