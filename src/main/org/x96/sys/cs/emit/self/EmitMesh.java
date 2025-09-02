package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Mesh;

public class EmitMesh extends Emit<Mesh> {
    public EmitMesh(Mesh mesh) {
        super(mesh);
    }

    @Override
    public String toCS() {
        String[] sources = new String[t.nuclei().length];
        for (int i = 0; i < t.nuclei().length; i++) {
            sources[i] = new EmitNucleus(t.nuclei()[i]).toCS();
        }
        return String.format("{%s}%s", String.join(" ", sources), new EmitOccurrence(t.occurrence()).toCS());
    }
}
