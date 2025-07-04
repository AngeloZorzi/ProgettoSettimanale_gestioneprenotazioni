package S5D5.ProgettoSettimanale_gestioneprenotazioni.configuration;


import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Edificio;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.entities.Postazione;
import S5D5.ProgettoSettimanale_gestioneprenotazioni.enumerating.TipoPostazione;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Edificio edificio1() {
        return new Edificio(
                null,
                "Palazzo Sbirindul",
                "Via Papa Giovanni",
                "Brescia"
        );
    }

    @Bean
    public Edificio edificio2() {
        return new Edificio(
                null,
                "Terrazza Supremo",
                "Foresta Dei Caduti",
                "Glasgorundul"
        );
    }

    @Bean
    public Postazione postazione1(Edificio edificio1) {
        return new Postazione(
                null,
                "P001",
                "Sgabuzzino con cuscino",
                TipoPostazione.PRIVATO,
                1,
                edificio1,
                null
        );
    }

    @Bean
    public Postazione postazione2(Edificio edificio1) {
        return new Postazione(
                null,
                "P002",
                "Stanza dello Spirito e del Tempo",
                TipoPostazione.OPENSPACE,
                4,
                edificio1,
                null
        );
    }

    @Bean
    public Postazione postazione3(Edificio edificio2) {
        return new Postazione(
                null,
                "P003",
                "Sala Riunioni",
                TipoPostazione.SALA_RIUNIONI,
                10,
                edificio2,
                null
        );
    }
}
