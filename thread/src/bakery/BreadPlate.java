package bakery;

public class BreadPlate {
   
   //싱글톤 패턴
   //static으로 만들어 new에게서 영향을 받지 않도록 한다.
   public static BreadPlate breadPlate;
   //기본 생성자을 private으로 캡슐화해서 메소드로만 접근 가능하도록 한다.
   private BreadPlate() {;}
   //객체화 하지 않고 사용하기 위해 static을 붙인다
   public static BreadPlate getInstance() {
      //breadPlate가 한번도 사용되지 않았다면 null이 들어가있어서
      //if문 안으로 들어가고
      if(breadPlate == null) {
         //여기서 객체화를 해준다.
         breadPlate = new BreadPlate();
      }
      //이후에 BreadPlate.getInstance()를 쓰는 모든 객체는
      //동일한 주소를 가지고 있다.
      return breadPlate;
   }
   //여기까지 싱글톤 패턴
   
   public int breadCount;
   public int eatCount;
   
   //빵 만들기
   //만든 빵이 9개가 넘어가면, 쓰레드를 멈춰준다. wait()
   
   //싱글톤패턴으로 필드의 주소를 하나만으로 공유하게 만들었다면
   //접근하는 객체 중 누가 이 메소드를 쓰려고 하는지 알 수 없음
   //따라서 동기화를 통해 단일 쓰레드로 바꿔줌
   public synchronized void makeBread() {
      if(breadCount>9) {
         System.out.println("빵이 가득 찼습니다.");
         //wait, sleep, join 은 try catch로 예외를 잡아줘야 함
         //catch로 잡은 InterruptedException은 쓰레드를 종료시키기 위해
         //우리가 일부러 발생키는 예외   Thread객체.interrupt()를 사용
         //catch문 안에는 종료시키기 위한 코드가 존재            //BreadMaker의 필드에 static으로 선언된 check를 true로 바꿈
         try {wait();} catch (InterruptedException e) {BreadMaker.check = true;}
      }else {
         //예외가 발생하면 try 내부 코드가 실행되지 않으므로 아래 코드가 한번 실행되고 Thread가 종료됨
         //그래서 else문으로 감싸면 예외 발생해도 아래 코드가 실행되지 않음
         breadCount++;
         System.out.println("빵을 1개 만들었습니다 현재 빵 개수 : " + breadCount + "개");
      }
   }
   
   //먹기
   //만든 빵이 0개면 못 먹고, 먹은 빵이 20개면 못 먹는다.
   //만약 빵을 먹게 되면, 멈춰있던 쓰레드를 깨워준다.
   //makeBread()처럼 동기화
   public synchronized void eatBread() {
      if(eatCount ==20) {
         System.out.println("빵이 다 떨어졌습니다! 그만 좀 드세요ㅠ");
      }else if(breadCount < 1) {
         System.out.println("🍩빵을 만들고 있어요!🍩");
      }else {
         eatCount++;
         breadCount--;
         System.out.println("빵을 1개 먹었습니다 현재 빵 개수 : " + breadCount + "개");
         notify();
         BreadMaker.i--;
      }
   }
}