import controller.Controller;
import view.View;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View appView = new View();
                Controller appController = new Controller(appView);
                appView.setController(appController);
                appView.setVisible(true);
            }
        });
    }
}