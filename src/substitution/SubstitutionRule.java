/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package substitution;

import java.util.ArrayList;

/**
 *
 * @author drygaay
 */
abstract class SubstitutionRule {
    public ArrayList< Substitution > performSubstitution(
       ArrayList< Substitution > original
    ) {
       return null; 
    }
    
    public abstract Substitution substutute(String text);
}
