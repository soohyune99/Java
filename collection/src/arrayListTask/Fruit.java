package arrayListTask;

public class Fruit {
	// 과일 이름, 가격
	// private
	// 기본 생성자
	// toString
	
	private String fruitName;
	private int fruitPrice;
	
	public Fruit() {;}

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public int getFruitPrice() {
		return fruitPrice;
	}

	public void setFruitPrice(int fruitPrice) {
		this.fruitPrice = fruitPrice;
	}

	@Override
	public String toString() {
		String str = fruitName + "," + fruitPrice;
		return str;
	}
	
	

}
