package kr.co.direa.office.controller;

import kr.co.direa.office.domain.Departments;
import kr.co.direa.office.dto.DepartmentDto;
import kr.co.direa.office.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kr.co.direa.office.constant.Constants.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DepartmentController {
    private final DepartmentsService departmentsService;

    @GetMapping(value = "/department-list")
    ResponseEntity<?> getDepartments() {
        List<DepartmentDto> departmentDtoList = departmentsService.findAll();

        return ResponseEntity.ok(
                departmentDtoList
        );
    }

    @PutMapping(value = "/edit-department")
    ResponseEntity<?> updateDepartment(@RequestBody DepartmentDto requestDto) {
        departmentsService.update(requestDto);

        return ResponseEntity.ok(
                SUCCESS
        );
    }

    @PostMapping(value = "/add-department")
    ResponseEntity<?> addDepartment(@RequestBody DepartmentDto requestDto) {
        departmentsService.save(requestDto);

        return ResponseEntity.ok(
                SUCCESS
        );
    }

    @DeleteMapping(value = "/department/{departmentId}")
    ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
        departmentsService.delete(departmentId);

        return ResponseEntity.ok(
                SUCCESS
        );
    }

}
