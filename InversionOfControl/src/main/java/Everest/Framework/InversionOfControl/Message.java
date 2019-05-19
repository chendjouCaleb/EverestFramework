package Everest.Framework.InversionOfControl;

public class Message {
    public static final String TYPED_COMPONENT_NOT_FOUND = "There are no component with type '%s'";
    public static final String NAMED_COMPONENT_NOT_FOUND = "There are no component with name '%s'";
    public static final String NAMED_WITH_EMPTY_NAME = "Cannot component with null or empty name";
    public static final String NO_PRINCIPAL_COMPONENT = "There are to many component with type %s but nothing of them is marked as principal";
    public static final String COLLECTION_WITH_NON_NATIVE_COLLECTION_TYPE =
            "Only a java native collection is usable for collection injection";

    public static final String GET_COLLECTION_OF_NON_TYPED_COLLECTION =
            "If you want to use collection type for injection, you must use a generics typed collection";
}
