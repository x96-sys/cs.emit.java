/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.x96.sys.foundation.emit.self.arch;



/**
 *
 * @author fera
 */
public abstract class Emit<T> implements Emitting {
    public final T t;

    public Emit(T t) {
        this.t = t;
    }

    @Override
    public String toCS() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
