package com.alimberdi.reservehub.services;

import com.alimberdi.reservehub.domain.dto.ResourceDTO;
import com.alimberdi.reservehub.domain.entity.Resource;
import com.alimberdi.reservehub.mapper.ResourceMapper;
import com.alimberdi.reservehub.repository.ResourceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepo resourceRepo;

    public ResourceDTO getResourceById(Long id) {
        return resourceRepo.findById(id)
                .map(ResourceMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    public List<ResourceDTO> getAllResources() {
        return resourceRepo.findAll()
                .stream()
                .map(ResourceMapper::toDTO)
                .toList();
    }

    public ResourceDTO createResource(ResourceDTO dto) {
        Resource resource = resourceRepo.save(ResourceMapper.toEntity(dto));
        return ResourceMapper.toDTO(resource);
    }

    public ResourceDTO updateResource(Long id, ResourceDTO dto) {
        if (!resourceRepo.existsById(id)) {
            throw new RuntimeException("Resource not found");
        }
        Resource resource = ResourceMapper.toEntity(dto);
        resource.setId(id);
        return ResourceMapper.toDTO(resourceRepo.save(resource));
    }

    public void deleteResource(Long id) {
        if (!resourceRepo.existsById(id)) {
            throw new RuntimeException("Resource not found");
        }
        resourceRepo.deleteById(id);
    }

}
