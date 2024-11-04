package code.Items;

public class HealItem extends Item {
    
    private int heal; //лечение
    private int antiradDuration; //длительность антирада
    
    public HealItem(String gameName, String desc, 
            int cost, int weight, boolean canSell, 
            int heal, int antiradDuration) {
        
        super(gameName, desc, cost, weight, canSell);
        
        this.heal = heal;
        this.antiradDuration = antiradDuration;
    }

}
