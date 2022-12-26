package casting;

class Car{
	void engineStart() {
		System.out.println("열쇠로 시동 킴");
	}
}

class SuperCar extends Car{
	@Override
	void engineStart() {
		System.out.println("음성으로 시동 킴");	
	}
	
	void openRoof() {
		System.out.println("지붕 열기");
	}
}

public class CastingTest {
	public static void main(String[] args) {
		Car matiz = new Car();
		SuperCar ferrari = new SuperCar();
		
		// up casting
		Car noOptionFerrari = new SuperCar();	
		
		// down casting
		SuperCar fullOptionFerrari = (SuperCar)noOptionFerrari;
		
		// 타입이 서로 달라서 (SuperCar)형변환 시켜도 오류가 난다.
//		SuperCar brokenFerrari = (SuperCar)new Car();
		
		
		// matiz 는 부모, ferrari는 자식
//      matiz instanceof Car : true
//		matiz는 부모타입이고 Car 부모타입이므로 true
      System.out.println(matiz instanceof Car);
      
//      matiz instanceof SuperCar : false
//      matiz는 부모타입이고 SuperCar 자식타입이므로 false
      System.out.println(matiz instanceof SuperCar);
      
//      ferrari instanceof Car : true
//      ferrari는 자식타입이고 Car는 부모타입이므로 true
      System.out.println(ferrari instanceof Car);
      
//      ferrari instanceof SuperCar : true
//      ferrari는 자식타입이고 SuperCar는 부모타입이므로 true
      System.out.println(ferrari instanceof SuperCar);
      
//      noOptionFerrari instanceof Car : true
//      noOptionFerrari는 upcasting으로 자식타입을 부모타입으로 변환해 부모타입이고 Car 부모타입이므로 true
      System.out.println(noOptionFerrari instanceof Car);
      
//      noOptionFerrari instanceof SuperCar : true
//      noOptionFerrari는 upcasting으로 자식타입을 부모타입으로 변환해 부모타입이고 SuperCar 자식타입이므로 true
      System.out.println(noOptionFerrari instanceof SuperCar);
      
//      fullOptionFerrari instanceof Car : true
//      fullOptionFerrari는 downcasting으로 부모타입을 자식타입으로 변환해 자식타입이고 Car 부모타입이므로 true
      System.out.println(fullOptionFerrari instanceof Car);
      
//      fullOptionFerrari instanceof SuperCar : true
//      fullOptionFerrari는 downcasting으로 부모타입을 자식타입으로 변환해 자식타입이고 SuperCar 자식타입이므로 true
      System.out.println(fullOptionFerrari instanceof SuperCar);

		
	}
}






























