package com.onus.crud_review1.services;

import com.onus.crud_review1.dtos.EmployeeDTO;
import com.onus.crud_review1.dtos.EmployeeResponseDTO;
import com.onus.crud_review1.entities.Employees;
import com.onus.crud_review1.mapper.EmployeeMapper;
import com.onus.crud_review1.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeDTO employeeDTO) {
        if(employeeRepository.existsByEmail(employeeDTO.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        Employees employee = EmployeeMapper.mapToEmployees(employeeDTO);
        employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeResponseDTO(employee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(String employeeId) {
        Employees employees = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return EmployeeMapper.mapToEmployeeResponseDTO(employees);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employees> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeeById(String employeeId) {
        Employees employees = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
        employeeRepository.delete(employees);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        Employees employees = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
        if(!employees.getEmail().equals(employeeDTO.getEmail()) && employeeRepository.existsByEmail(employeeDTO.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        employees.setFirstName(employeeDTO.getFirstName());
        employees.setLastName(employeeDTO.getLastName());
        employees.setEmail(employeeDTO.getEmail());
        employees.setDepartment(employeeDTO.getDepartment());

        Employees updatedEmployee = employeeRepository.save(employees);

        return EmployeeMapper.mapToEmployeeResponseDTO(updatedEmployee);
    }
}
