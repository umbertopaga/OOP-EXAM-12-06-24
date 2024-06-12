package cruciverba.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import cruciverba.model.Cruciverba;
import cruciverba.model.Orientamento;

public class MySchemaReader implements SchemaReader {

	private BufferedReader inputReader;

	public MySchemaReader(Reader inputReader) {
		this.inputReader = new BufferedReader(inputReader);
	}

	public Cruciverba leggiSchema() throws BadFileFormatException, IOException {
	List<String> lines = new ArrayList<>();
	String line;
	int numRighe = 0;
	int numColonne = -1;
	try {
		while ((line = inputReader.readLine()) != null) {
			lines.add(line);
			numRighe++;
			if (numColonne == -1) numColonne = line.length();
			//verifico che tutte le righe abbiano la stessa lunghezza fra loro;
			else if (line.length() != numColonne) throw new BadFileFormatException("Le righe non hanno lunghezza uguale fra loro\n");
		}
		//verifico che il file non sia vuoto quindi che non contiene righe o colonne;
		if(numRighe == 0 || numColonne == 0) throw new BadFileFormatException("Il file è vuoto\n");
		Cruciverba cruciverba = new Cruciverba(numRighe, numColonne);
		for (int r = 0; r < numRighe; r++) {
			List<ElementoRiga> elementi = SchemaReader.elementiRiga(lines.get(r));
			for (ElementoRiga elemento : elementi)
				if (!elemento.string().isEmpty())
					cruciverba.setParola(r, elemento.pos(), elemento.string(), Orientamento.ORIZZONTALE);
		}
		
		return cruciverba;
		
	}catch(IOException e) {
		throw new IOException("Errore nel input del file");
	}
	}
}