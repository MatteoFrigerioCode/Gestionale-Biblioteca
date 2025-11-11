/* Gestisce le richieste del bibliotecario, fornendo una serie di azioni possibili
ad esempio visualizzare l'elenco di libri, aggiungerne di nuovi, ecc */
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
void main() {
    Library libreria = new Library();
    Scanner in = new Scanner(System.in);
    int userInput;

    do {
        userInput = showPanel(in);
        switch (userInput) {
            case 0:
                System.out.println("Arrivederci!");
                break;

            case 1:
                addBook(in, libreria);
                break;

            case 2:
                addMembro(in, libreria);
                break;
            case 3:
                borrowBookManager(in, libreria);
                break;

            case 4:
                bringBookBack(in, libreria);
                break;

            case 5:
                visualizzaLibriInPrestito(in, libreria);
                break;

            case 6:
               findLibroInPrestito(in, libreria);
                break;

            case 7:
                libreria.visualizzaLibri();
                break;

            case 8:
                libreria.visualizzaMembri();
                break;

            default:
                System.out.println("Indice inserito non valido!");
        }
    } while(userInput != 0);
}

public static int showPanel(Scanner in) {
    System.out.println("--- GESTIONE BIBLIOTECA ---");
    System.out.println("1. Aggiungi un libro");
    System.out.println("2. Aggiungi un membro");
    System.out.println("3. Presta un libro");
    System.out.println("4. Restituisci un libro");
    System.out.println("5. Cerca un membro e vedi i libri che ha preso in prestito");
    System.out.println("6. Cerca un libro e vedi chi lo ha preso in prestito");
    System.out.println("7. Visualizza l'elenco completo dei libri con le relative informazioni ISBN");
    System.out.println("8. Visualizza l'elenco completo dei membri con le relative informazioni");
    System.out.println("0. Esci");

    int userInput;
    while (true) {
        System.out.println("Inserire un indice valido: ");
        try {
            userInput = in.nextInt();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Input non valido!");
            // Pulisce il buffer
            in.nextLine();
        }
    }
    // Pulisce il buffer
    in.nextLine();
    return userInput;
}

// Aggiunge un nuovo libro alla libreria
public static void addBook (Scanner in, Library libreria) {
    System.out.println("Inserire il codice ISBN del libro: ");
    String isbn = in.nextLine();
    // Controlla se il libro esiste già nell'elenco
    if (libreria.getElencoLibri().containsKey(isbn)) {
        System.out.println("ERRORE: questo libro esiste già nella libreria!");
        return;
    }

    System.out.println("Inserire il titolo del libro: ");
    String titolo = in.nextLine();
    System.out.println("Inserire l'autore del libro: ");
    String autore = in.nextLine();

    int annoDiPubblicazione = leggiInteroPositivo(in, "Inserire l'anno di pubblicazione: ");
    int nEdizione = leggiInteroPositivo(in, "Inserire il numero dell'edizione: ");
    int nPagine = leggiInteroPositivo(in, "Inserire il numero di pagine: ");

    // Aggiunge il libro alla libreria
    libreria.addBook(new Book(isbn, titolo, autore, annoDiPubblicazione, nEdizione, nPagine));
    System.out.println("Libro aggiunto correttamente");
}

// Segna in libreria che il libro è stato preso in prestito e segna da quale membro è stato preso
public static void setBookBorrowed(Membro m, Book b) {
    b.setInPrestito(m);
    m.addLibroInPrestito(b);
    System.out.println("Libro consegnato correttamente!");
}

// Riceve un membro e un libro in ingresso. Se sono validi, fa le operazioni necessarie per restituire un libro
public static void bringBookBack(Scanner in, Library libreria) {
    Membro m = getMembro(in, libreria);
    // Utente valido
    if (m != null) {
        Book b = getBook(in, libreria);
        // Libro valido
        if (b != null) {
            setBookReturned(m, b);
        }
    }
}

// Segna in libreria che il libro è stato restituito. Se il libro non era stato preso in prestito da quel membro segnala un errore
public static void setBookReturned(Membro m, Book b) {
    // Fa il double check (sia sulla lista dei libri in prestito dell'utente, sia sul campo inPrestito del libro)
    if (m.getLibriInPrestito().contains(b)) {
        if (b.getInPrestito() == m) {
            m.removeLibroInPrestito(b);
            b.setReturned();
            System.out.println("Libro restituito con successo!");
        }
        else {
            System.out.println("ERRORE: il libro non era stato dato in prestito a questo utente!");
        }
    }
    else {
        System.out.println("ERRORE: il membro non aveva preso questo libro in prestito!");
    }
}

