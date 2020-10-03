/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.analyzers.syntax;

import tac_assembly_generator.TAC.quadruple.Quadruple;
import tac_assembly_generator.languages.semantic.type.Type;

/**
 *
 * @author sergio
 */
public class SynthesizedOpAsst {
    private final Quadruple quadruple;
    private final Type type;

    public SynthesizedOpAsst(Quadruple quadruple, Type type) {
        this.quadruple = quadruple;
        this.type = type;
    }

    public Quadruple getQuadruple() {
        return quadruple;
    }

    public Type getType() {
        return type;
    }
    
}
