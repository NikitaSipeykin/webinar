package ru.nikSipeikin.javaTwo.netChat.server.GUI;

import ru.nikSipeikin.javaTwo.netChat.server.core.ChatServer;
import ru.nikSipeikin.javaTwo.netChat.server.core.ChatServerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, ChatServerListener {
    private static final int POS_X = 800;
    private static final int POS_Y = 200;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;

    private final ChatServer chatServer = new ChatServer(this);
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JPanel panelTop = new JPanel(new GridLayout(1, 2));
    private final JTextArea log = new JTextArea();


    public static void main(String[] args) {

        /**Лямбда выражение от ананимного класса реализущего интерфейс new Runnable() -> run()*/
        SwingUtilities.invokeLater(() -> new ServerGUI() {
        });

    }

    public ServerGUI() {

        Thread.setDefaultUncaughtExceptionHandler(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        log.setEditable(false);
        log.setLineWrap(true);

        JScrollPane scrollLog = new JScrollPane(log);

        /**Лямбда выражение от ананимного класса реализущего интерфейс new ActionListener() -> actionPerformed(ActionEvent e)*/
        //btnStart.addActionListener(e -> chatServer.start(8189));

        //так как мы используем сам интерфейс и переопределили метод интерфейса
        // ActionListener() -> actionPerformed(ActionEvent e) то мы можем реализовать вот такую запись и в методе
        // провести проверку того какой btn нажат
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);

        panelTop.add(btnStart);
        panelTop.add(btnStop);
        add(panelTop, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == btnStop){
            chatServer.stop();
        }else if (src == btnStart){
//            throw new RuntimeException("Hello from EDT!");
            chatServer.start(8089);
        }else{
            throw new RuntimeException("Unknown source: " + src);
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

    @Override
    public void onChatServerMessage(String message) {
        SwingUtilities.invokeLater(()->{
            log.append(message + "\n");
            log.setCaretPosition(log.getDocument().getLength());
        });
    }
}
