package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.Manifest;

public class EmitManifest extends Emit<Manifest> {

    public EmitManifest(Manifest t) {
        super(t);
    }

    @Override
    public String toCS() {
        String identity = new EmitIdentity(t.identity()).toCS();
        String c = new EmitCharacterization(t.characterization()).toCS();
        return String.format("%s %s %s", identity, "=", c);
    }

}
