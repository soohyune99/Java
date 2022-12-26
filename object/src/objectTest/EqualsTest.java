package objectTest;

import java.util.Random;

class Employee{
   int number;
   String name;
   
   public Employee() {;}

   public Employee(int number, String name) {
      super();
      this.number = number;
      this.name = name;
   }
   
   @Override
   public boolean equals(Object obj) {
      //주소 비교
      if(this == obj) {
         return true;				// 주소값이 같으면 true로 리턴
      }
      
      //타입 비교
      if(obj instanceof Employee) {						// 매개값이 Employee 타입인지 확인
         //Object에 up casting되었기 때문에 Employee에 새롭게 만든 number와 name은 Object에 없다.
         Employee anotherEmployee = (Employee)obj;		// Employee 타입으로 강제 타입 변환하고,
         if(this.number == anotherEmployee.number) {	// number 필드값이 동일한지 검사하고, 
            return true;								// 동일하다면, true로 리턴
         }
      }
      return false;										// 매개값이 Employee 타입이 아니거나 number 필드값이 다른 경우 false로 리턴
   }
}

public class EqualsTest {
   public static void main(String[] args) {
      
      Employee han = new Employee(1, "한동석");
      System.out.println(han.equals(new Employee(1, "한동석")));
      
//      String data1 = "ABC";
//      String data2 = "ABC";
//      String data3 = new String("ABC");
//      String data4 = new String("ABC");
//      
//      System.out.println(data1 == data2);
//      System.out.println(data1.equals(data2));
//      
//      System.out.println(data3 == data4);
//      System.out.println(data3.equals(data4));
//      
//      Random r1 = new Random();
//      Random r2 = new Random();
//      
//      System.out.println(r1 == r2);
//      System.out.println(r1.equals(r2));
//      
//      r1 = r2;
//      System.out.println(r1 == r2);
//      System.out.println(r1.equals(r2));
      
   }
}