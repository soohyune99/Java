package levelThree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LevelSix {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int result = (Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
			System.out.println("Case #" + (i+1) + ": " + result);
		}
		br.close();
	}
}
