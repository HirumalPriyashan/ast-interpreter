import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        RunTest("let");
    }

    @Test
    void testWhere() {
        RunTest("where");
    }

    @Test
    void testFnFrm() {
        RunTest("fnfrm");
    }

    @Test
    void testTuple() {
        RunTest("tuple");
    }

    @Test
    void testMultiParameter() {
        RunTest("multi-parameter");
    }

    @Test
    void testWithIn() {
        RunTest("within");
    }

    @Test
    void testOps() {
        RunTest("unary-and-binary");
    }

    @Test
    void testAt() {
        RunTest("at");
    }

    @Test
    void testSimultanious() {
        RunTest("simultanious");
    }

    @Test
    void testRec() {
        RunTest("rec");
    }

    private void RunTest(String testName) {
        String absolutePath = "D:/Semester 05/06 - Programming Languages/Labs/ast-interpreter/";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = getExpectedOutput(absolutePath + "outputs/outputs-ast/output-" + testName + ".txt");
        ASTInterpreter.interprete(absolutePath + "inputs/inputs-ast/input-" + testName + ".txt");
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