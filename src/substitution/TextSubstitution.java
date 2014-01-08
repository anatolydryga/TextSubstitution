package substitution;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * transform original text to text with substitutions according to rules.
 *
 * @author drygaay
 * 
 * @wa apply rules only on the original string
 */
public class TextSubstitution implements Iterable< Substitution> {

    private final String original;
    private final ArrayList< SubstitutionRule> rules;
    private ArrayList< Substitution> text = new ArrayList<>();

    /**
     * 
     * @param original
     * @param rules 
     * @throws UnsupportedOperationException if two rules have
     * substitutions on the same substring
     */
    TextSubstitution(String original, ArrayList<SubstitutionRule> rules) {
        if (rules == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.rules = rules;
        substitute();
    }

    private void substitute() {
        ArrayList< Substitution> textAcc = new ArrayList<>();
        text.add(new Substitution(original, original));
        
        for (SubstitutionRule rule : rules) {
            for (Substitution s : text) {
                if (s.isSubstitution() && rule.substitute(s.getSubstitute())) {
                    throw new UnsupportedOperationException(
                            "cannot have several rules that modify the same part of the string");
                }
                rule.substitute(s.getOriginal());
                for (Substitution sForRule: rule) {
                    textAcc.add(sForRule);
                 }
            }
            text.clear();
            text.addAll(textAcc);
        }
    }

    public String getText() {
        String textWithSubs = "";
        for (Substitution s : this) {
            textWithSubs += s.getSubstitute();
        }
        return textWithSubs;
    }

    @Override
    public Iterator<Substitution> iterator() {
        return new TextSubstitutionIterator();
    }

    private class TextSubstitutionIterator implements Iterator<Substitution> {

        int index = 0;
        int maxIndex = text.size();

        public TextSubstitutionIterator() {
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
