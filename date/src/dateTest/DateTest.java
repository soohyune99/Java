package dateTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		// SimpleDateFormat에 format 과 parse
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		Date date = new Date();
		Date time = new Date();
		
		date.setYear(100);		// 1900 + year = 내가 바꾸고 싶은 year
		date.setMonth(11);		// 내가 바꾸고 싶은 month - 1
		
		System.out.println(date);
		System.out.println(simpleDateFormat.format(date));
		
		try {
			time = simpleDateFormat.parse("2002년 12월 4일 09시 00분 00초");
			System.out.println(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
