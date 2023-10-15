package libraryBrussels.entities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.processing.Find;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
public class Client {
    public Client(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long numeroNational;
    @Column
    private String nom;
    @Column
    private String prenom;
    @OneToOne(mappedBy = "client")
    private AdresseClient adresseClient;

}
