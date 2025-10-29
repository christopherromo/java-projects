
/**
 * TreeOfLife.java
 * 
 * This file defines the TreeOfLife class, which reads in parrot objects from a file,
 * adds them to a binary tree, and traverses the tree to print a song and names.
 * 
 * @author Christopher Romo
 * @since 11/29/2022
 * @version 1.0
 */

import java.util.*;
import java.io.*;

/**
 * The TreeOfLife class contains a main method that reads in parrot objects from
 * a file,adds them to a binary tree, and traverses the tree to print a song and
 * names.
 */
public class TreeOfLife {
	/**
	 * Main method - reads in parrot objects from a file, adds them to a binary
	 * tree, and traverses the tree to print a song and names.
	 * 
	 * @param args command line arguments
	 * @throws IOException if file not found
	 */
	public static void main(String[] args) throws IOException {
		// declare variables
		int ID;
		String name;
		String songPhrase;
		BinaryTree theTreeOfLife = new BinaryTree();

		// open file for scanning
		File treeInfo = new File("input/data structures and algorithms input/treeoflife_input.txt");
		Scanner treeScanner = new Scanner(treeInfo);

		// fill tree with parrots
		while (treeScanner.hasNext()) {
			ID = treeScanner.nextInt();
			name = treeScanner.next();
			songPhrase = treeScanner.nextLine();

			Parrot aParrot = new Parrot(ID, name, songPhrase);
			theTreeOfLife.insert(aParrot);

		}
		treeScanner.close();

		// print song & visit leaves
		theTreeOfLife.levelOrder();
		theTreeOfLife.visitLeaves();

	} // main
} // TreeOfLife

/**
 * The Parrot class creates a parrot object.
 */
class Parrot implements Comparable<Parrot> {
	// instance variables
	private int ID;
	private String name;
	private String songPhrase;

	/**
	 * constructor that creates a parrot object
	 * 
	 * @param ID
	 * @param name
	 * @param songPhrase
	 */
	public Parrot(int ID, String name, String songPhrase) {
		this.ID = ID;
		this.name = name;
		this.songPhrase = songPhrase;
	}

	// getters
	public String getName() {
		return name;
	}

	public String getSongPhrase() {
		return songPhrase;
	}

	// behavior
	@Override
	public int compareTo(Parrot otherParrot) {
		if (ID < otherParrot.ID) {
			return -1;
		} else if (ID == otherParrot.ID) {
			return 0;
		} else {
			return 1;

		}
	}
} // Parrot

/**
 * The BinaryTree class defines a binary tree data structure.
 */
class BinaryTree {
	// instance variables
	private TreeNode root;

	/**
	 * constructor that creates an empty binary tree
	 */
	public BinaryTree() {
		root = null;
	}

	// insert
	public boolean insert(Parrot parrotToAdd) {
		if (root == null) // first entry
		{
			root = new TreeNode(parrotToAdd);
		} else // positioning
		{
			TreeNode parent = null;
			TreeNode current = root;

			while (current != null) {
				if (parrotToAdd.compareTo(current.parrot) < 0) // move to left
				{
					parent = current;
					current = current.left;
				} else if (parrotToAdd.compareTo(current.parrot) > 0) // move to right
				{
					parent = current;
					current = current.right;
				} else {
					return false;
				}
			}

			if (parrotToAdd.compareTo(parent.parrot) < 0) // insert tree node to the left
			{
				parent.left = new TreeNode(parrotToAdd);
			} else // insert tree node to the right
			{
				parent.right = new TreeNode(parrotToAdd);
			}
		}
		return true;
	}

	// levelOrder
	public void levelOrder() {
		if (root != null) {
			Queue<TreeNode> tempQueue = new LinkedList<>();
			tempQueue.offer(root);

			while (!tempQueue.isEmpty()) {
				// print phrase by polling, and add next levels nodes to queue
				TreeNode tempNode = tempQueue.poll();
				System.out.print(tempNode.parrot.getSongPhrase());

				if (tempNode.left != null) {
					tempQueue.offer(tempNode.left);
				}
				if (tempNode.right != null) {
					tempQueue.offer(tempNode.right);
				}
			}
			System.out.println();
		}
	}

	// visitLeaves for main
	public void visitLeaves() {
		visitLeaves(root);
	}

	// visitLeaves for class
	private void visitLeaves(TreeNode aNode) {
		if (aNode != null) {
			// if both left and right are null, it is a leaf
			if (aNode.left == null && aNode.right == null) {
				System.out.println(aNode.parrot.getName());
			} else {
				// otherwise, call function again with child nodes
				visitLeaves(aNode.left);
				visitLeaves(aNode.right);
			}
		}
	}

	/**
	 * The TreeNode represents a single node in the binary tree.
	 */
	private static class TreeNode {
		// declare variables
		private Parrot parrot;
		private TreeNode left;
		private TreeNode right;

		// constructor
		public TreeNode(Parrot parrot) {
			this.parrot = parrot;
			left = null;
			right = null;

		}
	}
} // BinaryTree