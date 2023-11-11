/*
* Este código se basa en código desarrollado por:
* https://www.geeksforgeeks.org/implementation-of-blockchain-in-java/
* Modificado por: < Santiago Rodriguez Bernal > < Diego Rodriguez Avila>
*/
// Java implementation to store
// blocks in an ArrayList

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Cadena {

	// ArrayList to store the blocks
	public static ArrayList<Bloque> blockchain = new ArrayList<Bloque>();
	public static int dificultad = 2;

	// Java implementation to check
	// validity of the blockchain

	// Function to check
	// validity of the blockchain
	public static Boolean isChainValid() {
		Bloque currentBlock;
		Bloque previousBlock;

		// Iterating through
		// all the blocks
		for (int i = 1; i < blockchain.size(); i++) {

			// Storing the current block
			// and the previous block
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);

			// Revisar si el hash almacenado en el bloque es correcto
			if (!currentBlock.darHash().equals(currentBlock.calcularHash())) {
				System.out.println("El hash actual es correcto.");
				return false;
			}

			// Revisar si el hash que aparece como previo es igual
			// al hash almacenado en el bloque previo
			if (!previousBlock.darHash().equals(currentBlock.darHashPrevio())) {
				System.out.println("El hash previo no es correcto");
				return false;
			}
		}

		// If all the hashes are equal
		// to the calculated hashes,
		// then the blockchain is valid
		return true;
	}

	public static void printChain() {
		for (int i = 0; i < blockchain.size(); i++) {
			Bloque currentBlock = blockchain.get(i);
			currentBlock.printBlock();
		}
	}

	public static void addBlock(Bloque newBlock) {
		newBlock.minarBloque(dificultad);
		blockchain.add(newBlock);

	}

	public static void mainOne(String[] args) {
		long start = System.currentTimeMillis();

		System.out.println("Creando el bloque Genesis... ");
		Bloque genesis = new Bloque(0, "Bloque Genesis", "0");
		addBlock(genesis);

		System.out.println("Adicionando el primer bloque ");
		Bloque bloque1 = new Bloque(1, "Primer Bloque", genesis.darHash());
		addBlock(bloque1);

		System.out.println("\n Imprimiendo la cadena resultante: ");
		printChain();
		System.out.println("verificando validez de la cadena: " + isChainValid());

		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("Tiempo ejecucion: " + timeElapsed + " ms");
	}

	public static void main(String[] args) {
		try {
			FileWriter fileWriter = new FileWriter("./docs/tiempoMinadoSHA512V2.txt");

			fileWriter.write("Dificultad\tTiempo (ms)\n");

			for (int dificultad = 2; dificultad <= 8; dificultad++) {
				System.out.println("_---------------------------------------------_: Dificultad" + dificultad);
				long start = System.currentTimeMillis();

				System.out.println("Creando el bloque Genesis... ");
				Bloque genesis = new Bloque(0, "Bloque Genesis", "0");
				addBlock(genesis);

				System.out.println("Adicionando el primer bloque ");
				Bloque bloque1 = new Bloque(1, "Primer Bloque", genesis.darHash());
				Cadena.dificultad = dificultad;
				addBlock(bloque1);

				System.out.println("\n Imprimiendo la cadena resultante: ");
				printChain();
				System.out.println("Verificando validez de la cadena: " + isChainValid());

				long finish = System.currentTimeMillis();
				long timeElapsed = finish - start;
				System.out.println("Tiempo ejecución con dificultad " + dificultad + ": " + timeElapsed + " ms");

				fileWriter.write(dificultad + "\t\t" + timeElapsed + "\n");
			}

			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
