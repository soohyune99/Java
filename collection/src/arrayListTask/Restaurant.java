package arrayListTask;

import java.util.ArrayList;

public class Restaurant {
	//  음식 정보를 담을 DB 선언
	   public static ArrayList<Food> foods = new ArrayList<Food>();
	   
	//  음식 추가
	   public void add(Food food) {
		   foods.add(food);
	   }
	   
	//  양식인지 중식인지 일식인지 한식인지 검사(문자열로 리턴)
	//  예)스파게티 입력 시 "양식"리턴 
	   public String checkKinds(String name) {
		   for (Food food : foods) {
			if(food.getName().equals(name)) {
				return food.getKinds();
			}
		}
		 return null;
	  }
	   
	//  사용자가 원하는 종류의 음식 목록 조회(종류는 한 가지만 입력가능)
	//  예)한식 입력 시 한식만 조회
	   public ArrayList<Food> getFoods(String kinds) {
		   ArrayList<Food> results = new ArrayList<Food>();
		   for (Food food : foods) {
			if(food.getKinds().equals(kinds)) {
				results.add(food);
			}
		}
		   return results;
	  }
	   
	   
  
	//  음식의 종류 수정(가격 10% 상승)
	//  예)한식 -> 중식, 음식 가격은 10% 상승
	   public void updateKinds(Food food) {
		   for (Food temp : foods) {
			if(temp.getName().equals(food.getName())) {
				temp.setKinds(food.getKinds());
				temp.setPrice((int)(temp.getPrice()*1.1));
				break;
			}
		}
	   }
  
	//  사용자가 원하는 종류의 음식 개수 조회
	//  예) 불고기, 제육볶음, 파스타, 초밥
	//  한식 입력 시 2개
	   public int searchFood(String kinds) {
//		   int count = 0;
//		   for (Food food : foods) {
//			   if(food.getKinds().equals(kinds)) {
//				   count++;
//			   }
//		   }
//		   return count;
		   return getFoods(kinds).size();
	   }
}




















