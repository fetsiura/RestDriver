package pl.driver.upgrade.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long adviceId;
    private String description;
    private Integer answerKey;
    @OneToMany
    @JoinColumn(name = "question_id",updatable = false, insertable = true)
    private List<Question> questions;

}
