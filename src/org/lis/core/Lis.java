package org.lis.core;

import org.lis.core.exception.LisErrorCode;
import org.lis.core.parser.Parser;
import org.lis.core.pipeline.LisCompilationPass;
import org.lis.core.pipeline.LisCompilationPipeline;
import org.lis.core.scanner.ScannedData;
import org.lis.core.scanner.Scanner;
import org.lis.core.scanner.Token;
import org.lis.core.util.LisFile;

public class Lis {
    public static void main(String[] args) {
        LisFile file = new LisFile(args[0]);
        LisCompilationPipeline pipeline = new LisCompilationPipeline();

        pipeline.insertStage(new Scanner());
        pipeline.insertStage(new Parser());
        pipeline.run(file);

    }

    public static void error(String message, Token badToken, LisErrorCode code) {

        if(!badToken.lexeme().isEmpty()) {
            System.err.println("Error at line " + badToken.line() + ", '" + badToken.lexeme() + "': " + message);
        }else {
            System.err.println("Error at line " + badToken.line() + ": " + message);

        }
        System.exit(code.ordinal());
    };
}