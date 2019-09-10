package rzd.zrw.upor.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rzd.zrw.upor.model.Department;
import rzd.zrw.upor.model.User;
import rzd.zrw.upor.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/departments")
public class DepartmentUIController {
    @Autowired
    private DepartmentService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Department> getAll() {
        return service.getAll();
    }
}
