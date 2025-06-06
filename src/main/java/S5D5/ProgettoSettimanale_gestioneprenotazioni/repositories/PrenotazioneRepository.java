package S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories;

import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Postazione;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Prenotazione;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {
    Optional<Prenotazione> findByPostazioneAndData(Postazione postazione, LocalDate data);

    Optional<Prenotazione> findByUtenteAndData(Utente utente, LocalDate data);

    List<Prenotazione> findByUtenteAndDataAfter(Utente utente, LocalDate data);
    boolean existsByPostazioneAndData(Postazione postazione, LocalDate data);

    boolean existsByUtenteAndData(Utente utente, LocalDate data);

    List<Prenotazione> findByUtente(Utente utente);
}
