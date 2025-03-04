import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BorrowBookButton extends JButton {
    public BorrowBookButton(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setText("Imprumuta carte");
        this.setBounds(700, 325, 200, 50);
        this.setFocusable(false);
        this.setFont(new Font("MV Boli", Font.BOLD, 15));
        this.addActionListener(e -> {
            new BorrowBookFrame(books, personInfos);
        });
    }
}
