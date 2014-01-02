/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package substitution;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author drygaay
 */
abstract class SubstitutionRule implements Iterable< Substitution > {
    
    protected ArrayList< Substitution > text;

    SubstitutionRule() {
        this.text = new ArrayList<>();
    }
    
    /**
     * parse given text string into list of Substitution(s)
     * 
     * @param text 
     */
    public abstract void substitute(String text);
    
       @Override
    public Iterator<Substitution> iterator() {
        return new SubstitutionRuleIterator();
    }

    private class SubstitutionRuleIterator implements Iterator<Substitution> {

        int index = 0;
        int maxIndex = text.size();

        public SubstitutionRuleIterator() {
        }

        @Override
        public boolean hasNext() {
            return index < maxIndex;
        }

        @Override
        public Substitution next() {
            return text.get(index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    
}
