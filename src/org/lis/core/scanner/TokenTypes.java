package org.lis.core.scanner;

public enum TokenTypes {

    // class keywords
    CLASS, // class
    STRUCT, // struct
    ENUM, // enum
    ABSTRACT, // abstract
    CONSTRUCTOR, // _constructor
    CONSTRUCTOR_FACTORY, // _constructor.AnyName
    THIS, // this
    SUPER, // super
    NEW, // new
    ANNOTATION, // @anyName
    PRIVATE, // private
    PUBLIC, // public
    PROTECTED, // protected

    // code keywords
    IF, // if
    ELSE, // else
    SWITCH, // switch
    CASE, // case
    DEFAULT, // default
    FUNCTION, // function
    RETURN, // return
    CONTINUE, // continue
    BREAK, // break
    FOR, // for
    WHILE, // while
    VOID, // void
    IMPORT, // import

    // symbols
    DOT, // .
    SEMI_COLON, // ;
    COLON, // :
    COMMA, // ,
    L_PAREN, // (
    R_PAREN, // )
    L_BRACKET, // [
    R_BRACKET, // ]
    L_BRACE, // {
    R_BRACE, // }

    // Boolean Comparators
    GREATER, // >
    GREATER_EQUAL, // >=
    LESS, // <
    LESS_EQUAL, // <=
    NOT_EQUAL, // !
    EQUAL_EQUAL, // ==

    // Boolean Operators
    AND, // &&
    OR, // ||
    NOT, // !

    // Math Operators
    PLUS, // +
    MINUS, // -
    MULTIPLY, // *
    DIVIDE, // /
    POW, // **
    INCREMENT, // ++
    DECREMENT, // --
    MOD, // %

    // Assignment Operators
    EQUAL, // =
    PLUS_EQUAL, // +=
    MINUS_EQUAL, // -=
    MULTIPLY_EQUAL, // *=
    DIVIDE_EQUAL, // /=
    POW_EQUAL, // **=
    MOD_EQUAL, // %=

    //  Keywords Primitive Types
    U_BYTE,
    BYTE, // byte
    STRING, // string
    U_INT,
    INT, // int
    FLOAT, // float
    CHAR, // char
    BOOL, // boolean
    VAR, // var

    // Literals
    IDENTIFIER, // name of variables
    STRING_LITERAL,
    NUMBER_LITERAL,
    CHAR_LITERAL,
    TRUE, // true
    FALSE, // false
    NULL, // null

    // ERROR
    BAD_TOKEN,
    // END
    EOF // end of file
}
