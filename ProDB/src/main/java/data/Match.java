package data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {

	@Id
	private int matchId;
	 
	private String date;
	
	private String radiant1;
	private String radiant2;
	private String radiant3;
	private String radiant4;
	private String radiant5;				// indexes are farm priority sensetive eg 1 = safe, 2 = mid...
	private String dire1;
	private String dire2;
	private String dire3;
	private String dire4;
	private String dire5;
	
	public Match() {
		
	}
	
}
