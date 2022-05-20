package View;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class NotificationWindow extends JFrame {
    JPanel contentPanel;
    JLabel notificationLabel;
    JButton okButton;

    public NotificationWindow(String message){
        super("Notification");
        initGUI(message);
    }

    void initGUI(String message){
        setSize(300, 300);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        notificationLabel = new JLabel(message);
        notificationLabel.setBounds(75, 60, 200, 100);
        contentPanel.add(notificationLabel);
        okButton = new JButton("OK");
        okButton.setBounds(90, 200, 100, 30);
        contentPanel.add(okButton);
        okButton.addActionListener(e -> {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        setVisible(true);
    }
}
