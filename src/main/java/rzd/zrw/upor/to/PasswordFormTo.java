package rzd.zrw.upor.to;

import rzd.zrw.upor.HasId;
import rzd.zrw.upor.PasswordsEqualConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordsEqualConstraint(message = "passwords are not equal")
public class PasswordFormTo implements HasId {

    private Integer id;

    @NotBlank
    @Size(min = 5, max = 32)
    private String oldPassword;

    @NotBlank
    @Size(min = 5, max = 32)
    private String newPassword;

    @NotBlank
    @Size(min = 5, max = 32)
    private String confirmPassword;

    public PasswordFormTo() {
    }

    public PasswordFormTo(Integer id, String oldPassword, String newPassword, String confirmPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "PasswordFormTo{" +
                ", old password='" + oldPassword + '\'' +
                ", new password='" + newPassword + '\'' +
                ", confirm password ='" + confirmPassword + '\'' +
                '}';
    }
}
