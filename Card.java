package Pkg;

import java.util.*;

public class Card {
	// Attriubutes
	public String suit;
	public String worth;
	public int value;

	public static String[] suitArray = { "Spades", "puppyToes", "Hearts", "Diamonds" };
	public static String[] worthArray = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
			"Ace" };

	// Constructor
	Card(int s, int W) {
		this.value = W + 2;
		this.suit = suitArray[s];
		this.worth = worthArray[W];
	}

	// methods
	public void show() { 
		System.out.println(this.worth + " of " + this.suit);
	}

	static Vector<Card> deck = new Vector();
	static Random rand = new Random();

	public static void buildDeck() { //fills the array with 52 different cards
		for (int i = 0; i < 4; i++) {

			for (int y = 0; y < 13; y++) {

				deck.add(new Card(i, y));

			}
		}
//		Shows cards in an ordered manner
//		for(int i = 0; i < deck.size(); i++) {
//			deck.get(i).show();
//		}

	}

	public static void shuffle() { // shuffes the cards
		Vector<Card> split1 = new Vector();
		Vector<Card> split2 = new Vector();

		for (int i = 0; i < deck.size(); i++) {
			if (i < ((deck.size() / 2) + rand.nextInt(deck.size()) - rand.nextInt(deck.size()))) {
				split1.add(deck.get(i));
			} else {
				split2.add(deck.get(i));

			}
		}
		

		deck.clear();
	
	

		for (int i = 0; i < (smallest(split1, split2).size()); i++) {
			deck.add(split1.get(i));
			deck.add(split2.get(i));

		}
		for (int i = smallest(split1, split2).size(); i < biggest(split1, split2).size(); i++) {
			deck.add(biggest(split1, split2).get(i));
		}
		

	}

	public static Vector<Card> smallest(Vector<Card> a, Vector<Card> b) { //used for the shuffler
		if (a.size() > b.size()) {
			return b;
		} else {
			return a;
		}
	}

	public static Vector<Card> biggest(Vector<Card> a, Vector<Card> b) { //used for the shuffler
		if (a.size() < b.size()) {
			return b;
		} else {
			return a;
		}
	}

	
	public static Vector deckbuilder() { //makes the deck array of 52 cards are shuffles them 10 times
		buildDeck();

		for (int i = 0; i < 10; i++) {
			shuffle();
		}

		return deck;
	}

	
	
	public static void main(String[] args) {
		deckbuilder();
		//shows random order of cards
//		System.out.println("");
//		
//		for(int i = 0; i < deck.size(); i++) {
//			deck.get(i).show();
//		}
	}

}
