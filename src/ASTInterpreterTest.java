import FileReader.FileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import FileReader.TextFileReader;

import static org.junit.jupiter.api.Assertions.*;

class ASTInterpreterTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testLet() {
        RunTest("inputs/input-let.txt","outputs/output-let.txt");
    }

    @Test
    void testWhere() {
        RunTest("inputs/input-where.txt","outputs/output-where.txt");
    }

    @Test
    void testFnFrm() {
        RunTest("inputs/input-fnfrm.txt","outputs/output-fnfrm.txt");
    }

    @Test
    void testTuple() {
        RunTest("inputs/input-tuple.txt","outputs/output-tuple.txt");
    }

    @Test
    void testMultiParameter() {
        RunTest("inputs/input-multi-parameter.txt","outputs/output-multi-parameter.txt");
    }

    @Test
    void testWithIn() {
        RunTest("inputs/input-within.txt","outputs/output-within.txt");
    }

    private void RunTest(String inputFile, String outputFile) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = getExpectedOutput(outputFile);
        ASTInterpreter.interprete(inputFile);
        assertEquals(expectedOutput, outContent.toString());
    }

    private String getExpectedOutput(String filename) {
        FileReader fileReader = new TextFileReader(filename);
        ArrayList<String> fileContent= fileReader.getData();
        StringBuilder str = new StringBuilder("");
        for (String w : fileContent) {
            str.append(w).append("\r\n");
        }
        String expectedOutput = str.toString();
        return expectedOutput;
    }
}