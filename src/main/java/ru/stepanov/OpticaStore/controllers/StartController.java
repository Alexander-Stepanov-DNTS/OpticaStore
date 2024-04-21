package ru.stepanov.OpticaStore.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.stepanov.OpticaStore.Services.ClientService;
import ru.stepanov.OpticaStore.models.Client;

import java.util.List;

@Controller
@RequestMapping("/mainPage")
public class StartController {

    public StartController(ClientService clientService) {}

    @GetMapping
    public String showAllTables() {
        return "mainPageTables";
    }
}