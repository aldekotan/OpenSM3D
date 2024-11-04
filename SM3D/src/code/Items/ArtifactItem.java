package code.Items;

public class ArtifactItem extends Item {

    private int accuracyBonus;//в %
    private int healthBonus;
    
    public ArtifactItem(String gameName, String desc, 
            int cost, int weight, boolean canSell, 
            int accuracyBonus, int healthBonus) {
        
        super(gameName, desc, cost, weight, canSell);
        
        this.accuracyBonus = accuracyBonus;
        this.healthBonus = healthBonus;
    }
}
