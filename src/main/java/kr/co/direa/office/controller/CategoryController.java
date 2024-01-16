package kr.co.direa.office.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoriesService categoriesService;

    @GetMapping(value = "/catrgory-list")
    ResponseEntity<?> getCategories() {
        List<CategoryDto> categoryDtoList = categoriesService.findAll();

        return ResponseEntity.ok(
                categoryDtoList
        );
    }

    @PostMapping(value = "/add-category")
    ResponseEntity<?> addCategory(@RequestBody CategoryDto requestDto) {
        categoriesService.save(requestDto);

        return ResponseEntity.ok(
                "success"
        );
    }

}
