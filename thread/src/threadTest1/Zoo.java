package threadTest1;

public class Zoo {
	public static void main(String[] args) {
		
		Animal tiger = new Animal("어흥");
		Animal elephant = new Animal("뿌우");
		Runnable monkey = new Animal("우끼끼");
		
		Thread tiger_thread = new Thread(tiger);
		Thread elephant_thread = new Thread(elephant);
		Thread monkey_thread = new Thread(monkey);
		
		tiger_thread.start();
		elephant_thread.start();
		
		try {
			tiger_thread.join();
			elephant_thread.join();
		}catch(InterruptedException e) {;}
		
		monkey_thread.start();
		
	}

}
