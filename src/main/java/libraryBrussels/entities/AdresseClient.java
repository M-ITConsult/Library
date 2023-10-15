package libraryBrussels.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
public class AdresseClient {
    public AdresseClient(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String rue;
    @Column
    private int numero;
    @Column
    private int cp;
    @Column
    private String ville;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
