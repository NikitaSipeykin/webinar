package ru.geekBrains.java.sypeykinNV.webinar.lesson_4_NAHW_NET_CHAT;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
/*
ДЗ к уроку 4
*  доработатть программу так, чтобы она могла отправлять сообщения в лог по нажатию кнопки  или по нажатию клавиши
   enter.
*  Создать лог в файле (показать комментарием, где и как Вы планируете писать сообщение в файловый журнал).
*  Прочитать методичку к следующему уроку
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
    private boolean shownIoErrors = false;

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

        buttonSend.addActionListener(this);
        //Ivan realization
        textFieldMessage.addActionListener(this);

        //my realization
//        textFieldMessage.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER){
//                    sender();
//                }
//            }
//        });

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
        } else if (src == buttonSend || src == textFieldMessage) {
            sendMessage();
        } else{
            throw new RuntimeException("Unknown Source: " + src);
        }
    }

    /** Message send methods **/

    private void sendMessage(){
        String msg = textFieldMessage.getText();
        String username = textFieldLogin.getText();
        if("".equals(msg)) return;
        textFieldMessage.setText(null);
        textFieldMessage.grabFocus();
        putLog(String.format("%s: %s", username, msg));
        writeMsgToLogFile(msg, username);
    }

    private void writeMsgToLogFile(String msg, String username) {
        try (FileWriter out = new FileWriter("log.txt", true)){
            out.write(username + ": " + msg + "\n");
            out.flush();
        }catch (IOException e){
            if (!shownIoErrors){
                shownIoErrors = true;
                showException(Thread.currentThread(), e);
            }
        }
    }

    private void putLog(String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }

    //my realization
    private void sender() {

        String msg =  textFieldMessage.getText();
        textFieldMessage.setText("");

        log.append(msg + "\n");
    }

    /** Message send methods **/

    /** exception methods **/

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        showException(t, e);
        System.exit(1);
    }

    private void showException(Thread t, Throwable e) {
        String msg;
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        if (stackTraceElements.length == 0)
            msg = "Empty Stacktrace";
        else {
            msg = String.format("Exception in \"%s\" %s\n\tat $s", t.getName(), e.getClass().getCanonicalName(),
                    e.getMessage(),stackTraceElements[0]);
            JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, msg, "Exception", JOptionPane.ERROR_MESSAGE);
    }

    /** exception methods **/
}