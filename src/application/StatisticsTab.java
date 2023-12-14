package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StatisticsTab {

	private BorderPane root = new BorderPane();
	ToggleGroup toggle;
	String selectedColumn = "";
	ComboBox<String> columnBox;
	TextField tfStatOf;
	Label lbStatOf;
	Label lbIfFound;
	Button btGetStat;
	TextField tfSum;
	TextField tfAvg;
	TextField tfMin;
	TextField tfMax;

	public StatisticsTab() {
		HBox hb = new HBox(15);
		toggle = new ToggleGroup();
		RadioButton rbYear = new RadioButton("Year");
		rbYear.setToggleGroup(toggle);
		RadioButton rbMonth = new RadioButton("Month");
		rbMonth.setToggleGroup(toggle);
		RadioButton rbDay = new RadioButton("Day");
		rbDay.setToggleGroup(toggle);
		RadioButton rbAll = new RadioButton("All Data");
		rbAll.setToggleGroup(toggle);
		hb.getChildren().addAll(rbYear, rbMonth, rbDay, rbAll);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(15, 0, 0, 0));

		GridPane gp = new GridPane();
		gp.setHgap(5);
		gp.setVgap(15);
		gp.setAlignment(Pos.CENTER);

		lbStatOf = new Label("(Day/Month/Year) ");
		tfStatOf = new TextField("");
		lbIfFound = new Label("");
		gp.addRow(0, lbStatOf, tfStatOf, lbIfFound);

		Label lbColumn = new Label("Column:");
		columnBox = new ComboBox<>();
		columnBox.setPromptText("Select A Column");
		columnBox.getItems().addAll("Israeli Lines", "Gaza Power Plant", "Egyptian Lines", "Total Daily Supply",
				"Overall Demand", "Power Cuts Hours", "Temp");
		gp.addRow(1, lbColumn, columnBox);

		btGetStat = new Button("Get Statistics");
		gp.addRow(2, btGetStat);

		GridPane gp2 = new GridPane();
		gp2.setHgap(5);
		gp2.setVgap(15);
		gp2.setAlignment(Pos.CENTER);
		gp2.setPadding(new Insets(0, 0, 15, 0));

		Label lbSum = new Label("Sum:");
		tfSum = new TextField();
		tfSum.setEditable(false);
		gp2.addRow(0, lbSum, tfSum);

		Label lbAvg = new Label("Avarage:");
		tfAvg = new TextField();
		tfAvg.setEditable(false);
		gp2.addRow(1, lbAvg, tfAvg);

		Label lbMax = new Label("Max:");
		tfMax = new TextField();
		tfMax.setEditable(false);
		gp2.addRow(2, lbMax, tfMax);

		Label lbMin = new Label("Min:");
		tfMin = new TextField();
		tfMin.setEditable(false);
		gp2.addRow(3, lbMin, tfMin);

		activeButtons();
		root.setBottom(gp2);
		root.setCenter(gp);
		root.setTop(hb);

	}

	public void activeButtons() {
		
		btGetStat.setOnAction(a -> {
			if (columnBox.getValue() == null || tfStatOf.getText().equals("") || toggle.getSelectedToggle() == null)
				lbIfFound.setText("Pleas choose all data :)\nType 0 here if All Data");
			else {
				lbIfFound.setText("");
				String column = columnBox.getValue().toString();
				String type = ((RadioButton) toggle.getSelectedToggle()).getText();
				int id = 0;

				if (!tfStatOf.getText().equals(""))
					id = Integer.parseInt(tfStatOf.getText());

				Statistics result = new Statistics();
				switch (type) {
				case "Day":
					result = Main.ser.getDayOfYearsStat(column, new Day(id));
					break;
				case "Month":
					result = Main.ser.getMonthOfYearsStat(column, new Month(id));
					break;
				case "Year":
					result = Main.ser.getYearDaysStat(column, new Year(id));
					break;
				case "All Data":
					result = Main.ser.getAllDataStat(column);
					break;
				}
				
				if (result.getMin() == 1000) {
					tfAvg.setText("Invalid/Null Input");
					tfMax.setText("Invalid/Null Input");
					tfMin.setText("Invalid/Null Input");
					tfSum.setText("Invalid/Null Input");
				} else {
					tfAvg.setText("" + result.getAvg());
					tfMax.setText("" + result.getMax());
					tfMin.setText("" + result.getMin());
					tfSum.setText("" + result.getSum());
				}
			}

		});
	}

	public StackPane getTab() {

		StackPane sp = new StackPane();
		sp.getChildren().add(root);
		return sp;
	}

}
