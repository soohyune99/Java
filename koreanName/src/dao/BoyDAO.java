package dao;

import vo.BoyVO;

public class BoyDAO {
	public BoyVO setObject(String line) {
		// datas 배열에 \t를 제외하고 line 담기
		String[] datas = line.split("\t");
		BoyVO boyVO = new BoyVO();
		
		// datas 인덱스에 이름, 랭킹, 이름수 담기
		boyVO.setName(datas[0]);
		boyVO.setRanking(Integer.valueOf(removeComma(datas[1])));
		boyVO.setPopulation(Integer.valueOf(removeComma(datas[2])));
		
		// boyVO로 리턴
		return boyVO;
	}
	
	// 콤마를 제거해주는 메소드
	public String removeComma(String data) {
		return data.replaceAll(",", "");
	}
}
