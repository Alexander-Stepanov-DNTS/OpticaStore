package ru.stepanov.OpticaStore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.OpticaStore.Repositories.ContractRepository;
import ru.stepanov.OpticaStore.models.Contract;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getContractById(Integer id) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        return optionalContract.orElse(null);
    }

    public void addContract(Contract contract) {
        contractRepository.save(contract);
    }

    public void updateContract(Integer id, Contract contract) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract existingContract = optionalContract.get();
            // Update the existing contract with the new data
            existingContract.setClient(contract.getClient());
            existingContract.setOrderForm(contract.getOrderForm());
            existingContract.setContractConclusionDate(contract.getContractConclusionDate());
            // Then save the updated contract
            contractRepository.save(existingContract);
        } else {
            throw new RuntimeException("Contract not found with id: " + id);
        }
    }

    public void deleteContract(Integer id) {
        contractRepository.deleteById(id);
    }
}

