import java.util.ArrayList;

public class Book {
    //array de numere inventar
    ArrayList<Integer> numereInventar = new ArrayList<Integer>();
    String autor;
    String titlu;

    //contructor
    public Book(String autor, String titlu, ArrayList<Integer> numereInventar) {
        this.autor = autor;
        this.titlu = titlu;
        this.numereInventar = numereInventar;
    }

    //constructor fara parametri
    public Book() {
    }

    @Override
    public String toString() {
        return "Autor: " + autor + ", Titlu: " + titlu;
    }

    //getter pentru autor
    public String getAuthor() {
        return autor;
    }

    //getter pentru numereInventar
    public ArrayList<Integer> getInventoryNumbers() {
        return numereInventar;
    }

    //getter pentru titlu
    public String getTitle() {
        return titlu;
    }
}
