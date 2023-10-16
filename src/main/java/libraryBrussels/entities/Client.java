package libraryBrussels.entities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.processing.Find;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String numeroNational;
    @Column
    private String nom;
    @Column
    private String prenom;
    @ManyToMany(mappedBy = "clients")
    private Set<Livre> livres = new HashSet<>();
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private AdresseClient adresseClient;

}
