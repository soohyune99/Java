package threadTest1;

public class AnimalTeacher implements Runnable{
   @Override
   public void run() {
      for (int i = 0; i < 10; i++) {									// 10번 반복
         System.out.println(Thread.currentThread().getName());			// 현재 진행중인 쓰레드 출력
         try {Thread.sleep(300);} catch (InterruptedException e) {;}	// 0.3초 멈췄다가 다시 진행
      }
   }
}



