package ru.stepanov.OpticaStore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.OpticaStore.Repositories.FramesCatalogRepository;
import ru.stepanov.OpticaStore.models.FramesCatalog;

import java.util.List;
import java.util.Optional;

@Service
public class FramesCatalogService {

    private final FramesCatalogRepository framesCatalogRepository;

    @Autowired
    public FramesCatalogService(FramesCatalogRepository framesCatalogRepository) {
        this.framesCatalogRepository = framesCatalogRepository;
    }

    public List<FramesCatalog> getAllFramesCatalog() {
        return framesCatalogRepository.findAll();
    }

    public FramesCatalog getFramesCatalogById(Integer id) {
        Optional<FramesCatalog> optionalFramesCatalog = framesCatalogRepository.findById(id);
        return optionalFramesCatalog.orElse(null);
    }

    public void addFramesCatalog(FramesCatalog framesCatalog) {
        framesCatalogRepository.save(framesCatalog);
    }

    public void updateFramesCatalog(Integer id, FramesCatalog framesCatalog) {
        Optional<FramesCatalog> optionalFramesCatalog = framesCatalogRepository.findById(id);
        if (optionalFramesCatalog.isPresent()) {
            FramesCatalog existingFramesCatalog = optionalFramesCatalog.get();
            // Update the existing frames catalog with the new data
            existingFramesCatalog.setName(framesCatalog.getName());
            existingFramesCatalog.setPrice(framesCatalog.getPrice());
            existingFramesCatalog.setStatus(framesCatalog.getStatus());
            // Then save the updated frames catalog
            framesCatalogRepository.save(existingFramesCatalog);
        } else {
            throw new RuntimeException("Frames Catalog not found with id: " + id);
        }
    }

    public void deleteFramesCatalog(Integer id) {
        framesCatalogRepository.deleteById(id);
    }
}

