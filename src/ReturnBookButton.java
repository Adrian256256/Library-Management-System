import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReturnBookButton extends JButton {
    public ReturnBookButton(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setText("Returneaza carte");
        this.setBounds(100, 325, 200, 50);
        this.setFocusable(false);
        this.setFont(new Font("MV Boli", Font.BOLD, 15));
        this.addActionListener(e -> {
            new ReturnBookFrame(books, personInfos);
        });
    }
}
