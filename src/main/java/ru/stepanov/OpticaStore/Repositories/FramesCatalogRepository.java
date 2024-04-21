package ru.stepanov.OpticaStore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepanov.OpticaStore.models.FramesCatalog;

public interface FramesCatalogRepository extends JpaRepository<FramesCatalog, Integer> {
}