package feature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegionTemperatureResolver {
	private static final String apiKey = "IDKmzvhfRoqyps74X5aKrQ";

	public static String getTemperature(String imageDate, String stn) {
		String temperature = null;

		try {
			//URL 및 헤더 설정
			URL url = new URL("https://apihub.kma.go.kr/api/typ01/url/kma_sfctm5.php?tm2=" + imageDate + "&obs=TA&stn="+ stn + "&disp=0&help=0&authKey=" + apiKey);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");

			//inputStream 생성 및 값 읽어오기
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "euc-kr"));
			StringBuffer response = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//기온 파싱하기
			String responseData = response.toString();
			String[] split = responseData.split("\\s+");
			int length = split.length - 1;
			temperature = split[length].substring(0, split[length].indexOf('#'));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temperature;
	}
}
