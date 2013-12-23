package substitution;

import java.util.ArrayList;

/**
 * transform original text to text with substitutions according to rules.
 *
 * @author drygaay
 */
public class TextSubstitution {

    private final String original;
    private ArrayList< SubstitutionRule> rules;
    
    private ArrayList< Substitution > text;
    
    TextSubstitution(String original, ArrayList< SubstitutionRule> rules) {
        this.original = original;
        this.rules = rules;
        substutute();
    }

    private void substutute() {
    }

    public ArrayList< Substitution >  getSubstitutionList() {
        return text;
    }

    public String getText() {
        return "";
    }
}
