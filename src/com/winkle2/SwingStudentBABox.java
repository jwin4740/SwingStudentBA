package com.winkle2;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static javax.swing.text.StyleConstants.Size;

public class SwingStudentBABox extends JPanel implements ActionListener {
    Font font1 = new Font("SansSerif", Font.BOLD, 12);
    JFileChooser fc;
    JButton openButton, saveButton;
    JTextField input1;
    JTextField input2;
    JTextField input3;
    Border labelBorder = BorderFactory.createEmptyBorder(0, 12, 0, 2);
    static private final String newline = "\n";

    private static void createAndShowGUI() {
        Border paddedBorder2 = BorderFactory.createEmptyBorder(20, 35, 20, 35);
        JFrame frame = new JFrame("Student Deposit Account Log Uploader");
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
        mainContent.setPreferredSize(new Dimension(650, 435));
        frame.add(mainContent, BorderLayout.CENTER);
        frame.setResizable(false);

        //Display the window.
        frame.pack();
        frame.setVisible(true);


    }

    public SwingStudentBABox() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // top to bottom
        fc = new JFileChooser();
        openButton = new JButton("Browse...", createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);
        openButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        saveButton = new JButton("...");
        saveButton.addActionListener(this);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        JPanel first = MakeTitledBorderPanel("Input File");
        input1 = new JTextField(36);
        input1.setFont(font1);
        input1.setBackground(Color.lightGray);
        input1.setEditable(false);
        input1.setPreferredSize(new Dimension(60, 26));
        JPanel firstPanel1 = new JPanel();
        firstPanel1.add(input1);
        firstPanel1.add(openButton);
        first.add(firstPanel1);


        JPanel comboPanel = new JPanel();
        JLabel formatLabel = new JLabel("Format");
        String[] formatOptions = {".XLSX", ".JSON", ".PDF"};
        JComboBox formatCombo = new JComboBox(formatOptions);


        formatCombo.setSelectedIndex(0);
        comboPanel.add(formatLabel);
        comboPanel.add(formatCombo);


        JPanel second = MakeTitledBorderPanel("Output File");


        JPanel secondWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel secondPanel1 = new JPanel();
        input2 = new JTextField(30);
        input2.setFont(font1);
        input2.setBackground(Color.lightGray);
        input2.setEditable(false);
        input2.setPreferredSize(new Dimension(60, 26));

        secondPanel1.add(input2);
        secondPanel1.add(saveButton);


        JLabel outputFileLabel = new JLabel("Output Destination");
        outputFileLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        outputFileLabel.setBorder(labelBorder);

        secondWrapper.add(outputFileLabel);
        secondWrapper.add(secondPanel1);
        second.setLayout(new BorderLayout());
        second.add(comboPanel, BorderLayout.NORTH);
        second.add(secondWrapper, BorderLayout.CENTER);


        JPanel third = MakeTitledBorderPanel("Upload to Sharepoint");
        JPanel thirdWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel thirdPanel1 = new JPanel();
        JLabel sharepointLabel = new JLabel("Sharepoint Url");
        sharepointLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        sharepointLabel.setBorder(labelBorder);
        input3 = new JTextField(36);
        input3.setFont(font1);
        input3.setPreferredSize(new Dimension(60, 26));
        input3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPopupMenu popup = new JPopupMenu();
                popup.setLayout(new BorderLayout());
                popup.add(new JPanel()); // your component
                popup.setPopupSize(100, 100);
                popup.show(input3, 0, input3.getHeight());

            }
        });
        thirdPanel1.add(sharepointLabel);
        thirdPanel1.add(input3);
        thirdWrapper.add(thirdPanel1);
        third.setLayout(new BorderLayout());
        third.add(thirdWrapper, BorderLayout.NORTH);

        add(first);
        add(second);
        add(third);
        add(MakeLowerPortion());
    }

//    private JPanel MakeTextFieldAndButton(int columns, JButton b, JTextField txt, Font f) {
//        JPanel g = new JPanel();
//        txt.setColumns(columns);
//        txt.setFont(f);
//        txt.setBackground(Color.lightGray);
//        txt.setEditable(false);
//        g.add(txt);
//        g.add(b);
//        return g;
//
//    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(SwingStudentBABox.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                input1.setText(file.getAbsolutePath());
            } else {
                input1.setText("");
            }


            //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                input2.setText(file.getAbsolutePath());
            } else {
                input2.setText("");
            }
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
        JButton setButton = new JButton("Upload");
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
                createAndShowGUI();
            }
        };
        SwingUtilities.invokeLater(r);
    }

}
