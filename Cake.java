public class Cake extends Menu{
    public Cake(int id, String name, int price, int amount){
        super(id,name,price,amount);
    }
    
    public void getInfo() {
        System.out.println("ID "+this.id+ " " + this.name + " " + this.price + " " + this.quantity);
    }
}