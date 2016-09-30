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
	private JPanel     mainPanel         = new JPanel();
	private JPanel     options           = new JPanel();
	private JPanel     arrayOptionsPanel = new JPanel();
	private JTextField numberField       = new JTextField();
	
	private int[] testArray;
	
	private void createRandomTestArray(int size) {
		testArray = new int[size];
		Random rand = new Random();
		
		for (int i = 0; i < size; i++) 
			testArray[i] = rand.nextInt();
	}
	private void createAscendingTestArray(int size) {
		testArray = new int[size];
		for (int i = 0; i < size; i++)
			testArray[i] = i;
	}
	private void createDescendingTestArray(int size) {
		testArray = new int[size];
		for (int i = size - 1, j = 0; j < size; j++, i--)
			testArray[j] = i;
	}
	
	private void createWindow() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setTitle("Sorting Times");
		JPanel titlePanel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Please select the sorts you would like to test.");
		titlePanel.add(label);
		mainPanel.add(titlePanel);
		createSortOptions();
		JPanel titlePanel2 = new JPanel(new BorderLayout());
		JLabel label2 = new JLabel("Please select one of the below options.");
		titlePanel2.add(label2);
		mainPanel.add(titlePanel2);
		createArrayOptions();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //need this to actually quit application!!!
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	private void createArrayOptions() {
		JPanel box = new JPanel();
		String[] arrayOptions = {"Randomized Array", "Sorted Ascending Array", "Sorted Descending Array"};
//		arrayOptionsPanel.setLayout(new BoxLayout(arrayOptionsPanel, BoxLayout.PAGE_AXIS));
		for (String option: arrayOptions) {
			arrayOptionsPanel.add(new JCheckBox(option));
		}
		box.add(arrayOptionsPanel);
		mainPanel.add(box);
	}
	
	private void createSortOptions() {
		JPanel box = new JPanel();
		String[] sortingNames = {"Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Heap Sort"};
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
		//if user option is to create random array
		int count = 0;
		String arrayOption = "";
		for (Component object: arrayOptionsPanel.getComponents()) {
			JCheckBox checkBox = (JCheckBox) object;
			if (checkBox.isSelected()) {
				count++;
				arrayOption = checkBox.getText();
			}
		}
		if (count == 1) {
			if (arrayOption == "Randomized Array")
				createRandomTestArray(Integer.parseInt(numberField.getText()));
			else if (arrayOption == "Sorted Ascending Array")
				createAscendingTestArray(Integer.parseInt(numberField.getText()));
			else
				createDescendingTestArray(Integer.parseInt(numberField.getText()));
			
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
}
