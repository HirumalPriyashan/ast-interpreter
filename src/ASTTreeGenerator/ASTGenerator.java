package ASTTreeGenerator;

import java.util.ArrayList;

public class ASTGenerator {

    private class TokenWithDepth {
    
        private String token;
        private int depth;
        
        private TokenWithDepth(String token, int depth){
            this.token = token;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "{" + this.token + ", " + this.depth +
                "}";
        }
    }

    public ASTGenerator() {
    
    }
    public void getASTTree(ArrayList<String> astList){
        for (String line : astList) {
            String token = line.replace(".", "");
            int depth = line.length() - token.length();
            TokenWithDepth tokenWithDepth = new TokenWithDepth(token, depth);
            System.out.println(tokenWithDepth);
        }
        return;
    }
}