// Gestisce tutta la procedura per prendere un libro in prestito
public static void borrowBookManager(Scanner in, Library libreria) {
    Membro m = getMembro(in, libreria);
    // Utente registrato
    if (m != null) {
        Book b = getBook(in, libreria);
        // Il libro richiesto è presente
        if (b != null) {
            // Il libro non è stato preso da nessuno
            if (b.getInPrestito() == null) {
                setBookBorrowed(m, b);
            }
            // Il libro esiste ma è attualmente in prestito a un altro utente
            else {
                System.out.println("ERRORE: Il libro è già stato preso da un altro membro!");
            }
        }
    }
}

// Chiede i dati di un libro e restituisce l'oggetto Book corrispondente
public static Book getBook(Scanner in, Library libreria) {
    System.out.println("Inserire un codice ISBN: ");
    String isbn = in.nextLine();
    // ISBN valido
    if (libreria.getElencoLibri().containsKey(isbn)) {
        return libreria.getElencoLibri().get(isbn);
    }
    // ISBN non valido
    System.out.println("Il libro inserito non è presente in libreria!");
    return null;
}

public static void findLibroInPrestito(Scanner in, Library libreria) {
    Book b = getBook(in, libreria);
    if (b != null) {
        Membro m = b.getInPrestito();
        if (m == null) {
            System.out.println("Il libro non è attualmente in prestito a qualcuno!");
            return;
        }
        // Esegue il double check per verificare che sia in prestito proprio a quel membro
        else if (m.getLibriInPrestito().contains(b)) {
            System.out.println("Il libro è in prestito a: ");
            System.out.println(m);
            return;
        }
        System.out.println("ERRORE: i dati del libro in prestito e dell'utente che lo avrebbe preso in prestito non coincidono. Sistema corrotto!");
    }
}

public static void visualizzaLibriInPrestito(Scanner in, Library libreria) {
    Membro m = getMembro(in, libreria);
    if (m != null) {
        HashSet<Book> libriInPrestito = m.getLibriInPrestito();
        if (libriInPrestito.isEmpty()) {
            System.out.println("Il membro non ha libri in prestito!");
        }
        else {
            int i = 0;
            for (Book b : libriInPrestito) {
                i++;
                System.out.println("Libro " + i + ": ");
                System.out.println(b);
            }
        }
    }
}

public static void addMembro(Scanner in, Library libreria) {
    System.out.println("Inserire il nome: ");
    String nome = in.nextLine();

    System.out.println("Inserire il cognome: ");
    String cognome = in.nextLine();

    LocalDate dataDiNascita = getDataDiNascita(in);

    Membro m = new Membro(nome, cognome, dataDiNascita);
    libreria.addMembro(m);
    System.out.println("Nuovo membro aggiunto con successo!");
    System.out.printf("Il tuo ID è %s\n", m.getnId());
}

// Restituisce il riferimento a un Membro registrato o null se non è registrato
public static Membro getMembro(Scanner in, Library libreria) {
    System.out.println("Inserire id utente: ");
    String id = in.nextLine();
    // L'utente è registrato correttamente
    if (libreria.getElencoMembri().containsKey(id)) {
        return libreria.getElencoMembri().get(id);
    }
    // L'utente non è registrato
    System.out.println("Utente non registrato!");
    return null;
}

// Chiede all'utente un numero finché lui non ne inserisce uno positivo
private static int leggiInteroPositivo (Scanner in, String prompt) {
    int n;
    while (true) {
        System.out.println(prompt);
        try {
            n = in.nextInt();
            if (n <= 0) {
                throw new RuntimeException();
            }
            break;
        }
        catch (InputMismatchException e) {
            System.out.println("Errore: la stringa inserita non è un intero!");
            // Svuota il buffer
            in.nextLine();
        }
        catch (RuntimeException e) {
            System.out.println("Errore: l'intero inserito deve essere maggiore di 0!");
            // Svuota il buffer
            in.nextLine();
        }
    }
    // Svuota il buffer da un eventuale carattere /n che sarebbe presente se fosse stata chiamata una nextInt() prima
    in.nextLine();
    return n;
}

// Chiede una data di nascita valida e crea l'oggetto corrispondente
private static LocalDate getDataDiNascita(Scanner in) {

    while (true) {
        // Chiede il giorno di nascita
        int giornoDiNascita = leggiInteroPositivo(in, "Inserire il giorno di nascita");
        // Chiede il mese di nascita
        int meseDiNascita = leggiInteroPositivo(in, "Inserire il mese di nascita");
        // Chiede l'anno di nascita
        int annoDiNascita = leggiInteroPositivo(in, "Inserire l'anno di nascita");

        try {
            return LocalDate.of(annoDiNascita, meseDiNascita, giornoDiNascita);
        } catch (DateTimeException e) {
            System.out.println("ERRORE: Data inserita non valida");
        }
    }
}