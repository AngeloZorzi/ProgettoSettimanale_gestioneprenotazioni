package S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories;

import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, String> {
    Optional<Utente> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
