package org.lis.core.scanner;

public record Token(TokenTypes tokenType, String lexeme, Object literal, int line) {
    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", lexeme='" + lexeme + '\'' +
                ", literal=" + literal +
                '}';
    }
}
