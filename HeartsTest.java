package Pkg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HeartsTest {

	@Test
	void test() {
		Player comp = new Player("comp");
//		comp.hand.add(new Card(0,10)); //queen
		comp.hand.add(new Card(1,9));  
		comp.hand.add(new Card(1,1));
		comp.hand.add(new Card(1,10));
//		comp.hand.add(new Card(2,12)); //heart
		comp.hand.add(new Card(3,2)); 
		comp.hand.add(new Card(1,4));
		comp.hand.add(new Card(0,5));
//		comp.hand.add(new Card(2,10));  //heart
		comp.hand.add(new Card(3,10));  
		System.out.println(Hearts.compNoSuit(comp));
		assertTrue(Hearts.compNoSuit(comp) == 6);

	}

}
