package com.onus.crud_review1.controllers;

import com.onus.crud_review1.dtos.employee.EmployeeResponseDTO;
import com.onus.crud_review1.dtos.PageResponseDTO;
import com.onus.crud_review1.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Cacheable( // key,value 형태로 캐시 저장함, 데이터가 새로 생성되는 경우를 고려해 업데이트 시간 고려해야함
            value = "employees",
            key = "T(java.util.Objects).hash(#pageNo, #pageSize, #sortBy, #sortDirection, #searchKeyword)"
    )

    @GetMapping("/all")
    public ResponseEntity<PageResponseDTO> getAllEmployeeWithPagination(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) String searchKeyword
    ) {
        PageResponseDTO res = employeeService.getAllEmployeeWithPagination(pageNo, pageSize, sortBy, sortDirection, searchKeyword);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable String employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }


}
