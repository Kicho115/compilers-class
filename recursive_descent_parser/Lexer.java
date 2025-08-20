package recursive_descent_parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LexicalException extends Exception {
    public LexicalException(String message) {
        super("Token: " + message + " is invalid");
    }
}

public class Lexer {
    private ArrayList<TokenType> types = new ArrayList<>();
    private ArrayList<Token> tokens = new ArrayList<>();

    public Lexer() {
        types.add(new TokenType("NUMERO", "-?[0-9]+(\\.([0-9]+))?"));
        types.add(new TokenType("CADENA", "\".*\""));
        types.add(new TokenType("OPARITMETICO", "[*/+-]"));
        types.add(new TokenType("OPRELACIONAL", "<=|>=|==|<|>|!="));
        types.add(new TokenType("IGUAL", "="));
        types.add(new TokenType("COMA", ","));
        types.add(new TokenType("PARENTESISIZQ", "\\("));
        types.add(new TokenType("PARENTESISDER", "\\)"));
        types.add(new TokenType("INICIOPROGRAMA", "inicio-programa"));
        types.add(new TokenType("FINPROGRAMA", "fin-programa"));
        types.add(new TokenType("LEER", "leer"));
        types.add(new TokenType("ESCRIBIR", "escribir"));
        types.add(new TokenType("SI", "si"));
        types.add(new TokenType("ENTONCES", "entonces"));
        types.add(new TokenType("FINSI", "fin-si"));
        types.add(new TokenType("MIENTRAS", "mientras"));
        types.add(new TokenType("FINMIENTRAS", "fin-mientras"));
        types.add(new TokenType("VARIABLE", "[a-zA-Z_] [a-zA-Z0-9_]*"));
        types.add(new TokenType("ESPACIO", "[ \t\f\r\n]+"));
        types.add(new TokenType("ERROR", "[^\t\f\n]+"));
    }

    public void analyze(String input) throws LexicalException {
        StringBuffer er = new StringBuffer();
        for (TokenType tt : types)
            er.append(String.format("(?<%s>%s)", tt.getName(), tt.getPattern()));
        Pattern p = Pattern.compile(new String(er.substring(1)));
        Matcher m = p.matcher(input);
        while (m.find()) {
            for (TokenType tt : types) {
                if (m.group("ESPACIO") != null)
                    continue;
                else if (m.group(tt.getName()) != null) {
                    if (tt.getName().equals("ERROR"))
                        throw new LexicalException(m.group(tt.getName()));
                    String name = m.group(tt.getName());
                    if (tt.getName().equals("CADENA")) {
                        name = name.substring(1, name.length() - 1);
                    }
                    tokens.add(new Token(tt, name));
                    break;
                }
            }
        }
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
