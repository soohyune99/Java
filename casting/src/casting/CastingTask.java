package casting;

public class CastingTask {
//	넷플릭스
//	애니메이션, 영화, 드라마 3개의 클래스 선언
//	사용자가 선택한 영상이
//	애니메이션이라면 "자막지원"기능 사용
//	영화라면 "4D"기능 사용
//	드라마라면 "굿즈"기능 사용
	
	public void check(Video video) {
		if(video instanceof Animation) {				// 만약 video의 타입이 Animation이면
			Animation ani = (Animation) video;
			ani.printSubtitle();
		}else if(video instanceof Film) {
			Film film = (Film) video;
			film.print4D();
		}else {
			Drama drama = (Drama) video;
			drama.sellGoods();
		}
	}
	
	public static void main(String[] args) {
		CastingTask ct = new CastingTask();
		ct.check(new Animation());
		ct.check(new Film());
		ct.check(new Drama());
	}
}












