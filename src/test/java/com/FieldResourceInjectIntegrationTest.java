package com;

import com.config.ApplicationContextTestResourceNameType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = ApplicationContextTestResourceNameType.class
)
public class FieldResourceInjectIntegrationTest {

    @Resource(name = "namedFile")    //match by name
    private File defaultFile;

    @Resource     //match by type
    private File file;


    //setter method
    private File namedFile;

    @Resource(name = "namedFile")
    protected void setNamedFile(File File) {
        this.namedFile = file;
    }

    @Test
    public void testResourceAnnotation() {
        assertNotNull(defaultFile);
        assertEquals("namedFile.txt",defaultFile.getName());
        assertEquals("namedFile.txt",file.getName());
    }

    @Test
    public void givenResourceAnnotation_WhenSetter_ThenDependencyValid(){
        assertNotNull(namedFile);
        assertEquals("namedFile.txt",namedFile.getName());
    }
}
