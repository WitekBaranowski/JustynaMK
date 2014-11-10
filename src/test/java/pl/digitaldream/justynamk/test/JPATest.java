package pl.digitaldream.justynamk.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.digitaldream.justynamk.ApplicationConfig;
import pl.digitaldream.justynamk.domain.User;
import pl.digitaldream.justynamk.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Witek on 2014-11-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@ActiveProfiles("dev")
public class JPATest {

    @Autowired
    UserRepository repository;

    @Test
    public void sampleTestCase() {
        User dave = new User();
        dave.setLogin("daveo");
        dave.setFirstName("dave");
        dave.setLastName("justynamk");

        dave = repository.save(dave);

        List<User> result = repository.findByLastName("justynamk");
        assertThat(result).hasSize(1);
        assertThat(result).contains(dave);
    }
}
