import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListBooksButton extends JButton {
    public ListBooksButton(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setText("Listare carti");
        this.setBounds(500, 325, 200, 50);
        this.setFocusable(false);
        this.setFont(new Font("MV Boli", Font.BOLD, 15));
        this.addActionListener(e -> {
            new ListBooksFrame(books, personInfos);
        });
    }
}
