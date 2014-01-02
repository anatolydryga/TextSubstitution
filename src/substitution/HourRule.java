/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package substitution;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * substitution rule "h" to "hr"
 * @author drygaay
 */
public class HourRule extends SubstitutionRule {
    Pattern pattern;
    
    public HourRule() {
        pattern = Pattern.compile("(\\s)(h)(\\W)");
    }
    
    
    @Override
    public void substitute(String original) { 
        text.clear();
        Matcher matcher = pattern.matcher(original);
        int start = 0; /// start of the string without the match
        while (matcher.find()) {
            String before = original.substring(start, matcher.end(1));
            if (! before.isEmpty()) {
                text.add(new Substitution(before, before));
            }
            text.add(new Substitution("h", "hr"));
            start = matcher.start(3);          
        }
        text.add(new Substitution(original.substring(start), original.substring(start)));
    }
 }
