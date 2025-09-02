package org.x96.sys.cs.emit.java.arch.java;

import org.junit.jupiter.api.Test;
import org.x96.sys.cs.emit.java.arch.java.Klass;
import org.x96.sys.cs.emit.java.arch.java.Matrix;

import static org.junit.jupiter.api.Assertions.*;

class KlassTest {
    @Test
    void happy(){
        Klass klass = new Klass("Visitor".getBytes(), "build = {0x0}", null);
        Matrix matrix = new Matrix();
        matrix.setTag("visit");
        matrix.setFeedback("Token[]");
        matrix.g("true");
        klass.addMatrix(matrix);
        assertEquals("""
                // build = {0x0}
                public class Visitor {
                
                    public Visitor(Tokenizer tokenizer) { super(tokenizer); }
                
                    @Override
                    public Token[] visit() {
                        true
                    }
                
                }
                """, klass.toString());
    }
}