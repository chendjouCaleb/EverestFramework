package Everest.Framework.Mvc.Action;

import Everest.Framework.Core.Exception.InvalidNameException;
import Everest.Framework.Mvc.Mapping.HttpMapping;
import Everest.Framework.Mvc.Mapping.MappingFor.MappingGetter;
import Everest.Framework.Mvc.Routing.MalformedUrlMappingException;
import Everest.Framework.Mvc.Routing.UnMappedException;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * The role of this class is to load the controller descriptor of java class.
 * @see ControllerDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 25-04-2019
 */

public class ControllerDescriptorLoader {

    /**
     * Parses and extract description of a controller class.
     * @param type The java class of the controller class.
     * @return The controller description of the class.
     */
    public ControllerDescriptor  LoadControllerDescriptor(@Nonnull Class<?> type){
        HttpMapping mapping = type.getAnnotation(HttpMapping.class);

        if(mapping == null){
            throw new UnMappedException("The controller class "+type+ " must be annotated with " + HttpMapping.class);
        }

        if(mapping.value().startsWith("/") || mapping.value().endsWith("/")){
            throw new MalformedUrlMappingException("The mapping of " + type + " controller cannot starts or ends with '/' characters");
        }

        if(!type.getName().endsWith("Controller")){
            throw new InvalidNameException("The name of controller class " + type + " must be end with 'Controller'.");
        }

        ControllerDescriptor controllerDescriptor = new ControllerDescriptor();
        controllerDescriptor.setClassType(type);
        controllerDescriptor.setMapping(mapping.value());

        if(mapping.name().equals("")){
            String name = type.getSimpleName();
            name = name.substring(0, name.lastIndexOf("Controller"));
            controllerDescriptor.setName(name);
        }else {
            controllerDescriptor.setName(mapping.name());
        }

        return controllerDescriptor;
    }


    /**
     * Parses method of {@param descriptor} classList and collects action descriptions.
     * After the collection operation, the collected {@link ActionDescriptor} is added to
     * {@param descriptor} {@link ActionDescriptor} list.
     *
     * @param descriptor The {@link ControllerDescriptor} of a parsed classType.
     */
    public void collectActionDescriptor(ControllerDescriptor descriptor) {
        MappingGetter mappingGetter = new MappingGetter();
        Method[] methods = descriptor.getClassType().getMethods();
        descriptor.setActionDescriptors(new ArrayList<>());

        for(Method method: methods){
            try{
                ActionDescriptorLoader loader = new ActionDescriptorLoader(mappingGetter);
                ActionDescriptor actionDescriptor = loader.loadActionDescriptor(method);
                descriptor.getActionDescriptors().add(actionDescriptor);
                actionDescriptor.setControllerDescriptor(descriptor);
            }catch (UnMappedException ignore) {
                // Is thrown is the method is not mapped with a mapping annotation.
            }
        }
    }


    /**
     * Checks that in the collected action of {@param descriptor}, two action dont have the same name.
     *
     * @param descriptor The {@link ControllerDescriptor} to check.
     */
    public void checkDuplicatedActionName(ControllerDescriptor descriptor) {

        for(int i = 1; i < descriptor.getActionDescriptors().size(); i++){
            ActionDescriptor iCurrent = descriptor.getActionDescriptors().get(i);

            for(int j = 0; j < i; j++){
                ActionDescriptor jCurrent = descriptor.getActionDescriptors().get(j);
                if(jCurrent.getName().equals(iCurrent.getName())){
                    throw new InvalidNameException("The action method '" + jCurrent.getMethod().getName()
                            + "' have a same name with action method '" + iCurrent.getMethod().getName() + "'.");
                }
            }
        }
    }
}
