package rzd.zrw.upor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "departments", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "depart_unique_name_idx")})
public class Department extends AbstractNamedEntity {

    @Column(name = "full_name")
    @Size(max = 100)
    private String fullName;

    public Department() {
    }

    public Department(Integer id, String name, String fullName) {
        super(id, name);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", fullName=" + fullName +
                ", name=" + name +
                '}';
    }

}
