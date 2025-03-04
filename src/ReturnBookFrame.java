import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReturnBookFrame extends JFrame {
    public ReturnBookFrame(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Returneaza carte");

        JLabel searchLabel = new JLabel("Cautare:");
        searchLabel.setBounds(50, 50, 100, 25);
        this.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(150, 50, 200, 25);
        this.add(searchField);

        //add a scrollable  list of buttons for each entry in BorrowDatabase.csv
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(12,64,53));
        panel.setForeground(Color.WHITE);
        panel.setFont(new Font("Times New Roman", Font.BOLD
                , 20));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 150, 700, 300);
        //make the scrollpane have a border
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        //add borrowed books to scrollPane as buttons for each
        try (BufferedReader br = new BufferedReader(new FileReader("BorrowDatabase.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("@");
                JButton button = new JButton(values[0] + " - " + values[1] + " - " + values[2] + " - " + values[3]);
                button.setBackground(new Color(12,64,53));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Times New Roman", Font.BOLD
                        , 20));
                button.setFocusable(false);
                button.addActionListener(e -> {
                    //print la line
                    System.out.println(values[0] + " - " + values[1] + " - " + values[2] + " - " + values[3]);
                    new BorrowInfo(books, personInfos, values[0] + "@" + values[1] + "@" + values[2] + "@" + values[3]);
                });
                panel.add(button);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.add(scrollPane);

        //add search button
        JButton searchButton = new JButton("Cauta");
        searchButton.setBounds(150, 100, 200, 25);
        this.add(searchButton);

        //refresh panel when search button is pressed
        searchButton.addActionListener(e -> {
            String search = searchField.getText();
            //refresh panel, delete all buttons
            panel.removeAll();
            try (BufferedReader br = new BufferedReader(new FileReader("BorrowDatabase.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split("@");
                    //copy values to another array
                    String[] auxValues = new String[values.length];
                    for (int i = 0; i < values.length; i++) {
                        auxValues[i] = values[i];
                    }
                    //compare non case sensitive
                    //inlocuiesc diacriticele cu litere normale
                    for (int i = 0; i < values.length; i++) {
                        values[i] = removeDiacritics(values[i]);
                    }
                    //inlocuiesc diacriticele cu litere normale din search
                    search = removeDiacritics(search);



                    if (values[0].toLowerCase().contains(search.toLowerCase()) || values[1].toLowerCase().contains(search.toLowerCase()) || values[2].toLowerCase().contains(search.toLowerCase()) || values[3].toLowerCase().contains(search.toLowerCase())) {
                        JButton button = new JButton(
                                auxValues[0] + " - " + auxValues[1] + " - " + auxValues[2] + " - " + auxValues[3]);
                        button.setBackground(new Color(12, 64, 53));
                        button.setForeground(Color.WHITE);
                        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
                        button.setFocusable(false);
                        button.addActionListener(e2 -> {
                            new BorrowInfo(books, personInfos,
                                    auxValues[0] + "@" + auxValues[1] + "@" + auxValues[2] + "@" + auxValues[3]);
                        });
                        panel.add(button);
                        panel.revalidate();
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            panel.revalidate();
            panel.repaint();
        });




        this.setVisible(true);

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
}
