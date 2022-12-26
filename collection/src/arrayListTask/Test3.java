package arrayListTask;

public class Test3 {
   public static void main(String[] args) {
      Restaurant restaurant = new Restaurant();
      Food food = new Food();
      
      food.setName("치즈돈까스");
      food.setPrice(8500);
      food.setKinds("일식");
      
      Restaurant.foods.add(food);
      
      food = new Food();
      
      food.setName("한우갈비찜");
      food.setPrice(12000);
      food.setKinds("한식");
      
      Restaurant.foods.add(food);

      food = new Food();
      
      food.setName("잡채찜닭돼지고기두루치기");
      food.setPrice(1500);
      food.setKinds("한식");
      
      Restaurant.foods.add(food);
      
//      System.out.println(restaurant.checkKinds("한우갈비찜"));
//      System.out.println(restaurant.checkKinds("치즈돈까스"));
      
//      for (Food selected : restaurant.getFoods("한식")) {
//         System.out.println(selected);
//      }
      
      food.setKinds("일식");
      restaurant.updateKinds(food);
//      System.out.println(Restaurant.foods);
      
      System.out.println(restaurant.searchFood("한식"));
   }
}















