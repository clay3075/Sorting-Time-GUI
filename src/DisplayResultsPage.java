import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DisplayResultsPage extends JFrame implements ActionListener {
	private JPanel mainPanel    = new JPanel();
	private JPanel wrapper      = new JPanel();
	private JPanel resultsPanel = new JPanel();
	private JPanel buttonsPanel = new JPanel();
	
	private double timeToDisplay;
	private int    arrSize;
	private String sortName;
	private String exportInfo = "";
	
	public DisplayResultsPage(double timeToDisplay, int arraySize, String sortName) {
		this.timeToDisplay = timeToDisplay;
		this.arrSize       = arraySize;
		this.sortName      = sortName;
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.PAGE_AXIS));
		
		mainPanel.setLayout(new GridBagLayout());
		setTitle(sortName + " Results");
		setPreferredSize(new Dimension(300, 200));
		
		//create display information
		createResultsDisplay();
		createButtons();
		
		mainPanel.add(wrapper);
		getContentPane().add(mainPanel, BorderLayout.CENTER); //add the content to the main window
		setLocationRelativeTo(null);
		pack();           //pack all elements to the screen
		setVisible(true); //show window to screen
	}
	
	private void createResultsDisplay() {
		double mil = timeToDisplay;
		double sec = mil / 1000;
		
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));
		
		resultsPanel.add(new JLabel(sortName + " Results"));
		exportInfo += "Results\n";
		resultsPanel.add(new JLabel("--------------"));
		exportInfo += "--------------\n";
		resultsPanel.add(new JLabel("Array Size:     " + arrSize));
		exportInfo += "Array Size:     " + arrSize + "\n";
		resultsPanel.add(new JLabel("Milliseconds:   " + mil + "ms"));
		exportInfo += "Milliseconds: " + mil + "ms\n";
		resultsPanel.add(new JLabel("Seconds:        " + sec + "s"));
		exportInfo += "Seconds:        " + sec + "s\n";
		
		wrapper.add(resultsPanel);
	}
	
	private void createButtons() {
		JButton button = new JButton("Export");
		button.addActionListener(this);
		buttonsPanel.add(button);
		
		wrapper.add(buttonsPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//ask where to export file and export to that location
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int choice = chooser.showOpenDialog(null);
		
		if (choice == JFileChooser.APPROVE_OPTION) {
			new Export(chooser.getSelectedFile().toString(), exportInfo);
		}
		
	}
}
