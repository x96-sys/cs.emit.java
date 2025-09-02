package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.archetype.Archetype;

import java.util.Optional;

public class EmitArchetype extends Emit<Optional<Archetype>> {

    public EmitArchetype(Optional<Archetype> t) {
        super(t);
    }

    @Override
    public String toCS() {
        return t.map(kind -> switch (kind) {
            case Ghost ->
                "_ ";
            case Shell ->
                "@ ";
            default ->
                throw new AssertionError(kind.name());
        }).orElse("");
    }

}
