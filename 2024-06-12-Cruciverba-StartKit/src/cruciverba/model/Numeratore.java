package cruciverba.model;

import java.util.*;
import java.util.stream.*;


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
	
	
	private void numeraCelle() {
		// ***** DA FARE *****
		// Incorpora la logica di numerazione delle celle
		// E' invocato solo dal costruttore: popola la matrice di OptionalInt secondo l’algoritmo descritto nel Dominio del Problema
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
		// ***** DA FARE *****
		// Restituisce una SortedMap<Integer,String> contenente l’elenco delle parole ORIZZONTALI di lunghezza ALMENO PARI A 2
		// ordinate in senso crescente sui rispettivi numeri
		//
		// NB: tenere presente che il metodo paroleInRiga(int i) di Schema estrae *tutte* le parole, anche quelle di lunghezza 1
		// Al contrario, qui sono richieste *solo* quelle di lunghezza almeno pari a 2, in quanto i caratteri singoli non vengono 
		// mai definiti nei cruciverba.
		//
		// SUGGERIMENTO: confrontare le parole di ogni riga, opportunamente filtrate per escludere i caratteri singoli, con i 
		// corrispondenti “numeri utili” calcolati dal metodo omonimo.
		return null; // FAKE
	}
	
	public SortedMap<Integer,String> verticali(){
		// ***** DA FARE *****
		// Restituisce una SortedMap<Integer,String> contenente l’elenco delle parole VERTICALI di lunghezza ALMENO PARI A 2
		// ordinate in senso crescente sui rispettivi numeri
		//
		// NB: tenere presente che il metodo paroleInColonna(int i) di Schema estrae *tutte* le parole, anche quelle di lunghezza 1
		// Al contrario, qui sono richieste *solo* quelle di lunghezza almeno pari a 2, in quanto i caratteri singoli non vengono 
		// mai definiti nei cruciverba.
		//
		// SUGGERIMENTO: confrontare le parole di ogni riga, opportunamente filtrate per escludere i caratteri singoli, con i 
		// corrispondenti “numeri utili” calcolati dal metodo omonimo.
		return null; // FAKE
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
