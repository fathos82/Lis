package org.lis.core.pipeline;

import org.lis.core.component.LisIOComponent;

@SuppressWarnings("ALL")
public abstract class LisCompilationPass<I extends LisIOComponent, O extends LisIOComponent> {
    public final O run(I input) {
        return  pass(input);

    }

    public abstract O pass(I input);

    public abstract Class<I> getInputType();
    public abstract Class<O> getOutputType();

}
