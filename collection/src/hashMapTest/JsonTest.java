package hashMapTest;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonTest {
	public static void main(String[] args) {
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		JSONObject userJSON = null;
		JSONArray users = new JSONArray();
		
		userMap.put("id", "hds1234");
		userMap.put("pw", "1234");
		userMap.put("name", "한동석");
		userMap.put("age", 20);
		
		userJSON = new JSONObject(userMap);
//		System.out.println(userJSON.toJSONString());
		
		users.add(userJSON);
	
		userMap.put("id", "hgd1234");
		userMap.put("pw", "5555");
		userMap.put("name", "홍길동");
		userMap.put("age", 25);
		
		userJSON = new JSONObject(userMap);
		users.add(userJSON);
		
		System.out.println(users.toJSONString());
		
		// toString을 쓰러면 object 타입이여야하는데 
		System.out.println(((JSONObject)users.get(1)).toString());
	}
}
