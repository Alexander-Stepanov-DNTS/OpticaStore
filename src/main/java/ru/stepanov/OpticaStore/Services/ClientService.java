package ru.stepanov.OpticaStore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.OpticaStore.Repositories.ClientRepository;
import ru.stepanov.OpticaStore.models.Client;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> findClientsWithoutDocuments() {
        return clientRepository.findClientsWithoutDocuments();
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public Client getClientById(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public void updateClient(Integer id, Client client) {
        Client existingClient = getClientById(id);
        if (existingClient != null) {
            existingClient.setLastName(client.getLastName());
            existingClient.setFirstName(client.getFirstName());
            existingClient.setMiddleName(client.getMiddleName());
            existingClient.setGender(client.getGender());
            existingClient.setPhone(client.getPhone());
            existingClient.setBirthDate(client.getBirthDate());
            // Установите другие поля по необходимости
            clientRepository.save(existingClient);
        }
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}


