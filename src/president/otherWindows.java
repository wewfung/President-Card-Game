package president;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class otherWindows {
	
	//Displays the help window
	public static void ins() {   
		String msg = "<html>" +
		"<b><font size=\"5\">How To Play:</font></b><br><br>" +
        "<b>Objective</b><br>" +
        "-The aim is to get rid of all your cards as soon as possible.<br><br>" + 

        "<b>Players and Cards</b><br>" +
        "-There are 4 people using a standard 52 card pack.<br>" + 
        "-The suits are irrelevant and the cards rank, from low<br>" + 
        "to High: 3 4 5 6 7 8 9 10 J Q K A 2.<br><br>" + 
        "<b>Deal</b><br>" +
        "-The game is played clockwise. <br>" + 
        "-All the cards are dealt out.<br><br>" + 

        "<b>Play</b><br>" +
        "The player with 3d starts by leading (face up), with any<br>" + 
        "number of 3's so long as 3d is included. Each player in<br>" + 
        "turn must then either pass (not play any cards), or play<br>" + 
        "a card(s) matching the number of cards in play and of<br>" + 
        "higher rank.<br><br>" + 
        "A single card is beaten by any higher single card. A set of<br>" + 
        "cards can only be beaten by a higher set containing the<br>" + 
        "same number of cards. <br><br>" + 
        "It is not necessary to beat the previous play, passing is<br>" + 
        "always allowed. Passing does not prevent you from playing<br>" + 
        "the next time your turn comes around.<br><br>" +
        "The play continues until someone makes a play which everyone<br>" + 
        "else passes. The player who \"won\" the round starts again<br>" +
        "by leading any card or set of equal cards.<br><br>" +
        "<b>Note:</b> Playing a 2 or a card of the same value in<br>" +
        "in play automaticaly \"wins\" the round." +
        "<br><br></html>"; 

		JLabel label = new JLabel(msg);   
		label.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JOptionPane j = new JOptionPane();
		BufferedImage tmpicon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		ImageIcon icon = new ImageIcon(tmpicon);
		j.setIcon(icon);
		JOptionPane.showConfirmDialog(null, label, "Instructions", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);   
	}

	//Displays credits window
	public static void creds() {
		String msg = "<html><font size=\"6\"><b>     Credits</b></font><br>" + 
				"<b>Kade Robertson</b> - Coder<br>" +
				"<b>William Fung</b> - Coder<br>" +
				"<b>Google Images</b> - Pictures";

		JLabel label = new JLabel(msg);   
		label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JOptionPane j = new JOptionPane();
		BufferedImage tmpicon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		ImageIcon icon = new ImageIcon(tmpicon);
		j.setIcon(icon);
		JOptionPane.showConfirmDialog(null, label, "Game Credits", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);   
	}
}
