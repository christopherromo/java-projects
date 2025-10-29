
/**
 * Stacks.java
 * 
 * This file defines the Stacks class which contains methods to manipulate stacks.
 * 
 * @author Christopher Romo
 * @since 10/07/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;

/**
 * The Stacks class contains methods to manipulate stacks including replacing
 * zeros with tens, sorting stacks, merging stacks, and printing stacks.
 */
public class Stacks {
	/**
	 * replaces all zeros in the incoming stack with tens
	 * 
	 * @param stack stack to modify
	 */
	public static void replaceZerosWithTen(Stack<Integer> stack) {
		// declare variables
		int stackSize = stack.size();
		Stack<Integer> tempStack = new Stack<>();

		// use for loop to replace zeros with ten
		for (int i = 0; i < stackSize; i++) {
			if (stack.peek() == 0) {
				stack.pop();
				stack.push(10);
			}
			tempStack.push(stack.pop());
		}

		// place values back into original stack
		for (int i = 0; i < stackSize; i++) {
			stack.push(tempStack.pop());

		}
	} // replaceZerosWithTen

	/**
	 * sorts an incoming generic stack from largest to smallest
	 * 
	 * @param <E>    generic type that extends Comparable
	 * @param aStack stack to sort
	 */
	public static <E extends Comparable<E>> void sortStack(GenericStack<E> aStack) {
		// declare variables
		E value;
		boolean foundInsertLocation;
		int stackSize = aStack.getSize();
		GenericStack<E> tempStack = new GenericStack<>();

		// run loop to compare values
		while (!aStack.isEmpty()) {
			// save top of stack in variable
			value = aStack.pop();
			foundInsertLocation = false;

			while (!tempStack.isEmpty() && foundInsertLocation == false) {
				// compare to temporary stack in order to sort
				if (value.compareTo(tempStack.peek()) > 0) {
					aStack.push(tempStack.pop());
				} else {
					// exits the while loop, found insertion point
					foundInsertLocation = true;
				}
			} // dig

			// save value on sorted stack
			tempStack.push(value);

		} // place

		// place values back into original stack
		for (int i = 0; i < stackSize; i++) {
			aStack.push(tempStack.pop());

		}
	} // sortStack

	/**
	 * merges two sorted generic stacks into one sorted generic stack
	 * 
	 * @param <E>      generic type that extends Comparable
	 * @param stackOne stack to merge
	 * @param stackTwo stack to merge
	 * @return merged stack
	 */
	public static <E extends Comparable<E>> GenericStack<E> mergeStacks(GenericStack<E> stackOne,
			GenericStack<E> stackTwo) {
		// declare mergedStack
		GenericStack<E> mergedStack = new GenericStack<>();

		// run loop to compare values on both stacks while not both aren't empty
		while (!stackOne.isEmpty() && !stackTwo.isEmpty()) {
			if (stackOne.peek().compareTo(stackTwo.peek()) > 0) {
				mergedStack.push(stackOne.pop());
			} else if (stackOne.peek().compareTo(stackTwo.peek()) <= 0) {
				mergedStack.push(stackTwo.pop());
			}
		}

		// after the while is done, only one of these will be executed
		// clear the remaining stack of values by pushing onto merge stacks
		if (!stackOne.isEmpty()) {
			while (!stackOne.isEmpty()) {
				mergedStack.push(stackOne.pop());
			}
		}
		if (!stackTwo.isEmpty()) {
			while (!stackTwo.isEmpty()) {
				mergedStack.push(stackTwo.pop());
			}
		}

		// return the merged stack
		return mergedStack;

	} // mergeStacks

	/**
	 * prints a stack of the type Integer
	 * 
	 * @param stack stack to print
	 */
	public static void printStack(Stack<Integer> stack) {
		// declare variables
		int stackSize = stack.size();
		Stack<Integer> tempStack = new Stack<>();

		// print stack & fill tempStack
		for (int i = 0; i < stackSize; i++) {
			tempStack.push(stack.peek());
			System.out.println(stack.pop());
		}

		// place values back into original stack
		for (int i = 0; i < stackSize; i++) {
			stack.push(tempStack.pop());
		}

		System.out.println();

	} // printStack (Integer)

	/**
	 * prints a generic stack
	 * 
	 * @param <E>   generic type that extends Comparable
	 * @param stack stack to print
	 */
	public static <E> void printStack(GenericStack<E> stack) {
		// declare variables
		int stackSize = stack.getSize();
		GenericStack<E> tempStack = new GenericStack<>();

		// print stack & fill tempStack
		for (int i = 0; i < stackSize; i++) {
			tempStack.push(stack.peek());
			System.out.println(stack.pop());
		}

		// place values back into original stack
		for (int i = 0; i < stackSize; i++) {
			stack.push(tempStack.pop());
		}

		System.out.println();

	} // printStack (Generic)

