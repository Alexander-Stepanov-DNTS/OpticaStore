package ru.stepanov.OpticaStore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepanov.OpticaStore.models.LensesCatalog;

public interface LensesCatalogRepository extends JpaRepository<LensesCatalog, Integer> {
}
