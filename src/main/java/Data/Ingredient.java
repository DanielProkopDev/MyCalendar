package Data;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Ingredient extends AbstractIngredient {

    private String name;

    private BigDecimal price;

    private String kind;

    private ArrayList<String> cuisineMostlyUsed;

    private int kcal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ArrayList<String> getCuisineMostlyUsed() {
        return cuisineMostlyUsed;
    }

    public void setCuisineMostlyUsed(ArrayList<String> cuisineMostlyUsed) {
        this.cuisineMostlyUsed = cuisineMostlyUsed;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }
}
