package br.com.project.rest.converter;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerConverter {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjetcs = new ArrayList<D>();
        for(Object o : origin){
            destinationObjetcs.add(mapper.map(o, destination));
        }
        return destinationObjetcs;
    }
}
