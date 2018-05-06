package com.winkle2;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class SwingStudentBABox extends JPanel {


    private static void createAndShowGUI2() {
        Border paddedBorder2 = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        JFrame frame = new JFrame("Student Box Layout Test");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int screenHeight = dim.height;
        int screenWidth = dim.width;
        int xPos = (screenWidth / 2) - (frame.getWidth() / 2);
        int yPos = (screenHeight / 2) - (frame.getHeight() / 2);
        frame.setLocation(xPos, yPos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        SwingStudentBABox mainContent = new SwingStudentBABox();
        mainContent.setBorder(paddedBorder2);
        mainContent.setPreferredSize(new Dimension(600, 600));
        frame.add(mainContent, BorderLayout.CENTER);


        //Display the window.
        frame.pack();
        frame.setVisible(true);


    }

    public SwingStudentBABox() {

//        frame.setSize(800, 500);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // top to bottom
        JButton button = new JButton("Button ...... 1");
        //button.setPreferredSize(...);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        add(button);

        button = new JButton("Button 2");
        //button.setPreferredSize(...);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        add(button);

        button = new JButton("Button ........... 3");
        //button.setPreferredSize(...);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        add(button);

        JLabel label = new JLabel("Label");
        //label.setPreferredSize(...);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getMinimumSize().height));
        add(label);

        JTextField textField = new JTextField();
        //textField.setPreferredSize(...);
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getMinimumSize().height));
        add(textField);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        Runnable r = new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI2();
            }
        };
        SwingUtilities.invokeLater(r);
    }

}
