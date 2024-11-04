package code.Items;

import code.Scripts;
import java.util.Hashtable;

/**
 *
 * @author aldo
 */
public class Item {

    //Описание предмета и "игровое" имя
    private String name;
    private String gameName;
    private String desc;
    
    //Инвентарная основа
    private int cost;
    private int weight;//вес*10
    private boolean canSell;

    //расположение иконки интерфейса
    private int xStart, xEnd;
    private int yStart, yEnd;
    
    private static Hashtable itemsList;
    
    //TODO add xStart,xEnd, yStart,yEnd
    public Item(String gameName, String desc, 
            int cost, int weight, boolean canSell) {
        this.gameName = gameName;
        this.desc = desc;
        
        this.cost = cost;
        this.weight = weight;
        this.canSell = canSell;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public static void loadItems() {
        itemsList = new Hashtable();
        //добавим пару медикаментов
        itemsList.put("med", 
                new HealItem("Field medpack",
                "A simple tool to patch you up right in the middle"
                        + "of a battle",
                100,5,true,25,0));
        itemsList.put("bigm", 
                new HealItem("Army medpack",
                "Powerfull pack with bunch of needles, bandages,"
                        + "aspirin and gematogen from Mother"
                        + " Russia. Helps alot"
                        + "in any hard battle",
                250,10,true,75,0));
    }
    
    public static Item getItem(String name) {
        return (Item) itemsList.get(name);
    }
}
