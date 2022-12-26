package threadTest1;

public class ZooTeacher {
   public static void main(String[] args) {
      String[] sounds = {"어흥", "야옹", "음메"};
      AnimalTeacher[] animals = new AnimalTeacher[sounds.length];
      Thread[] threads = new Thread[sounds.length];
      
      for (int i = 0; i < threads.length; i++) {
         animals[i] = new AnimalTeacher();						
         threads[i] = new Thread(animals[i], sounds[i]);	// run 재정의
      }
      
      for (int i = 0; i < threads.length; i++) {
         threads[i].start();
         if(i != 0) {					// 만약 i가 0이 아니면 join(), 순차적으로 출력하기 위해
            try {threads[i].join();} catch (InterruptedException e) {;}
         }
      }
      
   }
}












