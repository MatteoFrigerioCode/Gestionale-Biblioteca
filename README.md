# Gestionale Biblioteca in java

Questo è un programma gestionale a riga di comando (CLI) scritto in Java puro. È un progetto portfolio per dimostrare i principi dell'OOP e l'uso efficiente delle strutture dati

---

# Funzionalità principali

Il programma permette ad un "bibliotecario" di gestire l'intero processo di vita dei libri e dei membri all'interno della sua biblioteca, potendo utilizzare funzioni come:
**Aggiungere libri:** Registra nuovi libri nel catalogo, tramite la ricezione di codici ISBN (su cui viene fatto un controllo per verificare che non appartengono già all'archivio).
**Aggiungere membri:** Registra nuovi membri, fornendo ad ognuno un codice identificativo (ID) univoco.
**Gestire prestiti:** Associa un libro ad un membro, gestendo i casi limite (es: viene richiesto un libro che è già in prestito ad un altro membro).
**Gestire Restituzioni:** Gestisce la restituzione di un libro, con un double check per assicurarsi che i dati non siano stati corrotti.
**Ricercare Dati:** Trovare i libri in prestito ad un membro specifico e trovare a chi è in prestito un libro specifico, con controlli sulla validità del libro e del membro inseriti.
**Visualizzare l'intero archivio:** Visualizzare l'intero elenco di libri in possesso della libreria e l'intero elenco di membri registrati.

# Design tecnico e punti chiave

Il focus del progetto non era l'interfaccia, bensì la **robustezza** e **l'efficienza** del programma stesso.
**Efficienza$O(1)$:** Il cuore del sistema si basa su due `HashMap` (una per i membri mappando un ID, l'altra per i libri mappando un ISBN) per garantire che ogni azione di ricerca, aggiunta o rimozione sia **istantanea ($O(1)$)**, anche con milioni di voci.
**Robustezza dell'Input** Tutta l'iterazione con l'utente è gestita in maniera robusta. Il codice gestisce `InputMismatchException` (se l'utente scrive una stringa non numerica), `DateTimeException` (se l'utente scrive una data non valida), ID non trovati e numeri non positivi,**gestione del buffer dello `Scanner`** (gestendo il bug `nextInt()`/`nextLine()`.
**Logica anti-corruzione** Il sistema utilizza un design bidirezionale. Ogni operazione di prestito e restituzione esegue un **doppio controllo** per prevenire la desincronizzazione dei dati.
**Design modulare** Il codice è separato in classi e metodi statici "helper", ognuno con una singola responsabilità.
