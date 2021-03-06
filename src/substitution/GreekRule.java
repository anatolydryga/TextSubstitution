package substitution;

import java.util.HashMap;
import java.util.Map;

/**
 * all Greek letters (small and caps) and prefix micro changed to Latin and spelled out
 * @wa small letter mu is not covered by this rule 
 * @sa MicroMuRule.java
 * @wa prefix micro translated to u
 *
 * @author drygaay
 */
public class GreekRule extends SubstitutionRule {

    static final Map<Character, String> greekLetters = new HashMap<>();

    static {
        // low and micro
        greekLetters.put('\u03B1', "alpha");
        greekLetters.put('\u03B2', "beta");
        greekLetters.put('\u03B3', "gamma");
        greekLetters.put('\u03B4', "delta");
        greekLetters.put('\u03B5', "epsilon");

        greekLetters.put('\u03B6', "zeta");
        greekLetters.put('\u03B7', "eta");
        greekLetters.put('\u03B8', "theta");
        greekLetters.put('\u03B9', "iota");
        greekLetters.put('\u03BA', "kappa");

        greekLetters.put('\u03BB', "lamda");

        // although not really letter of the greek alphabet for
        // historical reasons appear similar to greek mu
        greekLetters.put('\u00B5', "u");

        greekLetters.put('\u03BD', "nu");
        greekLetters.put('\u03BE', "xi");
        greekLetters.put('\u03BF', "omicron");

        greekLetters.put('\u03C0', "pi");
        greekLetters.put('\u03C1', "rho");
        greekLetters.put('\u03C2', "sigma");
        greekLetters.put('\u03C3', "sigma");
        greekLetters.put('\u03C4', "tau");

        greekLetters.put('\u03C5', "upsilon");
        greekLetters.put('\u03C6', "phi");
        greekLetters.put('\u03C7', "chi");
        greekLetters.put('\u03C8', "psi");
        greekLetters.put('\u03C9', "omega");
        
        //CAPS
        greekLetters.put('\u0391', "alpha");
        greekLetters.put('\u0392', "beta");
        greekLetters.put('\u0393', "gamma");
        greekLetters.put('\u0394', "delta");
        greekLetters.put('\u0395', "epsilon");

        greekLetters.put('\u0396', "zeta");
        greekLetters.put('\u0397', "eta");
        greekLetters.put('\u0398', "theta");
        greekLetters.put('\u0399', "iota");
        greekLetters.put('\u039A', "kappa");

        greekLetters.put('\u039B', "lamda");
        greekLetters.put('\u039C', "mu");
        greekLetters.put('\u039D', "nu");
        greekLetters.put('\u039E', "xi");
        greekLetters.put('\u039F', "omicron");

        greekLetters.put('\u03A0', "pi");
        greekLetters.put('\u03A1', "rho");
        greekLetters.put('\u03A2', "sigma");
        greekLetters.put('\u03A3', "sigma");
        greekLetters.put('\u03A4', "tau");

        greekLetters.put('\u03A5', "upsilon");
        greekLetters.put('\u03A6', "phi");
        greekLetters.put('\u03A7', "chi");
        greekLetters.put('\u03A8', "psi");
        greekLetters.put('\u03A9', "omega");
    }

    @Override
    public boolean substitute(String original) {
        text.clear();
        boolean foundSubstitution  = false;
        int start = 0; /// start of the string without the match
        for (int i = 0; i < original.length(); i++) {
            Character curChar = original.charAt(i);
            if (greekLetters.containsKey(curChar)) {
                foundSubstitution = true;
                String before = original.substring(start, i);
                if ( ! before.isEmpty()) {
                    text.add(new Substitution(before, before));
                }
                text.add(new Substitution(curChar.toString(), greekLetters.get(curChar)));
                start = i + 1;
            }
        }
        text.add(new Substitution(original.substring(start), original.substring(start)));
        return foundSubstitution;
    }
}
