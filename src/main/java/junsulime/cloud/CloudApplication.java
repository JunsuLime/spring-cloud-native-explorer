package junsulime.cloud;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CloudApplication {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classic.xml");
    }

    public void setConfigurationProjectName(String projectName) {
        System.out.println("Project name is " + projectName);
    }

}
