package pl.driver.upgrade.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.driver.upgrade.model.Advice;

import java.util.List;

public interface AdviceRepository extends JpaRepository<Advice,Long> {

    @Query("select a from Advice a")
    List<Advice> findAllAdvices(Pageable page);
}
