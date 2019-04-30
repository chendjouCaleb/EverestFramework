package Everest.Framework.Mvc.Binding;


import Everest.Framework.Core.Inject.Instance;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import java.util.Collection;
import java.util.NoSuchElementException;

@Instance
public class EverestConstraintValidatorFactory implements ConstraintValidatorFactory {
    private Collection<ConstraintValidator> validators;

    public EverestConstraintValidatorFactory(Collection<ConstraintValidator> validators) {
        this.validators = validators;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> aClass) {
        if (aClass.getPackage().getName().startsWith("javax.validation") ||
                aClass.getPackage().getName().startsWith("org.hibernate.validator"))
        {
            try {
                // create standard validators by calling
                // default constructor
                return aClass.newInstance();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return (T) getInternalValidator(aClass);
    }

    public <T extends ConstraintValidator<?, ?>> T getInternalValidator(Class<T> type){
        for(ConstraintValidator validator : validators){
            if(validator.getClass().equals(type)){
                return (T) validator;
            }
        }

        throw new NoSuchElementException("The validator '" + type + "' was not found.");
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> constraintValidator) {

    }
}
