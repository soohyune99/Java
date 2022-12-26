package objectTest;

import java.util.Random;

class Student {
   int number;
   String name;
   
   public Student() {;}

   public Student(int number, String name) {
      super();
      this.number = number;
      this.name = name;
   }
   
   @Override
   public String toString() {				// toString() 재정의
      return number + ", " + name;
   }
}

public class ToStringTest {
   public static void main(String[] args) {
      Random r = new Random();
      System.out.println(r.toString());		// toString() 메소드는 "클래스명@16진수 해시코드"로 구성된 문자정보 리턴
      
      Student std = new Student(1, "홍길동");
      System.out.println(std);
   }
}




