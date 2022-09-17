package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;

public class Run {
	
	public static final String SERVICE_KEY = "3vbFSNBucTjUmlz76x3t%2FXHUxbPw4FBSuJfqY2xhH5n6sriEAxlGGP%2Fdqlhf2FiOxzA4PbMcX7GpGC%2FowflUrQ%3D%3D";
 
			
	

	public static void main(String[] args) throws IOException {
		
		// OpenApi서버로 요청하는 url만들기
		
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICE_KEY;
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8"); // 요청시 전달값에 한글이 있으면 인코딩 작업해줘야함
		url += "&returnType=json";
		
		// System.out.println(url);
		
		// 자바코드로 요청보내야함
		
		// ** HttpURLConnection 객체를 활용해서 OpenApi요청 절차 **
		// 1. 요청하고자하는 url을 전달하면서 java.net.URL 객체 생성
		URL requestUrl = new URL(url);
		// 2. 1번 과정으로 생성된 URL객체 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		// 3. 요청에 필요한 Header설정하기
		urlConnection.setRequestMethod("GET");
		// 4. 해당 OpenAPI서버로 요청을 보낸 후 입력데이터로 읽어오기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		System.out.println(responseText);
		
		// JSONObject, JSONArray -> JSON라이브러리에서 제공하는 애들 밑에 애들하고 다름
		// JsonObject. JsonArray. JsonElement를 이용해서 파싱 할 수 있음 => GSON에서 제공
		// 각각의 item 정보 => AirVO에 담고 => ArrayList에 차곡차곡쌓기
		
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
		System.out.println(totalObj);
		JsonObject responseObj = totalObj.getAsJsonObject("response"); // response속성에 접근 : {} JsonObject
		System.out.println(responseObj);
		JsonObject bodyObj = responseObj.getAsJsonObject("body"); // body속성에 접근 : {} JsonObj
		System.out.println(bodyObj);
		int totalCount = bodyObj.get("totalCount").getAsInt();
		System.out.println(totalCount);
		JsonArray itemArr = bodyObj.getAsJsonArray("items"); // items 속성 접근 : [] JsonArray
		System.out.println(itemArr);
		
		ArrayList<AirVO> list = new ArrayList();
		for(int i = 0; i < itemArr.size(); i++) {
			JsonObject item = itemArr.get(i).getAsJsonObject();
			// System.out.println(item);
			
			AirVO air = new AirVO();
			
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			air.setCoValue(item.get("coValue").getAsString());
			air.setNo2Value(item.get("no2Value").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			
			list.add(air);
			
		}
		
		// 5. 사용한 스트림 반납
		br.close();
		urlConnection.disconnect();
		
		for(AirVO a : list) {
			System.out.println(a);
		}
		
	}

}
