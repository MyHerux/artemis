package com.xu.artemis.portal.controller;

import com.xu.artemis.portal.domain.App;
import com.xu.artemis.portal.domain.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/apps")
public class AppController {

    @Autowired
    private AppRepository appRepository;

    @RequestMapping("")
    public Page<App> list(@PageableDefault(size = 50) Pageable pageable) {
        return appRepository.findAll(pageable);
    }

    @PostMapping(value = "")
    public App create() {
        App ramdomApp = new App();
        ramdomApp.setId(String.valueOf(System.currentTimeMillis()));
        ramdomApp.setName("new app " + System.currentTimeMillis());
        ramdomApp.setOwner("owner " + System.currentTimeMillis());
        ramdomApp.setCreateTimestamp(new Date());
        return appRepository.save(ramdomApp);
    }
}
