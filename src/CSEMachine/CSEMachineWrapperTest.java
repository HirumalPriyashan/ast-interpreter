package CSEMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CSEMachineWrapperTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testLet() {
        RunTest("1");
    }

    private void RunTest(String testName) {
        String absolutePath = "D:/Semester 05/06 - Programming Languages/Labs/ast-interpreter/";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = getExpectedOutput(absolutePath + "outputs/outputs-cse/output-" + testName + ".txt");
        CSEMachineWrapper.flatten(absolutePath + "inputs/inputs-cse/input-" + testName + ".txt");
        assertEquals(expectedOutput, outContent.toString());
    }

    private String getExpectedOutput(String filename) {
        try {
            return Files.readString(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
}
