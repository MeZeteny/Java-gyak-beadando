package hu.nje.gyakbead.repository;

import hu.nje.gyakbead.entity.Eredmeny;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EredmenyRepository extends JpaRepository<Eredmeny, Long> {
}