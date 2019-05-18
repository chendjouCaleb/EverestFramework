package Everest.Framework.InversionOfControl.Utils;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The utility class which contains methods to manipulate {@link Class} type.
 *
 * @author Chendjou
 * @version 1
 * @since 28-04-2019
 */
public class ClassUtils {
    private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);
    /**
     * Checks if a type is concrete, in other words, if we can invoke the constructor of object of this type.
     * @param type The type to check.
     * @return {@code true} if the type is concrete.
     */
    public static boolean isConcrete(Class<?> type){
        int mod = type.getModifiers();
        return (mod & 1536) == 0;
    }

    public static Collection<Class<?>> getClassOfPackage(String packageName) {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
        FilterBuilder filterBuilder = new FilterBuilder();
        filterBuilder.include(FilterBuilder.prefix(packageName));

        //filterBuilder.include(FilterBuilder.prefix("org"));
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(filterBuilder));
        Set<String> types = reflections.getAllTypes();
        logger.info("The package {} have {} classes",packageName, types.size());
        return reflections.getSubTypesOf(Object.class);
    }
}
