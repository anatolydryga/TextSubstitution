package substitution;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TextSubstitutionTest {

    ArrayList<SubstitutionRule> rules = new ArrayList<>();
    SubstitutionRule hRule = new TextRule("h", "hr");
    SubstitutionRule mlRule = new TextRule("Ml", "mL");
    SubstitutionRule mkgRule = new TextRule("Microg", "ug");
    SubstitutionRule mklRule = new TextRule("Microl", "uL");
    SubstitutionRule greekRule = new GreekRule();
    SubstitutionRule microRule = new MicroRule();


    @Test(expected = UnsupportedOperationException.class)
    public void overlappingRulesThrow() {
        SubstitutionRule blastRule = new TextRule("blast", "BLAST");
        SubstitutionRule blastRevRule = new TextRule("BLAST", "blast");
        rules.add(blastRule);
        rules.add(blastRevRule);
        TextSubstitution formatter = new TextSubstitution("blast", rules);
    }

    @Test(expected = NullPointerException.class)
    public void noRulesThrow() {
        TextSubstitution formatter = new TextSubstitution("blast", null);
    }

    @Test
    public void noRulesNoThrow() {
        TextSubstitution formatter = new TextSubstitution(
                "blast", new ArrayList< SubstitutionRule>());
        final String formatted = "blast";
        assertTrue(formatter.getText().equals(formatted));
        for (Substitution s : formatter) {
            assertFalse(s.isSubstitution());
            assertEquals(s.getOriginal(), s.getSubstitute());
        }
    }

    @Test
    public void hRuleNoSubstitutions() {
        rules.add(hRule);
        TextSubstitution formatter = new TextSubstitution("blast for 6 hours", rules);
        final String formatted = "blast for 6 hours";
        assertTrue(formatter.getText().equals(formatted));
        for (Substitution s : formatter) {
            assertFalse(s.isSubstitution());
            assertEquals(s.getOriginal(), s.getSubstitute());
        }
    }

    @Test
    public void mlRule() {
        rules.add(mlRule);
        TextSubstitution formatter = new TextSubstitution("blast for 6 Ml.", rules);
        final String formatted = "blast for 6 mL.";
        assertTrue(formatter.getText().equals(formatted));

        TextSubstitution formatterEnd = new TextSubstitution(" Ml.", rules);
        final String formatted2 = " mL.";
        assertTrue(formatterEnd.getText().equals(formatted2));

        TextSubstitution formatterStart = new TextSubstitution("Ml.", rules);
        final String formatted3 = "mL.";
        assertTrue(formatterStart.getText().equals(formatted3));
    }

    @Test
    public void mkgRule() {
        rules.add(mkgRule);
        TextSubstitution formatter = new 
                TextSubstitution("Microg Microg Microg !fox", rules);
        final String formatted = "ug ug ug !fox";
        assertTrue(formatter.getText().equals(formatted));
    }

    @Test
    public void mklRule() {
        rules.add(mklRule);
        TextSubstitution formatter = new 
                TextSubstitution(" Microl Microl sdfs Microl Microl ", rules);
        final String formatted = " uL uL sdfs uL uL ";
        assertTrue(formatter.getText().equals(formatted));
    }

    @Test
    public void severalRulesTest() {
        rules.add(mklRule);
        rules.add(hRule);
        rules.add(greekRule);
        rules.add(microRule);
        String original = "\u03BCM and h and Microl whatever";
        TextSubstitution formatter =
                new TextSubstitution(original, rules);
        final String expected = "uM and hr and uL whatever";
        String result = formatter.getText();
        assertTrue(result.equals(expected));
    }

    @Test
    public void hRule() {
        rules.add(hRule);
        TextSubstitution formatter = new TextSubstitution("blast for 6 h.", rules);
        final String formatted = "blast for 6 hr.";
        assertTrue(formatter.getText().equals(formatted));
        int nTextChunks = 1;
        for (Substitution s : formatter) {
            nTextChunks++;
        }
        assertEquals(nTextChunks, 4); // 3 text chunks 
    }

    @Test
    public void hRuleTwice() {
        rules.add(hRule);
        TextSubstitution formatter =
                new TextSubstitution("blast for 6 h and then for 3 h and", rules);
        final String formatted = "blast for 6 hr and then for 3 hr and";
        assertTrue(formatter.getText().equals(formatted));
        int nTextChunks = 0;
        int nTextSubs = 0;
        for (Substitution s : formatter) {
            if (s.isSubstitution()) {
                nTextSubs++;
            }
            nTextChunks++;
        }
        assertEquals(nTextChunks, 5);
        assertEquals(nTextSubs, 2);
    }

    @Test
    public void greekAlpha() {
        rules.add(greekRule);
        TextSubstitution formatter =
                new TextSubstitution("\u03B1 and whatever", rules);
        final String formatted = "alpha and whatever";
        assertTrue(formatter.getText().equals(formatted));
        int nTextChunks = 1;
        for (Substitution s : formatter) {
            nTextChunks++;
        }
        assertEquals(nTextChunks, 3); // 2 text chunks 
    }

    @Test
    public void microMTest() {
        rules.add(greekRule);
        rules.add(microRule);
        String original = "\u03BCM and whatever";
        TextSubstitution formatter =
                new TextSubstitution(original, rules);
        final String expected = "uM and whatever";
        String result = formatter.getText();
        assertTrue(result.equals(expected));
    }

    @Test
    public void microMfromAbstractTest() {
        rules.add(greekRule);
        String original = 
                "(GI(50) = 15.6 µM) is twice more toxic " +
                "than nano-curcumin (GI(50) = 32.5 µM), nano-curcumin.";
        TextSubstitution formatter =
                new TextSubstitution(original, rules);
        final String expected = 
                "(GI(50) = 15.6 uM) is twice more toxic " +
                "than nano-curcumin (GI(50) = 32.5 uM), nano-curcumin.";
        String result = formatter.getText();
        assertTrue(result.equals(expected));
    }

    @Test
    public void muMfromAbstractTest() {
        rules.add(greekRule);
        rules.add(microRule);
        String original = "to 100 μM";
        TextSubstitution formatter =
                new TextSubstitution(original, rules);
        final String expected = "to 100 uM";
        String result = formatter.getText();
        assertTrue(result.equals(expected));
    }

    @Test
    public void greekAlphabet() {
        rules.add(greekRule);
        rules.add(microRule);
        String greekAlphabet = "\u03b1 \u03b2 \u03b3 \u03b4 \u03b5 \u03b6 \u03b7 \u03b8 \u03b9 \u03ba \u03bb \u03bc \u03bd \u03be \u03bf \u03c0 \u03c1 \u03c3 \u03c2 \u03c4 \u03c5 \u03c6 \u03c7 \u03c8 \u03c9";
        TextSubstitution formatter = new TextSubstitution(greekAlphabet, rules);

        final String formatted = "alpha beta gamma delta epsilon zeta eta theta iota kappa lamda mu nu xi omicron pi rho sigma sigma tau upsilon phi chi psi omega";
        assertTrue(formatter.getText().equals(formatted));

        int nTextChunks = 0;
        int nTextSubs = 0;
        for (Substitution s : formatter) {
            if (s.isSubstitution()) {
                nTextSubs++;
            }
            nTextChunks++;
        }
        assertEquals(nTextChunks, 50);
        assertEquals(nTextSubs, 25);
    }

    @Test
    public void greekAlphabetCAPS() {
        rules.add(greekRule);
        String greekAlphabet = "\u0391 \u0392 \u0393 \u0394 \u0395 \u0396 \u0397 \u0398 \u0399 \u039A \u039B \u039C \u039D \u039E \u039F \u03A0 \u03A1 \u03A2 \u03A3 \u03A4 \u03A5 \u03A6 \u03A7 \u03A8 \u03A9";
        TextSubstitution formatter = new TextSubstitution(greekAlphabet, rules);
        final String formatted = "alpha beta gamma delta epsilon zeta eta theta iota kappa lamda mu nu xi omicron pi rho sigma sigma tau upsilon phi chi psi omega";
        assertTrue(formatter.getText().equals(formatted));
        int nTextChunks = 0;
        int nTextSubs = 0;
        for (Substitution s : formatter) {
            if (s.isSubstitution()) {
                nTextSubs++;
            }
            nTextChunks++;
        }
        assertEquals(nTextChunks, 50);
        assertEquals(nTextSubs, 25);
    }

    @Test
    public void allRulesAtOnce() {
        rules.add(hRule);
        rules.add(mlRule);
        rules.add(mkgRule);
        rules.add(mklRule);
        rules.add(greekRule);

        String random = "here we don't have any substitutions at all.";
        TextSubstitution formatter = new TextSubstitution(random, rules);
        assertTrue(formatter.getText().equals(random));
        int nTextChunks = 0;
        int nTextSubs = 0;
        for (Substitution s : formatter) {
            if (s.isSubstitution()) {
                nTextSubs++;
            }
            nTextChunks++;
        }
        assertEquals(nTextChunks, 1);
        assertEquals(nTextSubs, 0);
    }
}