package Game.login;

import Game.Data.constants;
import Game.game.Contoroler.thingInGame.SpecialSkill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static Game.Data.constants.*;
import static Game.Data.constants.PANEL_BACK_GRAND;

public class SkillTree extends JFrame {
    private static File data = new File ("src\\Game\\Data\\data.txt");
    boolean lock11, lock22, lock33;
    JPanel mainPanel;
    JButton EXIT;
    private JLabel Exp;
    private JLabel lock1;
    private JLabel lock2;
    private JLabel lock3;

    private JButton shop1;
    private JButton shop2;
    private JButton shop3;
    private JLabel flag1;
    private JLabel flag2;
    private JLabel flag3;

    private boolean decreaseEXP (int a) {
        if (totalEXP - a >= 0) {
            totalEXP -= a;
            return true;
        }
        return false;
    }

    public SkillTree () {
        getData ();
        setIconImage (new ImageIcon (constants.PROJECT_ICON_PATH).getImage ());
        setUndecorated (true);
        setFocusable (true);
        requestFocus ();
        requestFocusInWindow ();
        setBackground (new Color (0, 0, 0, 0));
        setSize (new Dimension (LOGIN_WIDTH, LOGIN_HEIGHT));
        setLocationRelativeTo (null);
        setVisible (true);
        setLayout (null);
        mainPanel = new JPanel ();
        mainPanel.setFocusable (true);
        mainPanel.requestFocus ();
        mainPanel.requestFocusInWindow ();
        mainPanel.setLayout (null);
        mainPanel.setBorder (BorderFactory.createLineBorder (PANEL_BAR_GRAND, 6));
        mainPanel.setBackground (PANEL_BACK_GRAND);
        mainPanel.setVisible (true);
        mainPanel.setOpaque (true);
        this.add (mainPanel);
        mainPanel.setBackground (Color.BLACK);
        setContentPane (mainPanel);
        EXIT = new JButton ();
        EXIT.setVisible (true);
        EXIT.setBounds (6, 6, 70, 41);
        EXIT.setIcon (new ImageIcon ("src\\sources\\photo\\Exit.png"));
        EXIT.addActionListener (new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                WriteData ();
                dispose ();
                new loginFrame ();
            }
        });
        mainPanel.add (EXIT);
        shop1 = new JButton ();
        shop1.setIcon (new ImageIcon ("src\\sources\\photo\\skillTree 1.png"));
        shop1.setBounds (constants.LOGIN_WIDTH / 3, constants.LOGIN_HEIGHT / 4, 229, 35);
        shop1.setVisible (true);
        shop1.setOpaque (true);
        shop1.addActionListener (new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (!lock11) {
                    if (decreaseEXP (750)) {
                        level1 = 1;
                        lock11 = true;
                        SpecialSkill.setCurrentSpecialSkill (new SpecialSkill.atack1());
                        peekNum = 1;
                        if (!lock11) {
                            lock1 = new JLabel (new ImageIcon ("src\\sources\\photo\\lock.png"));
                        } else {
                            lock1 = new JLabel (new ImageIcon ("src\\sources\\photo\\openlock.png"));
                        }
                    }
                }
                if (lock11) {
                    if (level1 == 1 && decreaseEXP (1500)) {
                        level1 = 2;
                    }
                    if (level1 == 2 && decreaseEXP (2000)) {
                        level1 = 3;
                    }
                    if (level1 == 1) {
                        TIME_OF_INCREASE_DAMAGE = 15000;
                    } else if (level1 == 2) {
                        TIME_OF_INCREASE_DAMAGE = 30000;
                    } else if (level1 == 3) {
                        TIME_OF_INCREASE_DAMAGE = 45000;
                    }
                    SpecialSkill.setCurrentSpecialSkill (new SpecialSkill.atack1());
                    peekNum = 1;
                    switch (peekNum) {
                        case 1 -> {
                            flag1.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                            flag3.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag2.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                        }
                        case 2 -> {
                            flag3.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag1.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag2.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                        }
                        case 3 -> {
                            flag1.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag2.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag3.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                        }
                    }
                }
                repaint ();
            }
        });
        this.add (shop1);
        shop3 = new JButton ();
        shop3.setIcon (new ImageIcon ("src\\sources\\photo\\skillTree3.png"));
        shop3.setBounds ((constants.LOGIN_WIDTH * 1) / 3, (constants.LOGIN_HEIGHT * 3) / 4, 288, 35);
        shop3.setVisible (true);
        shop3.setOpaque (true);
        shop3.addActionListener (new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (!lock33) {
                    if (decreaseEXP (1000)) {
                        level3 = 1;
                        lock33 = true;
//                        SpecialSkill.setCurrentSpecialSkill (new SpecialSkill.skill3 ());
                        peekNum = 3;
                        if (!lock33) {
                            lock3 = new JLabel (new ImageIcon ("src\\sources\\photo\\lock.png"));
                        } else {
                            lock3 = new JLabel (new ImageIcon ("src\\sources\\photo\\openlock.png"));
                        }
                    }
                }
                if (lock33) {
                    if (level3 == 1 && decreaseEXP (1500)) {
                        level3 = 2;
                    }
                    if (level3 == 2 && decreaseEXP (2000)) {
                        level3 = 3;
                    }
                    if (level3 == 2) {
                        NUMBER_OF_RAS = 2;
                    } else if (level3 == 3) {
                        NUMBER_OF_RAS = 4;
                    }
//                    SpecialSkill.setCurrentSpecialSkill (new SpecialSkill.skill3 ());
                    peekNum = 3;
                }
                switch (peekNum) {
                    case 1 -> {
                        flag1.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                        flag3.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                        flag2.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                    }
                    case 2 -> {
                        flag3.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                        flag1.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                        flag2.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                    }
                    case 3 -> {
                        flag1.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                        flag2.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                        flag3.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                    }
                }
                repaint ();

            }
        });
        this.add (shop3);
        shop2 = new JButton ();
        shop2.setIcon (new ImageIcon ("src\\sources\\photo\\skillTree 2.png"));
        shop2.setBounds ((constants.LOGIN_WIDTH * 1) / 3, constants.LOGIN_HEIGHT / 2, 329, 35);
        shop2.setVisible (true);
        shop2.setOpaque (true);
        shop2.addActionListener (new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (!lock22) {
                    if (decreaseEXP (750)) {
                        lock22 = true;
                        level2 = 1;
                        SpecialSkill.setCurrentSpecialSkill (new SpecialSkill.def1());
                        peekNum = 2;
                        if (!lock22) {
                            lock2 = new JLabel (new ImageIcon ("src\\sources\\photo\\lock.png"));
                        } else {
                            lock2 = new JLabel (new ImageIcon ("src\\sources\\photo\\openlock.png"));
                        }
                    }
                }
                if (lock22) {
                    if (level2 == 1) {
                        HeelRange = 1;
                    } else if (level2 == 2) {
                        HeelRange = 2;
                    } else if (level2 == 3) {
                        HeelRange = 3;
                    }
                    peekNum = 2;
                    SpecialSkill.setCurrentSpecialSkill (new SpecialSkill.def1());
                    switch (peekNum) {
                        case 1 -> {
                            flag1.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                            flag3.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag2.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                        }
                        case 2 -> {
                            flag3.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag1.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag2.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                        }
                        case 3 -> {
                            flag1.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag2.setIcon (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
                            flag3.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
                        }
                    }
                }
                repaint ();
            }
        });
        this.add (shop2);
        flag1 = new JLabel (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
        flag1.setVisible (true);
        flag1.setBounds ((constants.LOGIN_WIDTH) / 6, constants.LOGIN_HEIGHT / 4, 35, 35);
        flag1.setOpaque (true);
        this.add (flag1);
        flag2 = new JLabel (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
        flag2.setVisible (true);
        flag2.setBounds ((constants.LOGIN_WIDTH) / 6, constants.LOGIN_HEIGHT / 2, 35, 35);
        flag2.setOpaque (true);
        this.add (flag2);
        flag3 = new JLabel (new ImageIcon ("src\\sources\\photo\\notBuyRed.png"));
        flag3.setVisible (true);
        flag3.setBounds ((constants.LOGIN_WIDTH) / 6, (constants.LOGIN_HEIGHT * 3) / 4, 35, 35);
        flag3.setOpaque (true);
        this.add (flag3);
        if (!lock11) {
            lock1 = new JLabel (new ImageIcon ("src\\sources\\photo\\lock.png"));
        } else {
            lock1 = new JLabel (new ImageIcon ("src\\sources\\photo\\openlock.png"));
        }
        lock1.setVisible (true);
        lock1.setOpaque (true);
        lock1.setBounds ((constants.LOGIN_WIDTH * 5) / 6, constants.LOGIN_HEIGHT / 4, 27, 35);
        mainPanel.add (lock1);
        if (!lock22) {
            lock2 = new JLabel (new ImageIcon ("src\\sources\\photo\\lock.png"));
        } else {
            lock2 = new JLabel (new ImageIcon ("src\\sources\\photo\\openlock.png"));
        }
        lock2.setVisible (true);
        lock2.setOpaque (true);
        lock2.setBounds ((constants.LOGIN_WIDTH * 5) / 6, constants.LOGIN_HEIGHT / 2, 27, 35);
        mainPanel.add (lock2);
        if (!lock33) {
            lock3 = new JLabel (new ImageIcon ("src\\sources\\photo\\lock.png"));
        } else {
            lock3 = new JLabel (new ImageIcon ("src\\sources\\photo\\openlock.png"));
        }
        lock3.setVisible (true);
        lock3.setOpaque (true);
        lock3.setBounds ((constants.LOGIN_WIDTH * 5) / 6, (constants.LOGIN_HEIGHT * 3) / 4, 27, 35);
        mainPanel.add (lock3);
        Exp = new JLabel ();
        Exp.setSize (new Dimension (200, 35));
        Exp.setVisible (true);
        Exp.setLocation (new Point (80, 2));
        Exp.setText ("TOTAL  EXP :" + totalEXP);
        Exp.setFont (new Font ("Arial", Font.ITALIC, 13));
        Exp.getFont ().deriveFont (Font.BOLD);
        Exp.setForeground (new Color (100, 0, 250));
        Exp.setOpaque (false);
        Exp.setBackground (Color.black);
        mainPanel.add (Exp);
        switch (peekNum) {
            case 1 -> flag1.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
            case 2 -> flag2.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
            case 3 -> flag3.setIcon (new ImageIcon ("src\\sources\\photo\\buyGREEN.png"));
        }
    }

    private int totalEXP = 0;
    private int level1 = 1;
    private int level3 = 1;
    private int level2 = 1;
    private int peekNum = 0;

    private void getData () {
        try {
            Scanner sc = new Scanner (data);
            totalEXP = sc.nextInt ();
            lock11 = sc.nextBoolean ();
            lock22 = sc.nextBoolean ();
            lock33 = sc.nextBoolean ();
            level1 = sc.nextInt ();
            level2 = sc.nextInt ();
            level3 = sc.nextInt ();
            peekNum = sc.nextInt ();
        } catch (IOException t) {
        }
    }

    private void WriteData () {
        try {
            FileWriter fileWriter = new FileWriter (data);
            PrintWriter printWriter = new PrintWriter (fileWriter);
            printWriter.println (totalEXP);
            printWriter.println (lock11);
            printWriter.println (lock22);
            printWriter.println (lock33);
            printWriter.println (level1);
            printWriter.println (level2);
            printWriter.println (level3);
            printWriter.println (peekNum);
            printWriter.flush ();
        } catch (IOException e) {
        }
    }

    public static void increaseTotalExp (int totalEXP) {
        try {
            Scanner sc = new Scanner (data);
            int total = Integer.parseInt (sc.nextLine ());
            String a = "";
            while (sc.hasNextLine ()) {
                a += sc.nextLine () + "\n";
            }
            FileWriter fileWriter = new FileWriter (data);
            PrintWriter print = new PrintWriter (fileWriter);
            print.println (totalEXP + total);
            print.println (a);
            print.flush ();
        } catch (IOException e) {
        }
    }

    @Override
    public void paintComponents (Graphics g) {
        Exp.setText ("TOTAL  EXP :" + totalEXP);
        super.paintComponents (g);
    }
}
