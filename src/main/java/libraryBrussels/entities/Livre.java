package libraryBrussels.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long ISBN;
    @Column
    private String titre;
    @Column
    private LocalDate dateAchat;

    @ManyToMany
    @JoinTable(name = "Emprunt",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name="ISBN"))
    private Set<Client> clients = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;
    public Livre(){}
}
