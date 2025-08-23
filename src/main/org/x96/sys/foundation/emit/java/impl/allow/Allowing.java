package org.x96.sys.foundation.emit.java.impl.allow;

import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;
import org.x96.sys.foundation.cs.ir.manuscript.manifest.characterization.facet.terminals.*;
import org.x96.sys.foundation.emit.java.arch.java.Matrix;

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
