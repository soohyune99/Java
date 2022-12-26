package arrayListTask;

import java.util.ArrayList;

public class Market {
	// 과일을 저장할 DB 선언
	// static 의 장점:  모든 객체 공유할 수 있다
	  public static ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	  
	// 과일 추가
	  public void add(Fruit fruit) {
		  fruits.add(fruit);
	  }
	  
	  // 과일 삭제
	  // 과일 이름을 외부에서 전달 받는다.
	  public void remove(String fruitName) {
		  for (Fruit fruit : fruits) {
			  // DB에서 과일의 이름을 검색한다.
			  if(fruit.getFruitName().equals(fruitName)) {
					fruits.remove(fruit);
					break;		
		}
	  }
	}
	  
	  // 과일 가격이 평균 가격보다 낮은지 검사
	  public boolean compare(int price) {
		  return price < getAverage();
	  }
	  public double getAverage() {
		  int total = 0;
		  double avg = 0.0;
		  for (Fruit fruit : fruits) {
			total += fruit.getFruitPrice();
		}
		  return avg = Double.parseDouble(String.format("%.2f", total / (double)fruits.size()));
	  }
	  
	  
}























