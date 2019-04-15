package Everest.Framework.Mvc.Binding;

import Everest.Framework.Core.Decorator.Instance;
import Everest.Framework.Core.Decorator.InstanceFactory;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Instance
public class BindingConfiguration {

    @InstanceFactory
    public ValidatorFactory validatorFactory(ConstraintValidatorFactory constraintValidatorFactory){



        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .constraintValidatorFactory(constraintValidatorFactory)
                .buildValidatorFactory();
        return validatorFactory;
    }

}
