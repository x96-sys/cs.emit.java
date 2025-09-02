package org.x96.sys.cs.emit.java.impl.lexer.allow;

import org.junit.jupiter.api.Test;
import org.x96.sys.cs.emit.java.arch.java.Matrix;
import org.x96.sys.cs.emit.self.EmitManifest;
import org.x96.sys.cs.ir.manuscript.manifest.Manifest;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.Characterization;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.Track;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.occurrence.Occurrence;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Identity;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Nucleus;
import org.x96.sys.cs.ir.manuscript.manifest.characterization.facet.terminals.Term;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AllowManifestTest {

    @Test
    void happy(){
        Manifest m = new Manifest(new Identity("pair".getBytes()), new Characterization(Optional.empty(), new Track(new Nucleus[]{
                new Term(false, new Identity("primor".getBytes()), Optional.of(Occurrence.ZeroOrOne)),
                new Term(false, new Identity("i".getBytes()), Optional.of(Occurrence.ZeroOrMore)),
                new Term(false, new Identity("typo".getBytes()), Optional.empty()),
        })));

        assertEquals("pair = primor? i* typo;", new EmitManifest(m).toCS());

        AllowManifest am = new AllowManifest(m);
        Matrix allow = am.allow();
        assertEquals("""
                    @Override
                    public boolean allowed() {
                        return
                        // track
                        // nucleus
                        // term
                        new Primor(tokenizer).allowed()
                        ||
                        // nucleus
                        // term
                        new I(tokenizer).allowed()
                        ||
                        // nucleus
                        // term
                        new Typo(tokenizer).allowed()
                        ;
                    }
                """, allow.emitSource());
    }
}