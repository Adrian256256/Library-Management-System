import java.util.ArrayList;

public class PersonInfo {
    String nume;
    String prenume;
    String cnp;
    String clasa;
    //array de carti imprumutate
    ArrayList<Book> cartiImprumutate = new ArrayList<Book>();

    public PersonInfo(String nume, String prenume, String cnp, String clasa) {
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.clasa = clasa;
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " - " + cnp + " - " + clasa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getClasa() {
        return clasa;
    }

    public void setClasa(String clasa) {
        this.clasa = clasa;
    }
}

class BorBook extends Book {
    String dataImprumut;
    String dataReturnare;
}