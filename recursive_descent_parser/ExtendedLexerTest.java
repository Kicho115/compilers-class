package recursive_descent_parser;

import java.io.FileReader;
import java.io.IOException;

public class ExtendedLexerTest {
    public static void main(String[] args) throws LexicalException {
        System.out.println("=== Prueba del lexer extendido ===\n");
        
        // Test original file
        System.out.println("1. Análisis del archivo original (test.alg):");
        testFile("c:\\Users\\oscar\\IdeaProjects\\compilers-class\\recursive_descent_parser\\test.alg");
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test extended file with variable declarations and repite
        System.out.println("2. Análisis del pseudocodigo extendido (test_extended.alg):");
        testFile("c:\\Users\\oscar\\IdeaProjects\\compilers-class\\recursive_descent_parser\\test_extended.alg");
    }
    
    public static void testFile(String fileName) throws LexicalException {
        String entrada = readProgram(fileName);
        System.out.println("Código fuente:");
        System.out.println(entrada);
        System.out.println("\nTokens reconocidos:");
        
        Lexer lexer = new Lexer();
        lexer.analyze(entrada);
        
        for (Token t : lexer.getTokens()) {
            System.out.println(t);
        }
    }

    public static String readProgram(String fileName) {
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
            System.err.println("Error leyendo archivo: " + fileName);
            return "";
        }
    }
}
