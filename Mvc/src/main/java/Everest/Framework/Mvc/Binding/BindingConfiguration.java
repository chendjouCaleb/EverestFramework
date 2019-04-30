package Everest.Framework.Mvc.Binding;

import Everest.Framework.Core.Inject.Instance;
import Everest.Framework.Core.Inject.Factory;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Instance
public class BindingConfiguration {

    @Factory
    public ValidatorFactory validatorFactory(ConstraintValidatorFactory constraintValidatorFactory){



        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .constraintValidatorFactory(constraintValidatorFactory)
                .buildValidatorFactory();
        return validatorFactory;
    }

}
