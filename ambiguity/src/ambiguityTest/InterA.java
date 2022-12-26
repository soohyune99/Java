package ambiguityTest;

public interface InterA {
	// abstract을 없애기위해 default를 쓴다.
	default void printData() {
		System.out.println("InterA");
	}
}
