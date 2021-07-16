package pl.driver.upgrade.model.dto;

import pl.driver.upgrade.model.Advice;

import java.util.List;
import java.util.stream.Collectors;

public class AdviceDtoMapper {

    private AdviceDtoMapper(){}
    public static List<AdviceDto> mapToAdviceDtos(List<Advice> advices) {
        return advices.stream()
                .map(advice -> mapToAdviceDto(advice))
                .collect(Collectors.toList());
    }

    public static AdviceDto mapToAdviceDto(Advice advice) {
        return AdviceDto.builder()
                .id(advice.getId())
                .content(advice.getContent())
                .description(advice.getDescription())
                .created(advice.getCreated())
                .build();
    }
}
