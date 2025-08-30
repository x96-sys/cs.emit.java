package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.Manuscript;
import org.x96.sys.cs.ir.manuscript.manifest.Manifest;

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
