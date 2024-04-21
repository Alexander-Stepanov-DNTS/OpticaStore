package ru.stepanov.OpticaStore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.OpticaStore.Repositories.OrderFormRepository;
import ru.stepanov.OpticaStore.models.OrderForm;

import java.util.List;

@Service
public class OrderFormService {

    private final OrderFormRepository orderFormRepository;

    @Autowired
    public OrderFormService(OrderFormRepository orderFormRepository) {
        this.orderFormRepository = orderFormRepository;
    }

    public List<OrderForm> getAllOrderForms() {
        return orderFormRepository.findAll();
    }

    public OrderForm getOrderFormById(Integer id) {
        return orderFormRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public void addOrderForm(OrderForm orderForm) {
        orderFormRepository.save(orderForm);
    }

    public void updateOrderForm(Integer id, OrderForm updatedOrderForm) {
        OrderForm existingOrderForm = orderFormRepository.findById(Long.valueOf(id)).orElse(null);
        if (existingOrderForm != null) {
            // Обновляем только те поля заказа наряда, которые разрешено изменять
            existingOrderForm.setFrame(updatedOrderForm.getFrame());
            existingOrderForm.setLens(updatedOrderForm.getLens());
            existingOrderForm.setDescriptionOfRequiredWorks(updatedOrderForm.getDescriptionOfRequiredWorks());
            existingOrderForm.setClientReception(updatedOrderForm.getClientReception());
            orderFormRepository.save(existingOrderForm);
        }
    }

    public void deleteOrderForm(Integer id) {
        orderFormRepository.deleteById(Long.valueOf(id));
    }
}

