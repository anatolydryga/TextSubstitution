/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package substitution;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author drygaay
 */
public class TextSubstitutionTest {

    public TextSubstitutionTest() {
    }
    ArrayList< SubstitutionRule> rules = new ArrayList<>();
    SubstitutionRule hRule = new HourRule();
    SubstitutionRule greekRule = new GreekRule();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        rules.clear();
    }

    @Test
    public void noRules() {
        TextSubstitution formatter = new TextSubstitution("blast", null);
        final String formatted = "blast";
        assertTrue(formatter.getText().equals(formatted));
    }

    @Test
    public void hRuleNoSubstitutions() {
        rules.add(hRule);
        TextSubstitution formatter = new TextSubstitution("blast for 6 hours", rules);
        final String formatted = "blast for 6 hours";
        assertTrue(formatter.getText().equals(formatted));
    }

    @Test
    public void hRule() {
        rules.add(hRule);
        TextSubstitution formatter = new TextSubstitution("blast for 6 h", rules);
        final String formatted = "blast for 6 hr";
        assertTrue(formatter.getText().equals(formatted));
    }

    @Test
    public void hRuleTwice() {
        rules.add(hRule);
        TextSubstitution formatter =
                new TextSubstitution("blast for 6 h and then for 3 h and", rules);
        final String formatted = "blast for 6 hr and then for 3 hr and";
        assertTrue(formatter.getText().equals(formatted));
    }

    @Test
    public void greekAlpha() {
        rules.add(greekRule);
        TextSubstitution formatter =
                new TextSubstitution("\u03B1 and whatever", rules);
        final String formatted = "alpha and whatever";
        assertTrue(formatter.getText().equals(formatted));
    }

    @Test
    public void greekAlphabet() {
        rules.add(greekRule);
        String greekAlphabet = "\u03b1 \u03b2 \u03b3 \u03b4 \u03b5 \u03b6 \u03b7 \u03b8 \u03b9 \u03ba \u03bb \u03bc \u03bd \u03be \u03bf \u03c0 \u03c1 \u03c3 \u03c2 \u03c4 \u03c5 \u03c6 \u03c7 \u03c8 \u03c9";
        TextSubstitution formatter  = new TextSubstitution(greekAlphabet, rules);
        final String formatted = "alpha beta gamma delta epsilon zeta eta theta iota kappa lamda mu nu xi omicron pi rho sigma tau upsilon phi chi psi omega";
        assertTrue(formatter.getText().equals(formatted));
    }

    // TODO: check symbols                                                       
    @Test
    public void greekAlphabetCAPS() {
        rules.add(greekRule);
        String greekAlphabet = "\u0391 \u0392 \u0393 \u0394 \u0395 \u0396 \u0397 \u0398 \u0399 \u039A \u039B \u039C \u039D \u039E \u039F \u03A0 \u03A1 \u03A2 \u03A3 \u03A4 \u03A5 \u03A6 \u03A7 \u03A8 \u03A9";
        TextSubstitution formatter = new TextSubstitution(greekAlphabet, rules);
        final String formatted = "alpha beta gamma delta epsilon zeta eta theta iota kappa lamda mu nu xi omicron pi rho sigma tau upsilon phi chi psi omega";
        assertTrue(formatter.getText().equals(formatted));
    }
}