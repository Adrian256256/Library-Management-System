import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchButton extends JButton {
    public SearchButton(ArrayList<Book> books, BorrowDatabase borrowDatabase) {
        this.setText("Cautare carte");
        this.setBounds(500, 400, 200, 50);
        this.setFocusable(false);
        this.setFont(new Font("MV Boli", Font.BOLD, 15));

    }
}
