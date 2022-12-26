package markerInterfaceTest;

public class Lab {
	//외부에서 모든 동물을 받아와서
	// 초식동물인지 육식동물인지 그리고 잡식동물인지 구분하는 메소드
	// 초식 동물: Herbivore
	// 육식 동물: Carnivore
	public void checkKinds(Animal[] animals) {
		// 외부에서 Animal 타입의 배열을 전달받고
		// instaceof를 사용하여 종을 검사한다.
		for (int i = 0; i < animals.length; i++) {
			if(animals[i] instanceof HerbivoreMarker ) {
				System.out.println("초식동물");
			}else if(animals[i] instanceof CarnivoreMarker ) {
				System.out.println("육식동물");
			}else {
				System.out.println("잡식동물");
			}
		}
	}
	
	public static void main(String[] args) {
		
		Lab l = new Lab();
		Animal[] animals = {
				new Bear(),
				new Cow(),
				new Tiger(),
				new Dog(),
		};
		l.checkKinds(animals);
	}

}
