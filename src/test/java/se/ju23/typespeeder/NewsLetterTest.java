package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

public class NewsLetterTest {

    @Test
    public void testNewsLetterClassExists() {
        try {
            Class.forName("se.ju23.typespeeder.NewsLetter");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("se.ju23.typespeeder.NewsLetter class should exist.", e);
        }
    }
    @Test
    public void testNewsLetterContentLength() {
        try {
            Class<?> newsLetterClass = Class.forName("se.ju23.typespeeder.NewsLetter");

            Field contentField = newsLetterClass.getDeclaredField("content");
            assertNotNull(contentField, "Field 'content' should exist in se.ju23.typespeeder.NewsLetter.");

            assertTrue(contentField.getType().equals(String.class), "Field 'content' should be of type String.");

            String content = "This is a test newsletter to see if this will be long enough to pass the test in the NewsLetterTest class. I hope this is long enough" +
                    " please how much more of this.";
            LocalDateTime localDateTime = LocalDateTime.now();
            Object instance = newsLetterClass.getDeclaredConstructor(String.class, LocalDateTime.class).newInstance(content, localDateTime);
            Field field = newsLetterClass.getDeclaredField("content");
            field.setAccessible(true);
            String contentValue = (String) field.get(instance);

            assertTrue(contentValue.length() >= 100, "Content field length should be at least 100 characters.");
            assertTrue(contentValue.length() <= 200, "Content field length should be at most 200 characters.");

        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | InstantiationException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            fail("Error occurred while testing se.ju23.typespeeder.NewsLetter content field length.", e);
        }
    }

    @Test
    public void testNewsLetterPublishDateTime() {
        try {
            Class<?> someClass = Class.forName("se.ju23.typespeeder.NewsLetter");

            Field publishDateTime = someClass.getDeclaredField("publishDateTime");
            assertNotNull(publishDateTime, "Field 'publishDateTime' should exist in se.ju23.typespeeder.NewsLetter class.");

            assertTrue(publishDateTime.getType().equals(LocalDateTime.class), "Field 'publishDateTime' should be of type LocalDateTime.");

            //Creating test instance with test parameters.
            String content = "This is a test newsletter to see if this will be long enough to pass the test in the NewsLetterTest class. I hope this is long enough" +
                    " please how much more of this.";
            LocalDateTime localDateTime = LocalDateTime.now();
            Object instance = someClass.getDeclaredConstructor(String.class, LocalDateTime.class).newInstance(content, localDateTime);


            //Expected Time Format
            Field field = someClass.getDeclaredField("publishDateTime");
            field.setAccessible(true);
            LocalDateTime dateTimeValue = (LocalDateTime) field.get(instance);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTimeValue.format(formatter);

            //Fetching my instance time using method in class.
            Method publishDateTimeGetter = someClass.getMethod("getPublishDateTime");
            String myTime = (String) publishDateTimeGetter.invoke(instance);

            assertEquals(formattedDateTime, myTime ,"'publishDateTime' field should have format 'yyyy-MM-dd HH:mm:ss'.");

            Method getterMethod = someClass.getDeclaredMethod("getPublishDateTime");
            assertNotNull(getterMethod, "Getter method for the field 'publishDateTime' should exist.");


        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            fail("Error occurred while testing properties of se.ju23.typespeeder.NewsLetter.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
