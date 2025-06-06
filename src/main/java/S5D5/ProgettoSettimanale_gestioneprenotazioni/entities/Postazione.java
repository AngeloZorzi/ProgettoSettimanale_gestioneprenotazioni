package S5D5.ProgettoSettimanale_gestioneprenotazioni.entities;

import S5D5.ProgettoSettimanale_gestioneprenotazioni.enumerating.TipoPostazione;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codice;

    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo;

    private int numeroMassimoOccupanti;

    @ManyToOne
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioni;


}
