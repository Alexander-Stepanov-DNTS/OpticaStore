package ru.stepanov.OpticaStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stepanov.OpticaStore.Services.OrderFormService;
import ru.stepanov.OpticaStore.models.OrderForm;

import javax.validation.Valid;

@Controller
@RequestMapping("/order-forms")
public class OrderFormController {

    private final OrderFormService orderFormService;

    @Autowired
    public OrderFormController(OrderFormService orderFormService) {
        this.orderFormService = orderFormService;
    }

    @GetMapping
    public String showAllOrderForms(Model model) {
        model.addAttribute("orderForms", orderFormService.getAllOrderForms());
        return "order-forms/order-form-list";
    }

    @GetMapping("/add")
    public String showAddOrderFormPage(Model model) {
        model.addAttribute("newOrderForm", new OrderForm());
        return "order-forms/create-orderForm";
    }

    @PostMapping
    public String addOrderForm(@ModelAttribute("newOrderForm") @Valid OrderForm newOrderForm,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "order-forms/create-orderForm";
        }
        orderFormService.addOrderForm(newOrderForm);
        redirectAttributes.addFlashAttribute("successMessage", "Order Form added successfully");
        return "redirect:/order-forms";
    }

    @GetMapping("/{id}/edit")
    public String showEditOrderFormPage(@PathVariable("id") Integer id, Model model) {
        OrderForm orderForm = orderFormService.getOrderFormById(id);
        if (orderForm != null) {
            model.addAttribute("orderForm", orderForm);
            return "order-forms/edit-orderForm"; // Убрано расширение .html
        }
        return "redirect:/order-forms";
    }

    @PatchMapping("/{id}")
    public String editOrderForm(@PathVariable("id") Integer id,
                                @ModelAttribute("orderForm") @Valid OrderForm orderForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "order-forms/edit-orderForm";
        }
        orderFormService.updateOrderForm(id, orderForm);
        redirectAttributes.addFlashAttribute("successMessage", "Order Form updated successfully");
        return "redirect:/order-forms";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrderForm(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        orderFormService.deleteOrderForm(id);
        redirectAttributes.addFlashAttribute("successMessage", "Order Form deleted successfully");
        return "redirect:/order-forms";
    }
}

