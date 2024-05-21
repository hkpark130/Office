package kr.co.direa.office.service;

import kr.co.direa.office.domain.Projects;
import kr.co.direa.office.dto.ProjectDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.CategoriesRepository;
import kr.co.direa.office.repository.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Projects findByCode(String projectCode) {
        return projectsRepository.findByCode(projectCode).orElse(null);
    }

    public ProjectDto findByCodeOrNew(String code) {
        Projects project = findByCode(code);
        return (project != null)?new ProjectDto(project):null;
    }

    public void update(ProjectDto requestDto) {
        Projects project = projectsRepository.findById(requestDto.getId()).orElseThrow(
                () -> new CustomException(CustomErrorCode.NOT_FOUND_PROJECT,
                      "해당 프로젝트가 없습니다. id = " + requestDto.getId())
        );
        project.setCode(requestDto.getCode());
        project.setName(requestDto.getName());
        projectsRepository.save(project);

    }

    public void save(ProjectDto requestDto) {
        Projects project = new Projects();
        project.setName(requestDto.getName());
        project.setCode(requestDto.getCode());
        projectsRepository.save(project);
    }

    public void delete(Long projectId) {
        Projects project = projectsRepository.findById(projectId).orElseThrow(
                () -> new CustomException(CustomErrorCode.NOT_FOUND_PROJECT,
                        "해당 프로젝트가 없습니다. id = " + projectId)
        );
        projectsRepository.delete(project);
    }
}
