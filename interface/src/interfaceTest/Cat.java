package interfaceTest;

//implements가 인터페이스에 선언된 추상 메소드의 실체 메소드 선언
public class Cat implements Pet {

	@Override
	public void bang() {
		System.out.println("안한다");
	}

	@Override
	public void giveYourHand() {
		System.out.println("안한다");
	}

	@Override
	public void bite() {
		System.out.println("문다");
	}

	@Override
	public void sitDown() {
		System.out.println("안앉는다.");
	}

	@Override
	public void waitNow() {
		System.out.println("안기다린다.");
	}

	@Override
	public void getNose() {
		System.out.println("안한다");
	}

}
