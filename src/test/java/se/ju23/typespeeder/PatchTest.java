package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class PatchTest {

    @Test
    public void testPatchClassExists() {
        try {
            Class.forName("se.ju23.typespeeder.Patch");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("se.ju23.typespeeder.Patch class should exist.", e);
        }
    }

    @Test
    public void testPatchProperties() {
        try {
            Class<?> someClass = Class.forName("se.ju23.typespeeder.Patch");

            Field patchVersion = someClass.getDeclaredField("patchVersion");
            assertNotNull(patchVersion, "Field 'patchVersion' should exist in the se.ju23.typespeeder.Patch class.");
            assertTrue(patchVersion.getType().equals(String.class), "Field 'patchVersion' should be of type String.");

            Field realeaseDateTime = someClass.getDeclaredField("realeaseDateTime");
            assertNotNull(realeaseDateTime, "Field 'realeaseDateTime' should exist in se.ju23.typespeeder.Patch class.");

            assertTrue(realeaseDateTime.getType().equals(LocalDateTime.class), "Field 'realeaseDateTime' should be of type LocalDateTime.");

            //Creating test instance with test parameters.
            String patch = "Test";
            LocalDateTime localDateTime = LocalDateTime.now();
            Object instance = Class.forName("se.ju23.typespeeder.Patch").getDeclaredConstructor(String.class, LocalDateTime.class).newInstance(patch, localDateTime);


            //Expected Time Format
            Field field = someClass.getDeclaredField("realeaseDateTime");
            field.setAccessible(true);
            LocalDateTime dateTimeValue = (LocalDateTime) field.get(instance);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTimeValue.format(formatter);

            //Fetching my instance time using method in class.
            Method realeaseDateTimeGetter = someClass.getMethod("getRealeaseDateTime");
            String myTime = (String) realeaseDateTimeGetter.invoke(instance);

            assertEquals(formattedDateTime, myTime, "'realeaseDateTime' field should have format 'yyyy-MM-dd HH:mm:ss'.");

            Method getterMethod = someClass.getDeclaredMethod("getRealeaseDateTime");
            assertNotNull(getterMethod, "Getter method for field 'realeaseDateTime' should exist.");


        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            fail("Error occurred while testing properties of se.ju23.typespeeder.Patch.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
