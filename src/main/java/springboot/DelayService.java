package springboot;

import org.springframework.stereotype.Service;

@Service
public class DelayService {

    public String execute(String accountId) {
        try {
            System.out.println("AccountId in delay1 " + accountId);
            for (int i = 1; i < 10; i++) {
                Thread.sleep(200);
                System.out.print("Printing for "+ accountId + "no here! " + i);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return "delay1 done with "+ accountId;

    }
}
