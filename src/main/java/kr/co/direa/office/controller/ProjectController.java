package kr.co.direa.office.controller;

import kr.co.direa.office.dto.ProjectDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/project/{code}")
    ResponseEntity<?> getProjectByCode(@PathVariable String code) {
        ProjectDto projectDto = projectsService.findByCodeOrNew(code);
        return ResponseEntity.ok(
                projectDto
        );
    }

    @PutMapping(value = "/edit-project")
    ResponseEntity<?> updateProject(@RequestBody ProjectDto requestDto) {
        projectsService.update(requestDto);

        return ResponseEntity.ok(
                "success"
        );
    }

    @PostMapping(value = "/add-project")
    ResponseEntity<?> addProject(@RequestBody ProjectDto requestDto) {
        projectsService.save(requestDto);

        return ResponseEntity.ok(
                "success"
        );
    }

    @DeleteMapping(value = "/project/{projectId}")
    ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        projectsService.delete(projectId);

        return ResponseEntity.ok(
                "success"
        );
    }

}
