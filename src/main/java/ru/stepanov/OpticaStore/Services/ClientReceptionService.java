package ru.stepanov.OpticaStore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.OpticaStore.Repositories.ClientReceptionRepository;
import ru.stepanov.OpticaStore.models.ClientReception;

import java.util.List;
import java.util.Optional;

@Service
public class ClientReceptionService {

    private final ClientReceptionRepository clientReceptionRepository;

    @Autowired
    public ClientReceptionService(ClientReceptionRepository clientReceptionRepository) {
        this.clientReceptionRepository = clientReceptionRepository;
    }

    public List<ClientReception> getAllClientReceptions() {
        return clientReceptionRepository.findAll();
    }

    public ClientReception getClientReceptionById(Integer id) {
        return clientReceptionRepository.findById(id).orElse(null);
    }

    public void addClientReception(ClientReception clientReception) {
        clientReceptionRepository.save(clientReception);
    }

    public void updateClientReception(Integer id, ClientReception clientReception) {
        Optional<ClientReception> optionalClientReception = clientReceptionRepository.findById(id);
        if (optionalClientReception.isPresent()) {
            ClientReception existingClientReception = optionalClientReception.get();
            // Update the existing clientReception with the new data
            existingClientReception.setLeftEyeExaminationResults(clientReception.getLeftEyeExaminationResults());
            existingClientReception.setRightEyeExaminationResults(clientReception.getRightEyeExaminationResults());
            existingClientReception.setPrescriptionForRightLens(clientReception.getPrescriptionForRightLens());
            existingClientReception.setPrescriptionForLeftLens(clientReception.getPrescriptionForLeftLens());
            // Then save the updated clientReception
            clientReceptionRepository.save(existingClientReception);
        } else {
            throw new RuntimeException("Client Reception not found with id: " + id);
        }
    }

    public void deleteClientReception(Integer id) {
        clientReceptionRepository.deleteById(id);
    }
}
