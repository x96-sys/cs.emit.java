package org.x96.sys.foundation.emit.java.arch.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    @Test
    void happy(){
        Matrix matrix = new Matrix();
        matrix.setTag("visit");
        matrix.setFeedback("Token[]");
        matrix.g("true");
        assertEquals("""
                    @Override
                    public Token[] visit() {
                        true
                    }
                """, matrix.printSource());
    }
}