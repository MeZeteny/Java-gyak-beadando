package hu.nje.gyakbead.repository;

import hu.nje.gyakbead.entity.Gp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface GpRepository extends JpaRepository<Gp, LocalDate> {
}