import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Export extends JFrame implements ActionListener {
	private JPanel fileNamePanel = new JPanel();
	private JPanel mainPanel     = new JPanel();
	private JLabel instructions  = new JLabel("What would you like to name the file?");
	private JTextField fileName  = new JTextField(15);
	private JButton okButton	 = new JButton("Ok");
	String path;
	String information;
	
	public Export(String path, String information) {
		this.path = path;
		this.information = information;
		
		okButton.addActionListener(this);
		
		fileNamePanel.add(fileName);
		fileNamePanel.add(okButton);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(instructions);
		mainPanel.add(fileNamePanel);
		getContentPane().add(mainPanel, BorderLayout.CENTER); //add the content to the main window
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String fullPath = path + "/" + fileName.getText();
		//create the file
		try {
			PrintWriter writer = new PrintWriter(fullPath + ".txt");
			writer.print(information);
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
