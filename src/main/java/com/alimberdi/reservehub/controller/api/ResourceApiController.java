package com.alimberdi.reservehub.controller.api;

import com.alimberdi.reservehub.domain.dto.ResourceDTO;
import com.alimberdi.reservehub.services.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/resources")
public class ResourceApiController {

    private final ResourceService resourceService;

    // Getting resource by id
    @GetMapping("/{id}")
    public ResourceDTO getResource(@PathVariable Long id) {
         return resourceService.getResourceById(id);
    }

    // Getting all resources
    @GetMapping
    public List<ResourceDTO> getAllResources() {
        return resourceService.getAllResources();
    }

    // Creating resource
    @PostMapping
    public ResourceDTO createResource(@RequestBody ResourceDTO dto) {
        return resourceService.createResource(dto);
    }

    // Updating resource
    @PutMapping("/{id}")
    public ResourceDTO updateResource(@PathVariable Long id, @RequestBody ResourceDTO dto) {
        return resourceService.updateResource(id, dto);
    }

    // Deleting resource
    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }

}
