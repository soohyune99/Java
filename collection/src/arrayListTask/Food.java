package arrayListTask;

public class Food {
	// 이름, 가격, 종류(한식, 일식, 중식, 양식)
	// private
	private String name;
	private int price;
	private String kinds;
	
	// 기본 생성자
	public Food() {;}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getKinds() {
		return kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	// toString()
	@Override
	public String toString() {
		String str = name + "," + price + "," + kinds;
		return str;
	}
	

	
}
