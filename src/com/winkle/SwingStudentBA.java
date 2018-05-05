package com.winkle;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/*
 * SwingStudentBA.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class SwingStudentBA extends JFrame
        implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    private Border paddingBorder = BorderFactory.createEmptyBorder(50, 15, 50, 15);
    private Border rightPadding = BorderFactory.createEmptyBorder(0, 0, 0, 25);

    public SwingStudentBA() {

        JPanel m = new JPanel();

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).


        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
//        saveButton = new JButton("Save a File...",
//                createImageIcon("images/Save16.gif"));
//        saveButton.addActionListener(this);


        //For layout purposes, put the buttons in a separate panel
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon mine = createImageIcon("images/BA4.png");

//        Border buttonBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);

        JLabel label2 = new JLabel(mine);
        label2.setBorder(paddingBorder);
        imgPanel.add(label2);

//        JPanel buttonPanel = new JPanel(); //use FlowLayout
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
//        openButton.setBorder(buttonBorder);
//        saveButton.setBorder(buttonBorder);
//        buttonPanel.add(openButton);
//        buttonPanel.add(saveButton);
        Font font1 = new Font("SansSerif", Font.BOLD, 16);
        Font font2 = new Font("SansSerif", Font.BOLD, 16);
        JTextField b = new JTextField(20);
        b.setFont(font2);


        JTextField a = new JTextField(20);
        a.setFont(font1);
        openButton = new JButton("Browse...",
                createImageIcon("images/Open16.gif"));

        JButton dotButton = new JButton("...");
        openButton.addActionListener(this);
        JPanel outputPanel = MakeTitledBorderPanel("Output");
        outputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel inputPanel = MakeTitledBorderPanel("Input File");
        inputPanel.add(a);
        inputPanel.add(openButton);
        JLabel formatLabel = new JLabel("Format");
        String[] formatOptions = {"XLS", "PDF", "JSON"};
        JComboBox formatCombo = new JComboBox(formatOptions);
//        formatCombo.setLayout(new FlowLayout());
        formatCombo.setSelectedIndex(0);
        formatCombo.setBorder(rightPadding);

        outputPanel.add(formatLabel);
        outputPanel.add(formatCombo);
        outputPanel.add(b);
        outputPanel.add(dotButton);
        JPanel nestedBorderLayout = new JPanel();
        nestedBorderLayout.setLayout(new BorderLayout());
        nestedBorderLayout.add(inputPanel, BorderLayout.NORTH);
        nestedBorderLayout.add(outputPanel, BorderLayout.CENTER);


        //Add the buttons and the log to this panel.
        m.setLayout(new BorderLayout());
        m.add(imgPanel, BorderLayout.SOUTH);
//        add(logScrollPane, BorderLayout.LINE_START);
        m.add(nestedBorderLayout, BorderLayout.CENTER);
        MakeFrame().add(m);


    }

    private JTextField makeInput() {
        return new JTextField();
    }

    private JFrame MakeFrame() {
        JFrame frame = new JFrame("SwingStudentBA");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int screenHeight = dim.height;
        int screenWidth = dim.width;
        int xPos = (screenWidth / 2) - (frame.getWidth() / 2);
        int yPos = (screenHeight / 2) - (frame.getHeight() / 2);
        frame.setLocation(xPos, yPos);
        frame.setSize(650, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        //Display the window.
        frame.setVisible(true);
        return frame;
    }

    private JPanel MakeTitledBorderPanel(String title) {
        JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createCompoundBorder(paddingBorder, BorderFactory.createTitledBorder(title)));
        return pan;
    }


    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(SwingStudentBA.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

            //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(SwingStudentBA.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SwingStudentBA.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {

        new SwingStudentBA();

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
