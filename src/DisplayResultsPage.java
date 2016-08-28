import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DisplayResultsPage extends JFrame implements ActionListener {
	private JPanel mainPanel    = new JPanel();
	private JPanel wrapper      = new JPanel();
	private JPanel resultsPanel = new JPanel();
	private JPanel buttonsPanel = new JPanel();
	
	double timeToDisplay;
	int    arrSize;
	
	public DisplayResultsPage(double timeToDisplay, int arraySize, String sortName) {
		this.timeToDisplay = timeToDisplay;
		this.arrSize       = arraySize;
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.PAGE_AXIS));
		
//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
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
		
		resultsPanel.add(new JLabel("Results"));
		resultsPanel.add(new JLabel("--------------"));
		resultsPanel.add(new JLabel("Array Size:     " + arrSize));
		resultsPanel.add(new JLabel("Milliseconds: " + mil + "ms"));
		resultsPanel.add(new JLabel("Seconds:        " + sec + "s"));
		
		wrapper.add(resultsPanel);
	}
	
	private void createButtons() {
		buttonsPanel.add(new JButton("Export"));
		
		wrapper.add(buttonsPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
