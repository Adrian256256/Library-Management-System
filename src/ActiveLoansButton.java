import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ActiveLoansButton extends JButton {
    public ActiveLoansButton(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setText("Imprumuturi active");
        this.setBounds(900, 325, 200, 50);
        this.setFocusable(false);
        this.setFont(new Font("MV Boli", Font.BOLD, 15));
        this.addActionListener(e -> {
            new ActiveLoansFrame(books, personInfos);
        });
    }
}
