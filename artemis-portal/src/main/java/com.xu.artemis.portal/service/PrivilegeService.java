package com.xu.artemis.portal.service;

import com.xu.artemis.portal.entity.Privilege;
import com.xu.artemis.portal.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService {

    /**
     * 权限类型枚举
     */
    enum PrivilegeType {
        EDIT, REVIEW, RELEASE
    }

    @Autowired
    private PrivilegeRepository privilegeRepo;

    /**
     * 是否包含权限
     *
     * @param appId         app id
     * @param name          权限名称
     * @param privilegeType 权限类型
     * @return 是否包含权限
     */
    public boolean hasPrivilege(String appId, String name, PrivilegeType privilegeType) {
        Privilege privilege = privilegeRepo.findByAppIdAndPrivilegeType(appId, privilegeType.name());
        return privilege != null && privilege.getName().equals(name);
    }

    /**
     * 权限列表
     *
     * @param appId app id
     * @return 该id的权限列表
     */
    public List<Privilege> listPrivileges(String appId) {

        return privilegeRepo.findByAppId(appId);
    }

    /**
     * 添加新权限
     *
     * @param appId         app id
     * @param name          权限名称
     * @param privilegeType 权限类型
     * @return 新权限
     */
    public Privilege setPrivilege(String appId, String name, PrivilegeType privilegeType) {
        Privilege privilege = privilegeRepo.findByAppIdAndPrivilegeType(appId, privilegeType.name());
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setAppId(appId);
            privilege.setPrivilegeType(privilegeType.name());
        }
        privilege.setName(name);
        return privilegeRepo.save(privilege);
    }
}
