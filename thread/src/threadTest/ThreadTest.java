package threadTest;

public class ThreadTest {
	public static void main(String[] args) {
		// 1. Thread 클래스 상속
//		Thread1 t1 = new Thread1("★");
//		Thread1 t2 = new Thread1("♥");
		
		// 단일 쓰레드
		// 별 모양이 먼저 10번 출력 후 하트 모양 10번 출력
//		t1.run();
//		t2.run();
		
		// 멀티 쓰레드
		// 별 모양과 하트 모양이 섞여서 각각 10번 출력
//		t1.start();
//		t2.start();
		
		
		// 2. Runnable 인터페이스 지정
		Runnable t1 = new Thread2();
		Thread2 t2 = new Thread2();
		
		// 우리가 재정의한 run에 대해서 모른다.
		Thread thread1 = new Thread(t1, "!");
		Thread thread2 = new Thread(t2, "?");
		
		thread1.start();
		thread2.start();
		
		// join() : 사용한 객체의 쓰레드가 모두 종료되어야 다른 쓰레드가 실행된다.
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {;}
		
		System.out.println("main 쓰레드 종료");
		
		
		// Runnable()은 함수형 인터페이스, 람다식으로 구성
//		Runnable t1 = new Thread2();
//		Thread2 t2 = new Thread2();
//		
//		Runnable runner = () -> {
//			for (int i = 0; i < 5; i++) {
//				System.out.println(Thread.currentThread().getName());
//				try {Thread.sleep(300);} catch (InterruptedException e) {;}
//			}
//		};
//		
//		Thread thread1 = new Thread(runner, "!");
//		Thread thread2 = new Thread(runner, "?");
//		
		
		
	}
}


































