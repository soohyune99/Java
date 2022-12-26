package bakery;

public class BreadMaker implements Runnable{
   
   public static boolean check;
   public static int i;
   
   @Override
   public void run() {
      //빵을 20개 만든다

      for (i = 0; i < 20; i++) {
         BreadPlate.getInstance().makeBread();
         //makeBread()에서 InterruptedException 발생으로 인해
         //check가 true로 바뀌면 반복문 탈출
         if(check) {break;}
         try {   Thread.sleep(1000);} catch (InterruptedException e) {break;}
      }
      if(i != 20) {
         System.out.println("안녕히 가세요");
         return;
      }
      System.out.println(i);
      System.out.println("재료 소진");
   }
}