package hashSetTest;

public class Student {
   private int number;
   private String name;
   
   public Student() {;}

   public Student(int number, String name) {
      super();
      this.number = number;
      this.name = name;
   }

   public int getNumber() {
      return number;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   
   
//   equals()를 재정의했다면, hashCode()도 재정의해야 한다.
//   hashCode()로 비교하는 자료구조가 여러 번 나타나기 때문에
//   equals()만 재정의하게 되면 원하는 값으로 비교하지 않고, 필드의 주소로 비교하는
//   상황이 발생한다. 따라서 equals()에서 비교한 값을 hashCode()의 리턴값으로 재정의해야 한다.
   
   @Override
   public boolean equals(Object obj) {
      if(obj == this) {
         return true;
      }
      
      if(obj instanceof Student) {
         Student std = (Student) obj;
         if(this.number == std.number) {
            return true;
         }
      }
      
      return false;
   }
   
   @Override
   public int hashCode() {
      return this.number;
   }
   
}

















