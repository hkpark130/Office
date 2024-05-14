package kr.co.direa.office.service;

import kr.co.direa.office.domain.Departments;
import kr.co.direa.office.dto.DepartmentDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
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
        return departmentsRepository.findByName(manageDepName).orElse(null);
    }

    public DepartmentDto findByNameOrNew(String name) {
        Departments department = findByName(name);
        return (department != null)?new DepartmentDto(department):null;
    }

    public void update(DepartmentDto requestDto) {
        Departments department = departmentsRepository.findById(requestDto.getId()).orElseThrow(
                () -> new CustomException(CustomErrorCode.NOT_FOUND_DEPARTMENT,
                        "해당 부서가 없습니다. id = " + requestDto.getId())
        );
        department.setName(requestDto.getName());
        departmentsRepository.save(department);
    }

    public void save(DepartmentDto requestDto) {
        Departments department = new Departments();
        department.setName(requestDto.getName());
        departmentsRepository.save(department);
    }

    public void delete(Long departmentId) {
        Departments department = departmentsRepository.findById(departmentId).orElseThrow(
                () -> new CustomException(CustomErrorCode.NOT_FOUND_DEPARTMENT,
                        "해당 부서가 없습니다. id = " + departmentId)
        );
        departmentsRepository.delete(department);
    }
}
