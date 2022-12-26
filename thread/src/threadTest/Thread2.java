package threadTest;

public class Thread2 implements Runnable{
	
	@Override
	public void run() {
		// 멀티 쓰레드에서는 이것을 자원이라고 부른다.
		for (int i = 0; i <10; i++) {
			System.out.println(Thread.currentThread().getName());       // 현재 실행중인 쓰레드를 가져온다.
			try {Thread.sleep(500);} catch (InterruptedException e) {;} // 0.5초 멈추었다가 다시 진행
		}
	}

}



