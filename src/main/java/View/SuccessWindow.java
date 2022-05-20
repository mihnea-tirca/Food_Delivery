package View;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class SuccessWindow extends JFrame{

    JPanel contentPanel;
    JLabel sucessLabel;
    JButton okButton;

    public SuccessWindow(String message){
        super("Success");
        initGUI(message);
    }

    void initGUI(String message){
        setSize(300, 300);
        contentPanel = new JPanel(null);
        setContentPane(contentPanel);
        sucessLabel = new JLabel(message);
        sucessLabel.setBounds(75, 60, 200, 100);
        contentPanel.add(sucessLabel);
        okButton = new JButton("OK");
        okButton.setBounds(90, 200, 100, 30);
        contentPanel.add(okButton);
        okButton.addActionListener(e -> {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        setVisible(true);
    }
}
