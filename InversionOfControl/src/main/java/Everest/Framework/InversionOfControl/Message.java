package Everest.Framework.InversionOfControl;

public class Message {
    public static final String NAMED_COMPONENT_NOT_FOUND = "There are no component with name '%s'";
    public static final String NAMED_WITH_EMPTY_NAME = "Cannot component with null or empty name";
    public static final String COLLECTION_WITH_NON_NATIVE_COLLECTION_TYPE =
            "Only a java native collection is usable for collection injection";
}
