package kr.co.direa.office.controller;

import kr.co.direa.office.domain.Departments;
import kr.co.direa.office.dto.DepartmentDto;
import kr.co.direa.office.dto.ProjectDto;
import kr.co.direa.office.service.DepartmentsService;
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
public class DepartmentController {
    private final DepartmentsService departmentsService;

    @GetMapping(value = "/department-list")
    ResponseEntity<?> getProjects() {
        List<DepartmentDto> departmentDtoList = departmentsService.findAll();

        return ResponseEntity.ok(
                departmentDtoList
        );
    }

}
