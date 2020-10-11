/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class SemanticAsstIdQuad {
    private String id;
    private ArrayList<Object> quads;

    public SemanticAsstIdQuad(String id, ArrayList<Object> quads) {
        this.id = id;
        this.quads = quads;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Object> getQuads() {
        return quads;
    }
    
}
