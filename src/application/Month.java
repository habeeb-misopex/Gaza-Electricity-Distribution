package application;

public class Month implements Comparable<Month> {
	
	int id;
	LinkedList<Day> daysList;
	
	
	public Month(int id) {

		this.id = id;
		daysList = new LinkedList<Day>();
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LinkedList<Day> getDaysList() {
		return daysList;
	}


	@Override
	public int compareTo(Month o) {
		return id - o.id;
	}
	
	@Override
	public boolean equals(Object month) {
		return (id == ((Month) month).id);
	}
	
	@Override
	public String toString() {
		return id+"";
	}


	
	
	
	
	
	

}
