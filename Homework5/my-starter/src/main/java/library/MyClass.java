package library;

import org.springframework.beans.factory.InitializingBean;

public class MyClass implements InitializingBean {
    public void printInfo() {
        System.out.println(this.getClass().getSimpleName() + " is created.");
    }

    @Override
    public void afterPropertiesSet() {
        printInfo();
    }
}
