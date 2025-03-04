import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ActiveLoansFrame extends JFrame {
    public ActiveLoansFrame(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        JLabel label = new JLabel("Imprumuturi active");
        label.setBounds(300, 50, 1000, 50);
        label.setFont(new Font("MV Boli", Font.BOLD, 40));
        this.add(label);

        //make a scrollable list of active loans
        JTextArea textArea = new JTextArea();
        textArea.setBounds(100, 100, 800, 300);
        textArea.setBackground(new Color(12, 64, 53));
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
        textArea.setEditable(false);
        //read from BorrowDatabase.csv
        try {
            BufferedReader reader = new BufferedReader(new FileReader("BorrowDatabase.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("@");
                textArea.append("'" + data[0] + "' " + data[1] + " " + data[2] + " " + data[3] + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(100, 100, 800, 300);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        this.add(scrollPane);

        this.setVisible(true);
    }
}
