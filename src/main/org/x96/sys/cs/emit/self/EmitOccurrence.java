package org.x96.sys.cs.emit.self;

import org.x96.sys.cs.emit.self.arch.Emit;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;

import java.util.Optional;

public class EmitOccurrence extends Emit<Optional<Occurrence>> {
    public EmitOccurrence(Optional<Occurrence> occurrence) {
        super(occurrence);
    }

    @Override
    public String toCS() {
        if (t.isEmpty()) {
            return "";
        }
        switch (t.get()) {
            case ZeroOrOne -> {
                return "?";
            }
            case ZeroOrMore -> {
                return "*";
            }
            case OneOrMore -> {
                return "+";
            }
        }
        throw new RuntimeException("unreachable");
    }
}
