package rzd.zrw.upor;

import rzd.zrw.upor.to.PasswordFormTo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, Object> {

    @Override
    public void initialize(PasswordsEqualConstraint arg0) {
    }

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
        PasswordFormTo passwordFormTo = (PasswordFormTo) candidate;
        return passwordFormTo.getNewPassword().equals(passwordFormTo.getConfirmPassword());
    }
}
