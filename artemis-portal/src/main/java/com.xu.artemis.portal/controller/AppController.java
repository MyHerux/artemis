package com.xu.artemis.portal.controller;

import com.xu.artemis.portal.entity.App;
import com.xu.artemis.portal.exception.NotFoundException;
import com.xu.artemis.portal.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apps")
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {"application/json"})
    public App create(@RequestBody App app) {
        return appService.save(app);
    }

    @RequestMapping("/{appid}")
    public App detail(@PathVariable String appId) {
        App app = appService.findById(appId);
        if (app == null) {
            throw new NotFoundException();
        }
        return app;
    }

    @RequestMapping("")
    public List<App> list(Pageable pageable) {
        Page<App> page = appService.list(pageable);
        if (pageable.getPageNumber() > page.getTotalPages()) {
            throw new NotFoundException();
        }
        return page.getContent();
    }
}