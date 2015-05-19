package president;

/*
 * name: William F., Kade Robertson
 * class: ICS3U
 * assignment: Final Summative - President Card Game
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class president_GUI extends JFrame implements MouseListener{
	//Parallel arrays for the 4 hands
	public static ArrayList<Integer> handInt0 = new ArrayList<Integer>();
	public static ArrayList<Integer> handInt1 = new ArrayList<Integer>();
	public static ArrayList<Integer> handInt2 = new ArrayList<Integer>();
	public static ArrayList<Integer> handInt3 = new ArrayList<Integer>();
	public static ArrayList<String> handCard0 = new ArrayList<String>();
	public static ArrayList<String> handCard1 = new ArrayList<String>();
	public static ArrayList<String> handCard2 = new ArrayList<String>();
	public static ArrayList<String> handCard3 = new ArrayList<String>();

	//Different sizes of cards
	public static ImageIcon[] cardPic = new ImageIcon[52];
	public static ImageIcon[] cardPicSmall = new ImageIcon[52];
	public static ImageIcon[] cardPicLarge = new ImageIcon[52];

	public static JLayeredPane pane;
	public static Timer timerDelay;
	public static ActionListener delayStuff;

	//Objects used for the GUI
	public static JLabel[] namelbl = new JLabel[4];
	public static JLabel[] compCardRemaininglbl = new JLabel[3];
	public static JLabel[] compMessagelbl = new JLabel[3];

	public static JLabel errorlbl;

	public static JLabel[] cardlbl = new JLabel[13];	
	public static JLabel[] cardUserlbl = new JLabel[4];	
	public static JLabel[] cardComp1lbl = new JLabel[4];
	public static JLabel[] cardComp2lbl = new JLabel[4];
	public static JLabel[] cardComp3lbl = new JLabel[4];	
	public static JLabel[] cardDisplaylbl = new JLabel[4];
	public static JButton btnPlay;
	public static JButton btnPass;

	/*
	public static String[] piclocation = {
		"/pics/3d.png","/pics/3c.png","/pics/3h.png","/pics/3s.png",
		"/pics/4d.png","/pics/4c.png","/pics/4h.png","/pics/4s.png",
		"/pics/5d.png","/pics/5c.png","/pics/5h.png","/pics/5s.png",
		"/pics/6d.png","/pics/6c.png","/pics/6h.png","/pics/6s.png",
		"/pics/7d.png","/pics/7c.png","/pics/7h.png","/pics/7s.png",
		"/pics/8d.png","/pics/8c.png","/pics/8h.png","/pics/8s.png",
		"/pics/9d.png","/pics/9c.png","/pics/9h.png","/pics/9s.png",
		"/pics/10d.png","/pics/10c.png","/pics/10h.png","/pics/10s.png",
		"/pics/Jd.png","/pics/Jc.png","/pics/Jh.png","/pics/Js.png",
		"/pics/Qd.png","/pics/Qc.png","/pics/Qh.png","/pics/Qs.png",
		"/pics/Kd.png","/pics/Kc.png","/pics/Kh.png","/pics/Ks.png",
		"/pics/Ad.png","/pics/Ac.png","/pics/Ah.png","/pics/As.png",
		"/pics/2d.png","/pics/2c.png","/pics/2h.png","/pics/2s.png",
	};
	 */

	public static String[] piclocation = {
		"/president/pics/3d.png","/president/pics/3c.png","/president/pics/3h.png","/president/pics/3s.png",
		"/president/pics/4d.png","/president/pics/4c.png","/president/pics/4h.png","/president/pics/4s.png",
		"/president/pics/5d.png","/president/pics/5c.png","/president/pics/5h.png","/president/pics/5s.png",
		"/president/pics/6d.png","/president/pics/6c.png","/president/pics/6h.png","/president/pics/6s.png",
		"/president/pics/7d.png","/president/pics/7c.png","/president/pics/7h.png","/president/pics/7s.png",
		"/president/pics/8d.png","/president/pics/8c.png","/president/pics/8h.png","/president/pics/8s.png",
		"/president/pics/9d.png","/president/pics/9c.png","/president/pics/9h.png","/president/pics/9s.png",
		"/president/pics/10d.png","/president/pics/10c.png","/president/pics/10h.png","/president/pics/10s.png",
		"/president/pics/Jd.png","/president/pics/Jc.png","/president/pics/Jh.png","/president/pics/Js.png",
		"/president/pics/Qd.png","/president/pics/Qc.png","/president/pics/Qh.png","/president/pics/Qs.png",
		"/president/pics/Kd.png","/president/pics/Kc.png","/president/pics/Kh.png","/president/pics/Ks.png",
		"/president/pics/Ad.png","/president/pics/Ac.png","/president/pics/Ah.png","/president/pics/As.png",
		"/president/pics/2d.png","/president/pics/2c.png","/president/pics/2h.png","/president/pics/2s.png",
	};

	public static int cardSizeWIDTH = 80;
	public static int cardSizeHEIGHT = 110;

	//Variables for gameplay
	public static boolean canPlay;
	public static boolean cardsBurned;
	public static ArrayList selectedCard = new ArrayList();
	public static int playerCardsRemaining = 13;

	public static String[] cardsPlayed = new String[0];
	public static int currentPos = 0;
	public static int lastPlayedPos = 0;
	public static int passCount = 0;

	public static boolean firstTurn = true;
	public static boolean gameOver = false;

	public static int[] playerWinOrder = {0, 0, 0, 0};
	public static int intWinners = 0;

	public static int tempInt = 0;
	public static int tempInt2 = 0;

	public president_GUI(){
		setTitle("President Game");
		setSize(900, 900);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		//Sets window to desired position, regardless of screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenW = screenSize.getWidth();
		double screenH = screenSize.getHeight();		
		int prefW = 900;
		int prefH = 900;		
		int locationleft = (int) (screenW/2) - (prefW / 2) -150;
		int locationtop = (int) (screenH/2) - (prefH / 2) -20;

		Point pt = new Point(locationleft, locationtop);
		setLocation(pt);

		addMouseListener(this);

		//Set closing action
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				welcome.f1 = new welcome();
				dispose();
				welcome.f3.dispose();				
			}
		});

		//Background panel
		pane = new JLayeredPane();
		getContentPane().add(pane);
		pane.setLayout(null);

		//Menu bar with respective commands
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu file = new JMenu("File");
		JMenu view = new JMenu("View");

		//Returns user to main menu, quitting the game
		JMenuItem fileBack = new JMenuItem("Back");
		fileBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				welcome.main(null);
				welcome.f2.dispose();
				welcome.f3.dispose();
			}
		});
		//Restarts game
		JMenuItem fileNew = new JMenuItem("New");
		fileNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				welcome.completeReset();
				welcome.f2.dispose();
				welcome.f3.dispose();
				dealing.dealCards();
				welcome.f2 = new president_GUI();
				welcome.f3 = new log();			
			}
		});
		//Exits the game
		JMenuItem fileClose = new JMenuItem("Exit");
		fileClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);		
			}
		});

		JMenuItem viewLog = new JMenuItem("Log");
		viewLog.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				welcome.f3.setVisible(true);		
			}
		});	
		JMenuItem viewHelp = new JMenuItem("Help");
		viewHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				otherWindows.ins();		
			}
		});
		JMenuItem viewCred = new JMenuItem("Credits");
		viewCred.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				otherWindows.creds();	
			}
		});

		menubar.add(file);
		menubar.add(view);
		file.add(fileNew);
		file.add(fileBack);
		file.add(fileClose);
		view.add(viewLog);
		view.add(viewHelp);
		view.add(viewCred);

		//Sets background of game screen
		JLabel backgroundlbl = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/president/pics/BG.png"))));
		backgroundlbl.setBounds(-3, -28, 900, 900);
		pane.add(backgroundlbl, new Integer(1), 0);

		//Sets up the game (hands, pics, label locations etc.)
		assignCardpic();
		assignHand();

		//Player label at bottom right
		errorlbl = new JLabel();
		errorlbl.setBounds(600, 770, 200, 15);
		errorlbl.setForeground(Color.ORANGE);
		pane.add(errorlbl, new Integer(3), 0);

		//Labels for names
		for (int i=0; i<4; i++){
			namelbl[i] = new JLabel("Comp" +i);
			namelbl[i].setForeground(Color.LIGHT_GRAY);
			pane.add(namelbl[i], new Integer(3), 0);
		}
		namelbl[0].setBounds(350, 768, 100, 15);
		namelbl[1].setBounds(41, 418, 50, 15);
		namelbl[2].setBounds(357, 133, 50, 15);		
		namelbl[3].setBounds(765, 418, 50, 15);		

		//Labels for actions and cards remaining
		for (int i=0; i<3; i++){
			compCardRemaininglbl[i] = new JLabel("Cards Left: 13");
			compCardRemaininglbl[i].setForeground(Color.LIGHT_GRAY);
			pane.add(compCardRemaininglbl[i], new Integer(3), 0);

			compMessagelbl[i] = new JLabel("");
			compMessagelbl[i].setForeground(Color.RED);
			pane.add(compMessagelbl[i], new Integer(3), 0);
		}
		compCardRemaininglbl[0].setBounds(41, 432, 90, 15);	
		compCardRemaininglbl[1].setBounds(357, 148, 90, 15);
		compCardRemaininglbl[2].setBounds(765, 433, 90, 15);

		compMessagelbl[0].setBounds(41, 452, 90, 15);
		compMessagelbl[1].setBounds(470, 148, 90, 15);
		compMessagelbl[2].setBounds(765, 452, 90, 15);

		//Labels for player cards
		for(int i=0; i<13; i++){
			cardlbl[i] = new JLabel(cardPic[(Integer.parseInt(handInt0.get(i).toString()))]);		
			cardlbl[i].setBounds(110 + i*50, 650, cardSizeWIDTH, cardSizeHEIGHT);
			cardlbl[i].addMouseListener(this);
			pane.add(cardlbl[i], new Integer(6),0);
		}			

		//Labels for cards played
		for(int i=0; i<4; i++){
			cardUserlbl[i] = new JLabel();	
			cardUserlbl[i].setBounds(380 + i*30, 570, cardSizeWIDTH -40, cardSizeHEIGHT -60);

			cardComp1lbl[i] = new JLabel();	
			cardComp1lbl[i].setBounds(90, 261 + i*30, cardSizeWIDTH -40, cardSizeHEIGHT -60);			
			cardComp2lbl[i] = new JLabel();	
			cardComp2lbl[i].setBounds(357 + i*30, 74, cardSizeWIDTH -40, cardSizeHEIGHT -60);			
			cardComp3lbl[i] = new JLabel();	
			cardComp3lbl[i].setBounds(764, 261 + i*30, cardSizeWIDTH -40, cardSizeHEIGHT -60);

			cardDisplaylbl[i] = new JLabel();	
			cardDisplaylbl[i].setBounds(300 + i*60, 280, cardSizeWIDTH +40, cardSizeHEIGHT +60);

			pane.add(cardUserlbl[i], new Integer(5),0);
			pane.add(cardComp1lbl[i], new Integer(5),0);
			pane.add(cardComp2lbl[i], new Integer(5),0);
			pane.add(cardComp3lbl[i], new Integer(5),0);
			pane.add(cardDisplaylbl[i], new Integer(5),0);
		}	

		btnPlay = new JButton("Play");
		btnPlay.setBounds(365, 790, 80, 25);
		btnPlay.setEnabled(false);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playCard();

			}
		});
		pane.add(btnPlay, new Integer(4),0);

		//Button for player passing
		btnPass = new JButton("Pass");
		btnPass.setBounds(475, 790, 80, 25);
		btnPass.setEnabled(false);
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//If player passes
				log.addText(namelbl[0].getText() + " passed");
				errorlbl.setText("Pass");
				btnPlay.setEnabled(false);
				btnPass.setEnabled(false);				

				for (int i=0; i<4; i++){
					cardUserlbl[i].setIcon(null);
				}
				passCount +=1;
				passTurn();
				timerDelay.start();

			}
		});
		pane.add(btnPass, new Integer(4),0);

		//Timer for comp delay
		delayStuff = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				timerContents();
			}
		};        
		timerDelay = new Timer(900 , delayStuff);
		timerDelay.setRepeats(false);

		setVisible(true);
		
		gameOver = false;
		whoStarts();
	}

	//assuming your package name is images 


	public void assignCardpic(){
		URL imageUrl;
		Image myPicture;

		//Resizes the images at the directory and assigns sizes to their respective array
		for(int i=0; i<52; i++){
			imageUrl = getClass().getResource(piclocation[i]);
			myPicture = Toolkit.getDefaultToolkit().getImage(imageUrl);

			cardPic[i] = new ImageIcon(new ImageIcon(myPicture).getImage().getScaledInstance(cardSizeWIDTH, cardSizeHEIGHT,java.awt.Image.SCALE_SMOOTH));			
			cardPicSmall[i] = new ImageIcon(new ImageIcon(myPicture).getImage().getScaledInstance(cardSizeWIDTH -40, cardSizeHEIGHT -60, java.awt.Image.SCALE_SMOOTH));
			cardPicLarge[i] = new ImageIcon(new ImageIcon(myPicture).getImage().getScaledInstance(cardSizeWIDTH +40, cardSizeHEIGHT +60, java.awt.Image.SCALE_SMOOTH));

		}
	}

	public static void assignHand(){
		//Takes sorted hands from dealing.java and assigns them to parallel ArrayLists in the class
		String[] temp0 = dealing.hands[0].split(",");
		String[] temp1 = dealing.hands[1].split(",");
		String[] temp2 = dealing.hands[2].split(",");
		String[] temp3 = dealing.hands[3].split(",");

		for (int n=0; n<13; n++){
			handCard0.add(temp0[n]);
			handCard1.add(temp1[n]);
			handCard2.add(temp2[n]);
			handCard3.add(temp3[n]);
			for (int i=0; i<52; i++){
				if(temp0[n].equals(dealing.deck[i])) handInt0.add(i);
				else if(temp1[n].equals(dealing.deck[i])) handInt1.add(i);
				else if(temp2[n].equals(dealing.deck[i])) handInt2.add(i);
				else if(temp3[n].equals(dealing.deck[i])) handInt3.add(i);				
			}
		}		

		//**Debugging purposes**
		//System.out.println(handInt0);
		//System.out.println(handCard0);
	}

	public static void whoStarts(){
		//Looks for 3d, player possessing it starts
		if (handCard0.get(0).toString().equals("3d")){
			currentPos = 0;
			log.addText("It is your turn to start!");
			errorlbl.setText("It's your turn!");
			btnPlay.setEnabled(true);
		}
		else if(handCard1.get(0).toString().equals("3d")){
			currentPos = 0;
			timerDelay.start();
		}
		else if(handCard2.get(0).toString().equals("3d")){
			currentPos = 1;
			timerDelay.start();
		}
		else if(handCard3.get(0).toString().equals("3d")){
			currentPos = 2;
			timerDelay.start();
		}
	}


	public static void playCard() {						
		canPlay = true;		
		cardsBurned = false;
		errorlbl.setText("");		

		//Checks for whether selected cards are of same value
		for(int i=0; i<selectedCard.size()-1; i++){
			if (handCard0.get(Integer.parseInt(selectedCard.get(0).toString())).toString()
					.charAt(0) != 
					handCard0.get(Integer.parseInt(selectedCard.get(i+1).toString())).toString()
					.charAt(0)){
				errorlbl.setText("Cards not of same value");
				canPlay = false;
			}
		}

		if (cardsPlayed.length != 0 && canPlay == true && selectedCard.size()!=0){
			//Checks for proper amount of cards to be played
			if(selectedCard.size() != cardsPlayed.length) {
				errorlbl.setText("Incorrect number of cards selected");
				canPlay = false;
			}

			//Checks if cards can be played on those already on the table
			if(handCard0.get(Integer.parseInt(selectedCard.get(0).toString())).toString().replace("2", "30").replace("J", "11").replace("Q", "12").replace("K", "13").replace("A", "14").length() == 3){
				tempInt = Integer.parseInt(handCard0.get(Integer.parseInt(selectedCard.get(0).toString())).toString().replace("2", "30").replace("J", "11").replace("Q", "12").replace("K", "13").replace("A", "14").substring(0,2));
			}
			else tempInt = Integer.parseInt(handCard0.get(Integer.parseInt(selectedCard.get(0).toString())).toString().replace("2", "30").replace("J", "11").replace("Q", "12").replace("K", "13").replace("A", "14").substring(0,1));

			if(cardsPlayed[0].replace("2", "30").replace("Q", "12").replace("J", "11").replace("K", "13").replace("A", "14").length() == 3){
				tempInt2 = Integer.parseInt(cardsPlayed[0].replace("2", "30").replace("Q", "12").replace("J", "11").replace("K", "13").replace("A", "14").substring(0,2));
			}
			else tempInt2 = Integer.parseInt(cardsPlayed[0].replace("2", "30").replace("Q", "12").replace("J", "11").replace("K", "13").replace("A", "14").substring(0,1));

			if(tempInt<tempInt2) {
				errorlbl.setText("Card(s) are not higher in value");
				canPlay = false; 
			}
			//If cards are burned
			else if (tempInt == tempInt2)cardsBurned = true;
		}

		//Checks for if first turn, 3d is played
		if (firstTurn==true && canPlay == true){
			for(int i=0; i<selectedCard.size(); i++){
				if(selectedCard.get(i).toString().equals("0")){
					errorlbl.setText("");
					canPlay = true;
					firstTurn = false;
					break;
				}
				else {
					errorlbl.setText("Must play 3d on first turn");
					canPlay = false;
				}
			}
		}

		//Plays the cards if conditions are met
		if (selectedCard.size()<=4 && canPlay==true && selectedCard.size()!=0){			
			if (handCard0.get(Integer.parseInt(selectedCard.get(0).toString())).charAt(0) == '2'){
				cardsBurned = true;
			}

			//Sorts selectedCards
			int[] tempintarray = new int[selectedCard.size()];
			for (int n=0; n<selectedCard.size(); n++){
				tempintarray[n] = Integer.parseInt(selectedCard.get(n).toString());
			}
			Arrays.sort(tempintarray);

			cardsPlayed = new String[selectedCard.size()];
			selectedCard.clear();			
			for (int n=0; n<tempintarray.length; n++){
				selectedCard.add(tempintarray[n]);
				cardsPlayed[n] = handCard0.get(tempintarray[n]).toString();
			}

			//Resets and changes the appropriate labels
			for (int i=0; i<4; i++){
				cardUserlbl[i].setIcon(null);
				cardDisplaylbl[i].setIcon(null);
			}

			for (int n=0; n<selectedCard.size(); n++){
				tempInt = Integer.parseInt(handInt0.get(Integer.parseInt(selectedCard.get(n).toString())).toString());
				cardlbl[Integer.parseInt(selectedCard.get(n).toString())].setVisible(false);				
				cardUserlbl[n].setIcon(cardPicSmall[tempInt]);	
				cardDisplaylbl[n].setIcon(cardPicLarge[tempInt]);	
			}

			//Prepares for the next turn
			playerCardsRemaining -= selectedCard.size();
			selectedCard.clear();
			lastPlayedPos = 0;
			passCount = 0;
			btnPass.setEnabled(false);

			log.addText(namelbl[0].getText() + " played: " + Arrays.toString(cardsPlayed));

			//If player won, but not through burning
			if (playerCardsRemaining ==0 && cardsBurned == false){
				intWinners +=1;
				playerWinOrder[0] = intWinners;
				lastPlayedPos = 1;
				log.addText(namelbl[0].getText() + " is RANK " +intWinners);	
				errorlbl.setForeground(Color.MAGENTA);
				errorlbl.setText("Rank " + intWinners);
			}

			//If cards were burned
			if (cardsBurned==true){
				cardsPlayed = new String[0];
				for (int i=0; i<4; i++){
					cardComp1lbl[i].setIcon(null);
					cardComp2lbl[i].setIcon(null);
					cardComp3lbl[i].setIcon(null);
					cardDisplaylbl[i].setIcon(null);
				}
				log.addText(namelbl[0].getText() + " burned the cards");

				//Checks for if the player has won through burning
				if(playerCardsRemaining ==0){
					intWinners +=1;
					playerWinOrder[0] = intWinners;
					lastPlayedPos = 1;
					log.addText(namelbl[0].getText() + " is RANK " +intWinners);
					errorlbl.setForeground(Color.MAGENTA);
					errorlbl.setText("Rank " + intWinners);

					timerDelay.start();
				}
				else errorlbl.setText("Burn!");
			}

			//Proceeds to next comp
			else{
				btnPlay.setEnabled(false);
				checkGameOver();
				if(gameOver == false) timerDelay.start();
			}			

		}	

		//If player did not select any cards
		else if (selectedCard.size() == 0){
			errorlbl.setText("Select card(s) to play");
		}
	}

	public static void passTurn(){
		//Checks for whether remaining players have passed
		if(passCount ==3){
			cardsPlayed = new String[0];

			AI1.clearDisplaylbl();
			passCount =0;
			currentPos = lastPlayedPos;
			if (currentPos ==0){
				errorlbl.setText("It's your turn!");
				btnPlay.setEnabled(true);
			}
			else {
				currentPos -=1;	
			}
		}
	}

	//Checks for if game is complete
	public static void checkGameOver(){
		
		if (intWinners ==3){
			gameOver = true;
			btnPlay.setEnabled(false);
			btnPass.setEnabled(false);

			//Displays correct text in all labels
			for (int i=0; i<4; i++){
				if (playerWinOrder[i] == 0) {
					playerWinOrder[i] = 4;
					if (i != 0){
						compMessagelbl[i -1].setForeground(Color.MAGENTA);
						compMessagelbl[i-1].setText("BUM!");
					}
					else{
						errorlbl.setForeground(Color.MAGENTA);
						errorlbl.setText("BUM!");
					}
					break;
				}				
			}			
			log.addText("GAME OVER || Win Order: " + Arrays.toString(playerWinOrder));

			//Asks user to play again
			int n = JOptionPane.showConfirmDialog(
					pane, "Would you like to play again?",
					"Game Over!",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				welcome.f2.dispose();
				welcome.f3.dispose();
				welcome.completeReset();
				dealing.dealCards();
				welcome.f2 = new president_GUI();
				welcome.f3 = new log();			
			} 			
			else{
				welcome.main(null);
				welcome.f2.dispose();
				welcome.f3.dispose();
				welcome.completeReset();
			}			

		}		
	}

	public static void timerContents(){
		checkGameOver();
		if(gameOver == false){
			//Delays AI code
			currentPos += 1;

			//Clears messages if comp hasn't won
			for (int i=0; i<3;i++){
				if (playerWinOrder[i+1] == 0){
					compMessagelbl[i].setText("");
				}				
			}
			if (playerWinOrder[0] == 0) errorlbl.setText("");

			if (AI1.compBurned == true){
				timerDelay.setInitialDelay(900);
			}

			//Skips running AI code if hand is finished
			if (playerWinOrder[currentPos] == 0){
				if (currentPos ==1){
					AI1.pickACard(handInt1, handCard1, cardsPlayed, currentPos);
					timerDelay.start();
				}
				else if (currentPos ==2){
					AI1.pickACard(handInt2, handCard2, cardsPlayed, currentPos);
					timerDelay.start();
				}
				else if (currentPos ==3){
					AI1.pickACard(handInt3, handCard3, cardsPlayed, currentPos);

					if (AI1.compBurned == false){
						currentPos =0;

						//If player hasn't won, gives turn
						if (playerWinOrder[currentPos] == 0){
							errorlbl.setText("It's your turn!");
							btnPlay.setEnabled(true);
							btnPass.setEnabled(true);				
						}
						else{
							for (int i=0; i<4; i++){
								cardUserlbl[i].setIcon(null);
							}
							passCount +=1;
							passTurn();
							timerDelay.start();
						}	
					}
				}
			}

			//Lets player still go once Comp 3 has won
			else if (currentPos == 3){								
				AI1.clearComplbl();
				passCount +=1;
				passTurn();

				currentPos = 0;
				if (playerWinOrder[currentPos] == 0){
					errorlbl.setText("It's your turn!");
					btnPlay.setEnabled(true);
					if (AI1.compBurned == false) btnPass.setEnabled(true);				
				}
				else{
					for (int i=0; i<4; i++){
						cardUserlbl[i].setIcon(null);
					}
					passCount +=1;
					passTurn();
					timerDelay.start();
				}			
			}

			//Comp passes if already won
			else{
				AI1.clearComplbl();
				passCount +=1;
				passTurn();
				timerDelay.start();
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {				
		//Selects or deselects the chosen label
		for(int i=0; i<13; i++){
			if (e.getSource() == cardlbl[i]) {
				if (cardlbl[i].getLocation().y == 650){
					cardlbl[i].setBounds(110 + i*50, 630, cardSizeWIDTH, cardSizeHEIGHT);
					selectedCard.add(i+"");
				}
				else{
					cardlbl[i].setBounds(110 + i*50, 650, cardSizeWIDTH, cardSizeHEIGHT);
					selectedCard.remove(i+"");
				}
				break;			
			}
		}	
	}
	public void mouseReleased(MouseEvent e) {
	}

}
