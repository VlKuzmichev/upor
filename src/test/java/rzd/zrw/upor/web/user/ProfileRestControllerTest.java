package rzd.zrw.upor.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.to.UserTo;
import rzd.zrw.upor.util.UserUtil;
import rzd.zrw.upor.web.AbstractControllerTest;
import rzd.zrw.upor.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static rzd.zrw.upor.TestUtil.userHttpBasic;
import static rzd.zrw.upor.UserTestData.*;
import static rzd.zrw.upor.util.exception.ErrorType.VALIDATION_ERROR;
import static rzd.zrw.upor.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;
import static rzd.zrw.upor.web.user.AdminRestController.REST_URL;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN);
    }

//    @Test
//    void testUpdate() throws Exception {
//        UserTo updatedTo = new UserTo(null, "newName", "new full Name","newemail@ya.ru", "newPassword", 1500);
//
//        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(USER))
//                .content(JsonUtil.writeValue(updatedTo)))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//
//        assertMatch(userService.getByEmail("newemail@ya.ru"), UserUtil.updateFromTo(new User(USER), updatedTo));
//    }
//    @Test
//    void testUpdate() throws Exception {
//        UserTo updatedTo = new UserTo(null, "newName", "new fio", "newemail@ya.ru", "newPassword", 100003);
//
//        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(USER))
//                .content(JsonUtil.writeValue(updatedTo)))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//
//        assertMatch(userService.getByEmail("newemail@ya.ru"), UserUtil.updateFromTo(new User(USER), updatedTo));
//    }

//    @Test
//    void testUpdateInvalid() throws Exception {
//        UserTo updatedTo = new UserTo(null, null, null,"password", null, 100003);
//
//        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(USER))
//                .content(JsonUtil.writeValue(updatedTo)))
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(errorType(VALIDATION_ERROR))
//                .andDo(print());
//    }

//    @Test
//    @Transactional(propagation = Propagation.NEVER)
//    void testDuplicate() throws Exception {
//        UserTo updatedTo = new UserTo(null, "newName", "new fio", "admin@gmail.com", "newPassword", 100003);
//
//        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(USER))
//                .content(JsonUtil.writeValue(updatedTo)))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(errorType(VALIDATION_ERROR))
//                .andExpect(detailMessage(EXCEPTION_DUPLICATE_EMAIL))
//                .andDo(print());
//    }
}