package apiTest;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsTest {
	public static void main(String[] args) {
		
		String api_key = "NCSXE6GLL3RDU856";
	    String api_secret = "6EIGBO25HGM7KVCY9LEFOILOF5BFRLGA";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", "01026385504");
	    params.put("from", "01026385504");
	    params.put("type", "SMS");
	    params.put("text", "문앞에 택배 놓고 갑니다.");
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	}
}
