package org.x96.sys.foundation.emit.java;

import org.x96.sys.foundation.cs.ir.manuscript.Manuscript;

import java.util.ArrayList;
import java.util.List;


public class EmitVisitor {
    
    public static Manuscript MANUSCRIPT_CACHED = null;

    public final Manuscript manuscript;
    public Klass[] klasses;
    public String pkg;
    public String rootSource;
    private final List<String> baseImports;

    public EmitVisitor(Manuscript m) {
        this.manuscript = m;
        this.klasses = new Klass[m.manifests().length];
        this.baseImports = new ArrayList<>();
        setupBaseImports();
        build();
    }

    private void setupBaseImports() {
        baseImports.add("org.x96.sys.foundation.tokenizer.Tokenizer");
        baseImports.add("org.x96.sys.foundation.cs.lexer.visitor.Visitor");
        baseImports.add("org.x96.sys.foundation.tokenizer.token.Token");
    }

    private void build() {
        MANUSCRIPT_CACHED = manuscript;
    }

    public void setRootSource(String rootSource) {
        this.rootSource = rootSource;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    /** Gera todas as classes Java como strings */
    public String[] generateAllClasses() {
        String[] classes = new String[klasses.length];
        for (int i = 0; i < klasses.length; i++) {
            classes[i] = generateClassWithPackage(klasses[i]);
        }
        return classes;
    }

    /** Gera uma classe com package e imports */
    private String generateClassWithPackage(Klass klass) {
        StringBuilder sb = new StringBuilder();
        List<String> allImports = new ArrayList<>(baseImports);

        // Package declaration
        if (pkg != null && !pkg.isEmpty()) {
            sb.append("package ").append(pkg).append(";\n\n");
        }

        // Collect additional imports from bauducos, avoiding duplicates
        for (Matrix matrix : klass.cookies) {
            for (String importStr : matrix.synapses) {
                if (!importStr.isEmpty() && !allImports.contains(importStr)) {
                    allImports.add(importStr);
                }
            }
        }

        // Write all imports
        for (String importStr : allImports) {
            sb.append("import ").append(importStr).append(";\n");
        }

        sb.append("\n");
        sb.append(klass.toString());

        return sb.toString();
    }
//
//    /** Salva todas as classes em arquivos Java */
//    public void saveAllClasses(String outputDir) throws IOException {
//        Path dir = Paths.get(outputDir);
//        Files.createDirectories(dir);
//
//        // Apaga arquivos .java
//        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.java")) {
//            for (Path entry : stream) {
//                Files.deleteIfExists(entry);
//            }
//        }
//
//        for (Klass klass : klasses) {
//            String fileName = S.toCamelCase(klass.name) + ".java";
//            Path filePath = dir.resolve(fileName);
//            String classContent = generateClassWithPackage(klass);
//            Files.write(filePath, classContent.getBytes());
//            System.out.println("Generated: " + filePath);
//        }
//    }
//
//    /** Gera o código de todas as classes como uma única string */
//    public String generateAllClassesAsString() {
//        StringBuilder sb = new StringBuilder();
//        String[] classes = generateAllClasses();
//        for (String classContent : classes) {
//            sb.append(classContent).append("\n==========\n");
//        }
//        return sb.toString();
//    }
//
//    /** Método utilitário para debugging - imprime todas as classes geradas */
//    public void printAllClasses() {
//        String[] classes = generateAllClasses();
//        for (String classContent : classes) {
//            System.out.println(classContent);
//        }
//    }
}
