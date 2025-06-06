package S5D5.ProgettoSettimanale_gestioneprenotazioni.repositories;

import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    List<Edificio> findByCitta(String citta);

}
