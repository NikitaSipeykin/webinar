package ru.geekBrains.java.sypeykinNV.webinar.lesson_4_NAHW_NET_CHAT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
/*
ДЗ к уроку 4
* todo доработатть программу так, чтобы она могла отправлять сообщения в лог по нажатию кнопки или по нажатию клавиши
   enter.
* todo Создать лог в файле (показать комментарием, где и как Вы планируете писать сообщение в файловый журнал).
* todo Прочитать методичку к следующему уроку
* */

    private static final int WIDTH = 400;
    private static final int HEIGHT =300;
    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));

    private final JTextField textFieldIPAddress = new JTextField("127.0.0.1");
    private final JTextField textFieldPort = new JTextField("8189");
    private final JCheckBox checkBoxAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField textFieldLogin = new JTextField("Nick");
    private final JPasswordField textFieldPassword = new JPasswordField("123456");
    private final JButton buttonLogin = new JButton("login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton buttonDisconnect = new JButton("<html><b>Disconnected</b></html>");
    private final JTextField textFieldMessage = new JTextField();
    private final JButton buttonSend = new JButton("Send");

    private final JList<String> userList = new JList<>();

    public static void main(String[] args) {

        /**Лямбда выражение от ананимного класса реализущего интерфейс new Runnable() -> run()*/
        SwingUtilities.invokeLater(() -> new ClientGUI() {
        });

    }

    private ClientGUI(){         //02:00
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);

        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUser = new JScrollPane(userList);
        scrollUser.setPreferredSize(new Dimension(150, 0));
        log.setEditable(false);

        String[] users = {"User1", "User2", "User3", "User4", "User5",
                "User_with_an_exceptionally_long_name_in_this_chat"};
        userList.setListData(users);

        checkBoxAlwaysOnTop.addActionListener(this);

        panelTop.add(textFieldIPAddress);
        panelTop.add(textFieldPort);
        panelTop.add(checkBoxAlwaysOnTop);
        panelTop.add(textFieldLogin);
        panelTop.add(textFieldPassword);
        panelTop.add(buttonLogin);

        panelBottom.add(buttonDisconnect, BorderLayout.WEST);
        panelBottom.add(textFieldMessage, BorderLayout.CENTER);
        panelBottom.add(buttonSend, BorderLayout.EAST);

        add(scrollLog, BorderLayout.CENTER);
        add(scrollUser, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == checkBoxAlwaysOnTop){
            setAlwaysOnTop(checkBoxAlwaysOnTop.isSelected());
        }else{
            throw new RuntimeException("Unknown Source: " + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        msg = "Exception" + t.getName() + " " +
                e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t at " + stackTraceElements[0];
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
