package LinkSQL.LaptopWebsite.Validator;

import LinkSQL.LaptopWebsite.Validator.annotation.ValidUserId;
import LinkSQL.LaptopWebsite.model.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user == null)
            return true;
        return user.getId() != null;
    }
}
