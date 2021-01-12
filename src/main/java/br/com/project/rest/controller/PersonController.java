package br.com.project.rest.controller;

import br.com.project.rest.data.vo.PersonVo;
import br.com.project.rest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/personn")
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public PersonVo findById(@PathVariable("id") Long id){

        PersonVo personVo = service.findById(id);
        personVo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVo;
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<PersonVo> findAll(){
        return service.findAll();
    }

    @PostMapping(produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public PersonVo create(@RequestBody PersonVo personVo){
        return service.create(personVo);
    }

    @PutMapping(produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public PersonVo upadte(@RequestBody PersonVo personVo){
        return service.update(personVo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
         service.delete(id);
         return ResponseEntity.ok().build();
    }
}
