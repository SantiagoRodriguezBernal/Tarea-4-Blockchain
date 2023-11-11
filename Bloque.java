// Java implementation for creating 
// a block in a Blockchain 

import java.util.Date;

public class Bloque {

	// Every block contains
	// a hash, previous hash and
	// data of the transaction made
	private String hash;
	private String hashprevio;
	private String datos;
	private int nonce;
	private int id;
	private long timeStamp;

	// Constructor for the block
	public Bloque(int id, String datos, String hashprevio) {
		this.id = id;
		this.datos = datos;
		this.hashprevio = hashprevio;
		this.timeStamp = new Date().getTime();
		this.hash = calcularHash();
	}

	// Function to calculate the hash
	public String calcularHash() {
		String calculatedhash = Cripto.sha256(hashprevio + Long.toString(timeStamp) + datos + nonce);

		return calculatedhash;
	}

	public void printBlock() {
		System.out.println("==== Bloque " + id + " ====");
		System.out.println("Hash: " + hash);
		System.out.println("Hash Previo: " + hashprevio);
		System.out.println("Datos: " + datos);
		System.out.println("Nonce: " + nonce);
		System.out.println("Stamp: " + Long.toString(timeStamp));
	}

	public void minarBloque(int dificultad) {
		String tgt = getDificultad(dificultad); // Create a string with difficulty * "0"
		while (!hash.substring(0, dificultad).equals(tgt)) {
			nonce++;
			hash = calcularHash();
		}
		System.out.println("Bloque minado : " + hash);
	}

	// Returns difficulty string target, to compare to hash.
	// e.g. difficulty of 5 will return "00000"
	public String getDificultad(int dificultad) {
		return new String(new char[dificultad]).replace('\0', '0');
	}

	public String darHash() {

		// completar:
		// retornar el valor del hash del bloque

		String valorHash = hash;
		return valorHash;
	}

	public String darHashPrevio() {
		// completar:
		// retornar el valor del hash previo
		String valorHashPrevio = hashprevio;
		return valorHashPrevio;
	}

}
