package test_01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dshvedchenko on 6/3/16.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("springbeans/beans1.xml");

        PStates obj = (PStates) context.getBean("helloWorld");

        System.out.println( obj.getStatesList().stream().map( (v1) -> v1.length() ).reduce(0, (v1, v2) -> v1- v2)   );

        System.out.println("args = [" + obj.getTB().getName() + "]");
    }
}
