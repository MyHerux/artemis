package com.xu.artemis.portal.repository;


import com.xu.artemis.portal.PortalApplicationTestConfiguration;
import com.xu.artemis.portal.entities.App;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PortalApplicationTestConfiguration.class)
public class AppRepositoryTest {

  @Autowired
  AppRepository repository;

  @Test
  public void testCreate() {
    Assert.assertEquals(0, repository.count());

    App ramdomApp = new App();
    ramdomApp.setId(String.valueOf(System.currentTimeMillis()));
    ramdomApp.setName("new app " + System.currentTimeMillis());
    ramdomApp.setOwner("owner " + System.currentTimeMillis());
    ramdomApp.setCreateTimestamp(new Date());
    repository.save(ramdomApp);

    Assert.assertEquals(1, repository.count());
  }
}
