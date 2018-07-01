package com.xu.artemis.portal.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface AppRepository extends CrudRepository<App, String> {

    Page<App> findAll(Pageable pageable);
}
