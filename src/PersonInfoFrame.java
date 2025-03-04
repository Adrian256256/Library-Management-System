import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PersonInfoFrame extends JFrame {
    public PersonInfoFrame(PersonInfo personInfo) {
        this.setSize(800, 500);
        this.getContentPane().setBackground(new Color(12, 64, 53));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Informatii persoana");
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("logo.png").getImage());
        this.setLayout(null);


        JLabel numeLabel = new JLabel("Nume: " + personInfo.getNume());
        numeLabel.setBounds(50, 50, 700, 50);
        numeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        numeLabel.setForeground(Color.WHITE);
        this.add(numeLabel);

        JLabel prenumeLabel = new JLabel("Prenume: " + personInfo.getPrenume());
        prenumeLabel.setBounds(50, 100, 700, 50);
        prenumeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        prenumeLabel.setForeground(Color.WHITE);
        this.add(prenumeLabel);

        JLabel cnpLabel = new JLabel("CNP: " + personInfo.getCnp());
        cnpLabel.setBounds(50, 150, 700, 50);
        cnpLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        cnpLabel.setForeground(Color.WHITE);
        this.add(cnpLabel);

        JLabel clasaLabel = new JLabel("Clasa: " + personInfo.getClasa());
        clasaLabel.setBounds(50, 200, 700, 50);
        clasaLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        clasaLabel.setForeground(Color.WHITE);
        this.add(clasaLabel);

        //add a scrollable list of books borrowed by the person
        //look in BorrowDatabase.csv
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(12, 64, 53));
        panel.setForeground(Color.WHITE);
        panel.setFont(new Font("Times New Roman", Font.BOLD
                , 20));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 250, 700, 200);
        //make the scrollpane have a border
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        //add books to scrollPane as buttons for each
        //read each line in ReturnDatabase.csv
        try (BufferedReader br = new BufferedReader(new FileReader("ReturnDatabase.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("@");
                String titlu = values[0];
                String id = values[1];
                String nume = values[2];
                String dataImprumut = values[3];
                String dataReturnare = values[4];
                //verific daca numele persoanei e acelasi cu cel din fisier
                if (nume.equals(personInfo.getNume() + " " + personInfo.getPrenume())) {
                    JLabel bookLabel = new JLabel("[RETURNAT] " + titlu + " " + id + " - " + dataImprumut + " - " + dataReturnare);
                    bookLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
                    bookLabel.setForeground(Color.WHITE);
                    panel.add(bookLabel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read each line in BorrowDatabase.csv
        try (BufferedReader br = new BufferedReader(new FileReader("BorrowDatabase.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("@");
                String titlu = values[0];
                String id = values[1];
                String nume = values[2];
                String dataImprumut = values[3];
                //verific daca numele persoanei e acelasi cu cel din fisier
                if (nume.equals(personInfo.getNume() + " " + personInfo.getPrenume())) {
                    JLabel bookLabel = new JLabel(titlu + " " + id + " - " + dataImprumut);
                    bookLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
                    bookLabel.setForeground(Color.WHITE);
                    panel.add(bookLabel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(scrollPane);
        this.setVisible(true);
    }
}
