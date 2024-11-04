package code.Items;

public class AmmoItem extends Item {
    
    private String targetWeapon; //какое оружие подходит
    private int bullets;
    
    public AmmoItem(String gameName, String desc, 
            int cost, int weight, boolean canSell, 
            String targetWeapon, int bullets) {
        
        super(gameName, desc, cost, weight, canSell);
        
        this.targetWeapon = targetWeapon;
        this.bullets = bullets;
    }
}
