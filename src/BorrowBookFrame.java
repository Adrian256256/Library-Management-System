import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BorrowBookFrame extends JFrame {
    public BorrowBookFrame(ArrayList<Book> books, ArrayList<PersonInfo> personInfos) {
        //set frame properties
        this.setSize(500, 900);
        this.getContentPane().setBackground(new Color(12,64,53));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Imprumutare carte");
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("logo.png").getImage());
        this.setLayout(null);
        //add a search box to type name of book
        JTextField searchBar = new JTextField();
        searchBar.setBounds(50, 50, 400, 50);
        searchBar.setPreferredSize(new Dimension(200, 50));
        searchBar.setFont(new Font("Times New Roman", Font.BOLD
                , 20));
        this.add(searchBar);
        //search button
        JButton searchButton = new JButton("Cautare carte");
        searchButton.setBounds(50, 100, 400, 50);
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        //add search button to frame
        this.add(searchButton);
        //add selectable list of books, scrollable
        JComboBox<String> bookList = new JComboBox<>();
        bookList.setBounds(50, 150, 400, 50);
        bookList.setFont(new Font("Times New Roman", Font.BOLD, 20));
//        for (Book book : books) {
//            bookList.addItem(book.getTitle());
//        }
        this.add(bookList);
        //add a search button listener
        searchButton.addActionListener(e -> {
            String search = searchBar.getText();
            bookList.removeAllItems();
            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(search.toLowerCase()) || book.getAuthor().toLowerCase().contains(search.toLowerCase()) || book.getInventoryNumbers().toString().contains(search)) {
                    bookList.addItem(book.getTitle());
                }
            }
        });
        //daca am selectat o carte, adaugam un Jcombobox pentru nr inventar
        JComboBox<Integer> inventoryNumbers = new JComboBox<>();
        inventoryNumbers.setBounds(50, 200, 400, 50);
        inventoryNumbers.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.add(inventoryNumbers);
        //add a listener for the bookList
        bookList.addActionListener(e -> {
            inventoryNumbers.removeAllItems();
            for (Book book : books) {
                if (book.getTitle().equals(bookList.getSelectedItem())) {
                    for (int inventoryNumber : book.getInventoryNumbers()) {
                        inventoryNumbers.addItem(inventoryNumber);
                        //verific daca numarul de inventar este imprumutat
                        //citesc din BorrowDatabase.csv
                        try {
                            BufferedReader br = new BufferedReader(new FileReader("BorrowDatabase.csv"));
                            String line;
                            while ((line = br.readLine()) != null) {
                                String[] values = line.split("@");
                                if (values[1].equals(String.valueOf(inventoryNumber))) {
                                    inventoryNumbers.removeItem(inventoryNumber);
                                }
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });
        //add a selection for the person
        JComboBox<String> personList = new JComboBox<>();
        personList.setBounds(50, 250, 400, 50);
        personList.setFont(new Font("Times New Roman", Font.BOLD, 20));
//        for (PersonInfo personInfo : personInfos) {
//            personList.addItem(personInfo.getNume() + " " + personInfo.getPrenume());
//        }
        this.add(personList);
        //add a search button for people
        JButton searchPeopleButton = new JButton("Cautare persoana");
        searchPeopleButton.setBounds(50, 300, 400, 50);
        searchPeopleButton.setFocusable(false);
        searchPeopleButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        this.add(searchPeopleButton);
        //add a listener for the searchPeopleButton
        searchPeopleButton.addActionListener(e -> {
            String search = searchBar.getText();
            //ignorare diacritice
            search = removeDiacritics(search);
            personList.removeAllItems();
            for (PersonInfo personInfo : personInfos) {
                String nume = removeDiacritics(personInfo.getNume().toLowerCase());
                String prenume = removeDiacritics(personInfo.getPrenume().toLowerCase());
                if (nume.contains(search.toLowerCase()) || prenume.contains(search.toLowerCase()) || personInfo.getCnp().contains(search)) {
                    personList.addItem(personInfo.getNume() + " " + personInfo.getPrenume());
                }
            }
        });
        //add a borrow button
        JButton borrowButton = new JButton("Imprumuta carte");
        borrowButton.setBounds(50, 350, 400, 50);
        borrowButton.setFocusable(false);
        borrowButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        this.add(borrowButton);
        //add a listener for the borrowButton
        borrowButton.addActionListener(e -> {
            //show message of asking if the person is sure
            int dialogButton = JOptionPane.YES_NO_OPTION;
            //daca un combobox nu are nici o selectie, nu se poate face imprumut
            if (bookList.getSelectedItem() == null || personList.getSelectedItem() == null || inventoryNumbers.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Nu ati selectat o carte sau o persoana sau un nr de inventar");
                return;
            }
            String bookTitle = bookList.getSelectedItem().toString();
            String personName = personList.getSelectedItem().toString();
            String inventoryNumber = inventoryNumbers.getSelectedItem().toString();

            int dialogResult = JOptionPane.showConfirmDialog(null, "Sunteti sigur ca doriti sa imprumutati cartea '" + bookTitle + "' cu nr inventar " + inventoryNumber + " persoanei " + personName + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                //scriu in fisierul BorrowDatbase numele cartii, id, numele persoanei, cnp si data imprumutului
                try {
                    FileWriter fileWriter = new FileWriter("BorrowDatabase.csv", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(bookTitle + "@" + inventoryNumber + "@" + personName + "@" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "\n");
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Cartea '" + bookTitle + "' cu nr inventar " + inventoryNumber + " a fost imprumutata persoanei " + personName);
                //refresh the personList, bookList and inventoryNumbers
                bookList.removeAllItems();
                inventoryNumbers.removeAllItems();
                personList.removeAllItems();

            }
        });






        this.setVisible(true);
    }

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

