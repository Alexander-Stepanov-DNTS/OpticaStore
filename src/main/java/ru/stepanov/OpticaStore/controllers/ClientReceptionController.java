package ru.stepanov.OpticaStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stepanov.OpticaStore.DTO.ClientReceptionDTO;
import ru.stepanov.OpticaStore.Services.ClientReceptionService;
import ru.stepanov.OpticaStore.Services.ClientService;
import ru.stepanov.OpticaStore.Services.OrderFormService;
import ru.stepanov.OpticaStore.models.Client;
import ru.stepanov.OpticaStore.models.ClientReception;
import ru.stepanov.OpticaStore.models.OrderForm;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clientReceptions")
public class ClientReceptionController {

    private final ClientReceptionService clientReceptionService;

    private final ClientService clientService;

    private final OrderFormService orderFormService;

    @Autowired
    public ClientReceptionController(ClientReceptionService clientReceptionService, ClientService clientService, OrderFormService orderFormService) {
        this.clientReceptionService = clientReceptionService;
        this.clientService = clientService;
        this.orderFormService = orderFormService;
    }

    @GetMapping
    public String showAllClientReceptions(Model model) {
        List<ClientReception> list = clientReceptionService.getAllClientReceptions();
        List<ClientReceptionDTO> dtos = list.stream()
                .map(reception -> new ClientReceptionDTO(
                        reception.getId(),
                        reception.getDateAdded(),
                        reception.getLeftEyeExaminationResults(),
                        reception.getRightEyeExaminationResults(),
                        reception.getPrescriptionForRightLens(),
                        reception.getPrescriptionForLeftLens(),
                        reception.getClient().getId(),
                        (reception.getOrderForm() != null ? reception.getOrderForm().getId() : null)))
                .collect(Collectors.toList());
        model.addAttribute("clientReceptions", dtos);
        return "clientReceptions/clientReception-list.html";
    }

    @GetMapping("/add")
    public String showAddClientReceptionPage(Model model) {
        model.addAttribute("newClientReception", new ClientReceptionDTO());
        return "clientReceptions/create-clientReception.html";
    }

    @PostMapping
    public String addClientReception(@ModelAttribute("newClientReception") @Valid ClientReceptionDTO dto,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "clientReceptions/create-clientReception";
        }
        ClientReception newClientReception = new ClientReception();
        newClientReception.setDateAdded(dto.getDateAdded());
        newClientReception.setLeftEyeExaminationResults(dto.getLeftEyeExaminationResults());
        newClientReception.setRightEyeExaminationResults(dto.getRightEyeExaminationResults());
        newClientReception.setPrescriptionForRightLens(dto.getPrescriptionForRightLens());
        newClientReception.setPrescriptionForLeftLens(dto.getPrescriptionForLeftLens());

        // Fetching the existing client from the database
        Client existingClient = clientService.getClientById(dto.getClientId());
        if (existingClient == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid client ID provided.");
            return "clientReceptions/create-clientReception";
        }
        newClientReception.setClient(existingClient);

        // Handle OrderForm similarly if needed
        if (dto.getOrderFormId() != null) {
            OrderForm existingOrderForm = orderFormService.getOrderFormById(dto.getOrderFormId());
            if (existingOrderForm != null) {
                newClientReception.setOrderForm(existingOrderForm);
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Invalid order form ID provided.");
                return "clientReceptions/create-clientReception";
            }
        }

        clientReceptionService.addClientReception(newClientReception);
        redirectAttributes.addFlashAttribute("successMessage", "Client Reception added successfully");
        return "redirect:/clientReceptions";
    }

    @GetMapping("/{id}/edit")
    public String showEditClientReceptionPage(@PathVariable("id") Integer id, Model model) {
        ClientReception clientReception = clientReceptionService.getClientReceptionById(id);
        if (clientReception != null) {
            ClientReceptionDTO dto = new ClientReceptionDTO(
                    clientReception.getId(),
                    clientReception.getDateAdded(),
                    clientReception.getLeftEyeExaminationResults(),
                    clientReception.getRightEyeExaminationResults(),
                    clientReception.getPrescriptionForRightLens(),
                    clientReception.getPrescriptionForLeftLens(),
                    clientReception.getClient().getId(),
                    (clientReception.getOrderForm() != null ? clientReception.getOrderForm().getId() : null));
            model.addAttribute("clientReception", dto);
            return "clientReceptions/edit-clientReception";
        }
        return "redirect:/clientReceptions";
    }

    @PatchMapping("/{id}")
    public String editClientReception(@PathVariable("id") Integer id,
                                      @ModelAttribute("clientReception") @Valid ClientReceptionDTO dto,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "clientReceptions/edit-clientReception";
        }
        ClientReception clientReception = clientReceptionService.getClientReceptionById(id);
        if (clientReception != null) {
            clientReception.setDateAdded(dto.getDateAdded());
            clientReception.setLeftEyeExaminationResults(dto.getLeftEyeExaminationResults());
            clientReception.setRightEyeExaminationResults(dto.getRightEyeExaminationResults());
            clientReception.setPrescriptionForRightLens(dto.getPrescriptionForRightLens());
            clientReception.setPrescriptionForLeftLens(dto.getPrescriptionForLeftLens());

            // Fetch the existing client from the database instead of creating a new one
            Client existingClient = clientService.getClientById(dto.getClientId());
            if (existingClient == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Invalid client ID provided.");
                return "clientReceptions/edit-clientReception";
            }
            clientReception.setClient(existingClient);

            // Handling the order form similarly if an ID is provided
            if (dto.getOrderFormId() != null) {
                OrderForm existingOrderForm = orderFormService.getOrderFormById(dto.getOrderFormId());
                if (existingOrderForm != null) {
                    clientReception.setOrderForm(existingOrderForm);
                } else {
                    redirectAttributes.addFlashAttribute("errorMessage", "Invalid order form ID provided.");
                    return "clientReceptions/edit-clientReception";
                }
            } else {
                clientReception.setOrderForm(null);  // Clear the order form if no ID is provided
            }

            clientReceptionService.updateClientReception(id, clientReception);
            redirectAttributes.addFlashAttribute("successMessage", "Client Reception updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Client Reception not found.");
            return "redirect:/clientReceptions";
        }
        return "redirect:/clientReceptions";
    }

    @PostMapping("/{id}/delete")
    public String deleteClientReception(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        clientReceptionService.deleteClientReception(id);
        redirectAttributes.addFlashAttribute("successMessage", "Client Reception deleted successfully");
        return "redirect:/clientReceptions";
    }

}

