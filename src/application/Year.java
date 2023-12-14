package application;

public class Year implements Comparable<Year> {

	
	private int id;
	private LinkedList<Month> monthsList;
	
	

	public Year(int id) {

		this.id = id;
		monthsList = new LinkedList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LinkedList<Month> getMonthsList() {
		return monthsList;
	}


	@Override
	public int compareTo(Year o) {
		
		return id - o.id;
	}
	
	@Override
	public boolean equals(Object year) {
		return (id == ((Year) year).id);
	}
	
	@Override
	public String toString() {
		return id+"";
	}
}
 