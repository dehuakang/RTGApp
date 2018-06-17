package controller;

import view.View;
import model.Model;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;


public class Controller implements ActionListener, ChangeListener, MouseListener {

    public View view;
    public Model model;

    public Controller(View view) {
        this.model = new Model();
        this.view = view;
   }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("load")) {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki w formacie DICOM", "dcm");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(view);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                model.loadPicture(file);

            }

            view.drawButton.setEnabled(true);
            view.rubOutButton.setEnabled(true);
            view.sliderContrast1.setEnabled(true);
            view.sliderBrigthness1.setEnabled(true);
            view.filter1Button.setEnabled(true);
            view.filter2Button.setEnabled(true);

        } else if (e.getActionCommand().equals("filter1")) {

            model.filter1();

        } else if (e.getActionCommand().equals("filter2")) {

            model.filter2();

        } else if (e.getActionCommand().equals("draw")) {

            model.drawOnPicture(view.image1Label);
            System.out.println("draw");

        } else if (e.getActionCommand().equals("rubOut")) {

            model.rubOutLines(view.image1Label);

        }
    }

    public void stateChanged(ChangeEvent e) {

        if (e.getSource().equals(View.sliderContrast1)) {

            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int change = source.getValue();
                model.changeContrast1(change, view.contrast1Label);
            }

        } else if (e.getSource().equals(View.sliderBrigthness1)) {

            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int change = source.getValue();
                model.changeBrightness1(change);
            }

        } else if (e.getSource().equals(View.sliderContrast2)) {

            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int change = source.getValue();
                model.changeContrast2(change);
            }

        } else if (e.getSource().equals(View.sliderBrigthness2)) {

            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int change = source.getValue();
                model.changeBrightness2(change);
            }
        }

    }

    public void mouseEntered(MouseEvent e) {

        //Nothing happens

    }

    public void mouseExited(MouseEvent e) {

        //Nothing happens

    }

    public void mouseReleased(MouseEvent e) {

        //TODO

    }

    public void mouseClicked(MouseEvent e) {

        //Nothing happens

    }

    public void mousePressed(MouseEvent e) {

        //TODO

    }
}