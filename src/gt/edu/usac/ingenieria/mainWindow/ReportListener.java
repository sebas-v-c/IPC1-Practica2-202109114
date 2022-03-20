package gt.edu.usac.ingenieria.mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ReportListener implements ActionListener {
    private final WindowController controller;

    public ReportListener(WindowController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String currentDir = System.getProperty("user.dir");
        File newDir = new File(currentDir + "/reportes");
        if (!newDir.isDirectory()  && !newDir.exists()) {
            try {
                newDir.mkdir();
            } catch (Exception e) {
                controller.getView().showMessage("Ha habido un error creando el archivo");
            }
        }

        File report = new File(currentDir + "/reportes/" + controller.getView().getTittleText() + ".html");
        report.delete();
        FileWriter writer;
        PrintWriter newLine;

        try {
            report.createNewFile();
            writer = new FileWriter(report, true);
            newLine = new PrintWriter(writer);
            newLine.println(
                    """
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <style>
                                    table, th, td {
                                        border: 1px solid black;
                                    }
                                </style>
                                <meta charset="UTF-8">
                                <title>Reporte Existencias</title>
                            </head>
                            <body>
                            """
            );
            newLine.println("<h1>Sebastian Alejandro Vásquez Cartagena 202109114</h1>");
            newLine.println("<h2>" + controller.getAlgorithm() + "</h2>");
            newLine.println("<h3>" + controller.getTotalTime() + " s" + "</h3>");
            newLine.println("<h3>" + controller.getPasosTotales() + "</h3>");
            newLine.println("<br>");

            newLine.println("<h4>Tabla Ordenada</h4>");
            newLine.println("<table>");
            String table = crearTabla();
            newLine.println(table);
            newLine.println("</table>");

            newLine.println("<h4>Tabla NO Ordenada</h4>");
            newLine.println("<table>");
            BufferedReader csvReader = new BufferedReader(new FileReader(controller.getFilePath()));
            controller.readCSV(csvReader);
            table = crearTabla();
            newLine.println(table);
            newLine.println("</table>");
            //cerrar etiquetas
            newLine.println("</body>\n" + "</html>");
            writer.close();
            controller.getView().showMessage("El reporte se ha creado con éxito");
        } catch (Exception e) {
            controller.getView().showMessage("Ha habido un error escribiendo en el archivo");
        }
    }

    private String crearTabla() {
        StringBuilder tabla = new StringBuilder();
        tabla.append("""
                <tr>
                    <th>País</th>
                    <th>Valor</th>
                </tr>
                """);
        String[] countries = controller.getCountries();
        int[] values = controller.getValues();
        for (int elemento = 0; elemento < countries.length; elemento++) {
            tabla.append("<tr>\n");
            tabla.append("<td>").append(countries[elemento]).append("</td>\n");
            tabla.append("<td>").append(values[elemento]).append("</td>\n");
            tabla.append("</tr>\n");
        }
        return tabla.toString();
    }

}
