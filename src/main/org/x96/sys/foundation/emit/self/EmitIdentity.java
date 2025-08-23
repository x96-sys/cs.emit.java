package org.x96.sys.foundation.emit.self;

import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Identity;


public class EmitIdentity extends Emit<Identity> {
    
    public EmitIdentity(Identity t) {
        super(t);
    }

    @Override
    public String toCS() {
        return new String(t.raw());
    }    
}
