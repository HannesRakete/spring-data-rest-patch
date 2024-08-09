package com.hannesthielker.sdrpatch.repositories;

import com.hannesthielker.sdrpatch.entities.SchoolClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {
}
