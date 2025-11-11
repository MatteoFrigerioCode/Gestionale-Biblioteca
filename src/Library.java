import java.util.HashMap;
public class Library {
    // CAMPI
    private HashMap<String, Book> elencoLibri = new HashMap<>();
    private HashMap<String, Membro> elencoMembri = new HashMap<>();

    // COSTRUTTORI
    public Library() {
    }

    // METODI
    public HashMap<String, Book> getElencoLibri() {
        return elencoLibri;
    }

    public HashMap<String, Membro> getElencoMembri() {
        return elencoMembri;
    }

    // Aggiunge un libro alla biblioteca (key = isbn, value = libro)
    public void addBook(Book b) {
        elencoLibri.put(b.getIsbn(), b);
    }

    // Aggiunge un membro alla biblioteca (key = cognome nome, value = membro)
    public void addMembro(Membro m) {
        elencoMembri.put(m.getnId(), m);
    }

    // Visualizza tutti i membri registarti
    public void visualizzaMembri() {
        if (!elencoMembri.isEmpty()) {
            for (Membro m : elencoMembri.values()) {
                System.out.println(m);
            }
        }
        else {
            System.out.println("L'elenco dei membri è vuoto!");
        }
    }

    // Visualizza tutti i membri registarti
    public void visualizzaLibri() {
        if (!elencoLibri.isEmpty()) {
            for (Book b : elencoLibri.values()) {
                System.out.println(b);
            }
        }
        else {
            System.out.println("L'elenco dei libri è vuoto!");
        }
    }
}
