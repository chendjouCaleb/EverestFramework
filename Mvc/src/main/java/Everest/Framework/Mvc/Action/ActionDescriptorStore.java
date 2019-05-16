package Everest.Framework.Mvc.Action;

import Everest.Framework.Core.Exception.InvalidNameException;
import Everest.Framework.Core.Exception.InvalidOperationException;
import Everest.Framework.Core.Inject.Singleton;
import Everest.Framework.Mvc.Mapping.MappingFor.MappingGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The role of this class is to collect controller descriptor of all controller class of the application.
 * @see Everest.Framework.Mvc.Action.ControllerDescriptor
 * @see Everest.Framework.Mvc.Action.ActionDescriptor
 *
 * @author Chendjou
 * @version 1
 * @since 25-04-2019
 */

@Singleton
public class ActionDescriptorStore {
    private Logger logger = LoggerFactory.getLogger(ActionDescriptorStore.class);

    /**
     * Classes to parse an extract descriptors.
     */
    private List<Class<?>> controllerClasses;

    /**
     * The collected action descriptor.
     */
    private List<ActionDescriptor> actionDescriptors;

    /**
     * The collected controller descriptor.
     */
    private List<ControllerDescriptor> controllerDescriptors;

    private MappingGetter mappingGetter = new MappingGetter();


    /**
     * Parses and collect action and controller descriptor in provided classes.
     */
    public void collect() {
        List<ControllerDescriptor> controllerDescriptors = new ArrayList<>();
        List<ActionDescriptor> actionDescriptors = new ArrayList<>();
        for (Class classType: controllerClasses){
            ControllerDescriptorLoader descriptorLoader = new ControllerDescriptorLoader();
            ControllerDescriptor descriptor = descriptorLoader.LoadControllerDescriptor(classType);
            //checkDuplicateControllerName();
            descriptorLoader.collectActionDescriptor(descriptor);
            //checkDuplicatedActionName(descriptor);
            //checkDuplicateUrlMapping();
            controllerDescriptors.add(descriptor);
            actionDescriptors.addAll(descriptor.getActionDescriptors());

        }

        this.controllerDescriptors = controllerDescriptors;
        this.actionDescriptors = actionDescriptors;
    }



    /**
     * Checks that in the collected controllers, two controllers dont have the same name.
     */
    public void checkDuplicateControllerName() {
        if(actionDescriptors == null){
            throw new InvalidOperationException("The controllers descriptors is not loaded. Call collect() method.");
        }

        for(int i = 1; i < controllerDescriptors.size(); i++){
            ControllerDescriptor iCurrent = controllerDescriptors.get(i);

            for(int j = 0; j < i; j++){
                ControllerDescriptor jCurrent = controllerDescriptors.get(j);
                if(jCurrent.getName().equals(iCurrent.getName())){
                    throw new InvalidNameException("The controller '" + jCurrent.getClassType().getName()
                            + "' have a same name with controller '" + iCurrent.getClassType().getName() + "'.");
                }
            }
        }
    }


    /**
     * Checks that in the collected actions, two actions dont have the same url mapping.
     */
    public void checkDuplicateUrlMapping() {
        if(actionDescriptors == null){
            throw new InvalidOperationException("The action descriptors is not loaded. Call collect() method.");
        }

        for(int i = 1; i < actionDescriptors.size(); i++){
            ActionDescriptor iCurrent = actionDescriptors.get(i);
            String iCurrentMapping = iCurrent.getControllerDescriptor() + "/" + iCurrent.getMapping();

            for(int j = 0; j < i; j++){
                ActionDescriptor jCurrent = actionDescriptors.get(j);
                String jCurrentMapping = jCurrent.getControllerDescriptor() + "/" + jCurrent.getMapping();
                if(jCurrentMapping.equals(iCurrentMapping)){
                    throw new InvalidNameException("The action method'" + jCurrent.getMethod().getName()
                            + "' have a same url mapping with action method '" + iCurrent.getMethod().getName() + "'.");
                }
            }
        }
    }

   public boolean isCollected(){
        return actionDescriptors != null && controllerDescriptors != null;
   }

    public List<ActionDescriptor> getActionDescriptors() {
        return actionDescriptors;
    }

    public List<ControllerDescriptor> getControllerDescriptors() {
        return controllerDescriptors;
    }

    public ActionDescriptorStore setControllerClasses(List<Class<?>> controllerClasses) {
        this.controllerClasses = controllerClasses;
        return this;
    }
}
