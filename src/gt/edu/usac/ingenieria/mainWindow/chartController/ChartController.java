package gt.edu.usac.ingenieria.mainWindow.chartController;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class ChartController {
    private JFreeChart chart;


    // TODO create chart controller


//    public JFreeChart createChart(CategoryDataset dataset) {
        // TODO create chart
//    }

    public void setPanel(JPanel chartPanel) {
    }

    public void createChart(int[] values, String[] countries) {
        CategoryDataset dataset = createDataset(values, countries);

        JFreeChart chart = buildChart(dataset);
        this.chart = chart;
    }

    private CategoryDataset createDataset(int[] values, String[] countries) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < values.length; i++) {
            dataset.setValue(values[i], "Y_VALUES", countries[i]);
        }
        return dataset;
    }

    private JFreeChart buildChart(CategoryDataset dataset) {

        return ChartFactory.createBarChart(
                "PAISES Y VALORES",
                "",
                "Y_VALUES",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
    }

    public JFreeChart getChart() {
        return chart;
    }
}