	/**
	 * Main method - fill stacks, call methods, and print results.
	 * 
	 * @param args - command line arguments
	 * @throws IOException if file not found
	 */
	public static void main(String[] args) throws IOException {
		// part one

		// declare variables
		int[] numbers = { 0, 0, 4, 3, 0, 0, 2, 1, 0, 0 };
		Stack<Integer> aStack = new Stack<>();

		// create a stack using the array
		for (int i = 0; i < numbers.length; i++) {
			aStack.push(numbers[i]);
		}

		// call replaceZerosWithTen to modify the stack & print
		replaceZerosWithTen(aStack);
		System.out.println("Stack Values After 0's Replaced with 10\n"
				+ "----------------------------------------------");
		printStack(aStack);

		// part two

		// declare variables
		int value;
		String text;
		GenericStack<Integer> intStackOne = new GenericStack<>();
		GenericStack<Integer> intStackTwo = new GenericStack<>();
		GenericStack<String> stringStackOne = new GenericStack<>();
		GenericStack<String> stringStackTwo = new GenericStack<>();
		GenericStack<Integer> mergedIntStack;
		GenericStack<String> mergedStringStack;

		// open files for reading
		File integersOne = new File("input/data structures and algorithms input/stacks_int1_input.txt");
		File integersTwo = new File("input/data structures and algorithms input/stacks_int2_input.txt");
		File stringsOne = new File("input/data structures and algorithms input/stacks_str1_input.txt");
		File stringsTwo = new File("input/data structures and algorithms input/stacks_str2_input.txt");

		Scanner readIntOne = new Scanner(integersOne);
		Scanner readIntTwo = new Scanner(integersTwo);
		Scanner readStringOne = new Scanner(stringsOne);
		Scanner readStringTwo = new Scanner(stringsTwo);

		// fill stacks from files
		while (readIntOne.hasNext()) {
			value = readIntOne.nextInt();
			intStackOne.push(value);
		}
		while (readIntTwo.hasNext()) {
			value = readIntTwo.nextInt();
			intStackTwo.push(value);
		}
		while (readStringOne.hasNext()) {
			text = readStringOne.next();
			stringStackOne.push(text);
		}
		while (readStringTwo.hasNext()) {
			text = readStringTwo.next();
			stringStackTwo.push(text);
		}
		readIntOne.close();
		readIntTwo.close();
		readStringOne.close();
		readStringTwo.close();

		// print integer stacks
		System.out.println("Values read from stacks_int1_input.txt pushed onto integer1 stack\n"
				+ "----------------------------------------------");
		printStack(intStackOne);
		System.out.println("Values read from stacks_int2_input.txt pushed onto integer2 stack\n"
				+ "----------------------------------------------");
		printStack(intStackTwo);

		// print sorted integer stacks
		sortStack(intStackOne);
		sortStack(intStackTwo);
		System.out.println("Integer1 Stack: sorted largest to smallest\n"
				+ "----------------------------------------------");
		printStack(intStackOne);
		System.out.println("Integer2 Stack: sorted largest to smallest\n"
				+ "----------------------------------------------");
		printStack(intStackTwo);

		// print merged integer stacks
		mergedIntStack = mergeStacks(intStackOne, intStackTwo);
		System.out.println("Merged Stack: sorted smallest to largest\n"
				+ "----------------------------------------------");
		printStack(mergedIntStack);

		// print strings stacks
		System.out.println("Values read from stacks_str1_input.txt pushed onto string1 stack\n"
				+ "----------------------------------------------");
		printStack(stringStackOne);
		System.out.println("Values read from stacks_str2_input.txt pushed onto string2 stack\n"
				+ "----------------------------------------------");
		printStack(stringStackTwo);

		// print sorted string stacks
		sortStack(stringStackOne);
		sortStack(stringStackTwo);
		System.out.println("String1 Stack: sorted in reverse alphabetical\n"
				+ "----------------------------------------------");
		printStack(stringStackOne);
		System.out.println("String2 Stack: sorted in reverse alphabetical\n"
				+ "----------------------------------------------");
		printStack(stringStackTwo);

		// print merged string stacks
		mergedStringStack = mergeStacks(stringStackOne, stringStackTwo);
		System.out.println("Merged Stack: alphabetical\n"
				+ "----------------------------------------------");
		printStack(mergedStringStack);

	} // main
} // Stacks

/**
 * The GenericStack class defines a generic stack data structure.
 */
class GenericStack<E> {
	// instance variables
	private ArrayList<E> list;

	/**
	 * constructor that creates an empty stack
	 */
	public GenericStack() {
		list = new ArrayList<>();
	}

	// behavior
	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int getSize() {
		return list.size();
	}

	public void push(E value) {
		list.add(value);
	}

	public E pop() {
		E value = list.remove(getSize() - 1);
		return value;
	}

	public E peek() {
		E value = list.get(getSize() - 1);
		return value;
		
	}
} // GenericStack