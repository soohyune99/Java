package inheritanceTest;

class Human{
	void eat() {
		System.out.println("먹기");
	}
	
	void sleep() {
		System.out.println("잠자기");
	}
	
	void walk() {
		System.out.println("두발로 걷기");
	}
}

class Monkey extends Human{					// 부모는 Human, 자식은 Monkey extends를 이용해 상속
	void 이잡아라() {
		System.out.println("이잡기");
	}
	
	// @ : 어노테이션												
	@Override 			
	void walk() {
											// 부모의 코드를 그대로 사용하고자 할 때 super()를 사용
		super.walk();						// 부모에 있는거 가져오고
		System.out.println("네발로 걷기");		// 자식에서 코드 추가하기
	}
}

public class InheritanceTest2 {
	public static void main(String[] args) {
		Monkey kingkong = new Monkey();		
		kingkong.walk();					// 두발로 걷기 + 네발로 걷기 가 출력
	}
}


