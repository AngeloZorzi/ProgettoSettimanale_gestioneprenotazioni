package S5D5.ProgettoSettimanale_gestioneprenotazioni;


import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Edificio;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Postazione;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Prenotazione;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Utente;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories.EdificioRepository;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories.PostazioneRepository;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories.PrenotazioneRepository;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private Edificio edificio1;

    @Autowired
    private Edificio edificio2;

    @Autowired
    private Postazione postazione1;

    @Autowired
    private Postazione postazione2;

    @Autowired
    private Postazione postazione3;

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        if (edificioRepository.count() == 0) {
            edificioRepository.save(edificio1);
            edificioRepository.save(edificio2);
        }
        if (postazioneRepository.count() == 0) {
            postazioneRepository.save(postazione1);
            postazioneRepository.save(postazione2);
            postazioneRepository.save(postazione3);
        }

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Visualizza edifici e postazioni");
            System.out.println("2. Registra nuovo utente");
            System.out.println("3. Prenota postazione");
            System.out.println("4. Controlla disponibilità postazione");
            System.out.println("5. Visualizza prenotazioni utente");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            int scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1:
                    mostraEdificiEPostazioni();
                    break;
                case 2:
                    registraUtente();
                    break;
                case 3:
                    prenotaPostazione();
                    break;
                case 4:
                    controllaDisponibilita();
                    break;
                case 5:
                    visualizzaPrenotazioniUtente();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Programma Terminato");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    private void mostraEdificiEPostazioni() {
        System.out.println("Edifici disponibili:");
        edificioRepository.findAll().forEach(e -> {
            System.out.println(e.getNome() + " - " + e.getIndirizzo() + ", " + e.getCitta());
            System.out.println("Postazioni:");
            e.getPostazioni().forEach(p -> {
                System.out.println("  Codice: " + p.getCodice() + ", Tipo: " + p.getTipo() + ", Max occupanti: " + p.getNumeroMassimoOccupanti());
            });
        });
    }

    private void registraUtente() {
        System.out.print("Inserisci username: ");
        String username = scanner.nextLine();
        if (utenteRepository.existsById(username)) {
            System.out.println("Username già esistente!");
            return;
        }
        System.out.print("Inserisci nome completo: ");
        String nomeCompleto = scanner.nextLine();
        System.out.print("Inserisci email: ");
        String email = scanner.nextLine();

        Utente u = new Utente();
        u.setUsername(username);
        u.setNomeCompleto(nomeCompleto);
        u.setEmail(email);

        utenteRepository.save(u);
        System.out.println("Utente registrato correttamente.");
    }

    private void prenotaPostazione() {
        System.out.print("Inserisci username: ");
        String username = scanner.nextLine();
        Utente utente = utenteRepository.findById(username).orElse(null);
        if (utente == null) {
            System.out.println("Utente non trovato, registra prima.");
            return;
        }

        System.out.print("Inserisci codice postazione: ");
        String codicePostazione = scanner.nextLine();
        Postazione postazione = postazioneRepository.findByCodice(codicePostazione);
        if (postazione == null) {
            System.out.println("Postazione non trovata.");
            return;
        }

        System.out.print("Inserisci data prenotazione (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();
        LocalDate dataPrenotazione;
        try {
            dataPrenotazione = LocalDate.parse(dataStr);
        } catch (Exception e) {
            System.out.println("Data non valida.");
            return;
        }

        boolean giaPrenotata = prenotazioneRepository.existsByPostazioneAndData(postazione, dataPrenotazione);
        if (giaPrenotata) {
            System.out.println("Postazione già prenotata per quella data.");
            return;
        }

        boolean utenteHaPrenotazione = prenotazioneRepository.existsByUtenteAndData(utente, dataPrenotazione);
        if (utenteHaPrenotazione) {
            System.out.println("Hai già una prenotazione per quella data.");
            return;
        }

        Prenotazione p = new Prenotazione();
        p.setUtente(utente);
        p.setPostazione(postazione);
        p.setData(dataPrenotazione);

        prenotazioneRepository.save(p);
        System.out.println("Prenotazione effettuata con successo.");
    }

    private void controllaDisponibilita() {
        System.out.print("Inserisci codice postazione: ");
        String codicePostazione = scanner.nextLine();
        Postazione postazione = postazioneRepository.findByCodice(codicePostazione);
        if (postazione == null) {
            System.out.println("Postazione non trovata.");
            return;
        }

        System.out.print("Inserisci data da controllare (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();
        LocalDate data;
        try {
            data = LocalDate.parse(dataStr);
        } catch (Exception e) {
            System.out.println("Data non valida.");
            return;
        }

        boolean prenotata = prenotazioneRepository.existsByPostazioneAndData(postazione, data);
        if (prenotata) {
            System.out.println("La postazione è già prenotata in quella data.");
        } else {
            System.out.println("La postazione è disponibile in quella data.");
        }
    }

    private void visualizzaPrenotazioniUtente() {
        System.out.print("Inserisci username utente: ");
        String username = scanner.nextLine();
        Utente utente = utenteRepository.findById(username).orElse(null);
        if (utente == null) {
            System.out.println("Utente non trovato.");
            return;
        }

        System.out.println("Prenotazioni per " + utente.getUsername() + ":");
        prenotazioneRepository.findByUtente(utente).forEach(p -> {
            System.out.println("Postazione: " + p.getPostazione().getCodice() + ", Data: " + p.getData());
        });
    }



}
