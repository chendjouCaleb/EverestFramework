package Everest.Framework.InversionOfControl.DI.Lookup;

import Everest.Framework.InversionOfControl.DI.ComponentBuilder.ComponentCollectionBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentCollection;
import Everest.Framework.InversionOfControl.DI.ComponentProvider;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static Everest.Framework.InversionOfControl.Message.COLLECTION_WITH_NON_NATIVE_COLLECTION_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionLookupTest {

    private Integer intValue1 = 10, intValue2 = 15, intValue3 = 20;
    private Long longValue1 = 10L, longValue2 = 15L, longValue3 = 20L;
    private CollectionLookup collectionLookup;

    @BeforeEach
    void setUp()  {
        ComponentRegister register = new ComponentRegister();

        register.addSingleton(intValue1)
                .addSingleton(intValue2)
                .addSingleton(intValue3)
                .addSingleton(longValue1)
                .addSingleton(longValue2)
                .addSingleton(longValue3);

        ComponentCollectionBuilder builder = new ComponentCollectionBuilder();
        ComponentCollection components = builder.build(register);
        ComponentProvider componentProvider = new ComponentProvider(components);
        LookupEngine lookupEngine = componentProvider.getLookupEngine();

        collectionLookup = new CollectionLookup(lookupEngine);
    }

   @Test
    void look() {
       Collection intCollection = collectionLookup.look(Collection.class, Integer.class);
       Collection longCollection = collectionLookup.look(Collection.class, Long.class);

       assertEquals(3, intCollection.size());
       assertEquals(3, longCollection.size());
       assertTrue(intCollection.contains(intValue1) && intCollection.contains(intValue2) && intCollection.contains(intValue3));
       assertTrue(longCollection.contains(longValue1) && longCollection.contains(longValue2) && longCollection.contains(longValue3));
   }

   @Test
    void look_Collection_With_NonRegisteredType() {
        assertEquals(0, collectionLookup.look(Collection.class, Double.class).size());
   }

   @Test
    void try_look_withNonRegisteredCollectionType() {
       Throwable th = Assertions.assertThrows(IllegalArgumentException.class,
               () -> collectionLookup.look(CollectionType.class, Double.class).size());

       assertEquals(COLLECTION_WITH_NON_NATIVE_COLLECTION_TYPE, th.getMessage());
   }

   @Test
   void checkCollectionTypeBinding() {
       assertEquals(ArrayList.class, collectionLookup.look(Iterable.class, String.class).getClass());
       assertEquals(collectionLookup.look(Collection.class, String.class).getClass(), ArrayList.class);
       assertEquals(collectionLookup.look(List.class, String.class).getClass(), ArrayList.class);
       assertEquals(collectionLookup.look(ArrayList.class, String.class).getClass(), ArrayList.class);

       assertEquals(collectionLookup.look(LinkedList.class, String.class).getClass(), LinkedList.class);

       assertEquals(collectionLookup.look(Vector.class, String.class).getClass(), Vector.class);

       assertEquals(collectionLookup.look(Stack.class, String.class).getClass(), Stack.class);

       assertEquals(collectionLookup.look(Queue.class, String.class).getClass(), PriorityQueue.class);
       assertEquals(collectionLookup.look(PriorityQueue.class, String.class).getClass(), PriorityQueue.class);

       assertEquals(collectionLookup.look(Deque.class, String.class).getClass(), ArrayDeque.class);
       assertEquals(collectionLookup.look(ArrayDeque.class, String.class).getClass(), ArrayDeque.class);

       assertEquals(collectionLookup.look(Set.class, String.class).getClass(), HashSet.class);
       assertEquals(collectionLookup.look(HashSet.class, String.class).getClass(), HashSet.class);

       assertEquals(collectionLookup.look(LinkedHashSet.class, String.class).getClass(), LinkedHashSet.class);

       assertEquals(collectionLookup.look(SortedSet.class, String.class).getClass(), TreeSet.class);
       assertEquals(collectionLookup.look(TreeSet.class, String.class).getClass(), TreeSet.class);
   }

   private class CollectionType extends ArrayList {}
}