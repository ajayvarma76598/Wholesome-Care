package com.stackroute.wellnessservice.Repository;

import com.stackroute.wellnessservice.model.GuestUser;
import com.stackroute.wellnessservice.model.Helpline;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class HelplineRepositoryTest {
    @Autowired
    HelplineRepository helplineRepository;
    private Helpline helpline;

    @Before
    public void setUp() throws Exception {
        helpline=new Helpline();
        helpline.setCityName("pune");
        helpline.setHelplineNumber("6798463");

    }
    @After
    public void tearDown() throws Exception {

        helplineRepository.deleteAll();
    }
    @Test
    public void saveHelplineTest() {

        helplineRepository.insert(helpline);
        Helpline fetchedHelpline = helplineRepository.findById("pune").get();
        Assert.assertEquals(helpline.getCityName(), fetchedHelpline.getCityName());

    }
    @Test
    public void getHelplineByCityNameTest() {

        helplineRepository.insert(helpline);
        Helpline fetchedHelpline = helplineRepository.findById(helpline.getCityName()).get();
        Assert.assertEquals("pune", fetchedHelpline.getCityName());
    }
}
