package wrapperTest;

public class WrapperTest {
	public static void main(String[] args) {
		int data_i = 10;
		
		// boxing
		// 줄 그어진것, 디프리케이티드 : 이제 더이상 쓰지 마세요.라는 의미
//		Integer data_I = new Integer(data_i);
//		Integer data_I = Integer.valueOf(data_i);
		
		// auto boxing
		Integer data_I = data_i;
		
		// unboxing
		data_i = data_I.intValue();
		
		// auto unboxing
		data_i = data_I;
	}
}


