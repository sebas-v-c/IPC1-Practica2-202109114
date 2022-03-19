package gt.edu.usac.ingenieria.mainWindow;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WindowView extends JFrame{
    private JPanel mainPanel;
    private JButton browseButton;
    private JTextField tittleTextField;
    private JPanel chartPanel;
    private JLabel stopWatchLabel;
    private JLabel stepsLabel;
    private JTextField filePathTextField;
    private JButton genChartButton;
    private JRadioButton ascendantRadioButton;
    private JRadioButton descendentRadioButton;
    private JRadioButton quickSortRadioButton;
    private JRadioButton insertionSortRadioButton;
    private JButton sortButton;
    private JButton reportButton;


    public WindowView() {
        super("Ventanan principal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    // add a ChartPanel to the JChartPanel
    public void setChartPanel(ChartPanel chart) {
        if (chartPanel.getComponent(0) != null) {
            chartPanel.remove(0);
        }
        chartPanel.add(chart);
    }


    // setters and getters of text
    public void setStopWatchText(String time) {
        stopWatchLabel.setText(time);
    }

    public void setStepsText(String steps) {
        stepsLabel.setText(steps);
    }

    public String getTittleText() {
        return tittleTextField.getText();
    }

    public String getFilePathText() {
        return filePathTextField.getText();
    }

    public void setFilePathText(String path) {
        filePathTextField.setText(path);
    }

    // radio buttons logic
    public int getSelectedBehavior() {
        if (ascendantRadioButton.isSelected()) {
            // Returns the first option
            return 0;
        }
        // Returns the second option
        return 1;
    }

    public String getSelectedAlgorithm() {
        if (quickSortRadioButton.isSelected()) {
            return "QuickSort";
        } else if (insertionSortRadioButton.isSelected()) {
            return "InsertionSort";
        } else {
            return "Invalid";
        }
    }

    // Buttons visible/locked
    public void setBowseButtonVisible(boolean bool) {
        browseButton.setVisible(bool);
    }

    public void setGenChartButtonEnabled(boolean b) {
        genChartButton.setEnabled(b);
    }

    public void setButtonsLock(boolean b) {
        browseButton.setEnabled(b);
        sortButton.setEnabled(b);
        reportButton.setEnabled(b);
        genChartButton.setEnabled(b);
        genChartButton.setEnabled(b);
    }

    // Messages
    public void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(mainPanel, mensaje);
    }

    // Listeners
    public void addSortButtonListener(ActionListener listener) {
        sortButton.addActionListener(listener);
    }

    public void addReportButtonListener(ActionListener listener) {
        reportButton.addActionListener(listener);
    }

    public void addGenChartButtonListener(ActionListener listener) {
        genChartButton.addActionListener(listener);
    }

    public void addBrowseButtonListener(ActionListener listener) {
        browseButton.addActionListener(listener);
    }
}