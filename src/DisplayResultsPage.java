import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
  private static final long serialVersionUID = 5170927468039070649L;
  private JPanel mainPanel    = new JPanel();
  private JPanel wrapper      = new JPanel();
  private JPanel resultsPanel = new JPanel();
  private JPanel buttonsPanel = new JPanel();

  private double timeToDisplay;
  private int[]  arr;
  private int[]  old;
  private String exportInfo = "";
  private int comparisons = 0;

  public DisplayResultsPage(double timeToDisplay, int comparisons, int[] arr,
      int[] old, String sortName) {
    this.arr = arr;
    this.old = old;
    this.timeToDisplay = timeToDisplay;
    this.comparisons   = comparisons;
    wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.PAGE_AXIS));

    mainPanel.setLayout(new GridBagLayout());
    setTitle(sortName + " Results");
    setPreferredSize(new Dimension(300, 200));

    //create display information
    System.out.println("Preparing export information for " + sortName + " ...");
    createResultsDisplay();
    createButtons();

    mainPanel.add(wrapper);
    // add the content to the main window
    getContentPane().add(mainPanel, BorderLayout.CENTER);
    setLocationRelativeTo(null);
    pack();           //pack all elements to the screen
    setVisible(true); //show window to screen
  }

  private void createResultsDisplay() {
    resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));

    append("Array Size:   " + this.arr.length, true, true);
    append("Milliseconds: " + String.format("%.8f", timeToDisplay) + " ms", true, true);
    append("Comparisons:  " + comparisons, true, true);
    append("Input:", true, false);
    appendArray(this.old);
    append("Result:", true, false);
    appendArray(this.arr);

    wrapper.add(resultsPanel);
  }

  private void appendArray(int[] array) {
    final int elementsPerLine = 8;
    for (int i = 0; i < array.length; i += elementsPerLine) {
      for (int j = 0; j < elementsPerLine && i + j < array.length; ++j) {
        String number = Integer.toString(array[i + j]);
        append(String.format("%8.8s ", number), false, false);
      } append("", true, false);
    }
  }

  private void append(String input, Boolean newline, Boolean display) {
    if (display == true) {
      JLabel label = new JLabel(input);
      label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
      resultsPanel.add(label); }
    exportInfo += input + (newline ? "\r\n" : "");
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
