package com.example.arsenedata.repository;

import com.example.arsenedata.dataEntity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer>
{
}
