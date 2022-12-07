package rutgers.rupizzeria;

public class PizzaModel {
    String pizzaType;
    String pizzaCrust;
    String pizzaTopping;
    int image;

    public PizzaModel(String pizzaType, String pizzaCrust, String pizzaTopping, int image) {
        this.pizzaType = pizzaType;
        this.pizzaCrust = pizzaCrust;
        this.pizzaTopping = pizzaTopping;
        this.image = image;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public String getPizzaCrust() {
        return pizzaCrust;
    }

    public int getImage() {
        return image;
    }

    public String getPizzaTopping() {
        return pizzaTopping;
    }
}
