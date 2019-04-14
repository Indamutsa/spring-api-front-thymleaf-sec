package com.example.arsenedata.repository;

import com.example.arsenedata.dataEntity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer>
{
}
