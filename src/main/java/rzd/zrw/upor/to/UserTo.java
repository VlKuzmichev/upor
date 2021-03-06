package rzd.zrw.upor.to;

import org.hibernate.validator.constraints.SafeHtml;
import rzd.zrw.upor.HasEmail;
import rzd.zrw.upor.HasId;
import rzd.zrw.upor.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public class UserTo implements HasId, HasEmail {
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    @SafeHtml
    @Size(min = 2, max = 100)
    private String fullName;

    @Email
    @NotBlank
    @Size(max = 100)
    @SafeHtml // https://stackoverflow.com/questions/17480809
    private String email;

    @NotNull
    private Set<Role> roles;

    @Size(min = 5, max = 32)
    private String password;

    private Integer departmentId;

    public UserTo() {
    }

    public UserTo(Integer id) {
        this.id = id;
    }

    public UserTo(Integer id, String name, String fullName, String email, String password, Integer departmentId, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.departmentId = departmentId;
        this.roles = roles;
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}
