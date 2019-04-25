package Everest.Framework.Mvc.ActionResultExecutor;

import Everest.Framework.Http.DefaultHttpContext;
import Everest.Framework.Http.HttpContext;
import Everest.Framework.Http.StatusCode;
import Everest.Framework.Mvc.ActionInvocation.ActionInvocationResult;
import Everest.Framework.Mvc.ResponseFormatter.JsonResponseFormatter;
import Everest.Framework.Mvc.ResponseFormatter.ResponseFormatter;
import Everest.Framework.Mvc.ResponseFormatter.ResponseFormatterSelector;
import Everest.Framework.Mvc.Service.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionResultExecutorTest {

    private ActionResultExecutor executor;
    private JsonConverter jsonConverter;
    private ResponseFormatterSelector responseFormatterSelector;
    private ResponseFormatter responseFormatter;
    @BeforeEach
    void setUp() {
        jsonConverter = new JsonConverter();
        responseFormatterSelector = new ResponseFormatterSelector();
        responseFormatterSelector.addFormatter(new JsonResponseFormatter(jsonConverter));

        ResponseFormatter formatter = new ResponseFormatter(responseFormatterSelector);
        ActionResultExecutorProvider provider = new ActionResultExecutorProvider();

        provider.addExecutor(new EntityResultExecutor(formatter));
        provider.addExecutor(new EntityObjectResultExecutor());


        executor = new ActionResultExecutor(provider);
    }

    @Test
    void execute_with_void_result_type(){
        HttpContext httpContext = new DefaultHttpContext();
        ActionInvocationResult result = new ActionInvocationResult();
        result.setVoid(true);
        executor.execute(httpContext, result);

        assertEquals(StatusCode.NO_CONTENT.value(), httpContext.getResponse().statusCode());
        assertEquals("", httpContext.getResponse().writer().toString());
    }

    @Test
    void execute_with_object_an_resultExecutor(){
        HttpContext httpContext = new DefaultHttpContext();
        EntityObject entityObject = new EntityObject("The entity Object");
        ActionInvocationResult result = new ActionInvocationResult();
        result.setObjectResult(entityObject);
        executor.execute(httpContext, result);

        assertEquals(entityObject.getContent(), httpContext.getResponse().writer().toString());
    }

    @Test
    void execute_with_object_without_ResultExecutor(){

        HttpContext httpContext = new DefaultHttpContext();
        ActionInvocationResult result = new ActionInvocationResult();
        FakeEntity entity = new FakeEntity("name", "entity content");
        result.setObjectResult(entity);
        executor.execute(httpContext, result);

        assertEquals(200, httpContext.getResponse().statusCode());
        assertEquals(jsonConverter.toJSON(entity), httpContext.getResponse().writer().toString());
        assertEquals(httpContext.getOptions().getResponseContentType(), httpContext.getResponse().contentType());
        System.out.println("Content type: " + httpContext.getResponse().contentType());
    }
}

class EntityObject{
    private String content;

    public EntityObject(String content) { this.content = content; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}

class FakeEntity{
    private String name;
    private String content;

    public FakeEntity(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class EntityObjectResultExecutor implements IResultExecutor<EntityObject>{

    @Override
    public void execute(HttpContext httpContext, EntityObject result) {
        httpContext.getResponse().write(result.getContent());
    }
}