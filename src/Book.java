public class Book {
    // CAMPI
    private String isbn, titolo, autore;
    private int annoDiPubblicazione, nEdizione, nPagine;
    private Membro inPrestito;

    // COSTRUTTORI
    public Book (String isbn, String titolo, String autore, int annoDiPubblicazione, int nEdizione, int nPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.nEdizione = nEdizione;
        this.nPagine = nPagine;
    }

    // METODI
    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public int getnEdizione() {
        return nEdizione;
    }

    public Membro getInPrestito() {
        return inPrestito;
    }

    public void setInPrestito(Membro m) {
        inPrestito = m;
    }

    public void setReturned() {
        inPrestito = null;
    }

    public String toString() {
        return "Titolo: " + titolo + "\nISBN: " + isbn + "\nAutore: " + autore + "\nAnno di pubblicazione: " + annoDiPubblicazione + "\nEdizione numero: " + nEdizione + "\nNumero di pagine: " + nPagine;
    }
}
