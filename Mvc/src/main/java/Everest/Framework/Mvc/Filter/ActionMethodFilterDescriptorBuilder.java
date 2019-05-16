package Everest.Framework.Mvc.Filter;

import Everest.Framework.Core.Inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

@Singleton
public class ActionMethodFilterDescriptorBuilder {
    private Logger logger = LoggerFactory.getLogger(ActionMethodFilterDescriptorBuilder.class);
    private AnnotationFilterGetter annotationFilterGetter;
    private FilterDescriptorCollectionBuilder filterDescriptorCollectionBuilder;

    public ActionMethodFilterDescriptorBuilder(AnnotationFilterGetter annotationFilterGetter,
                                               FilterDescriptorCollectionBuilder filterDescriptorCollectionBuilder) {
        this.annotationFilterGetter = annotationFilterGetter;
        this.filterDescriptorCollectionBuilder = filterDescriptorCollectionBuilder;
    }

    public FilterDescriptorCollection getFiltersDescriptors(@Nonnull Class type, @Nonnull Method method) {
        FilterDescriptorCollection collection = new FilterDescriptorCollection();
        collection.addAll(getFiltersDescriptors(type));
        collection.addAll(getFiltersDescriptors(method));


        return collection;
    }

    public FilterDescriptorCollection getFiltersDescriptors(@Nonnull Method method) {
        List<Annotation> annotations = annotationFilterGetter.getFilterAnnotations(method);
        FilterDescriptorCollection collection = getFilterDescriptors(annotations);
        collection.forEach(c -> logger.debug("Filter: [ Action method = {}, Filter annotation = {}, Filter type = {}, " +
                        "Filter class = {}, Filter method = {} ]", method.getName(), c.getAnnotation().annotationType().getSimpleName(),
                c.getFilterType(), c.getType().getSimpleName(), c.getMethod().getName()));


        return collection;
    }

    public FilterDescriptorCollection getFiltersDescriptors(@Nonnull Class type) {
        return getFilterDescriptors(annotationFilterGetter.getFilterAnnotations(type));
    }

    private FilterDescriptorCollection getFilterDescriptors(List<Annotation> annotations) {
        FilterDescriptorCollection descriptorCollection = new FilterDescriptorCollection();

        for (Annotation annotation : annotations) {
            FilterBy filterBy = annotation.annotationType().getAnnotation(FilterBy.class);
            FilterDescriptorCollection descriptors = filterDescriptorCollectionBuilder.build(filterBy.filter());
            descriptors.forEach(d -> d.setAnnotation(annotation));

            descriptorCollection.addAll(descriptors);
        }

        return descriptorCollection;
    }
}
