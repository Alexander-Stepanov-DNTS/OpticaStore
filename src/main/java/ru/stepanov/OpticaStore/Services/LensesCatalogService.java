package ru.stepanov.OpticaStore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.OpticaStore.Repositories.LensesCatalogRepository;
import ru.stepanov.OpticaStore.models.LensesCatalog;

import java.util.List;
import java.util.Optional;

@Service
public class LensesCatalogService {

    private final LensesCatalogRepository lensesCatalogRepository;

    @Autowired
    public LensesCatalogService(LensesCatalogRepository lensesCatalogRepository) {
        this.lensesCatalogRepository = lensesCatalogRepository;
    }

    public List<LensesCatalog> getAllLensesCatalog() {
        return lensesCatalogRepository.findAll();
    }

    public LensesCatalog getLensesCatalogById(Integer id) {
        return lensesCatalogRepository.findById(id).orElse(null);
    }

    public void addLensesCatalog(LensesCatalog lensesCatalog) {
        lensesCatalogRepository.save(lensesCatalog);
    }

    public void updateLensesCatalog(Integer id, LensesCatalog lensesCatalog) {
        Optional<LensesCatalog> optionalLensesCatalog = lensesCatalogRepository.findById(id);
        if (optionalLensesCatalog.isPresent()) {
            LensesCatalog existingLensesCatalog = optionalLensesCatalog.get();
            // Update the existing lenses catalog with the new data
            existingLensesCatalog.setName(lensesCatalog.getName());
            existingLensesCatalog.setPrice(lensesCatalog.getPrice());
            existingLensesCatalog.setStatus(lensesCatalog.getStatus());
            // Then save the updated lenses catalog
            lensesCatalogRepository.save(existingLensesCatalog);
        } else {
            throw new RuntimeException("Lenses Catalog not found with id: " + id);
        }
    }

    public void deleteLensesCatalog(Integer id) {
        lensesCatalogRepository.deleteById(id);
    }
}

