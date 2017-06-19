package springboot;

import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.Exchange;
/**
 * Created by dhruvpratap on 6/12/17.
 */
public class MyCamelStrategy implements AggregationStrategy {


   public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        String oldBody = oldExchange.getIn().getBody(String.class);
        String newBody = newExchange.getIn().getBody(String.class);
        oldExchange.getIn().setBody(oldBody + "+" + newBody);
        System.out.println("OLD EXCHANGE!!!!! " + oldExchange.getIn().getBody());
        return oldExchange;
    }
}
