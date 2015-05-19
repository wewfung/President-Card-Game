package president;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class log extends JFrame{
	public static JTextArea jt = new JTextArea();

	public log() {
		setTitle("Game Logs");
		setSize(350, 500);
		setResizable(false);
		
		//Sets window to preferred location
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenW = screenSize.getWidth();
		double screenH = screenSize.getHeight();		
		int prefW = 350;
		int prefH = 500;
		int locationleft = (int) (screenW/2) - (prefW / 2) +490;
		int locationtop = (int) (screenH/2) - (prefH / 2) - 220;
		
		Point pt = new Point(locationleft, locationtop);
		setLocation(pt);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setVisible(false);				
			}
		});	
		
		//Adds scroll bar if necessary
		JScrollPane scroll = new JScrollPane(jt);
		add(scroll,BorderLayout.CENTER);		
		jt.setEditable(false);						
		setVisible(true);
	}
	
	//To obtain current time
	public static String getDate() {
		Calendar cTime = Calendar.getInstance();
		
		String curTime = "[" + cTime.get(Calendar.HOUR_OF_DAY)+":"+cTime.get(Calendar.MINUTE)+":"+cTime.get(Calendar.SECOND) + "]:\t";
		return curTime;
	}
	
	//Adds text to log with timestamp
	public static void addText(String txt) {
		jt.append(getDate() + txt +"\n");
		jt.select(jt.getCaretPosition(), 0);		
	}

}
