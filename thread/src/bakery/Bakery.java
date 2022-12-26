package bakery;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Bakery{
   public static void main(String[] args) {
      BreadMaker breadMaker = new BreadMaker();
      Thread maker = new Thread(breadMaker);
      //싱글톤패턴으로 선언한 클래스를 객체화하기 위해 메소드로 접근
      BreadPlate breadPlate = BreadPlate.getInstance();
      String[] btns = {"빵 먹기","나가기"};
      int choice = 0;
      //이미지 아이콘 추가하는 법. 귀엽다. 실무에서 안 씀
      ImageIcon icon = new ImageIcon("src/img/bread2.gif");
      
      //start()는 스케줄링인데 Thread 클래스에 있음
      maker.start();
      
      while(true) {
      choice = JOptionPane.showOptionDialog(null, "", "빵집", JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,icon, btns, null);
      
      if(choice == 0) {
         breadPlate.eatBread();
      }else {
         //나가기는 1, 닫기버튼은 -1
         maker.interrupt();
         break;
         }
      }
   }
}