package classTest;

class A{
	int data;
	
	A(int data){
		this.data = data;
	}
	
	void printData() {
		System.out.println(data);
	}
}

public class ClassTest {
	public static void main(String[] args) {
		A a = new A(10);		// 겍체화
		
		
		a.printData();
	}
}
