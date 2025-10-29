
/**
 * MessageDecoder.java
 * 
 * This file defines the MessageDecoder class, which decodes secret messages
 * using a decoding machine with a key grid.
 * 
 * @author Christopher Romo
 * @since 11/03/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;

/**
 * The MessageDecoder class contains a main method that decodes secret messages
 * using a decoding machine with a key grid.
 */
public class MessageDecoder {
	/**
	 * fills a queue with KeyGridElements from a file
	 * 
	 * @param aQueue      the queue to be filled
	 * @param messageFile the scanner for the message file
	 */
	public static void fillQueue(Queue<KeyGridElement> aQueue, Scanner messageFile) {
		// declare variables
		KeyGridElement gridTile;
		int row;
		int column;

		// loop until all grid tiles have been added to queue
		while (messageFile.hasNext()) {
			// obtain coordinates
			row = messageFile.nextInt();
			column = messageFile.nextInt();

			// create new tile and add to queue
			gridTile = new KeyGridElement(row, column);
			aQueue.offer(gridTile);

		}
	} // fillQueue

	/**
	 * Main method - decodes secret messages using a decoding machine with a key
	 * grid.
	 * 
	 * @param args command line arguments
	 * @throws IOException if file not found
	 */
	public static void main(String[] args) throws IOException {
		// declare variables
		Queue<KeyGridElement> secretMessageOne = new LinkedList<>();
		Queue<KeyGridElement> secretMessageTwo = new LinkedList<>();
		ArrayList<Character> characters = new ArrayList<>();

		// open files for reading
		File keyGridFile = new File("input/data structures and algorithms input/messagedecoder_keygrid_input.txt");
		File messageOneFile = new File("input/data structures and algorithms input/messagedecoder_message1_input.txt");
		File messageTwoFile = new File("input/data structures and algorithms input/messagedecoder_message2_input.txt");

		Scanner keyGridScanner = new Scanner(keyGridFile);
		Scanner messageOneScanner = new Scanner(messageOneFile);
		Scanner messageTwoScanner = new Scanner(messageTwoFile);

		// take in the number of rows and columns & initialize decoding machine
		int numRows = keyGridScanner.nextInt();
		int numCols = keyGridScanner.nextInt();
		DecodingMachine keyGrid = new DecodingMachine(numRows, numCols);

		// fill array list with characters and initialize iterator
		while (keyGridScanner.hasNext()) {
			char keyGridChar = keyGridScanner.next().charAt(0);
			characters.add(keyGridChar);
		}

		// create an iterator and send it to fill in 2D array
		Iterator<Character> characterIterator = characters.iterator();
		keyGrid.fillKeyGrid(characterIterator);

		// fill queues with keyGridElements and create iterators
		fillQueue(secretMessageOne, messageOneScanner);
		fillQueue(secretMessageTwo, messageTwoScanner);
		Iterator<KeyGridElement> messageOneIterator = secretMessageOne.iterator();
		Iterator<KeyGridElement> messageTwoIterator = secretMessageTwo.iterator();
		Iterator<Character> decodedMessageOne = keyGrid.decodeMessage(messageOneIterator);
		Iterator<Character> decodedMessageTwo = keyGrid.decodeMessage(messageTwoIterator);

		// print messages through iterators
		System.out.println("Decoding Machine's Key Grid\n"
				+ "---------------------------");
		keyGrid.printKeyGrid();
		System.out.println();
		System.out.println("1st Secret Decoded Message\n"
				+ "--------------------------------------------------------------------");
		while (decodedMessageOne.hasNext()) {
			System.out.print(decodedMessageOne.next());
		}
		System.out.println();
		System.out.println();
		System.out.println("2nd Secret Decoded Message\n"
				+ "--------------------------------------------------------------------");
		while (decodedMessageTwo.hasNext()) {
			System.out.print(decodedMessageTwo.next());
		}

		// close scanners
		keyGridScanner.close();
		messageOneScanner.close();
		messageTwoScanner.close();

	} // main
} // MessageDecoder

/**
 * The KeyGridElement class creates a key grid element object.
 */
class KeyGridElement {
	// instance variables
	private int row;
	private int column;

	/**
	 * constructor that creates a key grid element object
	 * 
	 * @param row
	 * @param column
	 */
	public KeyGridElement(int row, int column) {
		this.row = row;
		this.column = column;
	}

	// getters
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;

	}
} // KeyGridElement

/**
 * The DecodingMachine class creates a decoding machine object.
 */
class DecodingMachine {
	// instance variables
	private char[][] keyGrid;
	private int numRows;
	private int numCols;

	/**
	 * constructor that creates a decoding machine object
	 * 
	 * @param numRows
	 * @param numCols
	 */
	public DecodingMachine(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;

		keyGrid = new char[numRows][numCols];
	}

	// behavior

	/**
	 * fills the 2D array with characters from an iterator
	 * 
	 * @param charIterator iterator of characters
	 */
	public void fillKeyGrid(Iterator<Character> charIterator) {
		while (charIterator.hasNext()) {
			for (int i = 0; i < numRows; i++) {
				for (int k = 0; k < numCols; k++) {
					keyGrid[i][k] = charIterator.next();
				}
			}
		}
	}

	/**
	 * decodes a message using an iterator of KeyGridElements
	 * 
	 * @param msgIterator iterator of KeyGridElements
	 * @return iterator of decoded characters
	 */
	public Iterator<Character> decodeMessage(Iterator<KeyGridElement> msgIterator) {
		// declare variables
		ArrayList<Character> secretMessage = new ArrayList<>();
		KeyGridElement gridTile;

		// get a gridTile, go to it's coordinates and extract a char
		while (msgIterator.hasNext()) {
			gridTile = msgIterator.next();
			int row = gridTile.getRow();
			int column = gridTile.getColumn();
			char decodedChar = keyGrid[row][column];

			secretMessage.add(decodedChar);
		}

		// return secret message
		Iterator<Character> decodedMessage = secretMessage.iterator();
		return decodedMessage;
	}

	/**
	 * prints the key grid
	 */
	public void printKeyGrid() {
		for (int i = 0; i < numRows; i++) {
			for (int k = 0; k < numCols; k++) {
				System.out.print(keyGrid[i][k]);
			}
			System.out.println();

		}
	}
} // DecodingMachine