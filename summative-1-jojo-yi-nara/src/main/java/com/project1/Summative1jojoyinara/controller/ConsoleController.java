package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.model.Console;
import com.project1.Summative1jojoyinara.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private ConsoleRepository consoleRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole() {
        return consoleRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable Integer id) {
        Optional<Console> console = consoleRepository.findById(id);
        if(console.isPresent()) {
            return console.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Console createNewConsole(@RequestBody @Valid Console console) {
        System.out.println("console" + console);
        return consoleRepository.save(console);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAConsole(@RequestBody @Valid Console console) {
        consoleRepository.save(console);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeConsole(@PathVariable Integer id){
        consoleRepository.deleteById(id);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer){
        return consoleRepository.findConsoleByManufacturer(manufacturer);
    }


}
