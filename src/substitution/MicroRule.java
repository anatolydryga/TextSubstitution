package substitution;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * substitution rule for the Greek letter mu
 *  mu(Greek) is changed to mu(Latin)
 *  but if mu(Greek) followed by l,L,g is changed to u
 * @author drygaay
 */
public class MicroRule extends SubstitutionRule {
    final static Character greekMu = '\u03BC';
    final static String mu = "mu";
    final static String  u =  "u";
    final static List<Pattern> measures = new ArrayList<>();

    static {
        measures.add(Pattern.compile("^l\\b"));
        measures.add(Pattern.compile("^L\\b"));
        measures.add(Pattern.compile("^M\\b"));
        measures.add(Pattern.compile("^g\\b"));
    }
       
    @Override
    public boolean substitute(String original) {
        text.clear();
        boolean foundSubstitution  = false;
        int start = 0; /// start of the string without the match
        for (int i = 0; i < original.length(); i++) {
            Character curChar = original.charAt(i);
            if (greekMu.equals(curChar)) {
                foundSubstitution = true;
                String before = original.substring(start, i);
                if ( ! before.isEmpty()) {
                    text.add(new Substitution(before, before));
                }
                text.add(new Substitution(curChar.toString(), getGreekMuRep(original, i)));
                start = i + 1;
            }
        }
        text.add(new Substitution(original.substring(start), original.substring(start)));
        return foundSubstitution;
    }

    /**
     * 
     * @param original string
     * @param i index where we have letter mu
     * @return mu or u depending of string following after index i
     */
    private String getGreekMuRep(String original, int i) {
        for (Pattern p: measures) {
            Matcher matcher = p.matcher(original.substring(i + 1));
            if (matcher.find()) {
                return u;
            }
        }
        return mu;
    }
 }