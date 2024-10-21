package org.lis.core.scanner;

import org.lis.core.component.LisIOComponent;

import java.util.List;

public class ScannedData extends LisIOComponent<ScannedData> {
    private final List<Token> tokens;

    public ScannedData(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return tokens.toString();
    }
}
