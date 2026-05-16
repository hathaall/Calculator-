package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Calculator extends JFrame implements ActionListener, KeyListener {
    static JFrame frame;
    static JTextField lable;
    static JTextArea historyArea;
    String st0, st1, st2;

    Calculator() {
        st0 = st1 = st2 = "";
    }

    public static void main(String args[]) {
        frame = new JFrame("Calculator v1.1");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Calculator calc = new Calculator();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10)); // Added gap between layout areas

        // Display Field
        lable = new JTextField();
        lable.setEditable(false);
        lable.setHorizontalAlignment(JTextField.RIGHT);
        lable.setPreferredSize(new Dimension(200, 50));
        lable.setFont(new Font("Arial", Font.BOLD, 20));

        // History Area
        historyArea = new JTextArea(10, 15);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        historyArea.setBackground(new Color(245, 245, 245));
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBorder(BorderFactory.createTitledBorder("History"));

        // Buttons
        JButton btn0 = new JButton("0");
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");

        JButton btneq1 = new JButton("=");
        JButton btna = new JButton("+");
        JButton btns = new JButton("-");
        JButton btnd = new JButton("/");
        JButton btnm = new JButton("*");
        JButton btneq = new JButton("C");
        JButton btne = new JButton(".");

        // New Scientific Buttons
        JButton btnSqrt = new JButton("√");
        JButton btnPow = new JButton("x²");

        // Add action listeners and disable focus so the frame catches key events
        JButton[] buttons = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
                btneq1, btna, btns, btnd, btnm, btneq, btne, btnSqrt, btnPow};
        for (JButton btn : buttons) {
            btn.addActionListener(calc);
            btn.setFocusable(false);
        }

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 4, 5, 5));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Row 1
        p.add(btneq); // C
        p.add(btnSqrt); // √
        p.add(btnPow);  // x²
        p.add(btnd); // /

        // Row 2
        p.add(btn7);
        p.add(btn8);
        p.add(btn9);
        p.add(btnm); // *

        // Row 3
        p.add(btn4);
        p.add(btn5);
        p.add(btn6);
        p.add(btns); // -

        // Row 4
        p.add(btn1);
        p.add(btn2);
        p.add(btn3);
        p.add(btna); // +

        // Row 5
        p.add(new JLabel("")); // Empty space
        p.add(btn0);
        p.add(btne); // .
        p.add(btneq1); // =

        // Assembly
        frame.add(lable, BorderLayout.NORTH);
        frame.add(p, BorderLayout.CENTER);
        frame.add(historyScroll, BorderLayout.EAST); // Add history to the right

        frame.addKeyListener(calc);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        frame.setSize(450, 400); // Widened to accommodate history
        frame.setMinimumSize(new Dimension(400, 350));
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    // Helper method to log history
    private void logHistory(String calculation) {
        historyArea.append(calculation + "\n");
        // Auto-scroll to bottom
        historyArea.setCaretPosition(historyArea.getDocument().getLength());
    }

    // Extracted calculation logic so it can be called by both Mouse and Keyboard
    private void processInput(String x) {
        if ((x.charAt(0) >= '0' && x.charAt(0) <= '9') || x.charAt(0) == '.') {
            if (!st1.equals(""))
                st2 = st2 + x;
            else
                st0 = st0 + x;
            lable.setText(st0 + st1 + st2);
        } else if (x.charAt(0) == 'C' || x.equals("c")) {
            st0 = st1 = st2 = "";
            lable.setText("");
        } else if (x.equals("√")) {
            if (!st0.equals("") && st1.equals("")) {
                double val = Math.sqrt(Double.parseDouble(st0));
                logHistory("√(" + st0 + ") = " + val);
                st0 = Double.toString(val);
                lable.setText(st0);
            }
        } else if (x.equals("x²")) {
            if (!st0.equals("") && st1.equals("")) {
                double val = Math.pow(Double.parseDouble(st0), 2);
                logHistory("(" + st0 + ")² = " + val);
                st0 = Double.toString(val);
                lable.setText(st0);
            }
        } else if (x.charAt(0) == '=' || x.equals("\n")) {
            if (!st0.equals("") && !st1.equals("") && !st2.equals("")) {
                double t = 0;
                if (st1.equals("+")) t = (Double.parseDouble(st0) + Double.parseDouble(st2));
                else if (st1.equals("-")) t = (Double.parseDouble(st0) - Double.parseDouble(st2));
                else if (st1.equals("/")) t = (Double.parseDouble(st0) / Double.parseDouble(st2));
                else if (st1.equals("*")) t = (Double.parseDouble(st0) * Double.parseDouble(st2));

                String equation = st0 + " " + st1 + " " + st2 + " = " + t;
                lable.setText(equation);
                logHistory(equation); // Save to history

                st0 = Double.toString(t);
                st1 = st2 = "";
            }
        } else {
            // Operator handling
            if (st1.equals("") || st2.equals("")) {
                st1 = x;
            } else {
                double t = 0;
                if (st1.equals("+")) t = (Double.parseDouble(st0) + Double.parseDouble(st2));
                else if (st1.equals("-")) t = (Double.parseDouble(st0) - Double.parseDouble(st2));
                else if (st1.equals("/")) t = (Double.parseDouble(st0) / Double.parseDouble(st2));
                else if (st1.equals("*")) t = (Double.parseDouble(st0) * Double.parseDouble(st2));

                st0 = Double.toString(t);
                st1 = x;
                st2 = "";
            }
            lable.setText(st0 + st1 + st2);
        }
    }

    public void actionPerformed(ActionEvent e) {
        processInput(e.getActionCommand());
    }

    // Keyboard Listeners
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        String validChars = "0123456789.+-*/=c\n\b";
        if (validChars.indexOf(c) != -1) {
            if (c == '\b') { // Handle backspace as Clear for simplicity
                processInput("C");
            } else if (c == '\n') { // Enter key
                processInput("=");
            } else {
                processInput(String.valueOf(c).toUpperCase());
            }
        }
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}