package threadTest1;

public class Animal implements Runnable {
//	[실습]
//	동석이네 동물원에는 3마리의 동물이 있다.
//  각 동물은 울음 소리가 다르고 2마리의 동물은 동시에 운다.
//	나머지 1마리 동물은 2마리 동물이 모두 울고 나서 마지막에 운다.
//
//	package명은 threadTest1으로 만들고 클래스는 2개만 선언한다.
//	하나의 클래스에는 main 쓰레드가 있다.
//	Runnable 인터페이스로 멀티 쓰레드를 구현하고 반드시 join()을 사용한다.
//	※ 각 동물은 10번씩만 운다.
	
	
	private String sound;
	
	public Animal() {;}
	
	public Animal(String sound) {
		super();
		this.sound = sound;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.sound);
			try {Thread.sleep(500);} catch (InterruptedException e) {;}
		}
	}
}
