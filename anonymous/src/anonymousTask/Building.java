package anonymousTask;

public class Building {
   public static void main(String[] args) {
//      잠실점 오픈
//      잠실점은 무료 나눔 행사중이라서 판매 방식을 구현할 필요 없다.
//      본사에서는 전달받은 양식을 검사할 때
//      무료나눔 행사중인 매장이라면 "무료 나눔 행사중" 출력하기
      
      Starbucks gangnam = new Starbucks();
      Starbucks jamsli = new Starbucks();
      
      gangnam.register(new Form() {
         @Override
         public void sell(String order) {
            String[] menus = getMenu();
            for (int i = 0; i < menus.length; i++) {
               if(order.equals(menus[i])) {
                  System.out.println("판매 완료");
               }
            }
         }
         
         @Override
         public String[] getMenu() {
            String[] menus = {"아메리카노", "카푸치노", "캐모마일티"};
            return menus;
         }
      });
      
      // 익명 클래스
      jamsli.register(new FormAdapter() {
		
		@Override
		public String[] getMenu() {
			String[] menu= {"무료 나눔 행사중"};
			return menu;
		}
	});
      
   }
}