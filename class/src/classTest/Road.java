package classTest;

class Car{
	String brand;
	String color;
	int price;
	
	
//	 단축키: Alt + Shift + s 누른후 o  
	
public Car(String brand, String color, int price) {
	this.brand = brand;
	this.color = color;
	this.price = price;
}


	public Car(String color) {
	brand = "자동차";			// 겹치는 이름이 없을 경우, this. 생략 가능
	this.color = color;
}

	void engineStart() {
		System.out.println(brand + "시동 켜기");
	}


	void engineStop() {
		System.out.println(brand + "시동 끄기");
	}
}

public class Road {
//	실행 프로그램을 만들어주는 메소드
	public static void main(String[] args) {
		Car momCar = new Car("Benz", "Black", 10_000);
		Car dadyCar = new Car("BMW", "Blue", 8_000);
		Car myCar = new Car("Red");
	
		momCar.engineStart();
		dadyCar.engineStart();
		myCar.engineStart();
	}
}




















