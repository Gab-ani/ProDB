package application.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hero {
	
	@Id
	private int id;
	
	private String name;

	public Integer getId() {
		return id;
	}
	
}
