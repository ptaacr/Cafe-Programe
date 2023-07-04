import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;  

public class Cafe{
    public static Cake m1,m2,m3,m4,m5,m6,m7;
    public static Drink m8,m9,m10,m11,m12,m13,m14;
    public static ArrayList<Menu> Menus = new ArrayList<>();
    public static ArrayList<Integer> orderList = new ArrayList<>();
    public static ArrayList<Integer> orderAmount = new ArrayList<>();
    public static ArrayList<String> history = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static int orderOfday = 1;
    public static void main(String[] args) {
        Boolean runMain = true;
        clear();
        while(runMain) {
            getMainMenu();
            setData();
            int menuNo = sc.nextInt();
            switch(menuNo) {
                case 1: // Order
                    clear();
                    boolean run = true;
                    retrieveMenu();
                    while (run) {
                        showOrdered();
                        System.out.print("Enter menuId (Calculate Press: 0 and Press -1 Show Bill): ");
                        //System.out.println("Enter menuId (Press -1 Show Bill): ");
                        int orderId = sc.nextInt();
                        switch(caseId(orderId)) { 
                            case -1: 
                                run = false;
                                break;
                            case 0: // end ordering and calculate total
                                checkBill();
                                //System.out.println("Press -1 Show Bill");
                                orderList.clear();
                                orderAmount.clear();
                                break;
                                
                            case 1: // order
                                while (true){
                                    System.out.print("Enter amount: ");
                                    int amount = sc.nextInt();
                                    if (Menus.get(orderId-1) instanceof Cake){
                                        if (orderList.size()==0){
                                            if (amount <= Menus.get(orderId-1).getQuantity()){
                                                orderList.add(orderId);
                                                orderAmount.add(amount);
                                                break;
                                            } else{
                                                System.out.println("Not enough");
                                                break;
                                            }
                                        } else {
                                            if (orderList.contains(orderId)){
                                                int indexId = orderList.indexOf(orderId);
                                                if (orderAmount.get(indexId)+amount <= 0){
                                                    orderList.remove(indexId);
                                                    orderAmount.remove(indexId);
                                                    break;
                                                } else if (orderAmount.get(indexId)+amount <= Menus.get(orderId-1).getQuantity()){
                                                    orderAmount.set(indexId,orderAmount.get(indexId)+amount);
                                                    break;
                                                } else{
                                                    System.out.println("Not enough");
                                                    break;
                                                }
                                            } else{
                                                orderList.add(orderId);
                                                orderAmount.add(amount);

                                                break;
                                            }
                                        }
                                    } else if (Menus.get(orderId-1) instanceof Drink){
                                        if (orderList.size() == 0){
                                            orderList.add(orderId);
                                            orderAmount.add(amount);
                                            break;
                                        } else{
                                            if (orderList.contains(orderId)){
                                                int indexId = orderList.indexOf(orderId);
                                                if (orderAmount.get(indexId)+amount <= 0){
                                                    orderList.remove(indexId);
                                                    orderAmount.remove(indexId);
                                                    break;
                                                } else{
                                                    orderAmount.set(indexId,orderAmount.get(indexId)+amount);
                                                    break;
                                                }
                                            } else{
                                                orderList.add(orderId);
                                                orderAmount.add(amount);
                                                break;
                                            }
                                        }
                                    } else{
                                        System.out.println("menuid not match");
                                    }
                                }
                                break;
                            case -9999:
                                System.out.println("menuId entered not in menu");
                            }
                        }
                case 2:
                    for (String hist: history){
                        System.out.println(hist);
                    }
                    System.out.println("Press any key to back to Main Menu") ;
                    sc.next();
                    clear();
                    break;
                case 3:
                    clear();
                    runMain = false;
                    break;
            }
        }
    }
    
    public static void setData() {
        m1 = new Cake(1,"Honey Toast        "        ,120,10);
        m2 = new Cake(2,"Rassberry Pancake  "   ,89,10);
        m3 = new Cake(3,"Chocolate Cake     "      ,49,10);
        m4 = new Cake(4,"Cheesecake         "          ,55,10);
        m5 = new Cake(5,"Hokkaido Cheesecake" ,89,10);
        m6 = new Cake(6,"Tiramisu           "            ,90,10);
        m7 = new Cake(7,"Crape Cake         "          ,50,10);
        m8 = new Drink(8,"Milk Tea           "              ,35,0);
        m9 = new Drink(9,"Green Tea          "              ,40,0);
        m10 = new Drink(10,"Americano          "              ,50,0);
        m11 = new Drink(11,"Late               "              ,50,0);
        m12 = new Drink(12,"Cappucino          "              ,50,0);
        m13 = new Drink(13,"Chocolate          "              ,45,0);
        m14 = new Drink(14,"Lemon Tea          "              ,35,0);
        
        
        Menus.add(m1);
        Menus.add(m2);
        Menus.add(m3);
        Menus.add(m4);
        Menus.add(m5);
        Menus.add(m6);
        Menus.add(m7);
        Menus.add(m8);
        Menus.add(m9);
        Menus.add(m10);
        Menus.add(m11);
        Menus.add(m12);
        Menus.add(m13);
        Menus.add(m14);
        
    }
    
