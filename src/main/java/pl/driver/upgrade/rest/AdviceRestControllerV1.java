package pl.driver.upgrade.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.driver.upgrade.model.Advice;
import pl.driver.upgrade.model.dto.AdviceDto;
import pl.driver.upgrade.model.dto.AdviceDtoMapper;
import pl.driver.upgrade.service.AdviceService;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdviceRestControllerV1 {

    private final AdviceService adviceService;

    @GetMapping("/advices")
    public List<AdviceDto> getAdvices(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page!=null && page > 0 ? page : 1;
        Sort.Direction sortDirection =  sort !=null ? sort : Sort.Direction.ASC;
        return AdviceDtoMapper.mapToAdviceDtos(adviceService.getAll(pageNumber-1, sortDirection));
    }

    @GetMapping("/advices/quizzes")
    public List<Advice> getAdvicesWithQuizzes(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page!=null && page > 0 ? page : 1;
        Sort.Direction sortDirection =  sort !=null ? sort : Sort.Direction.ASC;
        return adviceService.getAllWithQuizzes(pageNumber-1,sortDirection);
    }


    @GetMapping("/advices/{id}")
    public Advice getAdvice(@PathVariable("id") Long id){

        return this.adviceService.findById(id);

    }


    @PostMapping("/advices")
    public Advice saveAdvice(@RequestBody Advice advice){
        return adviceService.save(advice);
    }


    @Transactional
    @PutMapping("/advices")
    public Advice updateAdvice(@RequestBody Advice advice){

        Advice adviceEdited = adviceService.findById(advice.getId());
        adviceEdited.setContent(advice.getContent());
        adviceEdited.setDescription(advice.getDescription());
        adviceEdited.setQuiz(advice.getQuiz());
        return adviceEdited;
    }

    @DeleteMapping("/advices/{id}")
    public void deleteAdvice(@PathVariable("id") Long id){
        adviceService.delete(id);
    }


}
