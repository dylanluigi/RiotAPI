package org.example;

import com.merakianalytics.orianna.Orianna;

import java.io.*;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */

    public class DisplayImage {

        public static void saveImage(String imageUrl, String destinationFile) throws IOException {
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        }

        public DisplayImage() throws IOException
        {

            Orianna.setRiotAPIKey(Example.get_API());
            String imageUrl = Example.get_IMG_URL();
            String destinationFile = "image.jpg";

            saveImage(imageUrl, destinationFile);

            BufferedImage img=ImageIO.read(new File("image.jpg"));
            ImageIcon icon=new ImageIcon(img);
            JFrame frame=new JFrame();
            frame.setLayout(new FlowLayout());
            frame.setSize(200,300);
            JLabel lbl=new JLabel();
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }



