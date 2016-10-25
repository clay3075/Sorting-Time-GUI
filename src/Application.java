import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Application extends JFrame implements ActionListener {
  private static final long serialVersionUID  = -6990106684439731648L;
  private static final int  MAX_VALUE         = 1048576;
  private JPanel            mainPanel         = new JPanel();
  private JPanel            options           = new JPanel();
  private JPanel            arrayOptionsPanel = new JPanel();
  private JSpinner          numberField       = new JSpinner(
    new SpinnerNumberModel(1, 1, MAX_VALUE, 1));

  private int[] testArray;

  private void createRandomTestArray(int size) {
    // Allocate storage for the resulting array
    testArray   = new int[size];
    // Create an instance of a random number generator
    Random rand = new Random();
    // Iterate through the entire size of the array
    for (int i  = 0; i < size; i++)
      // Assign each element a random integer value
      testArray[i] = rand.nextInt() % MAX_VALUE;
  }

  private void createAscendingTestArray(int size) {
    // Allocate storage for the resulting array
    testArray  = new int[size];
    // Iterate through the entire size of the array
    for (int i = 0; i < size; i++)
      // Assign each element to its own index value
      testArray[i] = i % MAX_VALUE;
  }

  private void createDescendingTestArray(int size) {
    // Allocate storage for the resulting array
    testArray  = new int[size];
    // Iterate through the entire size of the array
    for (int i = 0; i < size; i++)
      // Assign each element to the difference of size and its own index value
      testArray[i] = ((size - 1) - i) % MAX_VALUE;
  }

  private void createWindow() {
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    setTitle("Sorting Times");
    JPanel titlePanel = new JPanel(new BorderLayout());
    JLabel label = new JLabel("Select the sort to test.");
    titlePanel.add(label);
    mainPanel.add(titlePanel);
    createSortOptions();
    JPanel titlePanel2 = new JPanel(new BorderLayout());
    JLabel label2 = new JLabel("Select which type of array to sort.");
    titlePanel2.add(label2);
    mainPanel.add(titlePanel2);
    createArrayOptions();
    // When the primary window is closed, terminate the application
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().add(mainPanel, BorderLayout.CENTER);
    setLocationRelativeTo(null);
    pack();
    setVisible(true);
  }

  private void createArrayOptions() {
    JPanel box = new JPanel();
    // Create a list of buttons to be added to the application window
    String[] arrayOptions = { "Randomized Array", "Sorted Ascending Array",
      "Sorted Descending Array" };
    // Create a group for the buttons to logically associate with
    ButtonGroup grp = new ButtonGroup();
    // For each button title ...
    for (String option : arrayOptions) {
      // Create a button with the given title
      JRadioButton btn = new JRadioButton(option);
      // Add the button to the logical group
      grp.add(btn);
      // Add the button to the array options panel
      arrayOptionsPanel.add(btn);
    } // Auto-select the first button
    grp.setSelected(grp.getElements().nextElement().getModel(), true);
    // Add the array options panel to the primary window
    box.add(arrayOptionsPanel);
    mainPanel.add(box);
  }

  private void createSortOptions() {
    JPanel box = new JPanel();
    // Create a list of checkboxes to be added to the application window
    String[] sortingNames = { "Bubble Sort", "Insertion Sort", "Merge Sort",
      "Quick Sort", "Heap Sort" };
    options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));
    // Create a group for the buttons to logically associate with
    ButtonGroup grp = new ButtonGroup();
    for (String name : sortingNames) {
      // Create a checkbox instance for each title
      JRadioButton btn = new JRadioButton(name);
      // Add the button to the logical group
      grp.add(btn);
      // Add the button to the options panel
      options.add(btn);
    } // Auto-select the first button
    grp.setSelected(grp.getElements().nextElement().getModel(), true);
    // Add the array type options panel to the primary window
    box.add(options);
    // Add a start button to the array type options panel
    JButton startButton = new JButton("Start");
    // Set the action listener for the start button to this class instance
    startButton.addActionListener(this);
    // Create an input box to hold the requested array size
    JPanel inputBox = new JPanel();
    inputBox.setLayout(new BoxLayout(inputBox, BoxLayout.PAGE_AXIS));
    inputBox.add(new JLabel("Enter array size for testing."));
    inputBox.add(numberField);
    // Add the input box and start button to the primary window
    box.add(inputBox);
    box.add(startButton);
    mainPanel.add(box);
  }

  public static void main(String[] args) {
    // Create and display the application's primary window
    Application app = new Application();
    app.createWindow();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Determine which array type has been selected
    String arrayOption = "";
    for (Component object: arrayOptionsPanel.getComponents()) {
      JRadioButton btn = (JRadioButton) object;
      if (btn.isSelected())
        arrayOption = btn.getText(); }
    // Only continue if an array type has been selected
    if (!arrayOption.equalsIgnoreCase("")) {
      // Determine the size of the array to be created
      Integer arraySize = (Integer)numberField.getValue();
      // Create the appropriate array type (will be stored as class attribute)
           if (arrayOption == "Randomized Array")
        createRandomTestArray    (arraySize);
      else if (arrayOption == "Sorted Ascending Array")
        createAscendingTestArray (arraySize);
      else if (arrayOption == "Sorted Descending Array")
        createDescendingTestArray(arraySize);

      // Create a Thread used to start a sorting algorithm
      Thread thread = null;
      // Loop through each sorting algorithm
      for (Component option: options.getComponents()) {
        JRadioButton temp = (JRadioButton)option;
        // Determine if this sorting algorithm is selected
        if (temp.isSelected()) {
          // Determine which sorting algorithm this checkbox represents
          if (temp.getText() == "Insertion Sort")  {
            // Create a copy of the original array into a separate storage
            // location for this thread to mutate
            int testArray1[] = new int[testArray.length];
            for (int i = 0; i < testArray.length; i++)
              testArray1[i] = testArray[i];
            // Create an instance of Thread representing this sorting algorithm
            thread = new Thread(new InsertionSort(testArray1));
          } else if (temp.getText() == "Bubble Sort") {
            // Create a copy of the original array into a separate storage
            // location for this thread to mutate
            int testArray2[] = new int[testArray.length];
            for (int i = 0; i < testArray.length; i++)
              testArray2[i] = testArray[i];
            // Create an instance of Thread representing this sorting algorithm
            thread = new Thread(new BubbleSort(testArray2));
          } else if (temp.getText() == "Merge Sort") {
            // Create a copy of the original array into a separate storage
            // location for this thread to mutate
            int testArray3[] = new int[testArray.length];
            for (int i = 0; i < testArray.length; i++)
              testArray3[i] = testArray[i];
            // Create an instance of Thread representing this sorting algorithm
            thread = new Thread(new MergeSort(testArray3));
          } else if (temp.getText() == "Heap Sort") {
            // Create a copy of the original array into a separate storage
            // location for this thread to mutate
            int testArray4[] = new int[testArray.length];
            for (int i = 0; i < testArray.length; i++)
              testArray4[i] = testArray[i];
            // Create an instance of Thread representing this sorting algorithm
            thread = new Thread(new HeapSort(testArray4));
          } else if (temp.getText() == "Quick Sort") {
            // Create a copy of the original array into a separate storage
            // location for this thread to mutate
            int testArray5[] = new int[testArray.length];
            for (int i = 0; i < testArray.length; i++)
              testArray5[i] = testArray[i];
            // Create an instance of Thread representing this sorting algorithm
            thread = new Thread(new QuickSort(testArray5));
          } thread.start(); // Begin sorting on this instance of Thread
        }
      }
    }
  }
}
