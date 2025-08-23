package org.x96.sys.foundation.emit.self;

import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

public class EmitText extends Emit<Text> {
    public EmitText(Text text) {
        super(text);
    }

    @Override
    public String toCS() {
        return String.format("'%s'", new String(t.raw()));
    }
}
