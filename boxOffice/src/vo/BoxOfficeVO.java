package vo;

import java.text.DecimalFormat;

public class BoxOfficeVO {
	private int ranking;
	private String name;
	private String releaseDate;
	private long income;
	private int guestCount;
	private int screenCount;
	
	public BoxOfficeVO() {;}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public int getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}

	public int getScreenCount() {
		return screenCount;
	}

	public void setScreenCount(int screenCount) {
		this.screenCount = screenCount;
	}
	
	// 외부에서 메소드 제작 후 
	private String insertComma(String data) {
		for (int i = 0; i < data.length(); i++) {
			if(i % 3 == 0 && i != 0) {
				data = "," + data;
			}
			data = data.charAt(data.length() - 1 - i) + data;
		}return data;
	}
	
//	숫자타입은 3자리마다 , 삽입	
	@Override
	public String toString() {
		String str = null;
		String incomeWithComma = String.valueOf(income);
		String guestCountWithComma = String.valueOf(guestCount);
		String screenCountWithComma = String.valueOf(screenCount);
		
		for (int i = 0; i < incomeWithComma.length() ; i++) {
			
			if(i % 3 == 0 && i != 0) {
				incomeWithComma = "," + incomeWithComma;
			}
			incomeWithComma = incomeWithComma.charAt(incomeWithComma.length() - 1 - i) + incomeWithComma ;
		}
		str = ranking + "\t" + name + "\t" + releaseDate + "\t" + incomeWithComma + "\t" + guestCount + "\t" + screenCount;
		return str;
	}
	
	
}























