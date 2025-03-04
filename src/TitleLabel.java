import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TitleLabel extends JLabel {
    public TitleLabel() {
        this.setText("Baza de date a bibliotecii");
        ImageIcon logo = new ImageIcon("logo.png");
        Border border = BorderFactory.createLineBorder(Color.GREEN, 3);
        this.setIcon(logo);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.TOP);
        this.setIconTextGap(20);
        //set font
        this.setFont(new Font("MV Boli", Font.BOLD, 20));
        //set foreground color
        this.setForeground(new Color(0x00FF00));
//        this.setBorder(border);
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.CENTER);
        //setBounds on the middle of the screen, top part
        //window is 1200x700
        this.setBounds(0, 0, 1200, 300);
    }
}
