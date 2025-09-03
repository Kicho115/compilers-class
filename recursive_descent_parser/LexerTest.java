package recursive_descent_parser;

import java.io.FileReader;
import java.io.IOException;

public class LexerTest {
    public static void main(String[] arg) throws LexicalException {
        String entrada = readProgram("c:\\Users\\oscar\\IdeaProjects\\compilers-class\\recursive_descent_parser\\test.alg");
        Lexer lexer = new Lexer();
        lexer.analyze(entrada);
        System.out.println("*** Análisis léxico ***\n");
        for (Token t : lexer.getTokens()) {
            System.out.println(t);
        }
    }

    public static String readProgram (String fileName) {
        String input = "";

        try {
            FileReader fileReader = new FileReader(fileName);
            int character;

            while ((character = fileReader.read()) != -1) {
                input += (char) character;
            }

            fileReader.close();
            return input;
        } catch (IOException e) {
            return "";
        }
    }
}
