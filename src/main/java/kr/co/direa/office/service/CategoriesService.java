package kr.co.direa.office.service;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.DeviceDto;
import kr.co.direa.office.repository.CategoriesRepository;
import kr.co.direa.office.repository.NotificationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(NotificationsRepository notificationsRepository, CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<CategoryDto> findAll() {
        List<Categories> categoriesList =  categoriesRepository.findAll();
        return categoriesList.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }

}
