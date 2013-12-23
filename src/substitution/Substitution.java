package substitution;

/**
 * text substitution. original text and new text (if different from original)
 *
 * @author drygaay
 */
class Substitution {

    private final String original;
    private final String substitute;
    private final boolean isSubstitution;

    Substitution(String original, String substitute) {
        if (original.equals(substitute)) {
            this.isSubstitution = false;
        } else {
            this.isSubstitution = true;
        }
        this.original = original;
        this.substitute = substitute;
    }

    public String getOriginal() {
        return original;
    }

    public String getSubstitute() {
        return substitute;
    }

    public boolean isSubstitution() {
        return isSubstitution;
    }
}
