package pl.driver.upgrade.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String content;
    private LocalDateTime created=LocalDateTime.now();
    @OneToMany
    @JoinColumn(name = "adviceId",updatable = false, insertable = true)
    private List<Quiz> quiz;

}
