package substitution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * substitution rule for the text
 * example: "h" to "hr" as a separate word
 * @author drygaay
 */
public class TextRule extends SubstitutionRule {
    String from;
    String to;
    Pattern pattern;
    
    public TextRule(String from, String to) {
        this.pattern = Pattern.compile("\\b" + from + "\\b");
        this.from = from;
        this.to = to;
    }
       
    @Override
    public boolean substitute(String original) {
        text.clear();
        boolean foundSubstitution = false;
        Matcher matcher = pattern.matcher(original);
        int start = 0; /// start of the string without the match
        while (matcher.find()) {
            foundSubstitution = true;
            String before = original.substring(start, matcher.start());
            if ( ! before.isEmpty()) {
                text.add(new Substitution(before, before));
            }
            text.add(new Substitution(from, to));
            start = matcher.end();          
        }
        text.add(new Substitution(original.substring(start), original.substring(start)));
        return foundSubstitution;
    }
 }