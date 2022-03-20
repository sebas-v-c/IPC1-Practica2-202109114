package gt.edu.usac.ingenieria.mainWindow;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

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
    private JTextField delay;
    private JFreeChart chart;
    private DefaultCategoryDataset dataset;
    private boolean finished = false;


    public WindowView() {
        super("Ventanan principal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        setStopWatchText("00:00 s");
        setStepsText("00");
    }

    public String getDelay() {
        return delay.getText();
    }

    public void createChart(int[] values, String[] countries) {
        dataset = createDataset(values, countries);

        this.chart = buildChart(dataset);
    }

    public void updateDataSet(int[] values, String[] countries) {
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < dataset.getColumnCount(); i++) {
            dataset.setValue(values[i], "Y_VALUES", countries[i]);
        }
        chart = buildChart(dataset);
        chartPanel.removeAll();
        setChartPanel();
    }


    private DefaultCategoryDataset createDataset(int[] values, String[] countries) {
        dataset = null;
        dataset = new DefaultCategoryDataset();

//        int num = 0;
        for (int i = 0; i < values.length; i++) {
//            for (int j = 0; j < dataset.getColumnCount(); j++) {
//                if (countries[i].equals(dataset.getColumnKey(j))) {
//                    num++;
//                }
//            }
            dataset.setValue(values[i], "Y_VALUES", countries[i] + i);
//            if (num != 0) {
//                num = 0;
//            } else {
//                dataset.setValue(values[i], "Y_VALUES", countries[i]);
//            }
        }
        return dataset;
    }

    private JFreeChart buildChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "VALORES",
                "PAISES",
                "VALORES",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
    }

    public JFreeChart getChart() {
        return chart;
    }
    public void setReportButtonVisible(boolean b) {
        reportButton.setVisible(b);
    }

    // add a ChartPanel to the JChartPanel
    public void setChartPanel() {
        chartPanel.removeAll();
        ChartPanel chartPane = new ChartPanel(chart);
        chartPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPane.setBackground(Color.white);
        chartPanel.add(chartPane);
        chartPanel.revalidate();
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
    public boolean getSelectedBehavior() {
        if (ascendantRadioButton.isSelected()) {
            // Returns the first option
            return true;
        }
        // Returns the second option
        return false;
    }

    public String getSelectedAlgorithm() {
        if (quickSortRadioButton.isSelected()) {
            return "QuickSort";
        } else if (insertionSortRadioButton.isSelected()) {
            return "BubbleSort";
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