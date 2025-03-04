import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListPeopleFrame extends JFrame {
    public ListPeopleFrame(ArrayList<PersonInfo> personInfos) {
        this.setSize(800, 500);
        this.getContentPane().setBackground(new Color(12, 64, 53));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Listare persoane");
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("logo.png").getImage());
        this.setLayout(null);
        //add a scrollable  list of buttons for each book
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(12, 64, 53));
        panel.setForeground(Color.WHITE);
        panel.setFont(new Font("Times New Roman", Font.BOLD
                , 20));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 50, 700, 300);
        //make the scrollpane have a border
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        //add books to scrollPane as buttons for each
        for (PersonInfo personInfo : personInfos) {
            JButton button = new JButton(personInfo.toString());
            button.setBackground(new Color(12, 64, 53));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Times New Roman", Font.BOLD
                    , 20));
            button.setFocusable(false);
            button.addActionListener(e -> {
                new PersonInfoFrame(personInfo);
            });
            panel.add(button);
        }
        this.add(scrollPane);

        //searchbar for people
        JTextField searchBar = new JTextField();
        searchBar.setBounds(50, 400, 700, 50);
        searchBar.setPreferredSize(new Dimension(200, 50));
        searchBar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.add(searchBar);
        //search button
        JButton searchButton = new JButton("Cautare persoana");
        searchButton.setBounds(50, 350, 700, 50);
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        searchButton.addActionListener(e -> {
            String search = searchBar.getText();
            search = removeDiacritics(search.toLowerCase());
            //refresh panel
            panel.removeAll();
            for (PersonInfo personInfo : personInfos) {
                //compare name and surname with search
                String nume = personInfo.getNume();
                String prenume = personInfo.getPrenume();
                nume = removeDiacritics(nume.toLowerCase());
                prenume = removeDiacritics(prenume.toLowerCase());
                if (nume.contains(search) || prenume.contains(search) || personInfo.getClasa().toLowerCase().contains(search)) {
//                    System.out.println(nume + " " + prenume);
                    JButton button = new JButton(personInfo.toString());
                    button.setBackground(new Color(12, 64, 53));
                    button.setForeground(Color.WHITE);
                    button.setFont(new Font("Times New Roman", Font.BOLD, 20));
                    button.setFocusable(false);
                    button.addActionListener(e1 -> {
                        new PersonInfoFrame(personInfo);
                    });
                    panel.add(button);
                }
            }
            this.revalidate();
            this.repaint();
        });

        this.add(searchButton);
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
