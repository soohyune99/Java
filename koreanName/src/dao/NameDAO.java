package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import vo.BoyVO;
import vo.GirlVO;
import vo.NameDTO;

public class NameDAO {
	// 여자, 남자의 정보를 담은 객체 ArrayList 타입
	public ArrayList<GirlVO> girls;
	public ArrayList<BoyVO> boys;
	
	//병합 : path1과 path2를 병합, merge() 사용
	// path3은 두 데이터를 합치고 담을 공간
	public void merge(String path1, String path2, String path3) throws IOException{
		// boyReader = path1
		BufferedReader boyReader = DBConnecter.getReader(path1);
		// girlReader = path2
		BufferedReader girlReader = DBConnecter.getReader(path2);
		BufferedWriter bufferedWriter = null;
		
		BoyDAO boyDAO = new BoyDAO();
		GirlDAO girlDAO = new GirlDAO();
		
		girls = new ArrayList<GirlVO>();
		boys = new ArrayList<BoyVO>();
		
		// line은 읽어오는 텍스트를 담는다. temp는 읽어온 문자열을 담는다
		String line = null, temp = "";
		
		// 남자아이를 읽어왔는데 비어있지않으면 반복
		while((line = boyReader.readLine()) != null) {
			// 읽어온 문자열을 line에 계속해서 \n로 띄어쓰기 하면서 추가
			temp += line + "\n";
			// 남자아이 DAO- 메모장의 데이터를 VO객체로 리턴하는 메소드 : setObject()
			boys.add(boyDAO.setObject(line));
		}
		// 남자아이 읽어오는것 종료
		boyReader.close();
		
		// 여자아이를 읽어왔는데 비어있지않으면 반복
		while((line = girlReader.readLine()) != null) {
			// 읽어온 문자열을 line에 계속해서 \n로 띄어쓰기 하면서 추가
			temp += line + "\n";
			// 여자아이 DAO- 메모장의 데이터를 VO객체로 리턴하는 메소드 : setObject()
			girls.add(girlDAO.setObject(line));
		}
		
		// 여자아이 읽어오는것 종료
		girlReader.close();
		
		// 파일을 출력할 경로는 path3
		bufferedWriter = DBConnecter.getWriter(path3);
		// 남자아이와 여자아이 정보가 모두 담긴 temp
		bufferedWriter.write(temp);
		bufferedWriter.close();
	}
	
	//랭킹 수정
	// 랭킹 수정(이름 수 순서 - 내림차순) : updateRanking()
//
//
//	   ※ HashSet을 ArrayList로 변경
//	   HashSet set = new HashSet();
//	   ArrayList list = new ArrayList(set);
//	   ※ 동일한 이름 수일 경우 공동 순위로 구현한다.
//	   예)
//	   순위   이름 수
//	   1   15000   15000
//	   2   12000   12000
//	   3   10000   10000
//	   3   10000   8000
//	   3   10000
//	   6   8000
	// 랭킹 수정후 업데이트 할 경로
	public void updateRanking(String path) throws IOException{
		BufferedWriter bufferedWriter = DBConnecter.getWriter(path);
		// ※ 병합 시 두 개의 ArrayList를 하나의 ArrayList로 병합하는 방법
		// ArrayList total = new ArrayList();
		// ArrayList a = new ArrayList();  ArrayList b = new ArrayList();
		// total.addAll(a); total.addAll(b);
		// 남자아이와 여자아이의 타입이 다르므로 모든 클래스의 부모인 object타입으로 선언
		ArrayList<Object> datas = new ArrayList<Object>();
		// 이름 수만 담을 Integer타입의 ArrayList 
		ArrayList<Integer> populations = new ArrayList<Integer>();
		// 중복된 이름 수 제거 : HashSet 사용
		HashSet<Integer> populationSet = null;
		String temp = "";
		int ranking = 1, count = 0;
		
		// datas에 병합
		datas.addAll(boys);
		datas.addAll(girls);
		
		boys.stream().map(v -> v.getPopulation()).forEach(populations::add);
		girls.stream().map(v -> v.getPopulation()).forEach(populations::add);
		
		// ※ HashSet을 ArrayList로 변경
		// HashSet set = new HashSet();
		// ArrayList list = new ArrayList(set);
		populationSet = new HashSet<Integer>(populations);
		populations = new ArrayList<Integer>(populationSet);
		
		populations = (ArrayList<Integer>)populations.stream().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		
		for (Integer population : populations) {
			count = 0;
			for (Object obj : datas) {
				if(obj instanceof BoyVO) {
					BoyVO boyVO = (BoyVO) obj;
					if(population == boyVO.getPopulation()) {
						NameDTO nameDTO = new NameDTO();
						nameDTO.setGender("B");
						nameDTO.setName(boyVO.getName());
						nameDTO.setRanking(ranking);
						nameDTO.setPopulation(population);
						
						temp += nameDTO + "\n";
						count ++;
					}
				}
				
				if(obj instanceof GirlVO) {
					GirlVO girlVO = (GirlVO) obj;
					if(population == girlVO.getPopulation()) {
						NameDTO nameDTO = new NameDTO();
						nameDTO.setGender("G");
						nameDTO.setName(girlVO.getName());
						nameDTO.setRanking(ranking);
						nameDTO.setPopulation(population);
						
						temp += nameDTO + "\n";
						count ++;
					}
				}
			}
			if(count > 1) {
				ranking += count - 1;
			}
			ranking ++;
		}
		
		bufferedWriter.write(temp);
		bufferedWriter.close();
	}
}



















