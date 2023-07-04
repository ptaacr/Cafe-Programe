public class Menu{
    protected String name;
    protected int id, price, quantity;
    
    public Menu(int id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = amount;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setPrice(int price){
        this.price = price;
    }
    
    public int getPrice(){
        return this.price;
    }
    
    public void setQuantity(int amount){
        this.quantity = amount;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    public void getInfo() {
        System.out.println("ID "+this.id + " " + this.name + " " + this.price + " " + this.quantity);
    }
}
