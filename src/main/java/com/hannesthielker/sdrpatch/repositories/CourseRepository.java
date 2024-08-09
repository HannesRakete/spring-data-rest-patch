package com.hannesthielker.sdrpatch.repositories;

import com.hannesthielker.sdrpatch.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CourseRepository extends CrudRepository<Course, Long> {
}
