import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final String COMMA_DELIMITER = ";";

    public static void main(String[] args) {
        //citirea datelor din fisier listaCarti.csv
        //coloana 3 -> autor
        //coloana 4 -> titlu
        //coloana 2 -> numar inventar
        ArrayList<Book> books = new ArrayList<Book>();
        //citesc din fisier CSV
        //array aux pt nr inventar carti consecutive
        ArrayList<Integer> numereInventar = new ArrayList<Integer>();
        String autor = "";
        String titlu = "";

        try (BufferedReader br = new BufferedReader(new FileReader("listaCarti.csv"))) {
            String line;
            //skip first line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                if (values.length == 0) {
                    break;
                }
                autor = values[3];
                titlu = values[4];
                //daca numele autorului e gol, scriu***
                if (autor.equals("")) {
                    autor = "***";
                }
                //caut daca autorul si titlul exista deja in array
                boolean found = false;
                for (Book book : books) {
                    if (book.getAuthor().equals(autor) && book.getTitle().equals(titlu)) {
                        book.numereInventar.add(Integer.parseInt(values[2]));
                        found = true;
                        break;
                    }
                    //daca numele autorului incepe cu *
                    if (autor.charAt(0) == '*' && book.getTitle().equals(titlu)) {
                        book.numereInventar.add(Integer.parseInt(values[2]));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    //daca nu exista, creez o carte noua
                    Book book = new Book(autor, titlu,
                            new ArrayList<Integer>(Arrays.asList(Integer.parseInt(values[2]))));
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //cartea curenta
        try (BufferedReader br = new BufferedReader(new FileReader("listaCarti2.csv"))) {
            String line;
            //skip first line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                autor = values[2];
                titlu = values[3];
                //caut daca autorul si titlul exista deja in array
                boolean found = false;
                for (Book book : books) {
                    if (book.getAuthor().equals(autor) && book.getTitle().equals(titlu)) {
                        book.numereInventar.add(Integer.parseInt(values[1]));
                        found = true;
                        break;
                    }
                    //daca numele autorului incepe cu *
                    if (autor.charAt(0) == '*' && book.getTitle().equals(titlu)) {
                        book.numereInventar.add(Integer.parseInt(values[1]));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    //daca nu exista, creez o carte noua
                    Book book = new Book(autor, titlu,
                            new ArrayList<Integer>(Arrays.asList(Integer.parseInt(values[1]))));
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<PersonInfo> personInfos = new ArrayList<PersonInfo>();
        //citirea datelor din fisier persoane.csv
        //coloana 1 -> nume
        //coloana 3 -> prenume
        //coloana 5 -> CNP
        //coloana 6 -> clasa
        try (BufferedReader br = new BufferedReader(new FileReader("cititori.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                String nume = values[1];
                String prenume = values[3];
                String cnp = values[5];
                String clasa = values[6];
                //remove whitespaces from beginning and end
                nume = nume.trim();
                prenume = prenume.trim();
                cnp = cnp.trim();
                clasa = clasa.trim();



                PersonInfo personInfo = new PersonInfo(nume, prenume, cnp, clasa);
                personInfos.add(personInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainFrame mainFrame = new MainFrame(books, personInfos);


    }
}

