package S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories;

import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Postazione;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.enumerating.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    @Query("SELECT p FROM Postazione p WHERE p.tipo = :tipo AND p.edificio.citta = :citta")
    List<Postazione> trovaPerTipoECitta(TipoPostazione tipo, String citta);
}
