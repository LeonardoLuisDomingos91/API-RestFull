package br.com.project.rest.service;

import br.com.project.rest.converter.DozerConverter;
import br.com.project.rest.data.model.Person;
import br.com.project.rest.data.vo.PersonVo;
import br.com.project.rest.exception.ResourceNotFoundException;
import br.com.project.rest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonVo create(PersonVo person){
        Person entity = DozerConverter.parseObject(person, Person.class);
        PersonVo personVo = DozerConverter.parseObject(personRepository.save(entity), PersonVo.class);
        return person;
    }

    public PersonVo findById(Long id){
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nada"));
        return DozerConverter.parseObject(personRepository.save(entity), PersonVo.class);
    }

    public List<PersonVo> findAll(){
        return DozerConverter.parseListObjects(personRepository.findAll(), PersonVo.class);
    }
    
    public void delete(Long id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("vazia"));
        personRepository.delete(person);
    }

    public PersonVo update(PersonVo person){
        Person entity = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("não existe"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());


        PersonVo personVo = DozerConverter.parseObject(personRepository.save(entity), PersonVo.class);
        return personVo;
    }
}
