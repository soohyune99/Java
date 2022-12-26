package interfaceTest;

public interface Pet{
	final static int eyes = 2;
	int nose = 1;						// interface는 상수와 추상클래스만 되기때문에 앞에 생략 가능
	
	public abstract void bang();
	public void giveYourHand();			// 추상 메소드 선언 {}가 없다.
	public void bite();
	public void sitDown();
	public void waitNow();
	public void getNose();
}
