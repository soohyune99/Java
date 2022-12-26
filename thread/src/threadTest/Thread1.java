package threadTest;

public class Thread1 extends Thread{
	
	public String data;
	
	public Thread1() {;}
	
	public Thread1(String data) {
		super();
		this.data = data;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {				// 10번 반복
			System.out.println(data);					
			try {Thread.sleep(1000);} catch (InterruptedException e) {;}	// 1초 멈추었다가 다시 진행
		}
	}
}



