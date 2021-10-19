import FileReader.FileReader;
import FileReader.TextFileReader;
import java.util.ArrayList;

import ASTTreeGenerator.ASTGenerator;
import ASTTreeGenerator.Node;

public class Main {

    // let check x = 
    //     not Isinteger x -> 'error' |
    //     (x ls 0 -> 'negative' | 'positive')
    // in
    // print(check (-8), check 8)
    public static void main(String[] args) {
        // get ast content from file
        FileReader fileReader = new TextFileReader("input.txt");
        ArrayList<String> fileContent= fileReader.getData();

        // generate AST as a tree
        ASTGenerator astGenerator = new ASTGenerator();
        Node root = astGenerator.generateAST(fileContent);
        astGenerator.printTree();
    }
}
