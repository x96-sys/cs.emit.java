package org.x96.sys.foundation.emit.self;


import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;

public class EmitTrack extends Emit<Track>  {
    public EmitTrack(Track track) {
        super(track);
    }

    @Override
    public String toCS() {
        String[] sources = new String[t.nuclei().length];
        for (int i = 0; i < t.nuclei().length; i++) {
            sources[i] = new EmitNucleus(t.nuclei()[i]).toCS();
        }
        return String.format("%s%s", String.join(" ", sources), ";");
    }
}
