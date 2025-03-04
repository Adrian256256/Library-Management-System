import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class StatisticsFrame extends JFrame {
    public StatisticsFrame(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        this.setSize(900, 600);
        this.getContentPane().setBackground(new Color(12,64,53));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Statistics");
        this.setLocationRelativeTo(null);
        //make window not resizable
        this.setResizable(false);
        this.setLayout(null);
        //search bar pentru a cauta o data
        JTextField searchField = new JTextField();
        searchField.setBounds(50, 50, 200, 50);
        searchField.setFont(new Font("MV Boli", Font.BOLD, 20));
        this.add(searchField);
        //button pentru a cauta o data
        JButton searchButton = new JButton("Cauta");
        searchButton.setBounds(300, 50, 100, 50);
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("MV Boli", Font.BOLD, 15));
        this.add(searchButton);
        //label pentru a afisa rezultatul cautarii
        JLabel searchResult = new JLabel();
        searchResult.setBounds(50, 100, 700, 50);
        searchResult.setFont(new Font("MV Boli", Font.BOLD, 20));
        searchResult.setForeground(Color.WHITE);
        this.add(searchResult);
        //label pentru a afisa numarul de carti imprumutate la data respectiva
        JLabel borrowedBooksLabel = new JLabel("Numarul total de carti imprumutate: ");
        borrowedBooksLabel.setBounds(50, 150, 700, 50);
        borrowedBooksLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        borrowedBooksLabel.setForeground(Color.WHITE);
        this.add(borrowedBooksLabel);
        //label pentru a afisa numarul de carti returnate la data respectiva
        JLabel returnedBooksLabel = new JLabel("Numarul total de carti returnate: ");
        returnedBooksLabel.setBounds(50, 200, 700, 50);
        returnedBooksLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        returnedBooksLabel.setForeground(Color.WHITE);
        this.add(returnedBooksLabel);
        //label pentru a afisa numarul de persoane care au imprumutat/returnat carti la data respectiva
        JLabel peopleLabel = new JLabel("Numarul total de persoane care au imprumutat/returnat carti: ");
        peopleLabel.setBounds(50, 250, 700, 50);
        peopleLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        peopleLabel.setForeground(Color.WHITE);
        this.add(peopleLabel);
        //label pentru nr persoane clase primare
        JLabel primaryLabel = new JLabel("Numarul total de persoane din clasele primare: ");
        primaryLabel.setBounds(50, 300, 700, 50);
        primaryLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        primaryLabel.setForeground(Color.WHITE);
        this.add(primaryLabel);
        //label pentru nr persoane clase gimnaziale
        JLabel gymnasiumLabel = new JLabel("Numarul total de persoane din clasele gimnaziale: ");
        gymnasiumLabel.setBounds(50, 350, 700, 50);
        gymnasiumLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        gymnasiumLabel.setForeground(Color.WHITE);
        this.add(gymnasiumLabel);
        //label pentru nr persoane profesori
        JLabel teacherLabel = new JLabel("Numarul total de profesori: ");
        teacherLabel.setBounds(50, 400, 700, 50);
        teacherLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        teacherLabel.setForeground(Color.WHITE);
        this.add(teacherLabel);

        //actiune pentru butonul de cautare
        searchButton.addActionListener(e -> {
            //cautam in ReturnDatabase.csv si BorrowDatabase.csv
            //daca data cautata se gaseste in ReturnDatabase.csv
            //afisam numarul de carti returnate la data respectiva
            //daca data cautata se gaseste in BorrowDatabase.csv
            //afisam numarul de carti imprumutate la data respectiva
            //daca data cautata nu se gaseste in niciunul din fisiere
            //afisam un mesaj corespunzator
            try {
                //citim data introdusa de utilizator
                String data = searchField.getText();
                //cautam in ReturnDatabase.csv
                int returnedBooks = 0;
                //cautam in BorrowDatabase.csv
                int borrowedBooks = 0;
                //numar de persoane care au imprumutat/returnat carti la data respectiva
                int people = 0;
                //array de persoane care au imprumutat/returnat carti la data respectiva
                ArrayList<String> peopleList = new ArrayList<>();
                //caut in ReturnDatabase.csv
                try (BufferedReader br = new BufferedReader(new FileReader("ReturnDatabase.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split("@");
                        String borrowDate = values[3];
                        if (borrowDate.equals(data)) {
                            borrowedBooks++;
                            peopleList.add(values[2]);
                        }
                        String returnDate = values[4];
                        if (returnDate.equals(data)) {
                            returnedBooks++;
                            peopleList.add(values[2]);
                        }
                    }
                }
                //caut in BorrowDatabase.csv
                try (BufferedReader br = new BufferedReader(new FileReader("BorrowDatabase.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split("@");
                        String borrowDate = values[3];
                        if (borrowDate.equals(data)) {
                            borrowedBooks++;
                            peopleList.add(values[2]);
                        }
                    }
                }
                //elimin persoanele duplicate din peopleList
                for (int i = 0; i < peopleList.size(); i++) {
                    for (int j = i + 1; j < peopleList.size(); j++) {
                        if (peopleList.get(i).equals(peopleList.get(j))) {
                            peopleList.remove(j);
                            j--;
                        }
                    }
                }
                people = peopleList.size();
                //afisam rezultatul cautarii
                searchResult.setText("Rezultatul cautarii pentru data " + data + ":");
                //afisam numarul de carti imprumutate la data respectiva
                borrowedBooksLabel.setText("Numarul total de carti imprumutate la data de " + data + ": " + borrowedBooks);
                //afisam numarul de carti returnate la data respectiva
                returnedBooksLabel.setText("Numarul total de carti returnate la data de " + data + ": " + returnedBooks);
                //afisam numarul de persoane care au imprumutat/returnat carti la data respectiva
                peopleLabel.setText("Total persoane la data de " + data + ": " + people);
                //caut in lista personInfos cate persoane sunt din clasele primare, gimnaziale si cate sunt profesori
                int primary = 0;
                int gymnasium = 0;
                int teacher = 0;




                for (PersonInfo personInfo : personInfos) {
                    //pt fiecare persoana din personInfos
                    for (String person : peopleList) {
                        //daca numele persoanei din personInfos e acelasi cu cel din peopleList
                        //scot spatiile goale din personInfo
                        String nume = person.split(" ")[0];
                        //prenume is the rest of the string
                        String prenume = person.substring(nume.length() + 1);
                        if (personInfo.getNume().equals(nume) && personInfo.getPrenume().equals(prenume)) {
                            //daca persoana e din clasele primare
                            //daca clasa incepe cu P sau incepe cu cifele 1-4, e clasa primara
                            //print la numele persoanei si clasa
                            //daca scrie PROF, e profesor
                            if (personInfo.getClasa().contains("Prof")) {
                                teacher++;
                                System.out.println(personInfo.getNume() + " " + personInfo.getPrenume() + " " + personInfo.getClasa());
                            } else
                            if (personInfo.getClasa().charAt(0) == 'P' || personInfo.getClasa().charAt(0) == '1' || personInfo.getClasa().charAt(0) == '2' || personInfo.getClasa().charAt(0) == '3' || personInfo.getClasa().charAt(0) == '4') {
                                primary++;
                            } else
                            //daca invepe cu cifrele 5-8, e clasa gimnaziala
                            if (personInfo.getClasa().charAt(0) == '5' || personInfo.getClasa().charAt(0) == '6' || personInfo.getClasa().charAt(0) == '7' || personInfo.getClasa().charAt(0) == '8') {
                                gymnasium++;
                            }
                        }
                    }
                }
                //afisam numarul de persoane din clasele primare
                primaryLabel.setText("Numarul persoane primar " + data + ": " + primary);
                //afisam numarul de persoane din clasele gimnaziale
                gymnasiumLabel.setText("Numarul persoane gimnaziu " + data + ": " + gymnasium);
                //afisam numarul de profesori
                teacherLabel.setText("Numarul profesori " + data + ": " + teacher);
                //refresh the frame
                this.revalidate();
            } catch (Exception ex) {
                searchResult.setText("Data cautata nu exista in baza de date!");
                borrowedBooksLabel.setText("Numarul total de carti imprumutate la data respectiva: ");
                returnedBooksLabel.setText("Numarul total de carti returnate la data respectiva: ");
            }
        });
        this.setVisible(true);
    }
}
