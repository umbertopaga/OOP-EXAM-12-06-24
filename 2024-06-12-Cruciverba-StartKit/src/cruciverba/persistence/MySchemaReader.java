package cruciverba.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import cruciverba.model.Cruciverba;
import cruciverba.model.Orientamento;


public class MySchemaReader implements SchemaReader {
	
	private Reader inputReader;
	
	public MySchemaReader(Reader inputReader) {
		this.inputReader = inputReader;
	}
	
	public Cruciverba leggiSchema() throws BadFileFormatException, IOException {
		// ***** DA FARE *****
		// Dapprima acquisisce tutte le righe, poi istanzia il Cruciverba della giusta dimensione e lo popola, 
		// tramite il metodo setParola di Cruciverba, scrivendo in esso tutte e sole le parole orizzontali 
		// (le verticali risulteranno presenti implicitamente), **ivi comprese quelle di lunghezza 1**, 
		// ottenute elaborando ogni riga tramite il metodo elementiRiga di SchemaReader.
		return null; // FAKE
	}

}
