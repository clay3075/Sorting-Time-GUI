import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Application extends JFrame implements ActionListener {
	private JPanel     mainPanel = new JPanel();
	private JPanel     options   = new JPanel();
	private JTextField numberField = new JTextField();
	
	private int[] testArray;
	
	public void createRandomTestArray(int size) {
		testArray = new int[size];
		Random rand = new Random();
		
		for (int i = 0; i < size; i++) 
			testArray[i] = rand.nextInt();
//		testArray = new int[]{5,4,2,3,6}; //for testing only remove once done
	}
	
	private void createWindow() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setTitle("Sorting Times");
		JPanel titlePanel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Please select the sorts you would like to test.");
		titlePanel.add(label);
		mainPanel.add(titlePanel);
		createSortOptions();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //need this to actually quit application!!!
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	private void createSortOptions() {
		JPanel box = new JPanel();
		String[] sortingNames = {"Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Heap Sort", "Radix Sort"};
		options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));
		for (String name: sortingNames) {
			options.add(new JCheckBox(name));
		}
		box.add(options);
		JButton startButton = new JButton("Start");
		startButton.addActionListener(this);
		JPanel inputBox = new JPanel();
		inputBox.setLayout(new BoxLayout(inputBox, BoxLayout.PAGE_AXIS));
		inputBox.add(new JLabel("Enter array size for testing"));
		inputBox.add(numberField);
		box.add(inputBox);
		box.add(startButton);
		mainPanel.add(box);
	}
	
	public static void main(String[] args) {
		Application app = new Application();
		app.createWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//create array to be used for testing
		createRandomTestArray(Integer.parseInt(numberField.getText()));
		numberField.setText("");
		
		//will be used to start threads for sorting functions
		Thread thread = null;
		//loop through each check box
		for (Component option: options.getComponents()) {
			JCheckBox temp = (JCheckBox) option;
			if (temp.isSelected()) {
				//add thread to run selected sort in the background
				if (temp.getText() == "Insertion Sort")  {
					int testArray1[] = new int[testArray.length];
					for (int i = 0; i < testArray.length; i++)
						testArray1[i] = testArray[i];
					thread = new Thread(new InsertionSort(testArray1));
				}
				else if (temp.getText() == "Bubble Sort") {
					int testArray2[] = new int[testArray.length];
					for (int i = 0; i < testArray.length; i++)
						testArray2[i] = testArray[i];
					thread = new Thread(new BubbleSort(testArray2));
				}
				else if (temp.getText() == "Merge Sort") {
					int testArray3[] = new int[testArray.length];
					for (int i = 0; i < testArray.length; i++)
						testArray3[i] = testArray[i];
					thread = new Thread(new MergeSort(testArray3));
				}
				else if (temp.getText() == "Heap Sort") {
					int testArray4[] = new int[testArray.length];
					for (int i = 0; i < testArray.length; i++)
						testArray4[i] = testArray[i];
					thread = new Thread(new HeapSort(testArray4));
				}
				else if (temp.getText() == "Quick Sort") {
					int testArray5[] = new int[testArray.length];
					for (int i = 0; i < testArray.length; i++)
						testArray5[i] = testArray[i];
					thread = new Thread(new QuickSort(testArray5));
				}
				
				thread.start();          //start sorting algorithm
				temp.setSelected(false); //ready GUI for next use
			}
		}
	}
}
