package gt.edu.usac.ingenieria;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;
import gt.edu.usac.ingenieria.mainWindow.WindowController;
import gt.edu.usac.ingenieria.mainWindow.WindowView;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        // Set locale in spanish
        Locale.setDefault(new Locale("es"));
        System.out.println("HOLA");
        // Inicializar variables
        WindowView view = new WindowView();
        ExecutionInfo execInfo = new ExecutionInfo(0, 0);
        WindowController controller = new WindowController(view, execInfo);
    }
}
