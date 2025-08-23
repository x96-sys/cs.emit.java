package org.x96.sys.foundation.emit.java.impl.allow;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

public class AllowText implements Allowing {
    public AllowText(Text text)  {
    }
    @Override
    public Matrix allow() {
        return new Matrix();
    }
}
