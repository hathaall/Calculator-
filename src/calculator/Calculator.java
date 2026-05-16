package calculator;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame implements ActionListener {
    static JFrame frame;
    static JTextField lable;
    String st0, st1, st2;

    Calculator() {
        st0 = st1 = st2 = "";
    }

    public static void main(String args[]) {
        frame = new JFrame("calculator");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Calculator calc = new Calculator();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        lable = new JTextField(16);
        lable.setEditable(false);

        JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btna, btns, btnd, btnm, btne, btneq, btneq1;

        btn0 = new JButton("0");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");

        btneq1 = new JButton("=");
        btna = new JButton("+");
        btns = new JButton("-");
        btnd = new JButton("/");
        btnm = new JButton("*");
        btneq = new JButton("C");
        btne = new JButton(".");

        JPanel p = new JPanel();

        btnm.addActionListener(calc);
        btnd.addActionListener(calc);
        btns.addActionListener(calc);
        btna.addActionListener(calc);
        btn9.addActionListener(calc);
        btn8.addActionListener(calc);
        btn7.addActionListener(calc);
        btn6.addActionListener(calc);
        btn5.addActionListener(calc);
        btn4.addActionListener(calc);
        btn3.addActionListener(calc);
        btn2.addActionListener(calc);
        btn1.addActionListener(calc);
        btn0.addActionListener(calc);
        btne.addActionListener(calc);
        btneq.addActionListener(calc);
        btneq1.addActionListener(calc);

        p.add(lable);
        p.add(btna);
        p.add(btn1);
        p.add(btn2);
        p.add(btn3);
        p.add(btns);
        p.add(btn4);
        p.add(btn5);
        p.add(btn6);
        p.add(btnm);
        p.add(btn7);
        p.add(btn8);
        p.add(btn9);
        p.add(btnd);
        p.add(btne);
        p.add(btn0);
        p.add(btneq);
        p.add(btneq1);

        p.setBackground(Color.blue);
        frame.add(p);
        frame.setSize(200, 220);
        frame.show();
    }

    public void actionPerformed(ActionEvent e) {
        String x = e.getActionCommand();

        if ((x.charAt(0) >= '0' && x.charAt(0) <= '9') || x.charAt(0) == '.') {
            if (!st1.equals(""))
                st2 = st2 + x;
            else
                st0 = st0 + x;
            lable.setText(st0 + st1 + st2);
        } else if (x.charAt(0) == 'C') {
            st0 = st1 = st2 = "";
            lable.setText(st0 + st1 + st2);
        } else if (x.charAt(0) == '=') {
            double t;
            if (st1.equals("+"))
                t = (Double.parseDouble(st0) + Double.parseDouble(st2));
            else if (st1.equals("-"))
                t = (Double.parseDouble(st0) - Double.parseDouble(st2));
            else if (st1.equals("/"))
                t = (Double.parseDouble(st0) / Double.parseDouble(st2));
            else
                t = (Double.parseDouble(st0) * Double.parseDouble(st2));

            lable.setText(st0 + st1 + st2 + "=" + t);
            st0 = Double.toString(t);
            st1 = st2 = "";
        } else {
            if (st1.equals("") || st2.equals(""))
                st1 = x;
            else {
                double t;
                if (st1.equals("+"))
                    t = (Double.parseDouble(st0) + Double.parseDouble(st2));
                else if (st1.equals("-"))
                    t = (Double.parseDouble(st0) - Double.parseDouble(st2));
                else if (st1.equals("/"))
                    t = (Double.parseDouble(st0) / Double.parseDouble(st2));
                else
                    t = (Double.parseDouble(st0) * Double.parseDouble(st2));

                st0 = Double.toString(t);
                st1 = x;
                st2 = "";
            }
            lable.setText(st0 + st1 + st2);
        }
    }
}