package org.x96.sys.foundation.emit.self;

import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.Manuscript;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.Manifest;


public class EmitManuscript extends Emit<Manuscript> {

    public EmitManuscript(Manuscript t) {
        super(t);
    }

    @Override
    public String toCS() {
        StringBuilder sb = new StringBuilder();
        for (Manifest manifest : t.manifests()) {
            sb.append(String.format("%s%n", new EmitManifest(manifest).toCS()));
        }
        return sb.toString();
    }

}
