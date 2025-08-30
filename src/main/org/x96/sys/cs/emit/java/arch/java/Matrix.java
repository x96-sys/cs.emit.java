/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.x96.sys.cs.emit.java.arch.java;


import java.util.ArrayList;
import java.util.List;

public class Matrix {
    public final List<String> synapses;
    public final List<String> signals;
    public String tag;
    public String feedback;

    public Matrix() {
        this.synapses = new ArrayList<>();
        this.signals = new ArrayList<>();
    }

    public void i(String signal) {
        synapses.add(signal);
    }

    public void g(String signal) {
        signals.add(signal);
    }

    public void load(Matrix m) {
        this.synapses.addAll(m.synapses);
        this.signals.addAll(m.signals);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return String.format("%s%n", printSource());
    }

    public String printSource() {
        return String.format(
                """
                    @Override
                    public %s %s() {
                        %s
                    }
                """,
                this.feedback, this.tag, String.join("\n        ", this.signals));
    }
}
