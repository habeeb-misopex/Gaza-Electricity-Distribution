package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Service {

	private DLinkedList<Year> yearsList;

	public Service() {

		yearsList = new DLinkedList<Year>();
	}

	public DLinkedList<Year> getYearsList() {
		return yearsList;
	}

	public int insertRecord(ElectRecord record) {

		Year newYear = new Year(record.getDate().getYear());
		Month newMonth = new Month(record.getDate().getMonthValue());
		Day newDay = new Day(record.getDate().getDayOfMonth(), record);

		Year foundedYear = this.getYearsList().searchSorted(newYear);

		if (foundedYear == null) {
//		System.out.print("UY-");
			this.getYearsList().insertSorted(newYear);
			newYear.getMonthsList().insertSorted(newMonth);
			newMonth.getDaysList().insertSorted(newDay);
			return 1;

		} else {
//		System.out.print("FY-");

			Month foundedMonth = foundedYear.getMonthsList().searchSorted(newMonth);
			if (foundedMonth == null) {
//			System.out.print("UM-");
				foundedYear.getMonthsList().insertSorted(newMonth);
				newMonth.getDaysList().insertSorted(newDay);
				return 1;

			} else {
//			System.out.print("FM-");
				Day foundedDay = foundedMonth.getDaysList().searchSorted(newDay);
				if (foundedDay == null) {
//				System.out.print("UD");
					foundedMonth.getDaysList().insertSorted(newDay);
					return 1;

				} else {
					System.out.println("this data has a record");
				return 0;}
				
			}

		}
	}

	public void updateRecord(ElectRecord oldRecord, ElectRecord newRecord) {
		ElectRecord foundedRec = this.searchRecord(oldRecord);
		if (foundedRec != null) {
			oldRecord.updateTo(newRecord);
		} else
			System.out.println("Old not exist");
	}

	public boolean deleteRecord(ElectRecord record) {

		Year year = new Year(record.getDate().getYear());
		Month month = new Month(record.getDate().getMonthValue());
		Day day = new Day(record.getDate().getDayOfMonth(), record);

		ElectRecord foundedRec = this.searchRecord(record);
		if (foundedRec != null) {
			Day foundedDay = this.getYearsList().searchSorted(year).getMonthsList().searchSorted(month).getDaysList()
					.searchSorted(day);
			this.getYearsList().searchSorted(year).getMonthsList().searchSorted(month).getDaysList()
					.deleteSorted(foundedDay);
			System.out.println("deleted sucess");
		}
		System.out.println("not exist");
		return false;
	}

	public ElectRecord searchRecord(ElectRecord record) {

		Year year = new Year(record.getDate().getYear());
		Month month = new Month(record.getDate().getMonthValue());
		Day day = new Day(record.getDate().getDayOfMonth(), record);

		Year foundedYear = this.getYearsList().searchSorted(year);
		if (foundedYear != null) {
			Month foundedMonth = foundedYear.getMonthsList().searchSorted(month);
			if (foundedMonth != null) {
				Day foundedDay = foundedMonth.getDaysList().searchSorted(day);
				if (foundedDay != null) {
					System.out.println("Founded day");
					return foundedDay.getRecord();
				} else
					return null;

			} else
				return null;

		} else
			return null;

	}

	public void fillFile(File file) throws IOException {
		
		Scanner fileScanner = new Scanner(file);
		fileScanner.nextLine();

		while (fileScanner.hasNext()) {
			String[] tokens = fileScanner.nextLine().split(",");

			LocalDate date = LocalDate.parse(tokens[0]);
			float israeliLines = Float.parseFloat(tokens[1]);
			float gazaPowerPlant = Float.parseFloat(tokens[2]);
			float egyptianLines = Float.parseFloat(tokens[3]);
			float totalDailySupply = Float.parseFloat(tokens[4]);
			float overallDemand = Float.parseFloat(tokens[5]);
			float powerCutsHours = Float.parseFloat(tokens[6]);
			float temp = Float.parseFloat(tokens[7]);

			ElectRecord currRec = new ElectRecord(date, israeliLines, gazaPowerPlant, egyptianLines, totalDailySupply,
					overallDemand, powerCutsHours, temp);

			this.insertRecord(currRec);

		}
	}

	public Statistics getDayOfYearsStat(String column, Day selectedDay) {
		float sum = 0;
		float min = 1000;
		float max = 0;
		int count = 0;

		DNode<Year> ptrYear = this.getYearsList().getHead();

		while (ptrYear != null) {
			Node<Month> ptrMonth = ptrYear.getData().getMonthsList().getHead();
			while (ptrMonth != null) {

				Day founded = ptrMonth.getData().getDaysList().searchSorted(selectedDay);
				if (founded != null) {
					float result = 0;
					switch (column) {

					case "Israeli Lines":
						result = founded.getRecord().getIsraeliLines();
						break;
					case "Gaza Power Plant":
						result = founded.getRecord().getGazaPowerPlant();
						break;
					case "Egyptian Lines":
						result = founded.getRecord().getEgyptianLines();
						break;
					case "Total Daily Supply":
						result = founded.getRecord().getTotalDailySupply();
						break;
					case "Overall Demand":
						result = founded.getRecord().getOverallDemand();
						break;
					case "Power Cuts Hours":
						result = founded.getRecord().getPowerCutsHours();
						break;
					case "Temp":
						result = founded.getRecord().getTemp();
						break;

					}
					sum += result;
					min = Math.min(min, result);
					max = Math.max(max, result);
					count++;
				}
				ptrMonth = ptrMonth.getNext();

			}
			ptrYear = ptrYear.getNext();
		}

		return new Statistics(min, max, sum, sum / count);
	}

	public Statistics getMonthOfYearsStat(String column, Month selectedMonth) {
		float sum = 0;
		float min = 1000;
		float max = 0;
		int count = 0;

		DNode<Year> ptrYear = this.getYearsList().getHead();
		while (ptrYear != null) {
			Month foundedMonth = ptrYear.getData().getMonthsList().searchSorted(selectedMonth);
			if (foundedMonth != null) {
				Node<Day> ptrDay = foundedMonth.getDaysList().getHead();
				while (ptrDay != null) {

					float result = 0;
					switch (column) {

					case "Israeli Lines":
						result = ptrDay.getData().getRecord().getIsraeliLines();
						break;
					case "Gaza Power Plant":
						result = ptrDay.getData().getRecord().getGazaPowerPlant();
						break;
					case "Egyptian Lines":
						result = ptrDay.getData().getRecord().getEgyptianLines();
						break;
					case "Total Daily Supply":
						result = ptrDay.getData().getRecord().getTotalDailySupply();
						break;
					case "Overall Demand":
						result = ptrDay.getData().getRecord().getOverallDemand();
						break;
					case "Power Cuts Hours":
						result = ptrDay.getData().getRecord().getPowerCutsHours();
						break;
					case "Temp":
						result = ptrDay.getData().getRecord().getTemp();
						break;

					}
					sum += result;
					min = Math.min(min, result);
					max = Math.max(max, result);
					count++;

					ptrDay = ptrDay.getNext();
				}
			}
			ptrYear = ptrYear.getNext();
		}
		return new Statistics(min, max, sum, sum/count);

	}

	public Statistics getYearDaysStat(String column , Year selectedYear) {
		
		float sum = 0;
		float min = 1000;
		float max = 0;
		int count = 0;
		
		Year foundedYear = this.getYearsList().searchSorted(selectedYear);
		if(foundedYear != null) {
			Node<Month> ptrMonth = foundedYear.getMonthsList().getHead();
			while(ptrMonth != null) {
				Node<Day> ptrDay = ptrMonth.getData().getDaysList().getHead();
				while(ptrDay != null) {
					
					float result = 0;
					switch (column) {

					case "Israeli Lines":
						result = ptrDay.getData().getRecord().getIsraeliLines();
						break;
					case "Gaza Power Plant":
						result = ptrDay.getData().getRecord().getGazaPowerPlant();
						break;
					case "Egyptian Lines":
						result = ptrDay.getData().getRecord().getEgyptianLines();
						break;
					case "Total Daily Supply":
						result = ptrDay.getData().getRecord().getTotalDailySupply();
						break;
					case "Overall Demand":
						result = ptrDay.getData().getRecord().getOverallDemand();
						break;
					case "Power Cuts Hours":
						result = ptrDay.getData().getRecord().getPowerCutsHours();
						break;
					case "Temp":
						result = ptrDay.getData().getRecord().getTemp();
						break;

					}
					sum += result;
					min = Math.min(min, result);
					max = Math.max(max, result);
					count++;
					
					ptrDay = ptrDay.getNext();
				}
				ptrMonth = ptrMonth.getNext();
			}
		}
		
		
		return new Statistics(min, max, sum, sum/count);
	}

	public Statistics getAllDataStat(String column) {
		
		float sum = 0;
		float min = 1000;
		float max = 0;
		int count = 0;
		
		DNode<Year> ptrYear = this.getYearsList().getHead();
		while(ptrYear != null ) {
			Node<Month> ptrMonth = ptrYear.getData().getMonthsList().getHead();
			while(ptrMonth != null) {
				Node<Day> ptrDay = ptrMonth.getData().getDaysList().getHead();
				while(ptrDay != null) {
					
					float result = 0;
					switch (column) {

					case "Israeli Lines":
						result = ptrDay.getData().getRecord().getIsraeliLines();
						break;
					case "Gaza Power Plant":
						result = ptrDay.getData().getRecord().getGazaPowerPlant();
						break;
					case "Egyptian Lines":
						result = ptrDay.getData().getRecord().getEgyptianLines();
						break;
					case "Total Daily Supply":
						result = ptrDay.getData().getRecord().getTotalDailySupply();
						break;
					case "Overall Demand":
						result = ptrDay.getData().getRecord().getOverallDemand();
						break;
					case "Power Cuts Hours":
						result = ptrDay.getData().getRecord().getPowerCutsHours();
						break;
					case "Temp":
						result = ptrDay.getData().getRecord().getTemp();
						break;

					}
					sum += result;
					min = Math.min(min, result);
					max = Math.max(max, result);
					
					count++;
					ptrDay = ptrDay.getNext();
				}
				ptrMonth = ptrMonth.getNext();
			}
			ptrYear = ptrYear.getNext();
		}
		
		
		return new Statistics(min, max, sum, sum/count);
	}

}
