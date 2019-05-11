package Everest.Framework.InversionOfControl.DI.Lookup;

import java.util.*;

/**
 * This class look all component with same type an return the result in collection form.
 * This form of lookup is usable if the user want to inject all component of same type.
 * Only the Java native collection is supported because other
 * type can extends or implements a native collection type.
 *
 * @author Chendjou
 * @version 1
 * @since 11-05-2019
 */
public class CollectionLookup {
    public static Map<Class<? extends Iterable>, Class<? extends Iterable>> collectionsType = new HashMap<>();


    private LookupEngine lookupEngine;

    public CollectionLookup(LookupEngine lookupEngine) {
        this.lookupEngine = lookupEngine;
        initCollectionTypes();
    }

    public Object look(Class collectionType, Class componentType) {

        Collection collection = getCollection(collectionType);
        collection.addAll(lookupEngine.lookComponents(componentType));
        return collection;
    }

    private void initCollectionTypes() {
        collectionsType.put(Iterable.class, ArrayList.class);
        collectionsType.put(Collection.class, ArrayList.class);
        collectionsType.put(List.class, ArrayList.class);
        collectionsType.put(ArrayList.class, ArrayList.class);

        collectionsType.put(LinkedList.class, LinkedList.class);

        collectionsType.put(Vector.class, Vector.class);

        collectionsType.put(Stack.class, Stack.class);

        collectionsType.put(Queue.class, PriorityQueue.class);
        collectionsType.put(PriorityQueue.class, PriorityQueue.class);

        collectionsType.put(Deque.class, ArrayDeque.class);
        collectionsType.put(ArrayDeque.class, ArrayDeque.class);

        collectionsType.put(Set.class, HashSet.class);
        collectionsType.put(HashSet.class, HashSet.class);

        collectionsType.put(LinkedHashSet.class, LinkedHashSet.class);

        collectionsType.put(SortedSet.class, TreeSet.class);
        collectionsType.put(TreeSet.class, TreeSet.class);

    }

    private Collection getCollection(Class type) {
        if (!collectionsType.containsKey(type)) {
            throw new IllegalArgumentException("Only a java native collection is usable for collection injection");
        }
        try {
            return (Collection) collectionsType.get(type).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isCollectionType(Class type) {
        return collectionsType.containsKey(type);
    }
}