    public static void retrieveMenu() {
        System.out.println("-------------- Menu Cafe --------------");
        
        for (Menu menu : Menus) {
            menu.getInfo();
        }
    }

    
    private static void checkBill() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd / MMM / yyyy     HH:mm:ss: a");  
        Date date = new Date();  
        String dt = formatter.format(date);
        int toTal = 0;
        if (orderList.size() < 1){
            return ;
        }
        
        
        for (int i=0; i < orderList.size(); i++) {
            for (Menu menu: Menus){
                if (menu.getId() == orderList.get(i)){
                    toTal += menu.getPrice() * orderAmount.get(i);
                    menu.setQuantity(menu.getQuantity()-orderAmount.get(i));
                }
            }   
        }
        System.out.println("----------------------------------------");
        System.out.println("Total       = "+toTal);
        System.out.print("Enter recieve amount: ");
        int reCeive = sc.nextInt();
        
        while (reCeive < toTal){
            System.out.println("Amount of paid is not enough");
            System.out.print("Enter recieve amount: ");
            reCeive = sc.nextInt();
        }
        clear();
        
        System.out.println("\t\tReciept");
        System.out.println("----------------------------------------");
        System.out.printf("%-3s %-20s%n",orderOfday,dt);
        System.out.printf("%-3s %-20s %-4s%n","Id","Menu","Amount");
        System.out.println("----------------------------------------");
        for (int i=0;i<orderList.size();i++){
            for (Menu menu: Menus){
                if (menu.getId()==orderList.get(i)){
                    System.out.printf("%-3s %-20s %-4s%n",orderList.get(i),menu.getName(),orderAmount.get(i));
                    break;
                }
            }
            
        }
        System.out.println("----------------------------------------");
        System.out.println("Total = "+toTal);
        System.out.printf("%-10s %-10s%n","Receive = ",reCeive);
        System.out.printf("%-10s %-10s%n","Chance = ",reCeive-toTal);
        saveHistory(dt, orderOfday, reCeive, toTal);
        orderOfday += 1;
        retrieveMenu();
    }

    private static void getMainMenu() {
        System.out.println("-------------------- Cafe System ---------------------");
        System.out.println("1. Order");
        System.out.println("2. History");
        System.out.println("3. Exit");
        System.out.print("Enter menu :");
    }

    private static boolean checkId(int id) {
        for (Menu menu: Menus) {
            if (menu.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    private static int caseId(int id) {
        if (id == -1) { //go back to Main Menu
            return -1;
        } else if (id == 0){
            return 0;
        } else if (checkId(id)){
            return 1;
        } else {
            return -9999;
        }
    }
    
    public static void showOrdered(){
        //sc.next();
        //clear();
        if (orderList.size() == 0){
            System.out.println("Not Order yet");
        } else{
            for (Menu menu: Menus){
                for (int i=0; i < orderList.size(); i++){
                    if (menu.getId() == orderList.get(i)){
                        System.out.printf("%-3s %-20s %-5s%n",menu.getId(),menu.getName(),orderAmount.get(i));
                        break;
                    }
                }
            }
        }
    }

    public static void saveHistory(String dt, int orderOfday, int reCieve, int toTal) {
        String s = "--------------------------------------------------";
        String a = String.format("%-3s %-20s %-4s","id","name","amount");
        String head = String.format("%-3s %20s",orderOfday,dt);
        String reC = String.format("%-10s %-10s","Receive = ",reCieve);
        String ch = String.format("%-10s %-10s","Chance = ",reCieve-toTal);
        history.add(s);
        history.add(head);
        for (Menu menu: Menus){
            for (int i = 0; i < orderList.size(); i++){
                if (menu.getId() == orderList.get(i)){
                    a = String.format("%-3s %-20s %-4s",menu.getId(),menu.getName(),orderAmount.get(i));
                    history.add(a);
                    break;
                }
            }
        }
        history.add(reC);
        history.add(ch);
        history.add(s);
    }

    private static void clear() {
        for(int i = 0; i<50; i++) {
            System.out.println("");
        }
    }
}