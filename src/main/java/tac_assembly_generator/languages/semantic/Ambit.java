/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tac_assembly_generator.languages.semantic;

/**
 *
 * @author sergio
 */
public class Ambit {
    private int id;
    private Ambit father;

    public Ambit(int id, Ambit father) {
        this.id = id;
        this.father = father;
    }
    
}
