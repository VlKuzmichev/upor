package rzd.zrw.upor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import rzd.zrw.upor.service.UserService;

import javax.annotation.PostConstruct;


//@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
//        "classpath:spring/spring-mvc.xml",
//})
@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
})
@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
abstract public class AbstractControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    protected UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

}
