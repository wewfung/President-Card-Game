package president;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class welcome extends JFrame {
	
	//To refer to the different forms
	public static welcome f1;
	public static president_GUI f2;
	public static log f3;

	private JPanel contentPane;

	public static void main(String[] args) {
		f1 = new welcome();                
	}

	public welcome() {
		setResizable(false);
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(294, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		//Setup the main menu GUI
		JTextArea mainText = new JTextArea();
		mainText.setEditable(false);
		JTextArea mainText2 = new JTextArea();
		mainText2.setEditable(false);

		mainText2.setFont(new Font("Segoe UI", Font.BOLD, 26));
		mainText2.setBackground(UIManager.getColor("Button.background"));
		mainText2.setText("President");

		mainText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mainText.setBackground(UIManager.getColor("Button.background"));
		mainText.setText("Welcome to the President card game!");
				
		//Decorative pictures
		JLabel imagelabel = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/president/pics/cards.png"))));
		JLabel imagelabel2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/president/pics/cards.png"))));
			
		JButton enterGame = new JButton("Enter the Game!");
		JButton helpMenu = new JButton("View Game Help");
		JButton credits = new JButton("Credits");
		enterGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startGame();				
			}
		});
		helpMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				otherWindows.ins();
			}
		});
		credits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){				
				otherWindows.creds();
			}
		});
		
		contentPane.add(mainText2, BorderLayout.CENTER);
		contentPane.add(mainText, BorderLayout.CENTER);
		contentPane.add(imagelabel, BorderLayout.SOUTH);
		contentPane.add(imagelabel2, BorderLayout.SOUTH);
		contentPane.add(enterGame, BorderLayout.SOUTH);
		contentPane.add(helpMenu, BorderLayout.SOUTH);
		contentPane.add(credits, BorderLayout.SOUTH);

		setVisible(true);
	}

	public static void startGame(){
		//Asks for a name before game starts
		String name = JOptionPane.showInputDialog("Enter Name Below: (max.12)");			
		
		if (name !=null){
			completeReset();
			dealing.dealCards();
			
			f3 = new log();
			f2 = new president_GUI();			
			
			if (name.equals("")) president_GUI.namelbl[0].setText("Player 1");                    	
			else if (name.length()>12){
				president_GUI.namelbl[0].setText(name.substring(0, 12));
			}
			else president_GUI.namelbl[0].setText(name);   
			
			f1.dispose();
		}
		

	}

	public static void completeReset(){
		//Resets all variables for a new game
		dealing.hands = new String[]{"", "", "", ""};
		log.jt.setText("");
		log.jt.append(log.getDate() + "Game initialized.\n");
		
		president_GUI.handInt0.clear();
		president_GUI.handInt1.clear();
		president_GUI.handInt2.clear();
		president_GUI.handInt3.clear();

		president_GUI.handCard0.clear();
		president_GUI.handCard1.clear();
		president_GUI.handCard2.clear();
		president_GUI.handCard3.clear();

		president_GUI.selectedCard.clear();
		president_GUI.playerCardsRemaining = 13;

		president_GUI.cardsPlayed = new String[0];
		president_GUI.currentPos = 0;
		president_GUI.lastPlayedPos = 0;
		president_GUI.passCount = 0;

		president_GUI.firstTurn = true;

		president_GUI.playerWinOrder = new int[]{0, 0, 0, 0};
		president_GUI.intWinners = 0;
	}

}
