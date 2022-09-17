package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

	public static final String SERVICE_KEY = "3vbFSNBucTjUmlz76x3t%2FXHUxbPw4FBSuJfqY2xhH5n6sriEAxlGGP%2Fdqlhf2FiOxzA4PbMcX7GpGC%2FowflUrQ%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="air.do", produces="application/json; charset=UTF-8")
	public String airPollution(String location) throws IOException {
		
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICE_KEY;
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8"); // 요청시 전달값에 한글이 있으면 인코딩 작업해줘야함
		url += "&returnType=json";
		
		 System.out.println(url);
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		
		return responseText;
	}
	
}
