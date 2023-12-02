package feature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RegionSTNResolver {
	public static Map<String, String> regionMap = Map.ofEntries(Map.entry("서울특별시", "108"), Map.entry("인천광역시", "112"), Map.entry("경기도", "119"), Map.entry("강원특별자치도", "105"), Map.entry("충청북도", "131"), Map.entry("충청남도", "232"), Map.entry("세종특별자치시", "239"), Map.entry("대전광역시", "133"), Map.entry("전라북도", "146"), Map.entry("전라남도", "165"), Map.entry("광주광역시", "156"), Map.entry("경상북도", "155"), Map.entry("경상남도", "138"), Map.entry("대구광역시", "143"), Map.entry("울산광역시", "152"), Map.entry("부산광역시", "159"), Map.entry("제주특별자치도", "184"));

	public static String getSTN(String region) {
		return regionMap.get(region);
	}

	public static String getRegion(double latitude, double longitude) {
		String apiKey = "4865a8281adb66fbb6b078bde2205414";
		String region = null;
		String apiUrl = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?input_coord=WGS84&output_coord=WGS84&y=" + latitude + "&x=" + longitude;
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "KakaoAK " + apiKey);

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();

				String jsonResponse = response.toString();
				region = extractRegionFromJson(jsonResponse);
				System.out.println("Region: " + region);
			} else {
				System.out.println("HTTP Request Failed with response code: " + responseCode);
			}
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return region;
	}

	//Region 파싱 함수
	private static String extractRegionFromJson(String jsonResponse) {
		int startIndex = jsonResponse.indexOf("\"region_1depth_name\":") + "\"region_1depth_name\":".length();
		int endIndex = jsonResponse.indexOf(",", startIndex);
		return jsonResponse.substring(startIndex, endIndex).replaceAll("\"", "").trim();
	}
}