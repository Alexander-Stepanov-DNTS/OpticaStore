package ru.stepanov.OpticaStore.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepanov.OpticaStore.models.Contract;

import java.util.Date;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findByClientId(Long id);
    List<Contract> findByContractConclusionDateBetween(Date startDate, Date endDate);

}