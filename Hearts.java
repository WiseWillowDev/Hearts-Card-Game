package Pkg;

import java.util.*;

public class Hearts {
	static Vector<Card> Deck = new Vector();
	static Vector<Player> players = new Vector();
	static Vector<Card> play = new Vector();
	static Random rand = new Random();
	static boolean brokenHearts = false;
	static boolean playedQueen = false;
	//computer lead queen of spades at me, need fix
	//computers play as low if they see points, they need to play the card number that is just below the top card
	//computers dont lead high hearts if they lead hearts 
//did	// add a break hearts function to keep the bots from playing hearts until its
//did	// broken the first time
//did	//add a function to make the bots to dish out the hearts or the queen as fast as possible
//did	// add to the fucntion so the bots know that if there is hearts or a queen on
//did	// the table, to play as low as possible
	// add an overall function to loop through until the winner is decided with a
//did	// number of points
	// make the interface a little more prettier with spaces and explaining rules
//did	// add to the user that they can only choose cards that match the suit
//did	//add numbers to the chosen suit and just select from there
//did	// add the bots to not choose the king or ace of spades until the queen has been
//did	// played
	// bots only play low if they are part of the playing suit
	// bot will barely try to undercut the top player
	// add a clear screen function for multible players
// if the bot has the queen, they will still lead spades

	public static void main(String[] args) {
		gameSetup();
		firstRound();
		
		while (players.get(0).hand.size() > 0) {
			round();
		}
	}

	public static void dishCards() { // deals out the cards from the deck
		for (int i = 0; i < Deck.size(); i = i + 4) {
			players.get(0).hand.add(Deck.get(i));
			players.get(1).hand.add(Deck.get(i + 1));
			players.get(2).hand.add(Deck.get(i + 2));
			players.get(3).hand.add(Deck.get(i + 3));
		}
	}

	public static void gameSetup() { // creates the deck and shuffles it, and sorts the players hands

		Deck = Card.deckbuilder();

		players.add(new Player("Human"));
		players.add(new Player("com1"));
		players.add(new Player("com2"));
		players.add(new Player("com3"));

		players.get(0).isComp = false;
		dishCards();
		System.out.println(" ");

		for (int i = 0; i < players.size(); i++) {
			System.out.println("will " + players.get(i).name + " Start the game? " + players.get(i).whoStarts());
			players.get(i).sortHand();
//			players.get(i).showHand();

		}
		System.out.println(" ");

	}

