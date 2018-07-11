package com.xu.artemis.portal.repository;

import com.xu.artemis.portal.entity.Privilege;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PrivilegeRepository extends PagingAndSortingRepository<Privilege, Long> {

  List<Privilege> findByAppId(String appId);

  Privilege findByAppIdAndPrivilegeType(String appId, String privilegeType);
}
