package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import vo.BoxOfficeVO;

public class BoxOfficeDAO {
	
//	추가와 삽입
	public boolean add(BoxOfficeVO boxOfficeVO) throws IOException{
		return boxOfficeVO.getRanking() == 0 ? append(boxOfficeVO) : insert(boxOfficeVO);
	}
	
//	추가
//	화면에서 입력한 영화정보를 Model 객체로 전달받는다.
//	마지막에 추가하는 정보이므로 순위는 전달받지 않는다.
	private boolean append(BoxOfficeVO boxOfficeVO) throws IOException{
		BufferedWriter bufferedWriter = DBConnecter.getAppend();
		BufferedReader bufferedReader = DBConnecter.getReader();
		String content = null, newLine = null, line = null;
		int lastRanking = 0;
		boolean check = true;
		
		while((line = bufferedReader.readLine()) != null) {
//			기존 정보 중 순위를 제외한 5개 정보
			String excludedRankingOrigin = line.substring(line.indexOf("\t"));
//			전달받은 정보 중 순위를 제외한 5개 정보
			String excludedRankingNew = boxOfficeVO.toString();
			excludedRankingNew = excludedRankingNew.substring(excludedRankingNew.indexOf("\t"));
//			새롭게 전달받은 정보 5개와 기존 정보 5개가 일치하면 중복된 정보이므로
			if(excludedRankingOrigin.equals(excludedRankingNew)) {
//				FLAG를 false로 변경한다.
				check = false;
			}
//			총 반복 횟수가 마지막 순위이다.
			lastRanking ++;
		}
		
		bufferedReader.close();
		
//		새로운 마지막 순위를 연산하여 전달받은 정보 중 순위에 넣어준다.
		boxOfficeVO.setRanking(lastRanking + 1);
		
//		전달한 경로의 파일 내용 전체를 byte[]로 가져온다.
//		byte[]을 String생성자에 전달하여 디코딩한다.
		content = new String(Files.readAllBytes(Paths.get(DBConnecter.PATH)));
		
//		가져온 전체 내용 중 가장 마지막 문자가 줄바꿈 문자인지 검사한다.
//		줄바꿈 문자가 아니라면 newLine에 줄바꿈 문자를 넣어준다.
		newLine = content.charAt(content.length() - 1) == '\n' ? "" : "\n";
		
//		줄바꿈 문자 유무 검사의 결과를 새롭게 추가할 정보 앞에 연결해준다.
		bufferedWriter.write(newLine + boxOfficeVO.toString());
		bufferedWriter.close();
		
		return check;
	}
	
//	삽입
//	화면에서 입력한 전체 정보를 Model 객체로 전달받는다.
	private boolean insert(BoxOfficeVO boxOfficeVO) throws IOException{
		BufferedWriter bufferedWriter = null;
		BufferedReader bufferedReader = DBConnecter.getReader();
		String line = null, temp = "";
//		전달받은 순위를 newRanking에 담아준다.
		int newRanking = boxOfficeVO.getRanking();
		boolean check = false;
		
		while((line = bufferedReader.readLine()) != null) {
//			기존 정보 중 순위를 새롭게 전달받은 순위와 비교한다.
			if(Integer.valueOf(line.split("\t")[0]) == boxOfficeVO.getRanking()) {
//				해당 순위에 새로운 정보를 삽입해준다.
				temp += boxOfficeVO + "\n";
//				FLAG는 true로 변경해준다.
				check = true;
			}
//			FLAG가 true라면 삽입 된 후이기 때문에, 새로운 순위로 수정해주어야 한다.
//			삽입할 순위 + 1 부터 1씩 증가시키면 모두 1순위씩 밀려나게 된다.
			temp += check ? ++newRanking + line.substring(line.indexOf("\t")) : line;
			temp += "\n";
		}
		bufferedReader.close();
		
		bufferedWriter = DBConnecter.getWriter();
//		삽입 및 순위 수정이 모두 완료된 temp로 덮어씌워준다.
		bufferedWriter.write(temp);
		bufferedWriter.close();
		
		return check;
	}
	
//	수정
//	화면에서 수정된 전체 정보를 Model 객체로 전달받는다.
	public boolean update(BoxOfficeVO boxOfficeVO) throws IOException{
		BufferedReader bufferedReader = DBConnecter.getReader();
		BufferedWriter bufferedWriter = null;
		String line = null, temp = "";
		boolean check = false;
		while((line = bufferedReader.readLine()) != null) {
//			기존 정보 중 순위를 비교하여
			if(Integer.valueOf(line.split("\t")[0]) == boxOfficeVO.getRanking()) {
//				순위가 같다면 수정 대상이기 때문에
//				기존 정보 대신 새롭게 수정된 정보를 temp에 담아준다.
				temp += boxOfficeVO + "\n";
//				FLAG는 true로 변경한다.
				check = true;
//				기존 정보는 담아주면 안되기 때문에 continue로 막아준다.
				continue;
			}
//			수정 대상이 아닌 정보들은 그대로 temp에 담아준다.
			temp += line + "\n";
		}
		bufferedReader.close();
		
		bufferedWriter = DBConnecter.getWriter();
//		수정 완료된 정보를 기존 문서에 덮어씌워준다.
		bufferedWriter.write(temp);
		bufferedWriter.close();
		
		return check;
	}
	
//	삭제
//	외부에서 삭제할 순위를 전달받는다.
	public boolean delete(int ranking) throws IOException{
		BufferedReader bufferedReader = DBConnecter.getReader();
		BufferedWriter bufferedWriter = null;
		String line = null, temp = "";
		int deletedRanking = ranking;
		boolean check = false;
		
		while((line = bufferedReader.readLine()) != null) {
//			기존 정보 중 순위를 비교하여
			if(Integer.valueOf(line.split("\t")[0]) == ranking) {
				check = true;
				continue;
			}
//			수정 대상이 아닌 정보들은 그대로 temp에 담아준다.
			temp += check ? deletedRanking++ + line.substring(line.indexOf("\t")) : line;
			temp += "\n";
		}
		bufferedReader.close();
		
		bufferedWriter = DBConnecter.getWriter();
//		수정 완료된 정보를 기존 문서에 덮어씌워준다.
		bufferedWriter.write(temp);
		bufferedWriter.close();
		
		return check;
	}
	
//	조회
	public ArrayList<BoxOfficeVO> select(String name) throws IOException{
		BufferedReader bufferedReader = DBConnecter.getReader();
		ArrayList<BoxOfficeVO> boxOfficeVOs = new ArrayList<BoxOfficeVO>();
		String line = null;
		int i = 0;
		
		while((line = bufferedReader.readLine()) != null) {
			String[] datas = line.split("\t");
			if(datas[1].contains(name)) {
				BoxOfficeVO boxOfficeVO = new BoxOfficeVO();
				boxOfficeVO.setRanking(Integer.valueOf(datas[i++]));
				boxOfficeVO.setName(datas[i++]);
				boxOfficeVO.setReleaseDate(datas[i++]);
				boxOfficeVO.setIncome(Long.valueOf(removeComma(datas[i].equals("") ? "0" : datas[i++])));
				boxOfficeVO.setGuestCount(Integer.valueOf(removeComma(removeS(datas[i++]))));
				boxOfficeVO.setScreenCount(Integer.valueOf(removeComma(removeS(datas[i++]))));
				boxOfficeVOs.add(boxOfficeVO);
				i = 0;
			}
		}
		return boxOfficeVOs;
	}
	
//	목록
	public ArrayList<BoxOfficeVO> selectAll() throws IOException{
		BufferedReader bufferedReader = DBConnecter.getReader();
		ArrayList<BoxOfficeVO> boxOfficeVOs = new ArrayList<BoxOfficeVO>();
		String line = null;
		int i = 0;
		
		while((line = bufferedReader.readLine()) != null) {
			String[] datas = line.split("\t");
			BoxOfficeVO boxOfficeVO = new BoxOfficeVO();
			boxOfficeVO.setRanking(Integer.valueOf(datas[i++]));
			boxOfficeVO.setName(datas[i++]);
			boxOfficeVO.setReleaseDate(datas[i++]);
			boxOfficeVO.setIncome(Long.valueOf(removeComma(datas[i].equals("") ? "0" : datas[i++])));
			boxOfficeVO.setGuestCount(Integer.valueOf(removeComma(removeS(datas[i].equals("") ? "0" : datas[i++]))));
			boxOfficeVO.setScreenCount(Integer.valueOf(removeComma(removeS(datas[i].equals("") ? "0" : datas[i++]))));
			boxOfficeVOs.add(boxOfficeVO);
			i = 0;
		}
		return boxOfficeVOs;
	}
	
	public String removeComma(String data) {
		return data.replaceAll(",", "");
	}
	
	public String removeS(String data) {
		return data.replaceAll("S ", "");
	}
	
	
}























