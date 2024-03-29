package gt.edu.usac.ingenieria.mainWindow;

import com.sun.source.tree.TryTree;
import gt.edu.usac.ingenieria.algorithms.BubbleSort;
import gt.edu.usac.ingenieria.algorithms.QuickSort;
import gt.edu.usac.ingenieria.execution.ExecutionInfo;
import gt.edu.usac.ingenieria.execution.Timer;
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
    private long totalTime;
    private long totalSteps;
    private ExecutionInfo execInfo;
    private Timer timer;
    private boolean chartCreated;
    private long delay;

    public WindowController(WindowView view, ExecutionInfo execInfo) {
        this.view = view;
        this.execInfo = execInfo;
        // Temporalmente mientras no haya Jfrechart
//        view.setGenChartButtonEnabled(false);
        // Add listener to the view
        view.addGenChartButtonListener(new GenChartListener());
        view.addBrowseButtonListener(new BrowseListener());
        view.addSortButtonListener(new SortListener());
        view.addReportButtonListener(new ReportListener(this));
//        connectTable(view);
        this.timer = new Timer(this);
        chartCreated = false;
    }



    private void createAlgorithms() {
        view.setButtonsLock(false);
        view.setReportButtonVisible(false);

        algorithm = view.getSelectedAlgorithm();

        switch (algorithm) {
            case "QuickSort" -> {
                QuickSort quickSort = new QuickSort(countries, values, view.getSelectedBehavior(), execInfo, this);
                this.timer = new Timer(this);
                new Thread(timer).start();
                new Thread(quickSort).start();

            }
            case "BubbleSort" -> {
                BubbleSort bubbleSort = new BubbleSort(countries, values, view.getSelectedBehavior(), execInfo, this);
                this.timer = new Timer(this);
                new Thread(timer).start();
                new Thread(bubbleSort).start();
            }
        }


    }

    private int num = 0;
    public void moveFinished(String[] countries, int[] values, ExecutionInfo execInfo) {
        timer.pause();
        num++;

        String texto = String.valueOf(execInfo.getMoves());
        view.setStepsText(texto);

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (chartCreated) {
            view.createChart(values, countries);
            view.setChartPanel();
        }

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalSteps = execInfo.getMoves();
        totalTime = timer.getElapseTime();
        if (execInfo.isSorted()) {
            view.setButtonsLock(true);
            view.setReportButtonVisible(true);
            execInfo.resetStatus();
            timer.resetTimer();
            timer.pause();
            timer.stop();
        } else {
            timer.resume();
        }
    }

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
    public void setCountries(String[] countries) {
        this.countries = countries;
    }
    public void setValues(int[] values) {
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
        return (double) totalTime / 1_000_000_000;
    }
    public long getTotalSteps() {
        return totalSteps;
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
                view.showMessage("Alguno de los parametros es incorrecto");
                view.setButtonsLock(true);
            }
        }
    }


    private class GenChartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(getFilePath()));
                readCSV(csvReader);
                chartCreated = true;
                view.createChart(values, countries);
                view.setChartPanel();
            } catch (Exception e) {
                e.printStackTrace();
                view.showMessage("No se ha especificado un archivo o es inválido");
                view.setButtonsLock(true);
            }
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
