package recipes.domain;

import java.util.List;

/**
 *
 * @author aebjork
 */
public class Category {

    private int number;
    private CategoryEnum category;

    public Category(CategoryEnum category) {
        this.number = this.category.ordinal();
        this.category = category;
    }

    @Override
    public String toString() {
        return "  " + this.number + " - " + this.category;
    }

    public int getNumber() {
        return this.number;
    }

    public CategoryEnum getCategoryName() {
        return this.category;
    }

}
