package algebra.spring_boot;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/endpoint")
@AllArgsConstructor
public class ProductController {

    @GetMapping
    public void showText(){
        System.out.println("Get request /test/endpoint");
    }

    @GetMapping("/{param}")
    public void showParameter(@PathVariable Integer param){
        System.out.println("Get request with parameter: " + param);
    }

    @GetMapping("/{param}/with-response")
    public TestResponse showParameterWithResponse(@PathVariable String param){
        System.out.println("Get request with parameter: " + param);
        TestResponse response = new TestResponse(param, 12);
        return response;
    }

    @GetMapping("/{param}/with-list-response")
    public List<TestResponse> showParameterWithListResponse(@PathVariable String param){
        System.out.println("Get request with parameter: " + param);
        TestResponse response = new TestResponse(param, 12);
        TestResponse response2 = new TestResponse("test", 54);
        TestResponse response3 = new TestResponse("test", 54);
        TestResponse response4 = new TestResponse("test", 54);
        TestResponse response5 = new TestResponse("test", 54);
        TestResponse response6 = new TestResponse("test", 54);
        TestResponse response7 = new TestResponse("test", 54);
        return List.of(response, response2,response3, response2,response2,response2,response2,response2,response2);
    }
}
