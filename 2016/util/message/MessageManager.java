package message;

import javax.swing.JOptionPane;

public class MessageManager {
	
	public static void errMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void infoMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