	public static void firstRound() { // find who has the puppy toes and loops through the rounds after that
		int starter = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).whoStarts()) {
				starter = i;
			}
		}
		winnerSort(players.get(starter));
		System.out.println(players.get(0).name + " will start the game");
		int place = 0;
		for (int i = 0; i < players.get(0).hand.size(); i++) {
			if (players.get(0).hand.get(i).suit == Card.suitArray[1]
					&& players.get(0).hand.get(i).worth == Card.worthArray[0]) {
				place = i;
			}
		}
		play.add(players.get(0).hand.get(place));
		players.get(0).hand.remove(place);

		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).isComp) {
				compSuit(suitChecker(), players.get(i));
			} else {
				showPlay();
				userChoice();
			}
		}
		showPlay();
		System.out.println(playWinner().name + " has won");
		System.out.println("");
		pointAdd(playWinner());
		winnerSort(playWinner());
		play.clear();
	}

	public static void round() { // how it loops through the rounds

		for (int i = 0; i < players.size(); i++) {

			if (players.get(i).isComp) {
				if (i == 0) {
					if (brokenHearts == false) {
						// can still play queen - fixed?
						int suiter = rand.nextInt(3);

						if (suiter == 0 && playedQueen == false) {
							int jackOrBelow = 0;
							for (int j = 0; j < players.get(i).hand.size(); j++) {
								if (players.get(i).hand.get(j).value > jackOrBelow) {
									jackOrBelow = players.get(i).hand.get(j).value;
								}
							}
							if (jackOrBelow >= 12) {
								suiter = rand.nextInt(3) + 1;
								// use suiter to change suit to
							}
						}

						if (suiter == 2) {
							suiter = rand.nextInt(2);
							if (suiter == 0) {
								suiter = 1;
							} else {
								suiter = 3;
							}
						}

						compSuit(Card.suitArray[suiter], players.get(i));
					} else {
						
						int suiter = rand.nextInt(4);

						if (suiter == 0 && playedQueen == false) {
							int jackOrBelow = 0;
							for (int j = 0; j < players.get(i).hand.size(); j++) {
								if (players.get(i).hand.get(j).value > jackOrBelow) {
									jackOrBelow = players.get(i).hand.get(j).value;
								}
							}
							if (jackOrBelow >= 12) {
								suiter = rand.nextInt(3) + 1;
								// use suiter to change suit to
							}
						}
						
						
						
					
						compSuit(Card.suitArray[suiter], players.get(i));
					}
				} else {
					if (doCompHaveSuit(play.get(0).suit, players.get(i))) {
						compSuit(suitChecker(), players.get(i));
					} else {
//						int suiter = rand.nextInt(4);
//						compSuit(Card.suitArray[suiter],players.get(i));
						int place = compNoSuit(players.get(i));
						play.add(players.get(i).hand.get(place));
						players.get(i).hand.remove(place);
					}
				}
			} else {
				showPlay();
				userChoice();
			}
		}
		showPlay();
		System.out.println(playWinner().name + " has won");
		System.out.println(" ");
		pointAdd(playWinner());
		System.out.println(" ");
		winnerSort(playWinner());
		play.clear();
	}

	public static void compSuit(String suit, Player comp) { // how comp decides to play *add a function to see a heart
									//comp.showHand();						// and play low after that*
//	    we can avoid playing the spades in this function
		int largest = 0;
		int place = 0;
		boolean pointExist = false;
		for (int i = 0; i < play.size(); i++) {
			if (play.get(i).suit == Card.suitArray[2]) {
				pointExist = true;
			}
			if (play.get(i).suit == Card.suitArray[0] && play.get(i).worth == Card.worthArray[10]) {
				pointExist = true;

			}
		}

		if (pointExist == false) {
			for (int i = 0; i < comp.hand.size(); i++) {
				if (comp.hand.get(i).suit == suit && comp.hand.get(i).value > largest) {
					if (playedQueen) {
						largest = comp.hand.get(i).value;
						place = i;
					} else if (suit == Card.suitArray[0]) {
						// do stuff to avoid (spades) queen and king and ace

						if (comp.hand.get(i).value < 12) {
							largest = comp.hand.get(i).value;
							place = i;

						} else if (largest == 0) {
							largest = comp.hand.get(i).value;
							place = i;
						}

					} else {
						largest = comp.hand.get(i).value;
						place = i;
					}
				}
			}
		} else {
			int smallest = 14;
			for (int i = 0; i < comp.hand.size(); i++) {
				if (comp.hand.get(i).suit == suit && comp.hand.get(i).value < smallest) {
					place = i;
					smallest = comp.hand.get(i).value;
				}
			}
		}

		play.add(comp.hand.get(place));
		comp.hand.remove(place);

	}

	public static int compNoSuit(Player comp) { // the comp aggressivly deals out hearts returns index of the hand
		int damage = 0;
		int place = 100;
		for (int i = 0; i < comp.hand.size(); i++) {
			if (comp.hand.get(i).suit == Card.suitArray[0] && comp.hand.get(i).worth == Card.worthArray[10]) {
				return i;
			} else if (comp.hand.get(i).suit == Card.suitArray[2] && damage < comp.hand.get(i).value) {
				damage = comp.hand.get(i).value;
				place = i;
			}
		}

		if (place == 100) {

			for (int i = 0; i < comp.hand.size(); i++) {
				if (comp.hand.get(i).value > damage) {
					damage = comp.hand.get(i).value;
					place = i;
				}
			}
		}

		return place;
	}

	public static void userChoice() { // the user makes a decision
		System.out.println("Here are your cards");
		System.out.println("");
		boolean hasSuit = false;
		Vector<Card> forRound = new Vector();

		for (int j = 0; j < players.size(); j++) {
			if (players.get(j).isComp == false) {
				for (int i = 0; i < players.get(j).hand.size(); i++) {
					System.out.print(i + 1 + ") ");
					players.get(j).hand.get(i).show();
					if (play.size() > 0) {
						if (players.get(j).hand.get(i).suit == play.get(0).suit) {
							hasSuit = true;
						}
					}
				}
				if (play.size() > 0) {
					System.out.println("You can only choose the card that fits the suit of " + play.get(0).suit);
					for (int i = 0; i < players.get(j).hand.size(); i++) {
						if (players.get(j).hand.get(i).suit == play.get(0).suit) {
							// players.get(j).hand.get(i).show();
							forRound.add(players.get(j).hand.get(i));
						}
					}
				}

				System.out.println("Choose a card by giving a number to play that card");
				Scanner input = new Scanner(System.in);

				if (hasSuit == false) {
					System.out.println("Choose Any Card you want");

					int choose = input.nextInt();
					choose--;
					play.add(players.get(j).hand.get(choose));
					System.out.print("You played ");
					players.get(j).hand.get(choose).show();
					System.out.println("");
					players.get(j).hand.remove(choose);
				} else {
					for (int i = 0; i < forRound.size(); i++) {
						System.out.print(i + 1 + ") ");

						forRound.get(i).show();
					}
					int choose = input.nextInt();
					choose--;
					play.add(forRound.get(choose));
					System.out.print("You played ");
					forRound.get(choose).show();
					System.out.println("");

					for (int i = 0; i < players.get(j).hand.size(); i++) {
						if (forRound.get(choose) == players.get(j).hand.get(i)) {
							players.get(j).hand.remove(i);
						}
					}

				}

			}
		}
	}

	public static void showPlay() { // shows whats in the play pile

		for (int i = 0; i < play.size(); i++) {
			System.out.println(players.get(i).name + " played " + play.get(i).worth + " of " + play.get(i).suit);

		}
	}

	public static String suitChecker() { // returns the suit of the round
		return play.get(0).suit;
	}

	public static void winnerSort(Player winner) { // Sorts the winner to the 0 position of the array and they will play
													// first
		while (winner != players.get(0)) {
			Player holder = players.get(0);
			players.remove(0);
			players.add(holder);
		}
	}

	public static boolean doCompHaveSuit(String suit, Player comp) { // checks the computers hand to see if he has part
																		// of the suit
		for (int i = 0; i < comp.hand.size(); i++) {
			if (comp.hand.get(i).suit == suit) {
				return true;
			}
		}
		return false;
	}

	public static Player playWinner() { // determines the winner of the round, matches the suit and highest card of the
										// suit
		int largest = 0;
		int place = 0;
		for (int i = 0; i < play.size(); i++) {
			if (suitChecker() == play.get(i).suit && largest < play.get(i).value) {
				largest = play.get(i).value;
				place = i;
			}
		}

		return players.get(place);
	}

	public static void pointAdd(Player winner) { // add to the winner his score and prints out scores
		int points = 0;
		for (int i = 0; i < play.size(); i++) {
			if (play.get(i).suit == Card.suitArray[2]) {
				points++;
				brokenHearts = true;
			}
			if (play.get(i).suit == Card.suitArray[0] && play.get(i).worth == Card.worthArray[10]) {
				points = points + 13;
				brokenHearts = true;
				playedQueen = true;
			}
		}
		winner.points = winner.points + points;

		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).name + " has a score of " + players.get(i).points);
		}
		System.out.println("");
	}

}
