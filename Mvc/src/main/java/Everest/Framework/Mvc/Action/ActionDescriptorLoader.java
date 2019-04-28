package Everest.Framework.Mvc.Action;

import Everest.Framework.Core.Annotations;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingDescriptor;
import Everest.Framework.Mvc.Mapping.MappingFor.MappingGetter;
import Everest.Framework.Mvc.Routing.MalformedUrlMappingException;
import Everest.Framework.Mvc.Routing.UnMappedException;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * The role of this class is to collect action method descriptor of all controller class of the application.
 *
 * @author Chendjou
 * @version 1
 * @since 25-04-2019
 */
public class ActionDescriptorLoader {
    private MappingGetter mappingGetter;

    public ActionDescriptorLoader(MappingGetter mappingGetter) {
        this.mappingGetter = mappingGetter;
    }

    public ActionDescriptor loadActionDescriptor(Method method){
        ActionDescriptor descriptor = new ActionDescriptor();

        Annotation annotation = method.getAnnotation(HttpMapping.class);

        if(annotation == null){
            annotation = Annotations.annotatedAnnotation(method, HttpMapping.class);
        }

        if(annotation == null){
            throw new UnMappedException("The potential action method '" + method +
                    "' must be annotated by a least one mapping annotation.");
        }

        MappingDescriptor mapping = mappingGetter.mappingDescriptor(annotation);

        if(mapping.getValue().startsWith("/") || mapping.getValue().endsWith("/")){
            throw new MalformedUrlMappingException("The mapping of action methods" + method + " cannot starts or ends with '/' characters");
        }

        descriptor.setMethod(method);
        descriptor.setVerbs(mapping.getVerb());
        descriptor.setMapping(mapping.getValue());

        if(StringUtils.isEmpty(mapping.getName())){
            descriptor.setName(method.getName());
        }else {
            descriptor.setName(mapping.getName());
        }

        return descriptor;


    }
}
