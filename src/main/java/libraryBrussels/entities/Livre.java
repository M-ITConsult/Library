package libraryBrussels.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Setter
@Getter
@AllArgsConstructor
@Embeddable
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long ISBN;
    @Column
    private String titre;
    @Column
    private long auteur_id;
    @Column
    private LocalDate dateAchat;
    @OneToMany(mappedBy = "livre")
    private List<Auteur> auteurList;
    public Livre(){}
}
