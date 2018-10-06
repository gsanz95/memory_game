package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import misc.EmployeeErrorException;
import misc.EmptyFileException;
import org.junit.BeforeClass;
import org.junit.Test;

import misc.RecordProcessor;

public class TestRecordProcessorWithExceptions {
    private static String expectedFromData1;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        StringBuffer fileContents = new StringBuffer();
        Scanner fileInput = new Scanner(new File("expected1.txt"));
        while(fileInput.hasNextLine())
            fileContents.append(fileInput.nextLine() + "\n");
        expectedFromData1 = fileContents.toString();
        fileInput.close();
    }

    @Test
    public void testFileData1() throws EmptyFileException, EmployeeErrorException {
        assertEquals(expectedFromData1, RecordProcessor.processFile("data1.txt"));
    }

    @Test(expected=Exception.class)
    public void testFileData2() throws EmptyFileException, EmployeeErrorException {
        assertEquals(null, RecordProcessor.processFile("data2.txt"));
    }

    @Test(expected=Exception.class)
    public void testFileData3() throws EmptyFileException, EmployeeErrorException {
        assertEquals(null, RecordProcessor.processFile("data3.txt"));
    }
}