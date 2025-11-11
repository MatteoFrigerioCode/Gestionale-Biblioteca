import java.time.LocalDate;
import java.util.HashSet;

public class Membro {
    // CAMPI
    private static int id;
    private String nome, cognome, nId;
    private LocalDate dataDiNascita;
    private HashSet<Book> libriInPrestito = new HashSet<>();

    // COSTRUTTORI
    public Membro(String nome, String cognome, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        // Incrementa il numero di utenti
        id++;
        nId = String.valueOf(id);
    }

    // METODI
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getnId() {
        return nId;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public boolean addLibroInPrestito(Book b) {
        return libriInPrestito.add(b);
    }

    public boolean removeLibroInPrestito(Book b) {
        return libriInPrestito.remove(b);
    }

    public HashSet<Book> getLibriInPrestito() {
        return libriInPrestito;
    }

    public String toString() {
        return "Nome: " + nome + "\nCognome: " + cognome + "\nID: " + nId + "\nData di nascita: " + dataDiNascita.toString();
    }
}
