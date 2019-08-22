package Pkg;

import java.util.Vector;
import java.util.Random;

public class Player {
	
	public String name;
	public Vector<Card> hand = new Vector();
	public int points = 0;
	public boolean isComp = true;
	
	Player(String Name){
		this.name = Name;
	}
	
	public void showHand(){
		System.out.println(this.name + " Has this hand");
		for( int i = 0; i < this.hand.size(); i++) {
			this.hand.get(i).show();
		}
		System.out.println("");		
	}
	
	
	
	public void sortHand() {
		Vector<Card> holder = new Vector();
		Card placeHolder;
		for(int j = 0; j < this.hand.size(); j++) {
			for (int i = 0; i < this.hand.size()-1; i++) {
				if(this.hand.get(i).value > this.hand.get(i+1).value) {
					placeHolder = this.hand.get(i);
					this.hand.set(i,this.hand.get(i+1));
					this.hand.set(i+1, placeHolder);
				}
			}
		}
		for (int j = 0; j < Card.suitArray.length; j++) {
			for( int i = 0; i < this.hand.size(); i++) {
				//System.out.println(this.hand.get(i).worth + " of " + this.hand.get(i).suit);
				if(this.hand.get(i).suit == Card.suitArray[j]) {
					holder.add(this.hand.get(i));
				}
			}
		}
		this.hand.clear();
		for(int i = 0; i < holder.size(); i ++ ) {
			this.hand.add(holder.get(i));
		}
	}
	
	public boolean whoStarts() {
		boolean holder = false;
		for( int i = 0; i < this.hand.size(); i++) {
			if(this.hand.get(i).suit == Card.suitArray[1] && this.hand.get(i).worth == Card.worthArray[0]) {
				holder = true;
			}
		}
		return holder;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void doesntworksort() {
		Vector<Card> holder = new Vector();
		System.out.println("The size of the hand is "+ this.hand.size());
		
		for( int j = 0; j < 4; j++) {
			
				for(int y = 0; y < this.hand.size(); y++) {
					int largest = 0; 
					int place = 0;
					for( int i = 0; i < this.hand.size(); i++) {
						if(this.hand.get(i).suit == Card.suitArray[j] && this.hand.get(i).value > largest) {
							largest = this.hand.get(i).value;
							place = i;
						}	
					}
					if(this.hand.get(place).suit == Card.suitArray[j]) {
						holder.add(this.hand.get(place));
						this.hand.remove(place);
					}
				}
				
				for(int i = 0; i < holder.size(); i++) {
					
					System.out.println(holder.get(i).worth + " of " + holder.get(i).suit);

				}
				System.out.println(" ");
		}
		
		for(int i = 0; i < holder.size(); i++) {
			
			System.out.println(holder.get(i).worth + " of " + holder.get(i).suit);

		}
		System.out.println("The size of the array is "+ holder.size());
		System.out.println(" ");
		for(int i = 0; i < this.hand.size(); i++) {
			System.out.println(this.hand.get(i).worth + " of " + this.hand.get(i).suit);

		}
		
		
	}
	

}
