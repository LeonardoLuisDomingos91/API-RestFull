package br.com.project.rest.data.vo;

import lombok.Data;
import org.dozer.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
public class PersonVo extends RepresentationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    private Long key;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;




}
