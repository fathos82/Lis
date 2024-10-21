package org.lis.core.pipeline;

import org.lis.core.component.LisIOComponent;
import org.lis.core.component.LisValidationComponent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class LisCompilationPipeline {
    private final List<LisCompilationPass<? extends LisIOComponent, ? extends  LisIOComponent>>  passes = new ArrayList<>();

    public void insertStage(LisCompilationPass<? extends LisIOComponent, ? extends  LisIOComponent> pass){
        passes.add(pass);
    }
    public void run(LisIOComponent input){
        LisIOComponent currentInput = input;
        for(LisCompilationPass<? extends LisIOComponent, ? extends  LisIOComponent> pass : passes){
            currentInput =  runPass(pass, currentInput);
        }
    }

    private <I extends LisIOComponent, O extends LisIOComponent> LisIOComponent runPass(LisCompilationPass<I, O> pass, LisIOComponent input) {
        if(!pass.getInputType().isInstance(input)){
            throw new IllegalArgumentException("Input is not an instance of " + pass.getInputType().getName());
        }
        return pass.run((I) input);
    }
}
