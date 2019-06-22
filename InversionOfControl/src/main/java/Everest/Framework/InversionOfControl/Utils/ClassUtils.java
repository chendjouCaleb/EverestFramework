package Everest.Framework.InversionOfControl.Utils;

import Everest.Framework.Core.Exception.InputOutputException;
import com.google.common.reflect.ClassPath;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

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
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassPath cp = ClassPath.from(Thread.currentThread().getContextClassLoader());
            for(ClassPath.ClassInfo info : cp.getTopLevelClassesRecursive(packageName)) {
                classes.add(info.load());
            }
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
        logger.debug("There are {} classes in '{}'", classes.size(), packageName);
        return classes;
    }
    public static Collection<Class<?>> getClassOfPackages(String packageName) {
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
