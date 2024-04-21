package ru.stepanov.OpticaStore.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stepanov.OpticaStore.Services.ClientService;
import ru.stepanov.OpticaStore.models.Client;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String showAllClients(Model model) {
        List<Client> list = clientService.getAllClients();
        for(int i = 0; i != list.size(); i++){
            System.out.println(list.get(i));
        }

        model.addAttribute("clients", list);

        return "clients/client-list.html";
    }

    @GetMapping("/add")
    public String showAddClientPage(Model model) {
        model.addAttribute("client", new Client());
        return "clients/create-client";
    }

    @PostMapping
    public String addClient(@ModelAttribute("client") @Valid Client newClient,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "clients/create-client";
        }
        clientService.addClient(newClient);
        redirectAttributes.addFlashAttribute("successMessage", "Client added successfully");
        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String showEditClientPage(@PathVariable("id") Integer id, Model model) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            model.addAttribute("client", client);
            return "clients/edit-client";
        }
        return "redirect:/clients";
    }

    @PatchMapping("/{id}")
    public String editClient(@PathVariable("id") Integer id,
                             @ModelAttribute("client") @Valid Client client,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "clients/edit-client";
        }
        clientService.updateClient(id, client);
        redirectAttributes.addFlashAttribute("successMessage", "Client updated successfully");
        return "redirect:/clients";
    }

    @PostMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        clientService.deleteClient(id);
        redirectAttributes.addFlashAttribute("successMessage", "Client deleted successfully");
        return "redirect:/clients";
    }
}
