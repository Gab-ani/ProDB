package application.parsing;

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

import application.data.Match;

// The class dedicated to parse match data from https://stratz.com and pack into data objects

@Service
public class StratzMatchSniffer implements MatchSniffer {

	// will trigger GitGuardian but idgaf, they are free, only used for identification
	// TODO probably rewrite as some file config thing with gitignore or hide into database
	private static final String API_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJodHRwczovL3N0ZWFtY29tbXVuaXR5LmNvbS9vcGVuaWQvaWQvNzY1NjExOTgyODk3OTU3ODgiLCJ1bmlxdWVfbmFtZSI6IkRhcmsgb2YgQ2hhcmdlbmVzcyIsIlN1YmplY3QiOiIwNTZkZDI3Yi03MTAzLTRlMzctYTc0OC1jZmZjMjgwYjViZTgiLCJTdGVhbUlkIjoiMzI5NTMwMDYwIiwibmJmIjoxNjYwMzM3NTI2LCJleHAiOjE2OTE4NzM1MjYsImlhdCI6MTY2MDMzNzUyNiwiaXNzIjoiaHR0cHM6Ly9hcGkuc3RyYXR6LmNvbSJ9.m_s22LBhQgb863YOUDy0zze5k2I0dXgvw9kOGqbQXT0";
	
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0";
	
	public Match formById(Long matchId) {
		
		String json = fetchJson(matchId);
		
		return jsonToMatch(json);
		
	}
	
	private String fetchJson(long matchId) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", USER_AGENT);
		headers.add("Authorization", API_TOKEN);
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(urlFromId(matchId), HttpMethod.GET, entity, String.class);
			String json = response.getBody();
			
			return json;
		} catch (Exception e) {
			System.out.println("can't fetch " + matchId);
			return "";
		}
		
	}
	
	private Match jsonToMatch(String json) {
		
		if(json == null || json.equals("")) {
			System.out.println("empty json");
			return null;
		}
		
		Match result = new Match();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jNode;
		
		try {
			
			jNode = mapper.readTree(json);

			for(int i = 0; i < 10; i++) {
				parsePlayer(result, jNode.get("players").get(i));
			}
			
			result.setDate(jNode.get("statsDateTime").asText());
			result.setID(jNode.get("id").asLong());
			if(jNode.get("didRadiantWin").asBoolean()) {
				result.setWinner("radiant");
			} else {
				result.setWinner("dire");
			}
				
			
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		return result;
	}
	
	private void parsePlayer(Match match, JsonNode player) {
		
		String hero = player.get("heroId").asText();
//		System.out.println(hero);
		
		if(player.get("isRadiant").asBoolean()) {						
			
			if(player.get("role").asInt() == 2) {
				match.radiantHard(hero);
				return;
			}
			if(player.get("role").asInt() == 1) {
				match.radiantSoft(hero);
				return;
			}
			if(player.get("lane").asInt() == 1) {
				match.radiantCarry(hero);
				return;
			} else if(player.get("lane").asInt() == 2) {
				match.radiantMid(hero);
				return;
			} else {
				match.radiantOfflane(hero);
				return;
			}
			
		} else {														
			
			if(player.get("role").asInt() == 2) {
				match.direHard(hero);
				return;
			}
			if(player.get("role").asInt() == 1) {
				match.direSoft(hero);
				return;
			}
			if(player.get("lane").asInt() == 1) {
				match.direCarry(hero);
				return;
			} else if(player.get("lane").asInt() == 2) {
				match.direMid(hero);
				return;
			} else {
				match.direOfflane(hero);
				return;
			}
			
		}
		//*****************************************************************************************
		// player.role coded as:
		// 2 = hard
		// 1 = soft
		// 0 = core
		// player.lane coded as:
		// 1 = easylane
		// 2 = midlane
		// 3 = hardlane
		// player.side is coded as "isRadiant" boolean
		// so  radiant carry will be (isRadiant == true, lane = 1, role = 0) etc.
		//*****************************************************************************************
	}

	private String urlFromId(long id) {
		System.out.println("https://api.stratz.com/api/v1/match/" + id + "/breakdown");
		return "https://api.stratz.com/api/v1/match/" + id + "/breakdown";
	}
	
}
