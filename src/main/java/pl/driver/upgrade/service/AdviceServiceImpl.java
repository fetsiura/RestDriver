package pl.driver.upgrade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.driver.upgrade.model.Advice;
import pl.driver.upgrade.model.Quiz;
import pl.driver.upgrade.repository.AdviceRepository;
import pl.driver.upgrade.repository.QuizRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdviceServiceImpl implements AdviceService{

    private static final int PAGE_SIZE = 5;

    private final AdviceRepository adviceRepository;
    private final QuizRepository quizRepository;
    
    @Override
    public Advice findById(Long id) {
        log.info("In AdviceServiceImpl getById {}",id);
        return adviceRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Advice save(Advice advice) {
        log.info("In AdviceServiceImpl save {}",advice);
       return adviceRepository.save(advice);
    }

    @Override
    public Advice updateAdvice(Advice advice) {
        log.info("In AdviceServiceImpl update {}",advice);
        return adviceRepository.save(advice);
    }


    @Override
    public void delete(Long id) {
        log.info("In AdviceServiceImpl delete {}",id);
        adviceRepository.deleteById(id);
    }

    @Override
    public List<Advice> getAll(Integer page, Sort.Direction sort) {
        log.info("In AdviceServiceImpl getAll, page {}", page);
//        return adviceRepository.findAllAdvices(PageRequest.of(page,PAGE_SIZE, Sort.by(Sort.Order.asc("description"),Sort.Order.asc("created"))));
        return adviceRepository.findAllAdvices(PageRequest.of(page,PAGE_SIZE,
                Sort.by(sort, "created")));
    }

    @Override
    public List<Advice> getAllWithQuizzes(Integer page, Sort.Direction sort) {
        log.info("In AdviceServiceImpl getAllWithQuizzes, page {}", page);
        List<Advice> allAdvices = adviceRepository.findAllAdvices(PageRequest.of(page, PAGE_SIZE, Sort.by(sort,"created")));
        List<Long> ids = allAdvices.stream()
                .map(Advice::getId)
                .collect(Collectors.toList());
        List<Quiz> quizzes = quizRepository.findAllByAdviceIdIn(ids);
        allAdvices.forEach(advice -> advice.setQuiz(extractQuizzes(quizzes, advice.getId())));
        return allAdvices;
    }

    private List<Quiz> extractQuizzes(List<Quiz> quizzes, Long id) {
        return quizzes.stream()
                .filter(quiz -> quiz.getAdviceId() == id)
                .collect(Collectors.toList());
    }
}

