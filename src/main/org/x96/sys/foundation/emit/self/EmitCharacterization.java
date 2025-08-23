/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.x96.sys.foundation.emit.self;

import org.x96.sys.foundation.emit.self.arch.Emit;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.Characterization;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Mesh;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.Track;

/**
 *
 * @author fera
 */
public class EmitCharacterization extends Emit<Characterization> {
    
    public EmitCharacterization(Characterization t) {
        super(t);
    }

    @Override
    public String toCS() {
        String a = new EmitArchetype(t.archetype()).toCS();
        String b = "";
        switch (t.facet()) {
            case Mesh mesh -> {
                b = new EmitMesh(mesh).toCS();
            }
            case Track track -> {
                b = new EmitTrack(track).toCS();
            }
        }
        return String.format("%s%s", a, b);
    }
}
