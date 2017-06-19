package springboot;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringBootCamel  {

    @Autowired
    CamelContext camelContext;

    public static void main(String args[]){
        SpringApplication.run(SpringBootCamel.class, args);
    }

    @Bean
    public RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("direct:start")
                        //.log("accountId in hello is {header.accountId}");
                        .multicast(new MyCamelStrategy())
                        .log("accountId in hello is {header.accountId}")
                        .parallelProcessing(true)
                        .to("http://localhost:8080/delay1?accountId={header.accountId}", "http://localhost:8080/delay2?accountId={header.accountId}")
                        .end();

            }
        };
    }


    @RequestMapping(value="/delay1")
    @ResponseBody
    public String delayMethod (@RequestParam(value="accountId") String accountId ){

        try {
            System.out.println("AccountId in delay1 " + accountId);
            for (int i = 2; i < 10; i++) {
                Thread.sleep(200);
                System.out.print("Printing for "+ accountId + "no here! " + i);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return "delay1 done with "+ accountId;

    }


    @RequestMapping("/delay2")
    public String delayMethod2 (@RequestParam(value="accountId") String accountId ){

        try {
            for (int i = 11; i < 20; i++) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(200);
                System.out.print("Printing for "+ accountId + "no here! " + i);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return "delay2 done with "+ accountId;

    }

    @RequestMapping("/hello")
    public String hello (@RequestParam(value="accountId") String accountId ){

        //Endpoint endpoint, Object body, String header, Object headerValue
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.requestBodyAndHeader("direct:start", null, accountId, accountId);
        return "delay2 done with "+ accountId;

    }

}
