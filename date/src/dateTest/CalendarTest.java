package dateTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {
	public static void main(String[] args) {
		//calendar는 추상 클래스, 생성자를 생성하지 못한다. 그러므로 싱글톤패턴으로 사용
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd aaa hh:mm:dd");
//		Calendar today = Calendar.getInstance();
//		System.out.println(today);
//		
//		// format()안에 calendar타입을 쓰고 싶은데, object로 인식
//		// getTime() 은 date타입으로 변환해주는 메소드
//		System.out.println(simpleDateFormat.format(today.getTime()));
		
		Calendar date = Calendar.getInstance();
//		date.set(2000, 11, 4);		// month는 0부터 시작하기때문에 원하는 날짜-1을 해줘야 한다.
//		date.set(Calendar.YEAR, 2000);
//		System.out.println(simpleDateFormat.format(date.getTime()));
		System.out.println(date.get(Calendar.YEAR));
		// 원하는 month를 가져오려먼  month+1을 해줘야 한다.
		System.out.println(date.get(Calendar.MONTH)+1);	
	}
}
