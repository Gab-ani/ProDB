package parcing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.Match;

// The class dedicated to parse match data from https://stratz.com and pack into data objects

@Service
public class StratzMatchSniffer implements MatchSniffer {

	private static final String API_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJodHRwczovL3N0ZWFtY29tbXVuaXR5LmNvbS9vcGVuaWQvaWQvNzY1NjExOTgyODk3OTU3ODgiLCJ1bmlxdWVfbmFtZSI6IkRhcmsgb2YgQ2hhcmdlbmVzcyIsIlN1YmplY3QiOiIwNTZkZDI3Yi03MTAzLTRlMzctYTc0OC1jZmZjMjgwYjViZTgiLCJTdGVhbUlkIjoiMzI5NTMwMDYwIiwibmJmIjoxNjYwMzM3NTI2LCJleHAiOjE2OTE4NzM1MjYsImlhdCI6MTY2MDMzNzUyNiwiaXNzIjoiaHR0cHM6Ly9hcGkuc3RyYXR6LmNvbSJ9.m_s22LBhQgb863YOUDy0zze5k2I0dXgvw9kOGqbQXT0";
	
	public Match formById(Long matchId) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0");
		headers.add("Authorization", API_TOKEN);
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<String> response = restTemplate.exchange(urlFromId(6712506154l), HttpMethod.GET, entity, String.class);
		
		String json = response.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jNode;
		try {
			jNode = mapper.readTree(json);
			System.out.println(jNode.get("players").get(0).get("heroId"));
			System.out.println(jNode.get("players").get(1).get("heroId"));
			System.out.println(jNode.get("players").get(2).get("heroId"));
			System.out.println(jNode.get("players").get(3).get("heroId"));
			System.out.println(jNode.get("players").get(4).get("heroId"));
			System.out.println(jNode.get("players").get(5).get("heroId"));
			System.out.println(jNode.get("players").get(6).get("heroId"));
			System.out.println(jNode.get("players").get(7).get("heroId"));
			System.out.println(jNode.get("players").get(8).get("heroId"));
			System.out.println(jNode.get("players").get(9).get("heroId"));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
			PrintWriter out = new PrintWriter("output.txt");
			out.print(response.getBody());
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private String urlFromId(long id) {
		return "https://api.stratz.com/api/v1/match/" + id + "/breakdown";
	}
	
}
