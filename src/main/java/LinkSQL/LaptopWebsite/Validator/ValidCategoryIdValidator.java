package LinkSQL.LaptopWebsite.Validator;

import LinkSQL.LaptopWebsite.Validator.annotation.ValidCategoryId;
import LinkSQL.LaptopWebsite.model.Category;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return  category != null && category.getId() != null;
    }
}
