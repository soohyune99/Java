package dateTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTask {
	public static void main(String[] args) {
		// 본인의 생년월일 출력
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar birthday = Calendar.getInstance();
		birthday.set(1999, 11, 11);
		System.out.println(simpleDateFormat.format(birthday.getTime()));
		
	}
}
