package president;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AI1 {
	public static int intPlayed;
	public static boolean compCanPlay;
	public static boolean compPlayed;
	public static boolean compBurned;
	public static int temp =0;
	public static int temp2 =0;

	public static Random rnd = new Random();

	public static void pickACard(ArrayList<Integer> compIntHand, ArrayList<String> compCardHand, String[] playedCards, int pos)	{
		clearComplbl();
		compPlayed = false;
		compCanPlay = true;
		compBurned = false;

		//Checks for if Comp is starting, or playing on other cards
		if(playedCards.length !=0){
			intPlayed = playedCards.length -1;		

			//Loops through comp hand
			for (int i=0; i < compIntHand.size() - intPlayed; i++){
				compCanPlay = true;

				//Determines whether he has a pair, if the cards played are pairs etc.
				for (int n=0; n<intPlayed; n++){
					if(compCardHand.get(i).toString().charAt(0) != compCardHand.get(i+n+1).toString().charAt(0)){
						compCanPlay = false;	
					}
				}

				//80% chance that comp will not break a set of cards to play
				if (compCanPlay == true && compCardHand.size() > i+intPlayed+1){
					if (compCardHand.get(i + intPlayed +1).toString().charAt(0) == compCardHand.get(i).toString().charAt(0)){
						if (compCardHand.get(i).toString().charAt(0) == '2') temp2 = 60;
						else temp2 = 20;
						temp = rnd.nextInt(100) + 1;						
						if (temp > temp2){
							compCanPlay = false;
							temp = 0;

							for (int n= 3; n>0; n--){
								if (n+i < compCardHand.size()){
									if(compCardHand.get(i+n).toString().charAt(0) == compCardHand.get(i).toString().charAt(0)){
										temp = n;
										break;					
									}
								}		
							}
							i += temp;
						}
					}
				}

				//Checks for if the cards are higher than the ones on the board
				if (compCanPlay == true){
					if(compCardHand.get(i).toString().replace("2", "30").replace("J", "11").replace("Q", "12").replace("K", "13").replace("A", "14").length() == 3){
						temp = Integer.parseInt(compCardHand.get(i).toString().replace("2", "30").replace("J", "11").replace("Q", "12").replace("K", "13").replace("A", "14").substring(0,2));
					}
					else temp = Integer.parseInt(compCardHand.get(i).toString().replace("2", "30").replace("J", "11").replace("Q", "12").replace("K", "13").replace("A", "14").substring(0,1));

					if(playedCards[0].replace("2", "30").replace("Q", "12").replace("J", "11").replace("K", "13").replace("A", "14").length() == 3){
						temp2 = Integer.parseInt(playedCards[0].replace("2", "30").replace("Q", "12").replace("J", "11").replace("K", "13").replace("A", "14").substring(0,2));
					}
					else temp2 = Integer.parseInt(playedCards[0].replace("2", "30").replace("Q", "12").replace("J", "11").replace("K", "13").replace("A", "14").substring(0,1));

					if (temp >= temp2)	{
						if (temp == temp2 || temp == 30) compBurned = true;
						clearDisplaylbl();

						//Sets the respective images
						for(int z=0; z<intPlayed +1; z++){											
							president_GUI.cardDisplaylbl[z].setIcon(president_GUI.cardPicLarge[Integer.parseInt(compIntHand.get(i + z).toString())]);

							if (pos ==1) president_GUI.cardComp1lbl[z].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i + z).toString())]);
							else if (pos ==2) president_GUI.cardComp2lbl[z].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i + z).toString())]);
							else if (pos ==3) president_GUI.cardComp3lbl[z].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i + z).toString())]);
						}

						//Removes the cards from the Comp's parallel arrays / Sets new played cards
						for(int z=0; z<intPlayed +1; z++){	
							playedCards[z] = compCardHand.get(i).toString();
							compIntHand.remove(i);
							compCardHand.remove(i);						
						}

						//Comp did not pass
						compPlayed = true;
						president_GUI.lastPlayedPos = pos;
						president_GUI.passCount = 0;
						break;
					}
				}			

			}
		}

		//Comp's play on first turn (plays all 3's)
		else if(president_GUI.firstTurn == true){
			clearDisplaylbl();

			for (int i=0; i<5; i++){
				if(compCardHand.get(i).toString().charAt(0) != '3'){
					temp = i;
					break;					
				}
				else{
					president_GUI.cardDisplaylbl[i].setIcon(president_GUI.cardPicLarge[Integer.parseInt(compIntHand.get(i).toString())]);

					if (pos ==1) president_GUI.cardComp1lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i).toString())]);
					else if (pos ==2) president_GUI.cardComp2lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i).toString())]);
					else if (pos ==3) president_GUI.cardComp3lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i).toString())]);
				}

			}

			president_GUI.cardsPlayed = new String[temp];		

			for(int i=0; i<temp; i++){	
				president_GUI.cardsPlayed[i] = compCardHand.get(0).toString();
				compIntHand.remove(0);
				compCardHand.remove(0);						
			}

			compPlayed = true;
			president_GUI.lastPlayedPos = pos;
			president_GUI.firstTurn = false;
		}

		//Code for when computer starts (NOT FIRST TURN)
		else if(compIntHand.size() != 0){
			clearDisplaylbl();

			//Plays only high to low when 2 players remain, and the other has 1 card left
			if(president_GUI.intWinners != 2){
				playLowHigh(compIntHand, compCardHand, pos);
			}
			else if(president_GUI.intWinners == 2){
				if (president_GUI.handCard1.size() == 1)playHighLow(compIntHand, compCardHand, pos);
				else if (president_GUI.handCard2.size() == 1)playHighLow(compIntHand, compCardHand, pos);
				else if (president_GUI.handCard3.size() == 1)playHighLow(compIntHand, compCardHand, pos);
				else if (president_GUI.playerCardsRemaining == 1)playHighLow(compIntHand, compCardHand, pos);
				else playLowHigh(compIntHand, compCardHand, pos);
			}

			compPlayed = true;
			president_GUI.lastPlayedPos = pos;
			president_GUI.passCount = 0;
		}			

		//If the Comp has passed
		if (compPlayed == false){
			president_GUI.passCount +=1;
			president_GUI.passTurn();
			log.addText(president_GUI.namelbl[pos].getText() + " has passed");
			president_GUI.compMessagelbl[pos-1].setText("Pass");
		}
		else{
			log.addText(president_GUI.namelbl[pos].getText() + " played: " +Arrays.toString(president_GUI.cardsPlayed));
		}		

		//Updates cards remaining
		president_GUI.compCardRemaininglbl[pos-1].setText("Cards Left: "+compCardHand.size());

		//***Debugging purposes***
		//System.out.println("Comp " +pos);
		//System.out.println(compCardHand +"\t"+ compCardHand.size());

		//If comp burned cards
		if (compBurned == true){
			//Delays for longer to visually see
			president_GUI.timerDelay.setInitialDelay(1200);

			for (int i=0; i<4; i++){
				president_GUI.cardUserlbl[i].setIcon(null);
				if (president_GUI.currentPos ==1){
					president_GUI.cardComp2lbl[i].setIcon(null);
					president_GUI.cardComp3lbl[i].setIcon(null);
				}
				else if (president_GUI.currentPos ==2) {
					president_GUI.cardComp1lbl[i].setIcon(null);
					president_GUI.cardComp3lbl[i].setIcon(null);
				}
				else if (president_GUI.currentPos ==3) {
					president_GUI.cardComp1lbl[i].setIcon(null);		
					president_GUI.cardComp2lbl[i].setIcon(null);
				}
			}

			log.addText(president_GUI.namelbl[pos].getText() + " burned the cards");
			president_GUI.compMessagelbl[pos-1].setText("Burn!");
			president_GUI.cardsPlayed = new String[0];

			//If comp won with a burn
			if(compIntHand.size() == 0 && president_GUI.playerWinOrder[pos] == 0){					
				president_GUI.intWinners +=1;
				president_GUI.playerWinOrder[pos] = president_GUI.intWinners;
				log.addText(president_GUI.namelbl[pos].getText() + " finished in RANK " + president_GUI.intWinners +"!");
				president_GUI.compMessagelbl[pos -1].setForeground(Color.MAGENTA);
				president_GUI.compMessagelbl[pos -1].setText("Rank " + president_GUI.intWinners);

				//Gives turn to player if comp 3 won
				president_GUI.checkGameOver();
				if(president_GUI.gameOver == false){
					if (pos == 3){
						president_GUI.currentPos = -1;

						if (president_GUI.playerWinOrder[0] == 0){
							president_GUI.errorlbl.setText("It's your turn!");
							president_GUI.btnPlay.setEnabled(true);			
						}
						else{
							for (int i=0; i<4; i++){
								president_GUI.cardUserlbl[i].setIcon(null);
							}
							president_GUI.passCount +=1;
							president_GUI.passTurn();
							president_GUI.timerDelay.start();
						}	
					}
				}
			}
			else {
				president_GUI.currentPos = pos -1;
			}			

			if (president_GUI.currentPos != -1) president_GUI.timerDelay.start();
		}		
		//Win code for computer here
		else if (compIntHand.size() == 0 && president_GUI.playerWinOrder[pos] == 0){					
			president_GUI.intWinners +=1;
			president_GUI.playerWinOrder[pos] = president_GUI.intWinners;
			log.addText(president_GUI.namelbl[pos].getText() + " finished in RANK " + president_GUI.intWinners +"!");			
			president_GUI.compMessagelbl[pos -1].setForeground(Color.MAGENTA);
			president_GUI.compMessagelbl[pos -1].setText("Rank " + president_GUI.intWinners);
		}	
	}

	//Methods to clear the current images
	public static void clearDisplaylbl(){
		for (int i=0; i<4; i++){
			president_GUI.cardDisplaylbl[i].setIcon(null);			
		}
	}
	public static void clearComplbl(){
		for (int i=0; i<4; i++){
			if (president_GUI.currentPos ==1) president_GUI.cardComp1lbl[i].setIcon(null);
			if (president_GUI.currentPos ==2) president_GUI.cardComp2lbl[i].setIcon(null);
			if (president_GUI.currentPos ==3) president_GUI.cardComp3lbl[i].setIcon(null);			
		}
	}

	//Code to play high to low 
	public static void playHighLow(ArrayList<Integer> compIntHand, ArrayList<String> compCardHand, int pos){
		if (compCardHand.get(compCardHand.size()-1).toString().charAt(0) == '2') compBurned = true;

		for (int i=0; i<5; i++){
			if (compCardHand.size()-1 - i < 0){
				temp = i;
				break;
			}
			else if(compCardHand.get(compCardHand.size()-1 - i).toString().charAt(0) != compCardHand.get(compCardHand.size()-1).toString().charAt(0)){
				temp = i;
				break;					
			}
			else{
				president_GUI.cardDisplaylbl[i].setIcon(president_GUI.cardPicLarge[Integer.parseInt(compIntHand.get(compCardHand.size()-1 -i).toString())]);

				if (pos ==1) president_GUI.cardComp1lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(compCardHand.size()-1 -i).toString())]);
				else if (pos ==2) president_GUI.cardComp2lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(compCardHand.size()-1 -i).toString())]);
				else if (pos ==3) president_GUI.cardComp3lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(compCardHand.size()-1 -i).toString())]);
			}				
		}			
		president_GUI.cardsPlayed = new String[temp];		

		for(int i=temp -1; i>=0; i--){	
			president_GUI.cardsPlayed[i] = compCardHand.get(compCardHand.size()-1).toString();
			compIntHand.remove(compCardHand.size()-1);
			compCardHand.remove(compCardHand.size()-1);						
		}
	}

	//Code to play low to high
	public static void playLowHigh(ArrayList<Integer> compIntHand, ArrayList<String> compCardHand, int pos){
		if (compCardHand.get(0).toString().charAt(0) == '2') compBurned = true;

		for (int i=0; i<5; i++){
			if (compCardHand.size() <= i){
				temp = i;
				break;
			}
			else if(compCardHand.get(i).toString().charAt(0) != compCardHand.get(0).toString().charAt(0)){
				temp = i;
				break;					
			}
			else{
				president_GUI.cardDisplaylbl[i].setIcon(president_GUI.cardPicLarge[Integer.parseInt(compIntHand.get(i).toString())]);

				if (pos ==1) president_GUI.cardComp1lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i).toString())]);
				else if (pos ==2) president_GUI.cardComp2lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i).toString())]);
				else if (pos ==3) president_GUI.cardComp3lbl[i].setIcon(president_GUI.cardPicSmall[Integer.parseInt(compIntHand.get(i).toString())]);
			}				
		}			
		president_GUI.cardsPlayed = new String[temp];		

		for(int i=0; i<temp; i++){	
			president_GUI.cardsPlayed[i] = compCardHand.get(0).toString();
			compIntHand.remove(0);
			compCardHand.remove(0);						
		}
	}
}
