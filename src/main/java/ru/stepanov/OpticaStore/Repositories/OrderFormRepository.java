package ru.stepanov.OpticaStore.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepanov.OpticaStore.models.OrderForm;

public interface OrderFormRepository extends JpaRepository<OrderForm, Long> {
}