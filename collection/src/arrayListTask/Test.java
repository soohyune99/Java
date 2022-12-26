package arrayListTask;

public class Test {
	public static void main(String[] args) {
		Fruit apple = new Fruit();
		Fruit banana = new Fruit();
		
		Market market = new Market();
		
		apple.setFruitName("사과");
		apple.setFruitPrice(1000);
		
		banana.setFruitName("바나나");
		banana.setFruitPrice(1000);
		
		market.add(apple);
		market.add(banana);
		
		market.remove("사과");
		Market.fruits.forEach(System.out::println);
	}

}
