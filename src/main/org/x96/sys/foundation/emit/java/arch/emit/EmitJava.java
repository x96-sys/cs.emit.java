package org.x96.sys.foundation.emit.java.arch.emit;

public abstract class EmitJava<T> implements EmittingJava {
    public final T t;

    public EmitJava(T t) {
        this.t = t;
    }

    @Override
    public abstract String toJava();
    
}
