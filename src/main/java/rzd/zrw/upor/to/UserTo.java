package rzd.zrw.upor.to;

import rzd.zrw.upor.HasId;
import rzd.zrw.upor.model.Department;

public class UserTo implements HasId {
    private Integer id;

    private String name;

    private String fullName;

    private String email;

    private String password;

    private Integer departmentId;

    public UserTo() {
    }

    public UserTo(Integer id) {
        this.id = id;
    }

    public UserTo(Integer id, String name, String fullName, String email, String password, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.departmentId = departmentId;
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
