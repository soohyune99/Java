package dao;

import vo.GirlVO;

public class GirlDAO {
	public GirlVO setObject(String line) {
		// datas 배열에 \t를 제외하고 line 담기
		String[] datas = line.split("\t");
		GirlVO girlVO = new GirlVO();
		
		// datas 인덱스에 이름, 랭킹, 이름수 담기
		girlVO.setName(datas[0]);
		girlVO.setRanking(Integer.valueOf(removeComma(datas[1])));
		girlVO.setPopulation(Integer.valueOf(removeComma(datas[2])));
		
		// girlVO로 리턴
		return girlVO;
	}
	
	// 콤마를 제거해주는 메소드
	public String removeComma(String data) {
		return data.replaceAll(",", "");
	}
}
