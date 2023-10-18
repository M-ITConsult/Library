package libraryBrussels.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nom;
    @Column
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;

    public Auteur(){}

}
