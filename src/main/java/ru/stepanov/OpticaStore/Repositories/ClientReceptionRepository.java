package ru.stepanov.OpticaStore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepanov.OpticaStore.models.ClientReception;

import java.util.Date;
import java.util.List;

public interface ClientReceptionRepository extends JpaRepository<ClientReception, Integer> {
    List<ClientReception> findByClientId(Long id);

}
