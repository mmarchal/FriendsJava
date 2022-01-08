package com.maxfriends.back.converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class GenericConverter<E, DTO> {

    @Autowired
    private ModelMapper modelMapper;

    public DTO entityToDto(final E e, final Class<DTO> c) {
        return this.modelMapper.map(e, c);
    }

    public Collection<DTO> entitiesToDtos(final Collection<E> e, final Class<DTO> c) {
        if (e == null || e.isEmpty()) {
            return Collections.emptyList();
        }
        return e.stream().map(n -> this.entityToDto(n, c)).collect(Collectors.toCollection(ArrayList::new));
    }

    public E dtoToEntity(final DTO dto, final Class<E> c) {
        return this.modelMapper.map(dto, c);
    }

    public Collection<E> dtosToEntities(final Collection<DTO> dto, final Class<E> c) {
        if (dto == null || dto.isEmpty()) {
            return Collections.emptyList();
        }
        return dto.stream().map(n -> this.dtoToEntity(n, c)).collect(Collectors.toCollection(ArrayList::new));
    }

}
