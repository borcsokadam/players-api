package src.models;

import enums.Skill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player_skills")
@Setter
@Getter
public class PlayerSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Skill skill;

    private Integer value;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
