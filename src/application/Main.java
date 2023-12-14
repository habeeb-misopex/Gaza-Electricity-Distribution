package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.spi.CalendarDataProvider;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.TriangleMesh;

public class Main extends Application {
	public static Service ser = new Service();
	Button btSaveFile;

	@Override
	public void start(Stage primaryStage) throws IOException {
		TabPane root = new TabPane();

		Tab tab1 = new Tab("Managment");
		tab1.setClosable(false);
		tab1.setContent(new ManagementTab().getTab());

		Tab tab2 = new Tab("Statistics");
		tab2.setClosable(false);
		tab2.setContent(new StatisticsTab().getTab());

		Tab tab3 = new Tab("Export Folder");
		tab3.setClosable(false);
		StackPane tab3Sp = new StackPane();
		btSaveFile = new Button("Save Your Edited File!!");
		tab3Sp.getChildren().add(btSaveFile);
		tab3.setContent(tab3Sp);

		root.getTabs().addAll(tab1, tab2, tab3);

		btSaveFile.setOnAction(e -> {
			saveFile();
		});

		Scene scene = new Scene(root, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void saveFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		String line = "";
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		Stage stage = new Stage(); // You might need a stage to show the FileChooser
		File selectedFile = fileChooser.showSaveDialog(stage);
		Alert succes = new Alert(AlertType.INFORMATION);
		if (selectedFile != null) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
				DNode<Year> ptrYear = ser.getYearsList().getHead();
				while (ptrYear != null) {
					Node<Month> ptrMonth = ptrYear.getData().getMonthsList().getHead();
					while (ptrMonth != null) {
						Node<Day> ptrDay = ptrMonth.getData().getDaysList().getHead();
						while (ptrDay != null) {
							line = ptrDay.getData().getRecord().toString() + "\n";
							writer.write(line);
							ptrDay = ptrDay.getNext();
						}
						ptrMonth = ptrMonth.getNext();
					}
					ptrYear = ptrYear.getNext();
				}

				succes.setContentText("File Has Selected Successfully :)");
				succes.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
