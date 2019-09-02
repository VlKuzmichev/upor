package rzd.zrw.upor.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import rzd.zrw.upor.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rzd.zrw.upor.UserTestData.ADMIN;
import static rzd.zrw.upor.UserTestData.ADMIN_ID;
import static rzd.zrw.upor.web.json.JsonUtil.writeIgnoreProps;

class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(writeIgnoreProps(ADMIN, "registered")));
    }
}