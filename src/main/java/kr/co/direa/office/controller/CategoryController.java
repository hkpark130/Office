package kr.co.direa.office.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



//    @GetMapping(value = "/catrgory-list")
//    ResponseEntity<?> getCategories(HttpServletResponse response2) {
//        List<CategoryDto> categoryDtoList = categoriesService.findAll();
//        Map<String, Object> map;
//        List<Map<String, Object>> response = new ArrayList<>();
//
//        for (CategoryDto categoryDto : categoryDtoList) {
//            Long deviceCount = devicesService.countByCategoryId(categoryDto.toEntity());
//            map = new HashMap<>();
//            map.put("amount", deviceCount);
//            map.put("name", categoryDto.getName());
//            map.put("img", categoryDto.getImg());
//            response.add(map);
//        }
//
//        Cookie cookie = new Cookie("phk", "Sex");
//        cookie.setHttpOnly(true);
//        response2.addCookie(cookie);
//
//
//        return ResponseEntity.ok(
//                response
//        );
//    }

}
