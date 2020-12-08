package recipes.domain;

/**
 * A small class helping validate method in Validation class to return both the user object 
 * and the related information text to RecipesGUI
 * @param <K> user object
 * @param <V> info text
 * @see recipes.gui.RecipesGUI
 * @see recipes.domain.Validation
 */
public class Info<K, V> {
    
    private K user;
    private V text;

    /**
     * Constructor, creates a tuple with an User object and String info text.
     * @param user user object
     * @param text information text as String
     * @see recipes.domain.User
     */
    public Info(K user, V text) {
        this.text = text;
    }

    /**
     * Setter, sets an User object.
     * @param user
     * @see recipes.domain.User
     */
    public void setUser(K user) {
        this.user = user;
    }

    /**
     * Setter, sets a String object with information text.
     * @param text
     */
    public void setText(V text) {
        this.text = text;
    }

    /**
     * Getter, gets an User object.
     * @return an User object, or null
     * @see recipes.domain.User
     */
    public K getUser() {
        return this.user;
    }

    /**
     * Getter, gets a String field, containing information text.
     * @return text as String
     */
    public V getText() {
        return this.text;
    }

}
