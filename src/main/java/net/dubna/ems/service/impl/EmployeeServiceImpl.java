package net.dubna.ems.service.impl;

import lombok.AllArgsConstructor;
import net.dubna.ems.dto.EmployeeDto;
import net.dubna.ems.entity.Employee;
import net.dubna.ems.exception.ResourceNotFoundException;
import net.dubna.ems.mapper.EmployeeMapper;
import net.dubna.ems.repository.EmployeeRepository;
import net.dubna.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exists with given id : " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
