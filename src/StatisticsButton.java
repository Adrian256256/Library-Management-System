import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatisticsButton extends JButton {
    public StatisticsButton(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setText("Statistici");
        this.setBounds(500, 275, 200, 50);
        this.setFocusable(false);
        this.setFont(new Font("MV Boli", Font.BOLD, 15));
        this.addActionListener(e -> {
            new StatisticsFrame(books, personInfos);
        });
    }
}
