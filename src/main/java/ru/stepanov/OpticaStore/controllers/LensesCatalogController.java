package ru.stepanov.OpticaStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stepanov.OpticaStore.Services.LensesCatalogService;
import ru.stepanov.OpticaStore.models.LensesCatalog;

import javax.validation.Valid;

@Controller
@RequestMapping("/lenses-catalog")
public class LensesCatalogController {

    private final LensesCatalogService lensesCatalogService;

    @Autowired
    public LensesCatalogController(LensesCatalogService lensesCatalogService) {
        this.lensesCatalogService = lensesCatalogService;
    }

    @GetMapping
    public String showAllLensesCatalog(Model model) {
        model.addAttribute("lensesCatalog", lensesCatalogService.getAllLensesCatalog());
        return "lens/lensesCatalog-list";
    }

    @GetMapping("/add")
    public String showAddLensesCatalogPage(Model model) {
        model.addAttribute("newLensesCatalog", new LensesCatalog());
        return "lens/create-lensesCatalog";
    }

    @PostMapping
    public String addLensesCatalog(@ModelAttribute("newLensesCatalog") @Valid LensesCatalog newLensesCatalog,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "lens/create-lensesCatalog";
        }
        lensesCatalogService.addLensesCatalog(newLensesCatalog);
        redirectAttributes.addFlashAttribute("successMessage", "Lenses Catalog added successfully");
        return "redirect:/lenses-catalog";
    }

    @GetMapping("/{id}/edit")
    public String showEditLensesCatalogPage(@PathVariable("id") Integer id, Model model) {
        LensesCatalog lensesCatalog = lensesCatalogService.getLensesCatalogById(id);
        if (lensesCatalog != null) {
            model.addAttribute("lensesCatalog", lensesCatalog);
            return "lens/edit-lensesCatalog";
        }
        return "redirect:/lenses-catalog";
    }

    @PatchMapping("/{id}")
    public String editLensesCatalog(@PathVariable("id") Integer id,
                                    @ModelAttribute("lensesCatalog") @Valid LensesCatalog lensesCatalog,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "lens/edit-lensesCatalog";
        }
        lensesCatalogService.updateLensesCatalog(id, lensesCatalog);
        redirectAttributes.addFlashAttribute("successMessage", "Lenses Catalog updated successfully");
        return "redirect:/lenses-catalog";
    }

    @PostMapping("/{id}/delete")
    public String deleteLensesCatalog(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        lensesCatalogService.deleteLensesCatalog(id);
        redirectAttributes.addFlashAttribute("successMessage", "Lenses Catalog deleted successfully");
        return "redirect:/lenses-catalog";
    }
}
