package cruciverba.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.TreeMap;

import cruciverba.controller.Controller;
import cruciverba.model.Cruciverba;
import cruciverba.persistence.BadFileFormatException;


@SuppressWarnings({ "static-access", "unused" })
public class MainPane extends BorderPane {

	private TextArea areaSchemaBase, areaSchemaNumerato, areaHorVert;
	
	private Controller controller;
	private Button buttonCarica, buttonNumera, buttonHor, buttonVert;

	
	public MainPane(Controller controller, Stage stage) {
		this.controller=controller;
		
		VBox rightBox = new VBox();
			areaSchemaBase = new TextArea();
			areaSchemaBase.setPrefWidth(500);
			areaSchemaBase.setPrefHeight(200);
			areaSchemaBase.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
			areaSchemaBase.setEditable(false);
			rightBox.getChildren().addAll(new Label("Cruciverba da numerare:"), areaSchemaBase);
			areaSchemaNumerato = new TextArea();
			areaSchemaNumerato.setPrefWidth(500);
			areaSchemaNumerato.setPrefHeight(200);
			areaSchemaNumerato.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
			areaSchemaNumerato.setEditable(false);
			rightBox.getChildren().addAll(new Label("Cruciverba numerato:"), areaSchemaNumerato);
		this.setRight(rightBox);
		
		VBox leftBox = new VBox();
			buttonCarica = new Button("Carica schema");
			buttonCarica.setPrefWidth(200);
			buttonCarica.setOnAction(ev -> caricaSchema(stage));
			leftBox.getChildren().add(buttonCarica);
			
			buttonNumera = new Button("Numera schema");
			buttonNumera.setPrefWidth(200);
			buttonNumera.setOnAction(this::numeraSchema);
			leftBox.getChildren().add(buttonNumera);
			
			buttonHor = new Button("Orizzontali");
			buttonHor.setPrefWidth(200);
			buttonHor.setOnAction(this::orizzontali);
			leftBox.getChildren().add(buttonHor);
			
			buttonVert = new Button("Verticali");
			buttonVert.setPrefWidth(200);
			buttonVert.setOnAction(this::verticali);
			leftBox.getChildren().add(buttonVert);
			
			areaHorVert = new TextArea();
			areaHorVert.setPrefWidth(200);
			areaHorVert.setPrefHeight(300);
			areaHorVert.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
			areaHorVert.setEditable(false);
			leftBox.getChildren().add(areaHorVert);
		this.setLeft(leftBox);
	}
	
	
	void caricaSchema(Stage stage) {
		try {
			controller.leggiSchema();
			areaSchemaBase.setText(controller.getCruciverba().toString());
		} catch (BadFileFormatException | IOException e) {
			controller.alert("ERRORE DI CARICAMENTO", "Errore durante il caricamento dello schema", "riprovare il caricamento");
		}
		
	}
	
	void numeraSchema(ActionEvent ev) {
		try {
			areaSchemaNumerato.setText(controller.schemaNumerato());
		} catch (UnsupportedOperationException e) {
			Controller.alert("Errore di Numerazione", "Impossibile numerare il schema", e.getMessage());
		}
	}
	void orizzontali(ActionEvent ev) {
		try {
			Map<Integer,String> parole = controller.orizzontali();
			String formattedList = formattaElenco("Parole Orizzontali:\n", parole);
			areaHorVert.setText(formattedList);
		} catch (UnsupportedOperationException e) {
			Controller.alert("Errore", "Impossibile ottenere le parole orizzontali", e.getMessage());
		}
	}

	void verticali(ActionEvent ev) {
		try {
			Map<Integer,String> parole = controller.verticali();
			String formattedList = formattaElenco("Parole Verticali:\n", parole);
			areaHorVert.setText(formattedList);
		} catch (UnsupportedOperationException e) {
			Controller.alert("Errore", "Impossibile ottenere le parole orizzontali", e.getMessage());
		}
	}
	
	private String formattaElenco(String titolo, Map<Integer,String> defs) {
		StringJoiner joiner = new StringJoiner(System.lineSeparator(), titolo, "");
		for(Entry<Integer,String> entry : defs.entrySet()) {
			joiner.add(entry.getKey() + " - " + entry.getValue());
		}
		return joiner.toString();
	}
}

