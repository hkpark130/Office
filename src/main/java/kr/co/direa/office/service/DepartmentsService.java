package kr.co.direa.office.service;

import kr.co.direa.office.domain.Departments;
import kr.co.direa.office.dto.DepartmentDto;
import kr.co.direa.office.repository.DepartmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentsService {
    private final DepartmentsRepository departmentsRepository;

    public List<DepartmentDto> findAll() {
        List<Departments> departmentsList =  departmentsRepository.findAll();
        return departmentsList.stream()
                .map(DepartmentDto::new)
                .collect(Collectors.toList());
    }

    public Departments findByName(String manageDepName) {
        return departmentsRepository.findByName(manageDepName);
    }
}
