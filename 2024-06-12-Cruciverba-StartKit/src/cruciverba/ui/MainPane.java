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
import java.util.Map.Entry;
import java.util.StringJoiner;

import cruciverba.controller.Controller;
import cruciverba.persistence.BadFileFormatException;


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
		// ***** DA FARE *****
		// Legge il Cruciverba e ne mostra il contenuto nell’apposita area di testo areaSchemaBase.
		// Eventuali IOException o BadFileFormatException riportate dal Controller devono essere intercettate e gestite 
		// mostrando all’utente, tramite il metodo alert, un apposito messaggio d’errore, ritornando poi senza fare altro
	}
	
	void numeraSchema(ActionEvent ev) {
		// ***** DA FARE *****
		// Numera lo schema e mostra il risultato nell’areaSchemaNumerato: eventuali UnsupportedOperationException riportate 
		// dal Controller devono essere intercettate e gestite mostrando all’utente, tramite il metodo alert, un apposito 
		// messaggio d’errore, ritornando poi senza fare altro
	}
	
	void orizzontali(ActionEvent ev) {
		// ***** DA FARE *****
		// Recupera l’elenco delle parole e le mostra in areaHorVert, sfruttando il metodo formattaElenco fornito
		// Eventuali UnsupportedOperationException riportate dal Controller devono essere intercettate e gestite mostrando 
		// all’utente, tramite il metodo alert, un apposito messaggio d’errore, ritornando poi senza fare altro
	}

	void verticali(ActionEvent ev) {
		// ***** DA FARE *****
		// Recupera l’elenco delle parole e le mostra in areaHorVert, sfruttando il metodo formattaElenco fornito
		// Eventuali UnsupportedOperationException riportate dal Controller devono essere intercettate e gestite mostrando 
		// all’utente, tramite il metodo alert, un apposito messaggio d’errore, ritornando poi senza fare altro
	}
	
	private String formattaElenco(String titolo, Map<Integer,String> defs) {
		StringJoiner joiner = new StringJoiner(System.lineSeparator(), titolo, "");
		for(Entry<Integer,String> entry : defs.entrySet()) {
			joiner.add(entry.getKey() + " - " + entry.getValue());
		}
		return joiner.toString();
	}
}
