package com.xu.artemis.portal.controller;

import com.xu.artemis.portal.PortalApplicationTestConfiguration;
import com.xu.artemis.portal.entity.App;
import com.xu.artemis.portal.repository.AppRepository;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalApplicationTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AppRepository appRepository;

    @Test
    public void testCreate() throws URISyntaxException {
        App newApp = new App();
        newApp.setId(String.valueOf(System.currentTimeMillis()));
        newApp.setName("new app " + System.currentTimeMillis());
        newApp.setOwner("owner " + System.currentTimeMillis());

        URI uri = new URI("http://localhost:8080/apps");
        App createdApp = restTemplate.postForObject(uri, newApp, App.class);

        Assert.assertEquals(newApp.getId(), createdApp.getId());
        Assert.assertNull(newApp.getCreateTimestamp());
        Assert.assertNotNull(createdApp.getCreateTimestamp());

        App foundApp = appRepository.findById(newApp.getId()).get();

        Assert.assertEquals(newApp.getId(), foundApp.getId());
    }

    @Test
    public void testList() throws URISyntaxException {
        App newApp = new App();
        newApp.setId(String.valueOf(System.currentTimeMillis()));
        newApp.setName("new app " + System.currentTimeMillis());
        newApp.setOwner("owner " + System.currentTimeMillis());
        appRepository.save(newApp);

        URI uri = new URI("http://localhost:8080/apps");

        App[] apps = restTemplate.getForObject(uri, App[].class);
        Assert.assertEquals(1, apps.length);
        Assert.assertEquals(newApp.getId(), apps[0].getId());
    }

    @Test
    public void testListOutOfRange() throws URISyntaxException {
        App newApp = new App();
        newApp.setId(String.valueOf(System.currentTimeMillis()));
        newApp.setName("new app " + System.currentTimeMillis());
        newApp.setOwner("owner " + System.currentTimeMillis());
        appRepository.save(newApp);

        URI uri = new URI("http://localhost:8080/apps?page=2");

        ResponseEntity<App[]> entity = restTemplate.getForEntity(uri, App[].class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
        Assert.assertNull(entity.getBody());
    }
}