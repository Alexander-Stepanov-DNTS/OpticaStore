package ru.stepanov.OpticaStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stepanov.OpticaStore.DTO.DocumentDTO;
import ru.stepanov.OpticaStore.Services.ClientService;
import ru.stepanov.OpticaStore.Services.DocumentService;
import ru.stepanov.OpticaStore.models.Client;
import ru.stepanov.OpticaStore.models.Document;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final ClientService clientService;

    @Autowired
    public DocumentController(DocumentService documentService, ClientService clientService) {
        this.documentService = documentService;
        this.clientService = clientService;
    }

    @GetMapping
    public String showAllDocuments(Model model) {
        List<Document> documents = documentService.getAllDocuments();
        List<DocumentDTO> documentDTOs = documents.stream()
                .map(doc -> new DocumentDTO(doc.getId(), doc.getClient().getId(), doc.getDocType(), doc.getSeries(), doc.getNumber()))
                .collect(Collectors.toList());
        model.addAttribute("documents", documentDTOs);
        return "documents/document-list"; // Путь к HTML странице со списком документов
    }

    @GetMapping("/add")
    public String showAddDocumentForm(Model model) {
        model.addAttribute("document", new DocumentDTO()); // Используем DTO
        List<Client> clients = clientService.findClientsWithoutDocuments();
        model.addAttribute("clients", clients);
        return "documents/create-document"; // Путь к HTML странице с формой для добавления нового документа
    }

    @PostMapping
    public String addDocument(@ModelAttribute("document") @Valid DocumentDTO documentDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "documents/create-document";
        }
        System.out.println("TESTING");
        Document document = new Document(clientService.getClientById(documentDTO.getClientId()),
                documentDTO.getDocType(),
                documentDTO.getSeries(),
                documentDTO.getNumber());
        documentService.addDocument(document);
        redirectAttributes.addFlashAttribute("successMessage", "Document added successfully");
        return "redirect:/documents";
    }

    @GetMapping("/{id}")
    public String showEditDocumentForm(@PathVariable("id") Integer id, Model model) {
        Document document = documentService.getDocumentById(id);
        List<Client> clients = clientService.findClientsWithoutDocuments();
        Client client = clientService.getClientById(document.getClient().getId());
        model.addAttribute("clients", clients);
        if (document != null) {
            DocumentDTO documentDTO = new DocumentDTO(document.getId(), document.getClient().getId(),
                    document.getDocType(), document.getSeries(),
                    document.getNumber());
            model.addAttribute("document", documentDTO);
            model.addAttribute("clients", clients);
            model.addAttribute("client", client);
            return "documents/edit-document";
        }
        return "redirect:/documents";
    }

    @PatchMapping("/{id}")
    public String editDocument(@PathVariable("id") Integer id,
                               @ModelAttribute("document") @Valid DocumentDTO documentDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "documents/edit-document";
        }
        Document document = documentService.getDocumentById(id);
        if (document != null) {
            document.setClient(clientService.getClientById(documentDTO.getClientId()));
            document.setDocType(documentDTO.getDocType());
            document.setSeries(documentDTO.getSeries());
            document.setNumber(documentDTO.getNumber());
            documentService.updateDocument(id, document);
            redirectAttributes.addFlashAttribute("successMessage", "Document updated successfully");
        }
        return "redirect:/documents";
    }

    @PostMapping("/{id}/delete")
    public String deleteDocument(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        documentService.deleteDocument(id);
        redirectAttributes.addFlashAttribute("successMessage", "Document deleted successfully");
        return "redirect:/documents";
    }
}

