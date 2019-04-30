package Everest.Framework.Mvc.Routing;

import Everest.Framework.Core.Inject.Instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Instance
public class RouteBuilder {
    private List<RouteDescriptor> routeDescriptors = new ArrayList<>();

    public String url(String name, Map<String, String> params){
        RouteDescriptor route = getRouteModel(name);
        UrlGenerator generator = new UrlGenerator(route.getMapping(), params);
        return generator.generate();
    }

    public String url(String name){
        RouteDescriptor route = getRouteModel(name);
        UrlGenerator generator = new UrlGenerator(route.getMapping(), new HashMap<>());
        return generator.generate();
    }

    public String url(String name, Integer... params){
        RouteDescriptor model = getRouteModel(name);
        String mapping = model.getMapping();
        for (Object param:params){
            mapping = mapping.replaceFirst("\\{[\\w]+\\}", param.toString());
        }
        return "/"+mapping;
    }

    public String url(String name, Object[] params){
        RouteDescriptor model = getRouteModel(name);
        String mapping = model.getMapping();
        for (Object param:params){
            mapping = mapping.replaceFirst("\\{[\\w]+\\}", param.toString());
        }
        return "/"+mapping;
    }

    private RouteDescriptor getRouteModel(String name) {
        RouteDescriptor route  = routeDescriptors.stream()
                .filter(routeDescriptor -> routeDescriptor.getActionDescriptor().getName().equals(name))
                .findFirst().orElse(null);
        if(route == null){
            route  = routeDescriptors.stream()
                    .filter(routeDescriptor -> routeDescriptor.getActionDescriptor().getName().equals(name + "#GET"))
                    .findFirst().orElse(null);
        }
        if(route == null){
            String regex = name.replaceFirst(".","\\.") + "#[A-Z]+";
            route  = routeDescriptors.stream()
                    .filter(routeDescriptor -> routeDescriptor.getActionDescriptor().getName().matches(regex))
                    .findFirst().orElse(null);
        }
        if(route == null){
            throw new RouteNotFoundException("Aucune route trouvé avec le nom '" + name + "' ou '" + name + "#(verbs)' n'a été trouvé");
        }
        return route;
    }


    public void setRouteDescriptors(List<RouteDescriptor> routeDescriptors) {
        this.routeDescriptors = routeDescriptors;
    }
}
