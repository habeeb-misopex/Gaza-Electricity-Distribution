package application;

import java.time.LocalDate;

public class ElectRecord {

	private LocalDate date;
	private float israeliLines = 0;
	private float gazaPowerPlant = 0;
	private float egyptianLines = 0;
	private float totalDailySupply = 0;
	private float overallDemand = 0;
	private float powerCutsHours = 0;
	private float temp = 0;

	public ElectRecord(String str) {
		date = LocalDate.parse(str);
	}
	
	public ElectRecord(LocalDate d) {
		date =d;
	}
	

	public ElectRecord(LocalDate date, float israeliLines, float gazaPowerPlant, float egyptianLines,
			float totalDailySupply, float overallDemand, float powerCutsHours, float temp) {

		this.date = date;
		this.israeliLines = israeliLines;
		this.gazaPowerPlant = gazaPowerPlant;
		this.egyptianLines = egyptianLines;
		this.totalDailySupply = totalDailySupply;
		this.overallDemand = overallDemand;
		this.powerCutsHours = powerCutsHours;
		this.temp = temp;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public float getIsraeliLines() {
		return israeliLines;
	}

	public void setIsraeliLines(float israeliLines) {
		this.israeliLines = israeliLines;
	}

	public float getGazaPowerPlant() {
		return gazaPowerPlant;
	}

	public void setGazaPowerPlant(float gazaPowerPlant) {
		this.gazaPowerPlant = gazaPowerPlant;
	}

	public float getEgyptianLines() {
		return egyptianLines;
	}

	public void setEgyptianLines(float egyptianLines) {
		this.egyptianLines = egyptianLines;
	}

	public float getTotalDailySupply() {
		return totalDailySupply;
	}

	public void setTotalDailySupply(float totalDailySupply) {
		this.totalDailySupply = totalDailySupply;
	}

	public float getOverallDemand() {
		return overallDemand;
	}

	public void setOverallDemand(float overallDemand) {
		this.overallDemand = overallDemand;
	}

	public float getPowerCutsHours() {
		return powerCutsHours;
	}

	public void setPowerCutsHours(float powerCutsHours) {
		this.powerCutsHours = powerCutsHours;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return date + "," + israeliLines + "," + gazaPowerPlant + "," + egyptianLines + "," + totalDailySupply + ","
				+ overallDemand + "," + powerCutsHours + "," + temp;
	}

	@Override
	public boolean equals(Object rec) {
		return (date.equals(((ElectRecord) rec).date));
	}

	public void updateTo(ElectRecord newRec) {
		this.israeliLines = newRec.getIsraeliLines();
		this.gazaPowerPlant = newRec.getGazaPowerPlant();
		this.egyptianLines = newRec.getEgyptianLines();
		this.totalDailySupply = newRec.getTotalDailySupply();
		this.overallDemand = newRec.getOverallDemand();
		this.powerCutsHours = newRec.getPowerCutsHours();
		this.temp = newRec.getTemp();
	}

}
