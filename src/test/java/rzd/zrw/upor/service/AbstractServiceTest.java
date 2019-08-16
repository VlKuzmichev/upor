package rzd.zrw.upor.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rzd.zrw.upor.repository.JpaUtil;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract public class AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    private CacheManager cacheManager;

//    Uncomment if Using Hibernate Cache
//    @Autowired
//    protected JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
//        jpaUtil.clear2ndLevelHibernateCache();
    }

}
