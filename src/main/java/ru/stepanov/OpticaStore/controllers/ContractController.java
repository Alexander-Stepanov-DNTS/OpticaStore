package ru.stepanov.OpticaStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stepanov.OpticaStore.DTO.ContractDTO;
import ru.stepanov.OpticaStore.Services.ClientService;
import ru.stepanov.OpticaStore.Services.ContractService;
import ru.stepanov.OpticaStore.Services.OrderFormService;
import ru.stepanov.OpticaStore.models.Contract;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;
    private final ClientService clientService;
    private final OrderFormService orderFormService;

    @Autowired
    public ContractController(ContractService contractService, ClientService clientService, OrderFormService orderFormService) {
        this.contractService = contractService;
        this.clientService = clientService;
        this.orderFormService = orderFormService;
    }

    @GetMapping
    public String showAllContracts(Model model) {
        List<Contract> contracts = contractService.getAllContracts();
        List<ContractDTO> contractDTOs = contracts.stream()
                .map(contract -> new ContractDTO(contract.getId(),
                        contract.getClient().getId(),
                        contract.getOrderForm() != null ? contract.getOrderForm().getId() : null,
                        contract.getContractConclusionDate()))
                .collect(Collectors.toList());
        model.addAttribute("contracts", contractDTOs);
        return "contract/contract-list"; // Путь к HTML странице со списком договоров
    }

    @GetMapping("/add")
    public String showAddContractPage(Model model) {
        model.addAttribute("newContract", new ContractDTO()); // Используем DTO для нового договора
        return "contract/create-contract"; // Путь к HTML странице с формой для добавления нового договора
    }

    @PostMapping
    public String addContract(@ModelAttribute("newContract") @Valid ContractDTO newContractDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contract/create-contract"; // Путь к HTML странице с формой для добавления нового договора
        }
        Contract newContract = new Contract(
                clientService.getClientById(newContractDTO.getClientId()),
                orderFormService.getOrderFormById(newContractDTO.getOrderFormId()),
                newContractDTO.getContractConclusionDate());
        contractService.addContract(newContract);
        redirectAttributes.addFlashAttribute("successMessage", "Contract added successfully");
        return "redirect:/contracts";
    }

    @GetMapping("/{id}/edit")
    public String showEditContractPage(@PathVariable("id") Integer id, Model model) {
        Contract contract = contractService.getContractById(id);
        if (contract != null) {
            ContractDTO contractDTO = new ContractDTO(
                    contract.getId(),
                    contract.getClient().getId(),
                    contract.getOrderForm() != null ? contract.getOrderForm().getId() : null,
                    contract.getContractConclusionDate());
            model.addAttribute("contract", contractDTO);
            return "contract/edit-contract"; // Путь к HTML странице с формой для редактирования договора
        }
        return "redirect:/contracts";
    }

    @PatchMapping("/{id}")
    public String editContract(@PathVariable("id") Integer id,
                               @ModelAttribute("contract") @Valid ContractDTO contractDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contract/edit-contract"; // Путь к HTML странице с формой для редактирования договора
        }
        Contract contract = contractService.getContractById(id);
        if (contract != null) {
            contract.setClient(clientService.getClientById(contractDTO.getClientId()));
            contract.setOrderForm(orderFormService.getOrderFormById(contractDTO.getOrderFormId()));
            contract.setContractConclusionDate(contractDTO.getContractConclusionDate());
            contractService.updateContract(id, contract);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Contract updated successfully");
        return "redirect:/contracts";
    }

    @PostMapping("/{id}/delete")
    public String deleteContract(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        contractService.deleteContract(id);
        redirectAttributes.addFlashAttribute("successMessage", "Contract deleted successfully");
        return "redirect:/contracts";
    }
}

