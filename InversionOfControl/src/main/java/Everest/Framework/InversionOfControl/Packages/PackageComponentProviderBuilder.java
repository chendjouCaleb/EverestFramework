package Everest.Framework.InversionOfControl.Packages;

import Everest.Framework.Core.Exception.NullArgumentException;
import Everest.Framework.Core.IComponentProvider;
import Everest.Framework.Core.StringUtils;
import Everest.Framework.InversionOfControl.Abstractions.ComponentDescriptor;
import Everest.Framework.InversionOfControl.DI.ComponentProviderBuilder;
import Everest.Framework.InversionOfControl.DI.ComponentRegister;
import Everest.Framework.InversionOfControl.Utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The builder to build a {@link Everest.Framework.Core.IComponentProvider}
 * by scanning a java package and retains all component type.
 *
 * @author Chendjou
 * @version 1
 * @since 11-05-2019
 */
public class PackageComponentProviderBuilder extends ComponentRegister {
    private Logger logger = LoggerFactory.getLogger(PackageComponentProviderBuilder.class);
    private List<String> packageNames = new ArrayList<>();
    private List<ITypeFilter> typeFilters = new ArrayList<>();

    public PackageComponentProviderBuilder() {
        typeFilters.add(new DefaultTypeFilter());
    }

    public PackageComponentProviderBuilder(String... packageNames) {
        this();
        this.packageNames = Arrays.asList(packageNames);
    }

    public PackageComponentProviderBuilder(List<ITypeFilter> typeFilters, String... packageNames) {
        this(packageNames);
        this.typeFilters = typeFilters;
    }

    public IComponentProvider buildComponentProvider() {
        Set<Class<?>> types = new HashSet<>();

        for(String packageName: packageNames){
             types.addAll(ClassUtils.getClassOfPackage(packageName));
        }

        logger.debug("There {} types in packages", types.size());

        types = types.stream().filter(ClassUtils::isConcrete).collect(Collectors.toSet());
        logger.debug("Context packages: {}", packageNames);
        logger.debug("There {} concretes types in packages", types.size());

        for(Class<?> type: types) {
            for(ITypeFilter filter: typeFilters){
                if(filter.isEligible(type)){
                    ComponentDescriptor descriptor = new ComponentDescriptor();
                    descriptor.setPrincipal(filter.isPrincipal(type));
                    descriptor.setLifetime(filter.getLifeTime(type));
                    descriptor.setComponentType(filter.getComponentType(type));
                    descriptor.setImplementationInstance(filter.getInstance(type));
                    descriptor.setName(filter.getName(type));
                    descriptor.setImplementationType(filter.getImplementationType(type));
                     add(descriptor);
                }
            }
        }
        ComponentProviderBuilder builder = new ComponentProviderBuilder();

        return builder.buildProvider(this);
    }

    public void addPackageName(@Nonnull String name) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("You cannot add null or empty string as package name");
        }
        packageNames.add(name);
    }

    public void addTypeFilter(ITypeFilter typeFilter){
        if(typeFilter == null){
            throw new NullArgumentException("typeFilter");
        }
        typeFilters.add(typeFilter);
    }

    public PackageComponentProviderBuilder setTypeFilters(List<ITypeFilter> typeFilters) {
        this.typeFilters = typeFilters;
        return this;
    }
}
