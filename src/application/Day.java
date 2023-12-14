package application;

public class Day implements Comparable<Day> {

	private int id;
	private ElectRecord record;

	public Day(int id, ElectRecord record) {

		this.id = id;
		this.record = record;
	}
	
	public Day(int id) {

		this.id = id;
		this.record = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ElectRecord getRecord() {
		return record;
	}

	public void setRecord(ElectRecord record) {
		this.record = record;
	}

	@Override
	public int compareTo(Day o) {
		return id - o.id;
	}

	@Override
	public boolean equals(Object day) {
		return (id == ((Day) day).id);
	}
	
	@Override
	public String toString() {
		return id+"";
	}

}
