package kr.co.direa.office.controller;

import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.ProjectDto;
import kr.co.direa.office.service.CategoriesService;
import kr.co.direa.office.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProjectController {
    private final ProjectsService projectsService;

    @GetMapping(value = "/project-list")
    ResponseEntity<?> getProjects() {
        List<ProjectDto> projectDtoList = projectsService.findAll();

        return ResponseEntity.ok(
                projectDtoList
        );
    }

}
