package org.lis.core.scanner;
import static org.lis.core.exception.LisErrorCode.*;
import static org.lis.core.scanner.TokenTypes.*;

import org.lis.core.Lis;
import org.lis.core.pipeline.LisCompilationPass;
import org.lis.core.util.LisFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// TODO: Tratar float ,char, u_byte, u_int
public class Scanner extends LisCompilationPass<LisFile, ScannedData> {
    int startIndex = 0;
    int currentIndex = 0;
    int line = 1;
    private String source;
    List<Token> tokens;
    private static final Map<String, TokenTypes>  keywords;

    static {
        keywords = new HashMap<String, TokenTypes>();
        // class keywords
        keywords.put("class", CLASS);
        keywords.put("enum", ENUM);
        keywords.put("abstract", ABSTRACT);
        keywords.put("struct", STRUCT);
        keywords.put("_constructor", CONSTRUCTOR);
        keywords.put("this", THIS);
        keywords.put("super", SUPER);
        keywords.put("new", NEW);
//        keywords.put("enum", ANNOTATION);
        keywords.put("private", PRIVATE);
        keywords.put("public", PUBLIC);
        keywords.put("protected", PROTECTED);
        // code keywords
        keywords.put("if", IF);
        keywords.put("else", ELSE);
        keywords.put("switch", SWITCH);
        keywords.put("case", CASE);
        keywords.put("default", DEFAULT);
        keywords.put("function", FUNCTION);
        keywords.put("return", RETURN);
        keywords.put("continue", CONTINUE);
        keywords.put("break", BREAK);
        keywords.put("for", FOR);
        keywords.put("while", WHILE);
        keywords.put("void", VOID);
        keywords.put("import", IMPORT);
        //  Keywords Primitive Types
        keywords.put("u_byte", U_BYTE);
        keywords.put("byte", BYTE);
        keywords.put("string", STRING);
        keywords.put("u_int", U_INT);
        keywords.put("int", INT);
        keywords.put("float", FLOAT);
        keywords.put("char", CHAR);
        keywords.put("boolean", BOOL);
        keywords.put("var", VAR);

        //  literals
        keywords.put("true", TRUE);
        keywords.put("false", FALSE);
        keywords.put("null", NULL);



    }



    @Override
    public ScannedData pass(LisFile input) {
        source = input.getSource();
        scanTokens();
        return new ScannedData(tokens);
    }

    private void scanTokens() {
        tokens = new ArrayList<Token>();
        while (!isAtEnd()){
            startIndex = currentIndex;
            scanToken();
        }
        addToken(EOF);
    }

    private void scanToken() {
        char c = advance();
        switch (c){

            // code symbols:
            case '.':
                addToken(DOT);
                break;
            case ';':
                addToken(SEMI_COLON);
                break;
            case ',':
                addToken(COMMA);
                break;
            case '(':
                addToken(L_PAREN);
                break;
            case ')':
                addToken(R_PAREN);
                break;
            case '[':
                addToken(L_BRACKET);
                break;
            case ']':
                addToken(R_BRACKET);
                break;
            case '{':
                addToken(L_BRACE);
                break;
            case '}':
                addToken(R_BRACE);
                break;
            case ':':
                addToken(COLON);
                break;
            // Boolean Comparators
            case '>':
                if (match('=')){
                    addToken(GREATER_EQUAL);
                }
                else {
                    addToken(GREATER);
                }
                break;
            case '<':
                if (match('=')){
                    addToken(LESS_EQUAL);
                }
                else {
                    addToken(LESS);
                }
                break;
            case '!':
                if (match('=')){
                    addToken(NOT_EQUAL);
                }
                else {
                    addToken(NOT);
                }
            // Boolean Operators
                break;
            case '&':
                if (match('&')){
                    addToken(AND);
                }
                break;
            case '|':
                if (match('|')){
                    addToken(OR);
                }
                break;
            case '=':
                if (match('=')){
                    addToken(EQUAL_EQUAL);
                }
                else {
                    addToken(EQUAL);
                }
                break;
            // Math Operators and Assignment Operators
            case '+':
                if (match('=')){
                    addToken(PLUS_EQUAL);
                }
                else if (match('+')){
                    addToken(INCREMENT);
                }
                else {
                    addToken(PLUS);
                }
                break;
            case '-':
                if (match('=')){
                    addToken(MINUS_EQUAL);
                }
                else if (match('-')){
                    addToken(DECREMENT);
                }
                else {
                    addToken(MINUS);
                }
                break;
            case '*':
                if (match('*')){
                    if (match('=')){
                        addToken(POW_EQUAL); // **=
                    }
                    else {
                        addToken(POW); // **
                    }
                }
                else if (match('=')){
                    addToken(MULTIPLY_EQUAL); // *=
                }
                else {
                    addToken(MULTIPLY); // *
                }
                break;
            case '/':
                if (match('=')){
                    addToken(DIVIDE_EQUAL);
                }
                else if (match('/')){
                    comment();
                }
                break;
            case '@':
                addToken(ANNOTATION);
                break;
            case '"':
                string();
                break;
            case '\t':
            case '\r':
            case ' ':
                break;
            default:
                String lineSeparator = System.getProperty("line.separator");
                if(c == lineSeparator.charAt(0)){
                    line++;
                    break;
                }
                if (isDigit(c)){
                    number();
                }
                else if (isAlphaNumeric(c)){
                     identifier();
                }
                break;
        }

    }

    private void comment() {
        while (!check('\n')) advance();
    }

    private void string() {
        while (!isAtEnd() && !check('"')) advance();
        if (isAtEnd()){
            Token token = new Token(BAD_TOKEN, "",null, line);
            Lis.error("Unterminated string.", token, UNTERMINATED_STRING);
        }
        advance();
        String text = source.substring(startIndex+1, currentIndex-1);
        addToken(STRING_LITERAL,text, text);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    private boolean isAlpha(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private void number() {
        while (!isAtEnd() && isAlphaNumeric(peek())) advance();
        String text = source.substring(startIndex, currentIndex);
        addToken(NUMBER_LITERAL,text, Float.parseFloat(text));
    }

    private void identifier() {
        while (!isAtEnd() && isAlphaNumeric(peek())) advance();
        String text = source.substring(startIndex, currentIndex);

        addToken(keywords.getOrDefault(text, IDENTIFIER), text);
    }

    private void addToken(TokenTypes tokenType) {
        String text = source.substring(startIndex, currentIndex);
        addToken(tokenType, text, null);
    }

    private void  addToken(TokenTypes tokenType, Object literal) {
        String text = source.substring(startIndex, currentIndex);
        addToken(tokenType, text, literal);
    }

    private void  addToken(TokenTypes tokenType, String lexeme, Object literal) {
        Token token = new Token(tokenType, lexeme, literal, line);
        tokens.add(token);
    }

    private boolean check(char c){
        return peek() == c;
    }

    private boolean match(char c){
        if (isAtEnd()) return false;
        if (!check(c)) return false;
        advance();
        return true;
    }

    private char advance() {
        return source.charAt(currentIndex++);
    }
    private char peek(){
        return source.charAt(currentIndex);
    }

    private boolean isAtEnd() {
        return currentIndex >= source.length();
    }

    @Override
    public Class<LisFile> getInputType() {
        return LisFile.class;
    }

    @Override
    public Class<ScannedData> getOutputType() {
        return ScannedData.class;
    }
}
