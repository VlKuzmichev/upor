package rzd.zrw.upor.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@ContextConfiguration({
//        "classpath:spring/spring-app.xml"
//})
@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract public class AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    private CacheManager cacheManager;

//    Uncomment if Using Hibernate Cache
//    @Autowired
//    protected JpaUtil jpaUtil;

    @BeforeEach
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        //jpaUtil.clear2ndLevelHibernateCache();
    }

}
