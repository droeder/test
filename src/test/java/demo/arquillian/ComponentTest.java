package demo.arquillian;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;

import static org.junit.Assert.*;


@RunWith(Arquillian.class)
public class ComponentTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive javaArchive = ShrinkWrap
                .create(JavaArchive.class)
                .addClasses(Component.class)
//                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println("javaArchive = " + javaArchive);
        return javaArchive;
    }


    @Inject
    private Component component;

    @Test
    public void givenWord_WhenUppercase_ThenLowercase(){
        assertEquals("Message, MESSAGE",component.message(("MESSAGE")));

        component.sendMessage(System.out, "MESSAGE");
    }
}
