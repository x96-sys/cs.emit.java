package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Text;

public class EmitText extends Emit<Text> {
    public EmitText(Text text) {
        super(text);
    }

    @Override
    public String toCS() {
        return String.format("'%s'", new String(t.raw()));
    }
}
