package ru.stepanov.OpticaStore.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stepanov.OpticaStore.models.Document;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByDocType(String docType);
    List<Document> findBySeriesAndNumber(Integer series, Integer number);
    Optional<Document> findByClientId(Integer clientId);  // Использование client_id для поиска
}