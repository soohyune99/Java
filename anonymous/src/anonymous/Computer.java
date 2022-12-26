package anonymous;

public class Computer {
	public static void main(String[] args) {
		// 익명클래스 사용 방식
		Game game = new Game() {
			@Override
			public void play() {}
			@Override
			public void exit() {}
		}; // 세미콜론 주의
		
	}
}
