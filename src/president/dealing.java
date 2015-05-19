package president;

import java.util.ArrayList;
import java.util.Arrays;

public class dealing {	
	//Represents all cards in a standard deck
	public static String [] deck = { 
		"3d", "3c", "3h", "3s", 
		"4d", "4c", "4h", "4s", 
		"5d", "5c", "5h", "5s",
		"6d", "6c", "6h", "6s", 
		"7d", "7c", "7h", "7s",
		"8d", "8c", "8h", "8s",
		"9d", "9c", "9h", "9s",
		"10d", "10c", "10h", "10s",
		"Jd", "Jc", "Jh", "Js", 
		"Qd", "Qc", "Qh", "Qs",
		"Kd", "Kc", "Kh", "Ks",
		"Ad", "Ac", "Ah", "As", 
		"2d", "2c", "2h", "2s"};

	public static String[] hands = {"", "", "", ""};

	public static void dealCards(){
		//Converts deck[] to arraylist
		ArrayList<String> cards = new ArrayList<String>();
		int index;
		for (int q = 0; q < 52; q++){
			cards.add(deck[q]);
		}    	

		//Randomly selects card, deals to player and removes it from deck
		for (int i = 0; i < 4; i++){
			for (int z = 0; z < 13; z++){
				index = (int) (Math.random() * cards.size() - 2) + 1;
				hands[i] += cards.get(index) + ",";
				cards.remove(index);
			}
		}

		hands[0] = sortHand(hands[0]);
		hands[1] = sortHand(hands[1]);
		hands[2] = sortHand(hands[2]);
		hands[3] = sortHand(hands[3]);

		//***Debugging purposes***
		//System.out.println("Hand 0: " + hands[0]);
		//System.out.println("Hand 1: " + hands[1]);
		//System.out.println("Hand 2: " + hands[2]);
		//System.out.println("Hand 3: " + hands[3]);
	}

	public static String sortHand(String hand){
		//Sorts hands in ascending order
		String [] temp = new String[13];
		temp = hand.split(",");
		for (int g = 0; g < 13; g++){
			temp[g] = temp[g].replace("9", "90").replace("2", "96").replace("Q", "93").replace("J", "92").replace("K", "94").replace
					("A", "95").replace("10", "91").replace("d", "a");
		}
		Arrays.sort(temp);
		for (int p = 0; p < 13; p++){
			temp[p] = temp[p].replace("90", "9").replace("96", "2").replace("93", "Q").replace("92", "J").replace("94", "K").replace
					("95", "A").replace("91", "10").replace("a", "d");
		}
		String theout = "";
		for (int h = 0; h < 13; h++){
			theout += temp[h] + ","; 
		}
		return theout;
	}

}
