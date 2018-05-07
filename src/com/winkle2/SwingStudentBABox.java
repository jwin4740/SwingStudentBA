package com.winkle2;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static javax.swing.text.StyleConstants.Size;

public class SwingStudentBABox extends JPanel implements ActionListener {
    Font font1 = new Font("SansSerif", Font.BOLD, 16);
    JFileChooser fc;
    JButton openButton, saveButton;
    JTextArea log;
    static private final String newline = "\n";

    private static void createAndShowGUI2() {
        Border paddedBorder2 = BorderFactory.createEmptyBorder(20, 35, 20, 35);
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
        mainContent.setPreferredSize(new Dimension(600, 400));
        frame.add(mainContent, BorderLayout.CENTER);


        //Display the window.
        frame.pack();
        frame.setVisible(true);


    }

    public SwingStudentBABox() {
        fc = new JFileChooser();
        openButton = new JButton("Browse...", createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);

        saveButton = new JButton("...");
        saveButton.addActionListener(this);

        JLabel formatLabel = new JLabel("Format");
        String[] formatOptions = {"XLS", "PDF", "JSON"};
        JComboBox formatCombo = new JComboBox(formatOptions);


        formatCombo.setSelectedIndex(0);


        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // top to bottom
        JPanel first = MakeTitledBorderPanel("Input File");
        JPanel firstPanel1 = MakeTextFieldAndButton(20, openButton, font1);
        JPanel secondPanel1 = MakeTextFieldAndButton(15, saveButton, font1);
        first.add(firstPanel1);

        JPanel second = MakeTitledBorderPanel("Output File");
        second.add(Box.createHorizontalGlue());
        second.add(formatLabel);
        second.add(formatCombo);
        second.add(secondPanel1);

//        second.add(a);
        add(first);
        add(second);
        add(MakeLowerPortion());
    }

    private JPanel MakeTextFieldAndButton(int columns, JButton b, Font f) {
        JPanel g = new JPanel();
        JTextField txt = new JTextField(columns);
        txt.setFont(f);
        g.add(txt);
        g.add(b);
        return g;

    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(SwingStudentBABox.this);

//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                File file = fc.getSelectedFile();
//                //This is where a real application would open the file.
//                log.append("Opening: " + file.getName() + "." + newline);
//            } else {
//                log.append("Open command cancelled by user." + newline);
//            }
//            log.setCaretPosition(log.getDocument().getLength());

            //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(this);
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                File file = fc.getSelectedFile();
//                //This is where a real application would save the file.
//                log.append("Saving: " + file.getName() + "." + newline);
//            } else {
//                log.append("Save command cancelled by user." + newline);
//            }
//            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    private static JPanel MakeTitledBorderPanel(String title) {
        JPanel panelSection = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 0, 25, 0);
        panelSection.setBorder(BorderFactory.createCompoundBorder(paddingBorder, BorderFactory.createTitledBorder(title)));

        return panelSection;
    }

    private static JPanel MakeLowerPortion() {
        JPanel buttonPane = new JPanel();
        JButton setButton = new JButton("CONVERT");
        setButton.setMaximumSize(new Dimension(400, 300));
        setButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        setButton.setFocusPainted(false);
        setButton.setBackground(Color.green);
        setButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon mine = createImageIcon("images/BA4.png");
        JLabel label2 = new JLabel(mine);
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPane.add(label2);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(setButton);
        return buttonPane;
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SwingStudentBABox.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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
