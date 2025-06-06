package S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories;

import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, String> {
}
