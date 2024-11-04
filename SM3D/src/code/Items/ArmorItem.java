package code.Items;

public class ArmorItem extends Item {
    
    //percentage
    private int bulletDefence;
    private int radDefence;
    private int anomalyDefence;
    
    public ArmorItem(String gameName, String desc, 
            int cost, int weight, boolean canSell, 
            int bulletDefence, int radDefence, int anomalyDefence) {
        
        super(gameName, desc, cost, weight, canSell);
        
        this.bulletDefence = bulletDefence;
        this.radDefence = radDefence;
        this.anomalyDefence = anomalyDefence;
    }

}
