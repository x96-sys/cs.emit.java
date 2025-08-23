package org.x96.sys.foundation.emit.java.impl.visit;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

public class VisitText implements Visiting{
    private final Text t;

    public VisitText(Text text) {
        this.t =text; 
    }
    @Override
    public Matrix visit() {
        Matrix matrix = new Matrix();
        matrix.g("// text");
        return matrix;
    }
}
