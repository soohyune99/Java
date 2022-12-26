package levelThree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// StringTokenizer는 결과값이 문자열이면 split는 결과값이 문자열 배열
// StringTokenizer는 반복문을 이용해 하나하나 뽑을 수 밖에 없다.

public class LevelFive {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 입력받을 정수의 줄
		int T = Integer.parseInt(br.readLine());
		
		// 문자열 분리
		StringTokenizer st;
		
		for (int i = 0; i < T; i++) {
			// " " 구분 기준으로 문자열 분리
			st = new StringTokenizer(br.readLine(), " ");
			// 분리된 문자열을 nextToken()메서드로 확인
			bw.write((Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())) + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
		
		
	}
}
