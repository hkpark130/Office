package kr.co.direa.office.service;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Projects;
import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.ProjectDto;
import kr.co.direa.office.repository.CategoriesRepository;
import kr.co.direa.office.repository.NotificationsRepository;
import kr.co.direa.office.repository.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectsService {
    private final ProjectsRepository projectsRepository;

    public List<ProjectDto> findAll() {
        List<Projects> projectsList =  projectsRepository.findAll();
        return projectsList.stream()
                .map(ProjectDto::new)
                .collect(Collectors.toList());
    }

    public Projects findByName(String projectName) {
        return projectsRepository.findByName(projectName);
    }
}
