package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ManagementTab {

	private BorderPane root = new BorderPane();
	private Button btLoadFile;
	private Button btSeach;
	TextField tfIsraLines;
	TextField tfGazaPowerPlant;
	TextField tfEgyLines;
	TextField tfTotalDailySupply;
	TextField tfOverallDemand;
	TextField tfPowerCutsHours;
	TextField tfTemp;
	Label lbNote;
	DatePicker datePicker;

	Button btInsert;
	Button btDelete;
	Button btUpdate;

	public ManagementTab() {

		VBox vb1 = new VBox(15);
		datePicker = new DatePicker();
		btLoadFile = new Button("Select File");
		btSeach = new Button("Seach");
		lbNote = new Label("Any Note Will Appear Here !!");
		vb1.getChildren().addAll(datePicker, btSeach, btLoadFile, lbNote);
		vb1.setPadding(new Insets(50, 0, 0, 50));

		GridPane grid = new GridPane();
		grid.setVgap(20);
		grid.setHgap(10);

		Label lbIsraelLines = new Label("Israeli Lines:");
		tfIsraLines = new TextField();
		grid.addRow(0, lbIsraelLines, tfIsraLines);

		Label lbEgyLines = new Label("Egyptian Lines:");
		tfEgyLines = new TextField();
		grid.addRow(1, lbEgyLines, tfEgyLines);

		Label lbGazaPowePlant = new Label("Gaza Power Plant:");
		tfGazaPowerPlant = new TextField();
		grid.addRow(2, lbGazaPowePlant, tfGazaPowerPlant);

		Label lbTotalDailySupply = new Label("Total Daily Supply:");
		tfTotalDailySupply = new TextField();
		grid.addRow(3, lbTotalDailySupply, tfTotalDailySupply);

		Label lbOverallDemand = new Label("Overall Demand:");
		tfOverallDemand = new TextField();
		grid.addRow(4, lbOverallDemand, tfOverallDemand);

		Label lbPowerCuts = new Label("Power Cuts Hours:");
		tfPowerCutsHours = new TextField();
		grid.addRow(5, lbPowerCuts, tfPowerCutsHours);

		Label lbTemp = new Label("Temp:");
		tfTemp = new TextField();
		grid.addRow(6, lbTemp, tfTemp);
		grid.setAlignment(Pos.CENTER);

		HBox hx = new HBox(20);
		btInsert = new Button("Insert");
		btDelete = new Button("Delete");
		btUpdate = new Button("Update");
		hx.getChildren().addAll(btInsert, btUpdate, btDelete);
		hx.setAlignment(Pos.CENTER);
		hx.setPadding(new Insets(0, 0, 20, 0));

		root.setLeft(vb1);
		root.setCenter(grid);
		root.setBottom(hx);

		activeButtons();

	}

	public void activeButtons() {

		btSeach.setOnAction(a -> {
			if (datePicker.getValue() == null) {
				lbNote.setText("U have to select a date please :)");
			} else {
				LocalDate date = datePicker.getValue();
				ElectRecord record = Main.ser.searchRecord(new ElectRecord(date));
				if (record != null) {
					tfEgyLines.setText("" + record.getEgyptianLines());
					tfGazaPowerPlant.setText("" + record.getGazaPowerPlant());
					tfIsraLines.setText("" + record.getIsraeliLines());
					tfOverallDemand.setText("" + record.getOverallDemand());
					tfPowerCutsHours.setText("" + record.getPowerCutsHours());
					tfTemp.setText("" + record.getTemp());
					tfTotalDailySupply.setText("" + record.getTotalDailySupply());
					lbNote.setText("");
				} else {
					tfEgyLines.setText("");
					tfGazaPowerPlant.setText("");
					tfIsraLines.setText("");
					tfOverallDemand.setText("");
					tfPowerCutsHours.setText("");
					tfTemp.setText("");
					tfTotalDailySupply.setText("");
					lbNote.setText("This date has no record :(");
				}
			}
		});

		btInsert.setOnAction(b -> {

			if (datePicker.getValue() == null || tfEgyLines.getText() == "" || tfGazaPowerPlant.getText() == ""
					|| tfIsraLines.getText() == "" || tfOverallDemand.getText() == ""
					|| tfPowerCutsHours.getText() == "" || tfTemp.getText() == "" || tfTotalDailySupply.getText() == "")
				lbNote.setText("Fill all data please :)");
			else {

				ElectRecord record1 = new ElectRecord(datePicker.getValue());
				record1.setEgyptianLines(Float.parseFloat(tfEgyLines.getText()));
				record1.setGazaPowerPlant(Float.parseFloat(tfGazaPowerPlant.getText()));
				record1.setIsraeliLines(Float.parseFloat(tfIsraLines.getText()));
				record1.setOverallDemand(Float.parseFloat(tfOverallDemand.getText()));
				record1.setPowerCutsHours(Float.parseFloat(tfPowerCutsHours.getText()));
				record1.setTemp(Float.parseFloat(tfTemp.getText()));
				record1.setTotalDailySupply(Float.parseFloat(tfTotalDailySupply.getText()));
				if (Main.ser.insertRecord(record1) == 1)
					lbNote.setText("Inserted Successfully :)");
				else
					lbNote.setText("This date has a record ,\nU can update it :)");
			}
		});

		btUpdate.setOnAction(c -> {
			if (datePicker.getValue() == null || tfEgyLines.getText() == "" || tfGazaPowerPlant.getText() == ""
					|| tfIsraLines.getText() == "" || tfOverallDemand.getText() == ""
					|| tfPowerCutsHours.getText() == "" || tfTemp.getText() == "" || tfTotalDailySupply.getText() == "")
				lbNote.setText("Fill all data please :)");

			else {
				ElectRecord updatedRec = Main.ser.searchRecord(new ElectRecord(datePicker.getValue()));
				updatedRec.setEgyptianLines(Float.parseFloat(tfEgyLines.getText()));
				updatedRec.setGazaPowerPlant(Float.parseFloat(tfGazaPowerPlant.getText()));
				updatedRec.setIsraeliLines(Float.parseFloat(tfIsraLines.getText()));
				updatedRec.setOverallDemand(Float.parseFloat(tfOverallDemand.getText()));
				updatedRec.setPowerCutsHours(Float.parseFloat(tfPowerCutsHours.getText()));
				updatedRec.setTemp(Float.parseFloat(tfTemp.getText()));
				updatedRec.setTotalDailySupply(Float.parseFloat(tfTotalDailySupply.getText()));
				lbNote.setText("Updated Successfully :)");
			}

		});

		btDelete.setOnAction(d -> {
			if (datePicker.getValue() == null) {
				lbNote.setText("U have to select a date please :)");
			} else {
				ElectRecord deletedRec = Main.ser.searchRecord(new ElectRecord(datePicker.getValue()));
				if (deletedRec != null) {
					Main.ser.deleteRecord(deletedRec);
					tfEgyLines.setText("");
					tfGazaPowerPlant.setText("");
					tfIsraLines.setText("");
					tfOverallDemand.setText("");
					tfPowerCutsHours.setText("");
					tfTemp.setText("");
					tfTotalDailySupply.setText("");
					lbNote.setText("Deleted Successfully :)");

				} else {

					lbNote.setText("This day has no record :(");
				}
			}
		});

		btLoadFile.setOnAction(e -> {
			Alert error = new Alert(AlertType.ERROR);
			Alert succes = new Alert(AlertType.INFORMATION);

			try {
				FileChooser fc = new FileChooser();
				File source = fc.showOpenDialog(null);
				if (source == null) {
					error.setContentText("File has not been loaded :(");
					error.show();
				} else {
					Main.ser.fillFile(source);
					succes.setContentText("File has been loaded succssfully :)");
					succes.show();
				}
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		});

	}

	public StackPane getTab() {
		StackPane sp = new StackPane();
		sp.getChildren().add(root);
		return sp;
	}

}
