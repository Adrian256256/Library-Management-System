import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class BookFrame extends JFrame {
    public BookFrame(Book book, ArrayList<PersonInfo> people) {
        this.setSize(800, 600);
        this.getContentPane().setBackground(new Color(12, 64, 53));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Carte");
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("logo.png").getImage());
        this.setLayout(null);
        //scriu pe ecran numele cartii, autorul si numerele de inventar
        JLabel titleLabel = new JLabel("Titlu: " + book.getTitle());
        titleLabel.setBounds(50, 50, 700, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel);
        JLabel authorLabel = new JLabel("Autor: " + book.getAuthor());
        authorLabel.setBounds(50, 100, 700, 50);
        authorLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        authorLabel.setForeground(Color.WHITE);
        this.add(authorLabel);
        JLabel inventoryNumbersLabel = new JLabel("Numere de inventar: " + book.getInventoryNumbers().size());
        inventoryNumbersLabel.setBounds(50, 150, 700, 50);
        inventoryNumbersLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        inventoryNumbersLabel.setForeground(Color.WHITE);
        this.add(inventoryNumbersLabel);
        //label for disponibility
        JLabel disponibilityLabel = new JLabel("Nr. de inventar disponibile: ");
        disponibilityLabel.setBounds(50, 200, 700, 50);
        disponibilityLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        disponibilityLabel.setForeground(Color.WHITE);
        this.add(disponibilityLabel);
        //label for undisponibility
        JLabel undisponibilityLabel = new JLabel("Nr. de inventar indisponibile: ");
        undisponibilityLabel.setBounds(400, 200, 700, 50);
        undisponibilityLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        undisponibilityLabel.setForeground(Color.WHITE);
        this.add(undisponibilityLabel);
        ArrayList<Integer> notBorrowed = new ArrayList<>();
        ArrayList<Integer> isborrowed = new ArrayList<>();
        //iterate through BorrowDatabase.csv and check if the book is borrowed, if it is, make the text color red
        try {
            //for each number of inventory, check if it is borrowed
            for (int i = 0; i < book.getInventoryNumbers().size(); i++) {
                boolean borrowed = false;
                BufferedReader br = new BufferedReader(new FileReader("BorrowDatabase.csv"));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split("@");
                    if (values[1].equals(book.getInventoryNumbers().get(i).toString())) {
                        borrowed = true;
                        isborrowed.add(book.getInventoryNumbers().get(i));
                        break;
                    }
                }
                if (!borrowed) {
                    notBorrowed.add(book.getInventoryNumbers().get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JList<Integer> finalInventoryNumbersList = new JList<>(notBorrowed.toArray(new Integer[0]));
        finalInventoryNumbersList.setForeground(Color.WHITE);
        finalInventoryNumbersList.setFont(new Font("MV Boli", Font.BOLD, 20));
        JScrollPane scrollPane = new JScrollPane(finalInventoryNumbersList);
        scrollPane.setBounds(50, 250, 200, 300);
        //make the background of the list new Color(12, 64, 53)
        finalInventoryNumbersList.setBackground(new Color(12, 64, 53));
        JList<Integer> finalInventoryNumbersList2 = new JList<>(isborrowed.toArray(new Integer[0]));
        finalInventoryNumbersList2.setForeground(Color.RED);
        finalInventoryNumbersList2.setFont(new Font("MV Boli", Font.BOLD, 20));
        JScrollPane scrollPane2 = new JScrollPane(finalInventoryNumbersList2);
        scrollPane2.setBounds(400, 250, 200, 300);
        //make the background of the list new Color(12, 64, 53)
        finalInventoryNumbersList2.setBackground(new Color(12, 64, 53));
        this.add(scrollPane2);
        this.add(scrollPane);
        //add number of borrowed and not borrowed inventory numbers to the labels
        JLabel disponibility = new JLabel(String.valueOf(notBorrowed.size()));
        disponibility.setBounds(330, 200, 100, 50);
        disponibility.setFont(new Font("MV Boli", Font.BOLD, 20));
        disponibility.setForeground(Color.WHITE);
        this.add(disponibility);
        JLabel undisponibility = new JLabel(String.valueOf(isborrowed.size()));
        undisponibility.setBounds(700, 200, 100, 50);
        undisponibility.setFont(new Font("MV Boli", Font.BOLD, 20));
        undisponibility.setForeground(Color.WHITE);
        this.add(undisponibility);

        this.setVisible(true);

    }
}
