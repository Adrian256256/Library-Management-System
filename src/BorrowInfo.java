import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BorrowInfo extends JFrame {
    public BorrowInfo(ArrayList<Book> books, ArrayList<PersonInfo> personInfos, String entry) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Returneaza carte");
        //parse entry
        String[] values = entry.split("@");
        String titlu = values[0];
        String id = values[1];
        String nume = values[2];
        String dataImprumut = values[3];

        //create labels
        JLabel titleLabel = new JLabel("Titlu: " + titlu);
        titleLabel.setBounds(50, 50, 200, 30);
        this.add(titleLabel);
        JLabel idLabel = new JLabel("ID: " + id);
        idLabel.setBounds(50, 100, 200, 30);
        this.add(idLabel);
        JLabel numeLabel = new JLabel("Nume: " + nume);
        numeLabel.setBounds(50, 150, 200, 30);
        this.add(numeLabel);
        JLabel dataImprumutLabel = new JLabel("Data imprumut: " + dataImprumut);
        dataImprumutLabel.setBounds(50, 200, 200, 30);
        this.add(dataImprumutLabel);
        //create return button
        JButton returnButton = new JButton("Returneaza");
        returnButton.setBounds(50, 250, 200, 30);
        this.add(returnButton);
        //create cancel button
        JButton cancelButton = new JButton("Anuleaza");
        cancelButton.setBounds(50, 300, 200, 30);
        this.add(cancelButton);
        //return button action
        returnButton.addActionListener(e -> {
            //search in file BorrowDatabase.csv for the entry and remove it
            try {
                BufferedReader br = new BufferedReader(new FileReader("BorrowDatabase.csv"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("BorrowDatabaseTemp.csv"));
                //remove the line from "BorrowDatabase.csv"
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.equals(entry)) {
                        bw.write(line + "\n");
                    }
                }
                br.close();
                bw.close();
                //transfer the content from "BorrowDatabaseTemp.csv" to "BorrowDatabase.csv"
                br = new BufferedReader(new FileReader("BorrowDatabaseTemp.csv"));
                bw = new BufferedWriter(new FileWriter("BorrowDatabase.csv"));
                while ((line = br.readLine()) != null) {
                    bw.write(line + "\n");
                }
                br.close();
                bw.close();
                //delete "BorrowDatabaseTemp.csv"
                if (!new File("BorrowDatabaseTemp.csv").delete()) {
                    System.out.println("Failed to delete BorrowDatabaseTemp.csv");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //scriu in fisierul ReturnDatabase linia scoasa din BorrowDatabase.csv + @ + data returnarii
            try {
                FileWriter fileWriter = new FileWriter("ReturnDatabase.csv", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(entry + "@" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "\n");
                bufferedWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Carte returnata cu succes!");
            this.dispose();
        });
        //cancel button action
        cancelButton.addActionListener(e -> {
            this.dispose();
        });
        this.setVisible(true);

    }
}
