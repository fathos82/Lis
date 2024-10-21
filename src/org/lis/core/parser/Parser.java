package org.lis.core.parser;

import org.lis.core.Lis;
import org.lis.core.exception.LisErrorCode;
import org.lis.core.parser.expression.LisExpressionNode;
import org.lis.core.parser.expression.LisLiteralExpressionNode;
import org.lis.core.pipeline.LisCompilationPass;
import org.lis.core.scanner.ScannedData;
import org.lis.core.scanner.Token;
import org.lis.core.scanner.TokenTypes;

import java.util.ArrayList;
import java.util.List;

import static org.lis.core.exception.LisErrorCode.*;
import static org.lis.core.scanner.TokenTypes.*;

public class Parser extends LisCompilationPass<ScannedData, ParsedData> {
    List<LisExpressionNode> expressions;
    List<Token> tokens;
    int current;

    @Override
    public ParsedData pass(ScannedData input) {
        tokens = input.getTokens();
        parse();
        return new ParsedData();
    }

    private void parse(){
        expressions = new ArrayList<>();
        while(!isAtEnd()){
            LisExpressionNode expressionNode = expression();
            expressions.add(expressionNode);

        }
        System.out.println(expressions);

    }

    private LisExpressionNode expression() {
        return literal();
    }

    private LisExpressionNode literal() {
        if (match(FALSE, TRUE, NULL, STRING_LITERAL, NUMBER_LITERAL, CHAR_LITERAL, IDENTIFIER)){
            return new LisLiteralExpressionNode(previous());
        }
        Lis.error("Invalid expression.", peek(), INVALID_EXPRESSION);
        return null;
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private boolean isAtEnd(){
        return tokens.get(current).tokenType() == TokenTypes.EOF;
    }

    private Token peek(){
        return tokens.get(current);
    }
    private Token peekNext(){
        return tokens.get(current + 1);
    }
    private boolean check(TokenTypes tokenType){
        return peek().tokenType() == tokenType;
    }
    private boolean match(TokenTypes... tokenTypes){
        for (TokenTypes tokenType : tokenTypes) {
            if (check(tokenType)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private void advance() {
        current++;
    }

    private void consume(TokenTypes tokenType, String message, LisErrorCode lisErrorCode){
        if (!match(tokenType)){
            Lis.error(message, peek(), lisErrorCode);
        }
    }

    @Override
    public Class<ScannedData> getInputType() {
        return ScannedData.class;
    }

    @Override
    public Class<ParsedData> getOutputType() {
        return ParsedData.class;
    }
}
