package ru.stepanov.OpticaStore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.stepanov.OpticaStore.models.Client;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByLastName(String lastName);

    List<Client> findByFirstName(String firstName);

    List<Client> findByMiddleName(String middleName);

    List<Client> findByPhone(Integer phone);

    Optional<Client> findById(Integer id);

    List<Client> findByBirthDateBetween(Date startDate, Date endDate);

    @Query("SELECT c FROM Client c LEFT JOIN Document d ON c.id = d.client.id WHERE d.id IS NULL")
    List<Client> findClientsWithoutDocuments();

}