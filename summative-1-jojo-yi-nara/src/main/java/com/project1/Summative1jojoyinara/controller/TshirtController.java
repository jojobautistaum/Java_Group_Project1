package com.project1.Summative1jojoyinara.controller;

import com.project1.Summative1jojoyinara.model.Tshirt;
import com.project1.Summative1jojoyinara.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/t-shirt")
public class TshirtController {

    @Autowired
    TshirtRepository tshirtRepository;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllTshirt() {
        return tshirtRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirt(@PathVariable Integer id) {
        Optional<Tshirt> returnVal = tshirtRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt(@RequestBody @Valid Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody @Valid Tshirt tshirt) {
        tshirtRepository.save(tshirt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable Integer id) {
        tshirtRepository.deleteById(id);
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllTshirtByColor(@PathVariable String color) {
        return tshirtRepository.findTShirtByColor(color);
    }

    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllTshirtBySize(@PathVariable String size) {
        return tshirtRepository.findTShirtBySize(size);
    }
}
