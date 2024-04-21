package ru.stepanov.OpticaStore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.OpticaStore.Repositories.ClientRepository;
import ru.stepanov.OpticaStore.Repositories.DocumentRepository;
import ru.stepanov.OpticaStore.models.Client;
import ru.stepanov.OpticaStore.models.Document;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, ClientRepository clientRepository) {
        this.documentRepository = documentRepository;
        this.clientRepository = clientRepository;
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(Integer id) {
        Optional<Document> document = documentRepository.findById(id);
        return document.orElse(null);
    }

    public Document getDocumentByClient(Integer id) {
        Optional<Document> document = documentRepository.findByClientId(id);
        return document.orElse(null);
    }

    public void addDocument(Document document) {
        documentRepository.save(document);
    }

    public void updateDocument(Integer id, Document updatedDocument) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Document with id " + id + " does not exist"));

        document.setDocType(updatedDocument.getDocType());
        document.setSeries(updatedDocument.getSeries());
        document.setNumber(updatedDocument.getNumber());
        documentRepository.save(document);
    }

    public void deleteDocument(Integer id) {
        documentRepository.deleteById(id);
    }
}

