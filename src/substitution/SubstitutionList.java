package substitution;

import java.util.List;

/**
 *  static functions for manipulation of list of Substitutions
 * @author drygaay
 */
public class SubstitutionList {

     /** 
      * for the list of substitutions creates text string with all substitute(s)
      * (which are transformation of original by applying rules)
      */
    public static String getSubstitutionText(List<Substitution> subs) {
        String textWithSubs = "";
        for (Substitution s : subs) {
            textWithSubs += s.getSubstitute();
        }
        return textWithSubs;
    }
}
