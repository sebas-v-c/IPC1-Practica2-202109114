package gt.edu.usac.ingenieria.mainWindow;

import gt.edu.usac.ingenieria.algorithms.InsertionSort;
import gt.edu.usac.ingenieria.algorithms.QuickSort;
import gt.edu.usac.ingenieria.execution.ExecutionInfo;
import gt.edu.usac.ingenieria.mainWindow.chartController.ChartController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WindowController {
    private String[] countries;
    private int[] values;
    private WindowView view;
    private ChartController chartController;
    private String filePath;
    private String algorithm;
    private double totalTime;
    private int pasosTotales;
    private ExecutionInfo execInfo;

    public WindowController(WindowView view, ExecutionInfo execInfo) {
        this.view = view;
        this.execInfo = execInfo;
        // Temporalmente mientras no haya Jfrechart
        view.setGenChartButtonEnabled(false);
        // Add listener to the view
        view.addGenChartButtonListener(new GenChartListener());
        view.addBrowseButtonListener(new BrowseListener());
        view.addSortButtonListener(new SortListener());
        view.addReportButtonListener(new ReportListener(this));
//        connectTable(view);
    }



    // TODO Listener Create selected algorithm and lock buttons
    private void createAlgorithms() {
        view.setButtonsLock(false);

        algorithm = view.getSelectedAlgorithm();

        switch (algorithm) {
            case "QuickSort" -> {
                QuickSort quickSort = new QuickSort(countries, values, view.getSelectedBehavior(), execInfo, this);
                quickSort.sort();
            }
            case "InsertionSort" -> {
                InsertionSort insertionSort = new InsertionSort(countries, values, view.getSelectedBehavior(), execInfo);
            }
        }


    }

    public void moveFinished(String[] countries, int[] values, ExecutionInfo execInfo) {
        // TODO slowly update the timer for 0.5 seconds
        double execTime = execInfo.getTotalTime() / 1_000_000_000;
        setMovesText(String.valueOf(execInfo.getMoves()));
        setTimeText(String.valueOf(execTime));
        System.out.println(execTime);
        System.out.println(execInfo.getMoves());


        if (execInfo.isSorted()) {
            view.setButtonsLock(true);
            view.setReportButtonVisible(true);
        }
    }
    // TODO Update stopwatch label and call chart update

    public void readCSV(BufferedReader csvReader) throws IOException {
        String row;
        String[] countries = new String[0];
        int[] values = new int[0];
        // Jump the first line of the file
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            try {
                values = addElement(values, Integer.parseInt(data[1]));
                countries = addElement(countries, data[0]);
            } catch (Exception e) {
                view.showMessage("Alguno de los datos del csv es invalido, saltando dato");
            }
        }
        csvReader.close();
        setCountries(countries);
        setValues(values);
    }

    // Setters and getters
    private void setCountries(String[] countries) {
        this.countries = countries;
    }
    private void setValues(int[] values) {
        this.values = values;
    }
    public int[] getValues() {
        return values;
    }
    public String[] getCountries() {
        return countries;
    }
    public String getAlgorithm() {
        return algorithm;
    }
    public WindowView getView() {
        return view;
    }
    public String getFilePath() {
        return view.getFilePathText();
    }
    public double getTotalTime() {
        return (double) execInfo.getTotalTime() / 1_000_000_000;
    }
    public long getPasosTotales() {
        return execInfo.getMoves();
    }
    public void setTimeText(String timeText) {
        view.setStopWatchText(timeText);
    }
    public void setMovesText(String moves) {
        view.setStepsText(moves);
    }


    public String[] addElement(String[] array, String newString) {
        int n =  array.length;
        String[] newArray = new String[n + 1];
        System.arraycopy(array, 0, newArray, 0, n);
        newArray[n] = newString;

        return newArray;
    }
    public int[] addElement(int[] array, int newInt) {
        int n =  array.length;
        int[] newArray = new int[n + 1];
        System.arraycopy(array, 0, newArray, 0, n);
        newArray[n] = newInt;

        return newArray;
    }

    public void sleepTime(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Listeners classes

    private class SortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(getFilePath()));
                readCSV(csvReader);
//                for (String element : countries) {
//                    System.out.println(element);
//                }
                createAlgorithms();
            } catch (Exception e) {
                view.showMessage("El archivo especificado no existe o es inválido");
                view.setButtonsLock(true);
            }
        }
    }


    private class GenChartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            chartController = new ChartController();
            chartController.createChart();
            view.setChartPanel(chartController.getChart());
        }
    }

    private class BrowseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // Create a file chooser and change the locale to display text in spanish
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Archivos csv", "csv");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                filePath = chooser.getSelectedFile().getPath();
                view.setFilePathText(filePath);
//                System.out.println("Se ha seleccionado este archivo: " +
//                        chooser.getSelectedFile().getName());
            }
        }
    }
}
