package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

public interface Allowing {
    public Matrix allow();

    public default boolean isFinished(Matrix matrix, Nucleus n) {
        switch (n) {
            case Natural natural -> {
                return true;
            }
            case Switch aSwitch -> {
                return true;
            }
            case Term term -> {
                if (term.negate()) {
                    matrix.g("&&");
                    return false;
                } else {
                    if (term.occurrence().isPresent()) {
                        return switch (term.occurrence().get()) {
                            case ZeroOrOne, ZeroOrMore -> {
                                matrix.g("||");
                                yield false;
                            }
                            default -> true;
                        };
                    } else {
                        return true;
                    }
                }
            }
            case Text text -> {
                return true;
            }
            case Trace trace -> {
                return true;
            }
        }
    }

    public default boolean isFinished(Matrix matrix, Fleck f) {
        switch (f) {
            case Range range -> {
                return true;
            }
            case Switch aSwitch -> {
                return true;
            }
            case Term term -> {
                if (term.negate()) {
                    matrix.g("&&");
                    return false;
                } else {
                    if (term.occurrence().isPresent()) {
                        if (term.occurrence().get() == Occurrence.ZeroOrOne) {
                            matrix.g("||");
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
            case Text text -> {
                return true;
            }
            case Trace trace -> {
                return true;
            }
        }
    }
}
