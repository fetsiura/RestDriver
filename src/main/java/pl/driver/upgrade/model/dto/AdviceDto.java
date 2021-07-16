package pl.driver.upgrade.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdviceDto {
    private Long id;
    private String description;
    private String content;
    private LocalDateTime created=LocalDateTime.now();
}
