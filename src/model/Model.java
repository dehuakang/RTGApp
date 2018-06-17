package model;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import sun.awt.image.BufferedImageDevice;
import view.View;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.image.DataBufferByte;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.StringTokenizer;

import static javax.imageio.ImageIO.write;
import static org.opencv.imgproc.Imgproc.equalizeHist;


public class Model {

    String patientFullName, patientBodyPartExamined, patientStudyDate, fileName, filePath;
    Mat outputCopy;

    public void loadPicture (File file) {
        try {

            DicomInputStream din = new DicomInputStream(file);
            DicomObject dicomObject = din.readDicomObject();
            din.close();

            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = bufferedImage.getScaledInstance(500, 680, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(image);
            View.image1Label.setIcon(imageIcon);
            filePath=file.getAbsolutePath();
            fileName=file.getName();
            File f = new File(filePath + ".jpg");
            write(bufferedImage, "jpg", f);
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Mat originalCopy = Highgui.imread(filePath + ".jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
            Mat outputCopy = new Mat();

            //Showing the picture in separate window
            JFrame newFrame = new JFrame();
            JLabel newLabel = new JLabel();
            newFrame.add(newLabel);
            newLabel.setIcon(imageIcon);
            newFrame.pack();
            newFrame.setVisible(true);

            //Loading patients data into Data panel
            patientFullName = dicomObject.getString(Tag.PatientName);
            String correctPatientName = patientFullName.replace("^"," ");
            StringTokenizer stok = new StringTokenizer(correctPatientName);
            String firstName = stok.nextToken();
            StringBuilder middleName = new StringBuilder();

            //TODO Co zrobić jak nie ma tokenów??? Obsługa błędu!
            String lastName = stok.nextToken();

            while (stok.hasMoreTokens())
            {
                middleName.append(lastName + " ");
                lastName = stok.nextToken();
            }

            patientStudyDate = dicomObject.getString(Tag.StudyDate);
            String studyYear = patientStudyDate.substring(0, 4);
            String studyMonth = patientStudyDate.substring(4, 6);
            String studyDay = patientStudyDate.substring(6, 8);

            patientBodyPartExamined = dicomObject.getString(Tag.BodyPartExamined);

            View.firstNameTxt.setText(firstName+middleName);
            View.lastNameTxt.setText(lastName);
            View.studyTxt.setText(patientBodyPartExamined);
            View.dateTxt.setText(studyDay + "." + studyMonth + "." + studyYear);

        } catch (IOException e1) {
            //TODO
        }
    }

    private static BufferedImage matToBufferedImage(Mat mat) {
        BufferedImage image = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();

        mat.get(0, 0, dataBuffer.getData());

        return (image);
    }



    public void filter1() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.out.println("filePath: "+ filePath);
        //System.out.println("fileName: "+ fileName);
        Mat original = Highgui.imread(filePath + ".jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat output = new Mat();
        //Imgproc.GaussianBlur(original, output, new Size(25, 25),1,6);
        //Imgproc.threshold(original,output,60,255,Imgproc.THRESH_TOZERO);
        equalizeHist(original,output);
        BufferedImage outOfFilter = matToBufferedImage(output);
        Image imageOutOfFilter = outOfFilter.getScaledInstance(500, 680, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageOutOfFilter);
        View.image1Label.setIcon(imageIcon);

    }

    public void filter2() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat original = Highgui.imread(filePath + ".jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat output = new Mat();
        equalizeHist(original,output);
        Imgproc.threshold(output,output,60,255,Imgproc.THRESH_TOZERO);
        BufferedImage outOfFilter = matToBufferedImage(output);
        Image imageOutOfFilter = outOfFilter.getScaledInstance(500, 680, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageOutOfFilter);
        View.image1Label.setIcon(imageIcon);
    }

    public void drawOnPicture(JLabel label) {
        int[] tab = new int[6];
        label.addMouseListener(new MouseAdapter() {
            int x, y;
            int k = 0;
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                insert(x);
                insert(y);
                if (k==6) {
                    k=0;
                    tab[0]=tab[1]=tab[2]=tab[3]=tab[4]=tab[5]=0;
                }
            }

            private void insert(int val) {
                tab[k] = val;
                k++;

                Graphics g = label.getGraphics();

                if (tab[3] != 0) {

                    paintLine(tab[0], tab[1], tab[2], tab[3], g);
                    //g.drawString(Double.toString(count(tab, 1)), tab[2], tab[3] - 10);

                }
                if (tab[5] != 0) {
                    paintLine(tab[2], tab[3], tab[4], tab[5], g);
                    //g.drawString(Double.toString(count(tab, 2)), tab[4], tab[5] - 10);
                    g.drawString("Angle:"+ (countAngle(tab[2], tab[3],tab[0], tab[1],
                            tab[4],tab[5]))+" degrees",tab[0],tab[5]-40);
                }

            }

            private void paintLine(int xCoord1, int yCoord1, int xCoord2, int yCoord2, Graphics g) {
                g.setColor(Color.yellow);
                g.drawLine(xCoord1, yCoord1, xCoord2, yCoord2);
                g.drawRect(xCoord1, yCoord1, 2, 2);
                g.drawRect(xCoord2, yCoord2, 2, 2);

            }

            private double count(int[] table, int whichLine) {
                double distance1 = 0;
                double distance2 = 0;

                if (whichLine == 1) {
                    distance1 = table[0] - table[2];
                    distance2 = table[1] - table[3];
                }

                if (whichLine == 2) {
                    distance1 = table[2] - table[4];
                    distance2 = table[3] - table[5];
                }

                distance1 = distance1 * distance1;
                distance2 = distance2 * distance2;
                double distance = Math.sqrt(distance1 + distance2);

                System.out.println(distance);
                return distance;

            }

            private int countAngle(double X1, double Y1 , double X2, double Y2,
                                      double X3, double Y3){

                double numerator = Y2*(X1-X3) + Y1*(X3-X2) + Y3*(X2-X1);
                double denominator = (X2-X1)*(X1-X3) + (Y2-Y1)*(Y1-Y3);
                double ratio = numerator/denominator;

                double angleRad = Math.atan(ratio);
                int angleDeg = (int)((angleRad*180)/Math.PI);
                int finalAngle = 0;


                if (angleDeg >0) {
                    if (  X1 < X2 && X1 < X3) {
                        angleDeg = angleDeg;
                        System.out.println("1 if");
                    }
                    if ( X1 > X2 && X1 > X3) {
                        angleDeg=angleDeg;
                        System.out.println("2 if");
                    }
                    finalAngle =  angleDeg;
                }
                if (angleDeg<0) {
                    if ( X1 < X2 && X1 < X3) {
                        angleDeg =-angleDeg;
                        System.out.println("3 if");
                    }
                    if ( X1 > X2 && X1 > X3) {
                        angleDeg=-angleDeg;
                        System.out.println("4 if");
                    }

                    finalAngle = angleDeg;
                    System.out.println("k"+k);

                }
                return finalAngle;
            }

        });


    }

    public void rubOutLines(JLabel label) {

        label.repaint();
        //TODO

    }
    // public void imageCopy(){
    // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    // Mat originalCopy = Highgui.imread(filePath + ".jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
    // Mat outputCopy = new Mat();
    // }

    public void changeBrightness1(int change) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat original = Highgui.imread(filePath + ".jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat output2 = new Mat();
        if (change > 0) {
            original.convertTo(output2, -1, change);
        }
        if (change < 0) {
            original.convertTo(output2, 1, -change);
        }
        BufferedImage outOfChange = matToBufferedImage(output2);
        Image imageOutOfFilter = outOfChange.getScaledInstance(500, 680, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageOutOfFilter);

        View.image1Label.setIcon(imageIcon);

        System.out.println(change);

    }

    public void changeBrightness2(int change) {

        //TODO

    }

    public void changeContrast1(int change,JLabel label) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat original = Highgui.imread(filePath + ".jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat output2 = new Mat();
        Icon icon = View.image1Label.getIcon();
        Image im = ((ImageIcon) icon).getImage();
        BufferedImage bimage = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        bimage.getGraphics().drawImage(im, 0, 0 , null);

            Mat out;
            byte[] data;
            int r, g, b;
// konwersja z bufferedimage do mat
            if (bimage.getType() == BufferedImage.TYPE_INT_RGB) {
                out = new Mat(bimage.getHeight(), bimage.getWidth(), CvType.CV_8UC3);
                data = new byte[bimage.getWidth() * bimage.getHeight() * (int) out.elemSize()];
                int[] dataBuff = bimage.getRGB(0, 0, bimage.getWidth(), bimage.getHeight(), null, 0, bimage.getWidth());
                for (int i = 0; i < dataBuff.length; i++) {
                    data[i * 3] = (byte) ((dataBuff[i] >> 0) & 0xFF);
                    data[i * 3 + 1] = (byte) ((dataBuff[i] >> 8) & 0xFF);
                    data[i * 3 + 2] = (byte) ((dataBuff[i] >> 16) & 0xFF);
                }
            } else {
                out = new Mat(bimage.getHeight(), bimage.getWidth(), CvType.CV_8UC1);
                data = new byte[bimage.getWidth() * bimage.getHeight() * (int) out.elemSize()];
                int[] dataBuff = bimage.getRGB(0, 0, bimage.getWidth(), bimage.getHeight(), null, 0, bimage.getWidth());
                for (int i = 0; i < dataBuff.length; i++) {
                    r = (byte) ((dataBuff[i] >> 0) & 0xFF);
                    g = (byte) ((dataBuff[i] >> 8) & 0xFF);
                    b = (byte) ((dataBuff[i] >> 16) & 0xFF);
                    data[i] = (byte) ((0.21 * r) + (0.71 * g) + (0.07 * b));
                }
            }
            out.put(0, 0, data);

//kontrastowanie

            original.convertTo(output2, -1,Math.round(change*0.50), -100);



        BufferedImage outOfChange = matToBufferedImage(output2);
        Image imageOutOfFilter = outOfChange.getScaledInstance(500, 680, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageOutOfFilter);

        View.image1Label.setIcon(imageIcon);

        System.out.println(change);

    }

    public void changeContrast2(int change) {

        //TODO

    }
}