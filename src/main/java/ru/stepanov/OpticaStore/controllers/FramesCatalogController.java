package ru.stepanov.OpticaStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stepanov.OpticaStore.Services.FramesCatalogService;
import ru.stepanov.OpticaStore.models.FramesCatalog;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/frames-catalog")
public class FramesCatalogController {

    private final FramesCatalogService framesCatalogService;

    @Autowired
    public FramesCatalogController(FramesCatalogService framesCatalogService) {
        this.framesCatalogService = framesCatalogService;
    }

    @GetMapping()
    public String showAllFramesCatalog(Model model) {
        List<FramesCatalog> framesCatalog = framesCatalogService.getAllFramesCatalog();
        model.addAttribute("framesCatalog", framesCatalog);
        return "frame/framesCatalog-list";
    }

    @GetMapping("/add")
    public String showAddFramesCatalogPage(Model model) {
        model.addAttribute("newFramesCatalog", new FramesCatalog());
        return "frame/create-frameCatalog";
    }

    @PostMapping("/add")
    public String addFramesCatalog(@ModelAttribute("newFramesCatalog") @Valid FramesCatalog newFramesCatalog,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "frame/create-frameCatalog";
        }
        framesCatalogService.addFramesCatalog(newFramesCatalog);
        redirectAttributes.addFlashAttribute("successMessage", "Frames Catalog added successfully");
        return "redirect:/frames-catalog/list";
    }

    @GetMapping("/{id}/edit")
    public String showEditFramesCatalogPage(@PathVariable("id") Integer id, Model model) {
        FramesCatalog framesCatalog = framesCatalogService.getFramesCatalogById(id);
        if (framesCatalog != null) {
            model.addAttribute("framesCatalog", framesCatalog);
            return "frame/edit-frameCatalog";
        }
        return "redirect:/frames-catalog/list";
    }

    @PostMapping("/{id}/edit")
    public String editFramesCatalog(@PathVariable("id") Integer id,
                                    @ModelAttribute("framesCatalog") @Valid FramesCatalog framesCatalog,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "frame/edit-frameCatalog";
        }
        framesCatalogService.updateFramesCatalog(id, framesCatalog);
        redirectAttributes.addFlashAttribute("successMessage", "Frames Catalog updated successfully");
        return "redirect:/frames-catalog/list";
    }

    @PostMapping("/{id}/delete")
    public String deleteFramesCatalog(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        framesCatalogService.deleteFramesCatalog(id);
        redirectAttributes.addFlashAttribute("successMessage", "Frames Catalog deleted successfully");
        return "redirect:/frames-catalog/list";
    }
}

