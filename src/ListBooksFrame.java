import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListBooksFrame implements ActionListener {
    //array of books
    ArrayList<Book> books;
    ArrayList<PersonInfo> people;

    public ListBooksFrame(ArrayList<Book> books, ArrayList<PersonInfo> people) {
        this.people = people;
        this.books = books;
        JFrame frame = new JFrame();
        frame.setSize(800, 500);
        frame.getContentPane().setBackground(new Color(12,64,53));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Listare carti");
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("logo.png").getImage());
        frame.setLayout(null);

        //add a scrollable  list of buttons for each book
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(12,64,53));
        panel.setForeground(Color.WHITE);
        panel.setFont(new Font("Times New Roman", Font.BOLD
                , 20));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 50, 700, 300);
        //make the scrollpane have a border
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        //add books to scrollPane as buttons for each
        for (Book book : books) {
            JButton button = new JButton(book.toString());
            button.setBackground(new Color(12,64,53));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Times New Roman", Font.BOLD
                    , 20));
            button.setFocusable(false);
            button.addActionListener(e -> {
                new BookFrame(book, people);
            });
            //delete border of button
            button.setBorderPainted(false);
            panel.add(button);
        }
        frame.add(scrollPane);
        //searchbar for books
        JTextField searchBar = new JTextField();
        searchBar.setBounds(50, 400, 700, 50);
        searchBar.setPreferredSize(new Dimension(200, 50));
        searchBar.setFont(new Font("Times New Roman", Font.BOLD
                , 20));
        frame.add(searchBar);
        //search button
        JButton searchButton = new JButton("Cautare carte");
        searchButton.setBounds(50, 350, 700, 50);
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Times New Roman", Font.BOLD
                , 15));
        searchButton.addActionListener(e -> {
            String search = searchBar.getText();
            //refresh panel
            panel.removeAll();
            //inlocuiesc diacriticele cu literele corespunzatoare
            search = removeDiacritics(search);
            search = search.toLowerCase();

            for (Book book : books) {
                String title = book.getTitle().toLowerCase();
                String author = book.getAuthor().toLowerCase();
                String inventoryNumbers = book.getInventoryNumbers().toString();
                //scot diacriticele din titlu si autor
                title = removeDiacritics(title);
                author = removeDiacritics(author);
                if (title.contains(search) || author.contains(search) || inventoryNumbers.contains(search)) {
                    JButton button = new JButton(book.toString());
                    button.setBackground(new Color(12,64,53));
                    button.setForeground(Color.WHITE);
                    button.setFont(new Font("Times New Roman", Font.BOLD
                            , 20));
                    button.setFocusable(false);
                    button.addActionListener(e1 -> {
                        new BookFrame(book, people);
                    });
                    button.setBorderPainted(false);
                    panel.add(button);
                }

            }
            //refresh panel
            panel.revalidate();
        }
        );
        frame.add(searchButton);

        frame.setVisible(true);
    }

    //metoda care scoate diacriticele dintr-un string
    public String removeDiacritics(String s) {
        s = s.replace("ă", "a");
        s = s.replace("â", "a");
        s = s.replace("î", "i");
        s = s.replace("ș", "s");
        s = s.replace("ț", "t");
        s = s.replace("Ă", "A");
        s = s.replace("Â", "A");
        s = s.replace("Î", "I");
        s = s.replace("Ș", "S");
        s = s.replace("Ț", "T");
        return s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
