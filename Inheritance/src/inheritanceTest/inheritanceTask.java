package inheritanceTest;

class Car{
	// 브랜드, 색상, 가격
	String brand;
	String color;
	int price;
	
	// 기본 생성자 ( 9번과 12번 줄은 같이 쓰는것을 습관화)
	public Car() {;}
	
	// 기본 생성자 초기화 
	public Car(String brand, String color, int price) {
		this.brand = brand;
		this.color = color;
		this.price = price;
	}
	
	// 열쇠로 시동 킴
	void engineStart() {
		System.out.println("열쇠로 시동이 켜졌습니다.");
	}
	// 열쇠로 시동 끔
	void engineStop() {
		System.out.println("열쇠로 시동이 꺼졌습니다.");
	}
}

class SuperCar extends Car{
	// 모드
	String mode;
	
	// 자식 기본 생성자는 부모의 기본 생성자를 호출한다.
	// 부모에 기본 생성자가 없으면 자식의 기본 생성자는 오류가 발생한다.
	public SuperCar() {;}
	
	public SuperCar(String brand, String color, int price, String mode) {
		// 부모의 생성자 중 3개의 값을 전달받는 생성자 호출
		super(brand, color, price);
		this.mode = mode;
	}
	
	// 음성으로 시동 킴
	@Override   										// 오버라이딩 = 재정의
	void engineStart() {								// 부모인 Car에서 시동키는것을 다시 SuperCar인 자식에서 재정의
		System.out.println("음성으로 시동이 켜졌습니다.");		// 그러나 코드 추가는 아니므로 super.engineStart();를 가져오지는 않는다.
	}
	
	// 음성으로 시동 끔
	@Override											// 위에 오버라이딩 = 재정의 와 같다.
	void engineStop() {									// 부모인 Car에서 시동키는것을 다시 SuperCar인 자식에서 재정의
		super.engineStop();								// super.engineStop();를 사용해서 열쇠로도 시동이 꺼지게 한다.
		System.out.println("음성으로 시동이 꺼졌습니다.");
	}
	
	// 지붕 열기
	void openRoof() {									// 새로운 지붕열기 라는 기능 생성
		System.out.println("지붕 열기");
	}
	
	// 지붕 닫기	
	void closeRoof() {									// // 새로운 지붕닫기 라는 기능 생성
		System.out.println("지붕 닫기");
	}
}

public class inheritanceTask {
	public static void main(String[] args) {
		SuperCar rollsroyce = new SuperCar();
		rollsroyce.engineStart();					// 음성으로 시동이 켜졌습니다. 출력
		rollsroyce.engineStop();					// 열쇠로 시동이 꺼졌습니다. 와 음성으로 시동이 꺼졌습니다. 출력
		rollsroyce.openRoof();						// 지붕 열기 출력
		rollsroyce.closeRoof();						// 지붕 닫기 출력
	} 
}














