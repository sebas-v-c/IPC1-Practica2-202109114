package gt.edu.usac.ingenieria;

import gt.edu.usac.ingenieria.mainWindow.WindowController;
import gt.edu.usac.ingenieria.mainWindow.WindowView;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        // Set locale in spanish
        Locale.setDefault(new Locale("es"));
        // Inicializar variables
        WindowView view = new WindowView();
        WindowController controller = new WindowController(view);
    }
}
