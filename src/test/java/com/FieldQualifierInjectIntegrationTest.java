package com;

import com.config.ArbitraryDependency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = ArbitraryDependency.class
)
public class FieldQualifierInjectIntegrationTest {

    @Inject
    private ArbitraryDependency dependency1;
    @Inject
    private ArbitraryDependency dependency2;

    @Test
    public void givenInjectQualifier_WhenOnField_ThenDependency1Valid(){
        assertNotNull(dependency1);
        assertEquals("test label",dependency1.toString());
    }

    @Test
    public void givenInjectQualifier_WhenOnField_ThenDependency2Valid(){
        assertNotNull(dependency2);
        assertEquals("test label",dependency2.toString());
    }

}
