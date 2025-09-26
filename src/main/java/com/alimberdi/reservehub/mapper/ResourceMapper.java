package com.alimberdi.reservehub.mapper;

import com.alimberdi.reservehub.domain.dto.ResourceDTO;
import com.alimberdi.reservehub.domain.entity.Resource;

public class ResourceMapper {

    public static ResourceDTO toDTO(Resource resource) {
        return new ResourceDTO(resource.getId(), resource.getName(), resource.getDescription(), resource.getCapacity());
    }

    public static Resource toEntity(ResourceDTO dto) {
        return new Resource(
                dto.id(),
                dto.name(),
                dto.description(),
                dto.capacity(),
                null
        );
    }

}
