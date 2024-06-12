package cruciverba.model;

import java.util.*;
import java.util.stream.*;


@SuppressWarnings("unused")
public class Numeratore {
	
	private Cruciverba schema;
	private int numRighe, numColonne;
	private OptionalInt[][] griglia;
	
	public Numeratore(Cruciverba schema) {
		this.schema=schema;
		this.numRighe = schema.getNumRighe();
		this.numColonne = schema.getNumColonne();
		this.griglia = new OptionalInt[numRighe][numColonne];
		numeraCelle();
	}
	
//numerrare le celle vedi algoritmo
	private void numeraCelle() {
		int counter = 1;
		for (int r = 0; r < numRighe; r++) {
			for (int c = 0; c < numColonne; c++) {
				boolean isStartOfWord = false;
				char current = schema.getCella(r, c);
				if (current != Cruciverba.NERA) {
					boolean right = (c < numColonne - 1) && schema.getCella(r, c + 1) != Cruciverba.NERA;
					boolean down = (r < numRighe - 1) && schema.getCella(r + 1, c) != Cruciverba.NERA;
					boolean left = (c > 0) && schema.getCella(r, c - 1) == Cruciverba.NERA;
					boolean up = (r > 0) && schema.getCella(r - 1, c) == Cruciverba.NERA;
					if ((c == 0 && right) || (left && right)) {
						isStartOfWord = true; 
					}
					if ((r == 0 && down) || (up && down)) {
						isStartOfWord = true; 
					}
				}
				if (isStartOfWord) griglia[r][c] = OptionalInt.of(counter++);
					else griglia[r][c] = OptionalInt.empty();
			}
		}
	}

	public Cruciverba getSchema() {
		return schema;
	}
	
	public int getNumRighe() {
		return numRighe;
	}

	public int getNumColonne() {
		return numColonne;
	}
	
	private void validaIndiceRiga(int indiceRiga) {
		if (indiceRiga<0 || indiceRiga>=numRighe) throw new IllegalArgumentException("Riga fuori range: " + indiceRiga);
	}
	
	private void validaIndiceColonna(int indiceColonna) {
		if (indiceColonna<0 || indiceColonna>=numColonne) throw new IllegalArgumentException("Colonna fuori range: " + indiceColonna);
	}
	
	public OptionalInt getNumeroCella(int indiceRiga, int indiceColonna) {
		validaIndiceRiga(indiceRiga); validaIndiceColonna(indiceColonna);
		return griglia[indiceRiga][indiceColonna];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int r=0; r<numRighe; r++) { 
			sb.append("|");
			for(int c=0; c<numColonne; c++) {
				var num = griglia[r][c];
				sb.append(num.isEmpty() ? (schema.getCella(r,c)==Cruciverba.NERA ? String.format("%2s", Cruciverba.NERA) : String.format("%2s", Cruciverba.BIANCA)) : 
							String.format("%02d", num.getAsInt()) );
				sb.append("|"); // se non si vuole il separatore verticale: sb.append(Cruciverba.BLANK);
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	
	public OptionalInt[] getRiga(int indiceRiga) {
		validaIndiceRiga(indiceRiga);
		return griglia[indiceRiga];
	}

	public OptionalInt[] getColonna(int indiceColonna) {
		validaIndiceColonna(indiceColonna);
		var colonna = new OptionalInt[numRighe];
		for (int i=0; i<numRighe; i++)
			colonna[i] = griglia[i][indiceColonna];
		return colonna;
	}
	
	public SortedMap<Integer,String> orizzontali(){
		SortedMap<Integer,String> result = new TreeMap<>();
		for (int i = 0; i < numRighe; i++) {
			char[] rigaCaratteri = schema.getRiga(i);
			OptionalInt[] rigaNumeri = getRiga(i);
			int[] numeri = numeriUtili(rigaNumeri, rigaCaratteri);
			String[] parole = schema.paroleInRiga(i);
			Map<Integer,String> map = new HashMap<>();
			for (String parola : parole) {
				if (parola.length() > 1) {
					for (int num : numeri) {
						if (!map.containsKey(num)) {
							map.put(num, parola);
							break;
						}
					}
				}
			}
		result.putAll(map);
		}
		return result;
	}
	
	public SortedMap<Integer,String> verticali(){
		SortedMap<Integer,String> result = new TreeMap<>();
		for (int j = 0; j < numColonne; j++) {
			char[] colCaratteri = schema.getColonna(j);
			OptionalInt[] colNumeri = getColonna(j); 
			int[] numeri = numeriUtili(colNumeri, colCaratteri);
			String[] parole = schema.paroleInColonna(j);
			Map<Integer,String> map = new HashMap<>();
			for (String parola : parole) {
				if (parola.length() > 1) {
					for (int num : numeri) {
						if (!map.containsKey(num)) {
							map.put(num, parola);
							break;
							}
						}
					}
				}
			result.putAll(map);
		}
		return result;
	}

	
	
	
	private int[] numeriUtili(OptionalInt[] rigaNumeri, char[] rigaCaratteri) {
		var numeri = new ArrayList<Integer>();
		if(rigaNumeri[0].isPresent() && rigaCaratteri[1]!=Cruciverba.NERA ) numeri.add(rigaNumeri[0].getAsInt());
		for(int i=1; i<rigaCaratteri.length-1; i++) {
			if(rigaNumeri[i].isPresent() && rigaCaratteri[i-1]==Cruciverba.NERA && rigaCaratteri[i+1]!=Cruciverba.NERA) numeri.add(rigaNumeri[i].getAsInt());
		}
		return numeri.stream().mapToInt(Integer::intValue).toArray();
	}
	
}


