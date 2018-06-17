package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame {


    public static JPanel mainPanel, loadPanel, picturePanel, filt1Panel, filt2Panel, dataPanel, patientPanel, drawingPanel, pic1Panel, pic2Panel;
    public static JButton loadButton, filter1Button, filter2Button, drawButton, rubOutButton;
    public static JTextField  firstNameTxt, lastNameTxt, studyTxt, dateTxt, measureAngleTxt;
    public static JLabel firstNameLabel, lastNameLabel, studyLabel, dateLabel, contrast1Label, contrast2Label, brightness1Label, brightness2Label, image1Label, image2Label, measureAngleLabel;
    public static JSlider sliderContrast1, sliderContrast2, sliderBrigthness1, sliderBrigthness2;

    public View() {

        this.setTitle("Aplikacja do wyznaczania kątów skrzywienia w kręgosłupie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400,800);
        this.setResizable(true);

        //PANELS
        //Main panel
        mainPanel = new JPanel();
        mainPanel.setMaximumSize(new Dimension(1400,750));
        mainPanel.setMinimumSize(new Dimension(1400,750));
        mainPanel.setPreferredSize(new Dimension(1400,750));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.getContentPane().add(mainPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(500,10)));

        //Load panel
        loadPanel = new JPanel();
        loadPanel.setMaximumSize(new Dimension(1400,40));
        loadPanel.setMinimumSize(new Dimension(1400,40));
        loadPanel.setPreferredSize(new Dimension(1400,40));
        mainPanel.add(loadPanel);

        //Picture panel
        picturePanel = new JPanel ();
        picturePanel.setMaximumSize(new Dimension(1400,650));
        picturePanel.setMinimumSize(new Dimension(1400,650));
        picturePanel.setPreferredSize(new Dimension(1400,650));
        picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.X_AXIS));
        mainPanel.add(picturePanel);

        //Filter 1 panel
        filt1Panel = new JPanel ();
        filt1Panel.setMaximumSize(new Dimension(600,650));
        filt1Panel.setMinimumSize(new Dimension(600,650));
        filt1Panel.setPreferredSize(new Dimension(600,650));
        filt1Panel.setLayout(new BoxLayout(filt1Panel, BoxLayout.Y_AXIS));
        //filt1Panel.setBorder(BorderFactory.createLineBorder(Color.blue));
        picturePanel.add(filt1Panel);

        //Filter 2 panel
        filt2Panel = new JPanel ();
        filt2Panel.setMaximumSize(new Dimension(220,650));
        filt2Panel.setMinimumSize(new Dimension(220,650));
        filt2Panel.setPreferredSize(new Dimension(220,650));
        filt2Panel.setLayout(new BoxLayout(filt2Panel, BoxLayout.Y_AXIS));
        //filt2Panel.add(Box.createRigidArea(new Dimension(0,20)));

        filt2Panel.setBorder(BorderFactory.createLineBorder(Color.blue));
        filt2Panel.setBorder(BorderFactory.createTitledBorder("Filtracja obrazu"));
        picturePanel.add(filt2Panel);

        //Picture 1 panel
        pic1Panel= new JPanel ();
        pic1Panel.setMaximumSize(new Dimension(500,650));
        pic1Panel.setMinimumSize(new Dimension(500,650));
        pic1Panel.setPreferredSize(new Dimension(500,650));
        pic1Panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        filt1Panel.add(pic1Panel);

        //Pictrue 2 panel
        //pic2Panel= new JPanel ();
        //pic2Panel.setMaximumSize(new Dimension(500,500));
        //pic2Panel.setMinimumSize(new Dimension(500,500));
        //pic2Panel.setPreferredSize(new Dimension(500,500));
        //pic2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        //filt2Panel.add(pic2Panel);

        //Data panel
        dataPanel = new JPanel ();
        dataPanel.setMaximumSize(new Dimension(400,650));
        dataPanel.setMinimumSize(new Dimension(400,650));
        dataPanel.setPreferredSize(new Dimension(400,650));
        picturePanel.add(dataPanel);

        //Patient panel
        patientPanel = new JPanel ();
        patientPanel.setMaximumSize(new Dimension(400,325));
        patientPanel.setMinimumSize(new Dimension(400,325));
        patientPanel.setPreferredSize(new Dimension(400,325));
        patientPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        patientPanel.setBorder(BorderFactory.createTitledBorder("Pacjent"));
        dataPanel.add(patientPanel);

        //Drawing panel
        drawingPanel = new JPanel ();
        drawingPanel.setMaximumSize(new Dimension(400,315));
        drawingPanel.setMinimumSize(new Dimension(400,315));
        drawingPanel.setPreferredSize(new Dimension(400,315));
        drawingPanel.setLayout(new BoxLayout(drawingPanel, BoxLayout.Y_AXIS));
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        drawingPanel.setBorder(BorderFactory.createTitledBorder("Rysowanie"));
        dataPanel.add(drawingPanel);

        //CONTENTS OF PANELS
        //Load panel
        loadButton = new JButton("Załaduj obraz");
        loadButton.setMaximumSize(new Dimension(120,30));
        loadButton.setMinimumSize(new Dimension(120,30));
        loadButton.setPreferredSize(new Dimension(120,30));
        loadPanel.add(loadButton);

        //Filter 1
        filt1Panel.add(Box.createRigidArea(new Dimension(500,20)));
        filter1Button = new JButton("Filtr 1");
        filter1Button.setMaximumSize(new Dimension(100,40));
        filter1Button.setMinimumSize(new Dimension(100,40));
        filter1Button.setPreferredSize(new Dimension(100,40));
        filter1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        filter1Button.setEnabled(true);
        filt2Panel.add(filter1Button);

        //Filter 2
        filt2Panel.add(Box.createRigidArea(new Dimension(500,15)));

        filter2Button = new JButton("Filtr 2");
        filter2Button.setMaximumSize(new Dimension(100,40));
        filter2Button.setMinimumSize(new Dimension(100,40));
        filter2Button.setPreferredSize(new Dimension(100,40));
        filter2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        filter2Button.setEnabled(true);
        filt2Panel.add(filter2Button);

        filt2Panel.add(Box.createRigidArea(new Dimension(500,15)));
        contrast1Label = new JLabel("Kontrast");
        contrast1Label.setMaximumSize(new Dimension(100,20));
        contrast1Label.setMinimumSize(new Dimension(100,20));
        contrast1Label.setPreferredSize(new Dimension(100,20));
        contrast1Label.setHorizontalAlignment(SwingConstants.CENTER);
        contrast1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        filt2Panel.add(contrast1Label);

        filt2Panel.add(Box.createRigidArea(new Dimension(500,15)));

        sliderContrast1 = new JSlider(1,20, 1);
        sliderContrast1.setMaximumSize(new Dimension(200,40));
        sliderContrast1.setMinimumSize(new Dimension(200,40));
        sliderContrast1.setPreferredSize(new Dimension(200,40));
        sliderContrast1.setMajorTickSpacing(100);
        sliderContrast1.setMinorTickSpacing(10);
        sliderContrast1.setPaintLabels(true);
        sliderContrast1.setPaintTicks(true);
        sliderContrast1.setEnabled(false);
        filt2Panel.add(sliderContrast1);

        brightness1Label=new JLabel("Jasność");
        brightness1Label.setMaximumSize(new Dimension(100,20));
        brightness1Label.setMinimumSize(new Dimension(100,20));
        brightness1Label.setPreferredSize(new Dimension(100,20));
        brightness1Label.setHorizontalAlignment(SwingConstants.CENTER);
        brightness1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        filt2Panel.add(brightness1Label);

        sliderBrigthness1 = new JSlider(-10,10,0);
        sliderBrigthness1.setMaximumSize(new Dimension(200,40));
        sliderBrigthness1.setMinimumSize(new Dimension(200,40));
        sliderBrigthness1.setPreferredSize(new Dimension(200,40));
        sliderBrigthness1.setMajorTickSpacing(100);
        sliderBrigthness1.setMinorTickSpacing(10);
        sliderBrigthness1.setPaintLabels(true);
        sliderBrigthness1.setPaintTicks(true);
        sliderBrigthness1.setEnabled(false);
        sliderBrigthness1.setValue(0);
        filt2Panel.add(sliderBrigthness1);





        //Picture 1 panel
        image1Label = new JLabel();
        image1Label.setMaximumSize(new Dimension(400,500));
        image1Label.setMinimumSize(new Dimension(400,500));
        pic1Panel.add(image1Label);

        //Picture 2 panel
        // image2Label = new JLabel();
        // image2Label.setMaximumSize(new Dimension(400,500));
        //image2Label.setMinimumSize(new Dimension(400,500));
        //pic2Panel.add(image2Label);

        //Patient panel
        firstNameLabel= new JLabel("Imię" );
        firstNameLabel.setMaximumSize(new Dimension(300,30));
        firstNameLabel.setMinimumSize(new Dimension(300,30));
        firstNameLabel.setPreferredSize(new Dimension(300,30));
        patientPanel.add(firstNameLabel);

        firstNameTxt= new JTextField();
        firstNameTxt.setMaximumSize(new Dimension(300,30));
        firstNameTxt.setMinimumSize(new Dimension(300,30));
        firstNameTxt.setPreferredSize(new Dimension(300,30));
        firstNameTxt.setEnabled(false);
        patientPanel.add(firstNameTxt);

        lastNameLabel= new JLabel("Nazwisko" );
        lastNameLabel.setMaximumSize(new Dimension(300,30));
        lastNameLabel.setMinimumSize(new Dimension(300,30));
        lastNameLabel.setPreferredSize(new Dimension(300,30));
        patientPanel.add(lastNameLabel);

        lastNameTxt= new JTextField();
        lastNameTxt.setMaximumSize(new Dimension(300,30));
        lastNameTxt.setMinimumSize(new Dimension(300,30));
        lastNameTxt.setPreferredSize(new Dimension(300,30));
        lastNameTxt.setEnabled(false);
        patientPanel.add(lastNameTxt);

        studyLabel= new JLabel("Badanie" );
        studyLabel.setMaximumSize(new Dimension(300,30));
        studyLabel.setMinimumSize(new Dimension(300,30));
        studyLabel.setPreferredSize(new Dimension(300,30));
        patientPanel.add(studyLabel);

        studyTxt= new JTextField();
        studyTxt.setMaximumSize(new Dimension(300,30));
        studyTxt.setMinimumSize(new Dimension(300,30));
        studyTxt.setPreferredSize(new Dimension(300,30));
        studyTxt.setEnabled(false);
        patientPanel.add(studyTxt);

        dateLabel= new JLabel("Data" );
        dateLabel.setMaximumSize(new Dimension(300,30));
        dateLabel.setMinimumSize(new Dimension(300,30));
        dateLabel.setPreferredSize(new Dimension(300,30));
        patientPanel.add(dateLabel);

        dateTxt= new JTextField();
        dateTxt.setMaximumSize(new Dimension(300,30));
        dateTxt.setMinimumSize(new Dimension(300,30));
        dateTxt.setPreferredSize(new Dimension(300,30));
        dateTxt.setEnabled(false);
        patientPanel.add(dateTxt);

        //Drawing panel
        drawingPanel.add(Box.createRigidArea(new Dimension(400,30)));

        drawButton = new JButton("Rysuj");
        drawButton.setMaximumSize(new Dimension(100,40));
        drawButton.setMinimumSize(new Dimension(100,40));
        drawButton.setPreferredSize(new Dimension(100,40));
        drawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        drawButton.setEnabled(false);
        drawingPanel.add(drawButton);

        drawingPanel.add(Box.createRigidArea(new Dimension(400,30)));

        rubOutButton = new JButton("Wymaż proste");
        rubOutButton.setMaximumSize(new Dimension(150,40));
        rubOutButton.setMinimumSize(new Dimension(150,40));
        rubOutButton.setPreferredSize(new Dimension(150,40));
        rubOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rubOutButton.setEnabled(false);
        drawingPanel.add(rubOutButton);

        setVisible(true);
    }

    public void setController(Controller c) {
        this.loadButton.addActionListener(c);
        this.loadButton.setActionCommand("load");

        this.filter1Button.addActionListener(c);
        this.filter1Button.setActionCommand("filter1");

        this.filter2Button.addActionListener(c);
        this.filter2Button.setActionCommand("filter2");

        this.drawButton.addActionListener(c);
        this.drawButton.setActionCommand("draw");

        this.rubOutButton.addActionListener(c);
        this.rubOutButton.setActionCommand("rubOut");

        this.sliderContrast1.addChangeListener(c);

        this.sliderBrigthness1.addChangeListener(c);


        this.image1Label.addMouseListener(c);


    }
}