package pl.driver.upgrade.service;

import org.springframework.data.domain.Sort;
import pl.driver.upgrade.model.Advice;

import java.util.List;

public interface AdviceService {
    Advice findById(Long id);
    Advice save(Advice advice);
    void delete(Long id);
    List<Advice> getAll(Integer size, Sort.Direction sort);
    List<Advice> getAllWithQuizzes(Integer size, Sort.Direction sort);
    Advice updateAdvice(Advice advice);
}
