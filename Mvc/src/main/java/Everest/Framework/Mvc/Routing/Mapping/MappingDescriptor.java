package Everest.Framework.Mvc.Routing.Mapping;


import Everest.Framework.Http.HttpMethod;

/**
 * Provides an description of a action method mapping.
 * This class is useful because it provides an uniform representation of all annotation mapping.
 *
 * @see GetMapping
 * @see PostMapping
 * @see PutMapping
 * @see PathMapping
 * @see DeleteMapping
 * @see OptionsMapping
 */
public class MappingDescriptor {

    /**
     * The url mapping of the action or controller.
     */
    private String value;

    /**
     * The verbs of a HTTP request which can invoke the annotated action.
     */
    private String name;

    /**
     * The assigned name of the controller or action name.
     * Let it empty if you want to use the class name as
     * controller name and action name as action name.
     */
    private HttpMethod verb;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MappingDescriptor(){

    }
    public MappingDescriptor(HttpMapping httpMapping){
        value = httpMapping.value();
        verb = httpMapping.verbs();
        name = httpMapping.name();
    }

    public HttpMethod getVerb() {
        return verb;
    }

    public void setVerb(HttpMethod verb) {
        this.verb = verb;
    }


}
