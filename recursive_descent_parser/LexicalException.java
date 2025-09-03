package recursive_descent_parser;

class LexicalException extends Exception {
    public LexicalException(String message) {
        super("Token: " + message + " is invalid");
    }
}