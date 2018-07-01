package com.xu.artemis.portal.service;

import com.xu.artemis.portal.entity.App;
import com.xu.artemis.portal.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AppService {

    @Autowired
    private AppRepository appRepository;

    public App findById(String appId) {
        return appRepository.findById(appId).get();
    }

    public Page<App> list(Pageable pageable) {
        return appRepository.findAll(pageable);
    }

    public App save(App app) {
        app.setCreateTimestamp(new Date());
        return appRepository.save(app);
    }
}