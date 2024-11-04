package code.Items;

public class WeaponItem extends Item {
    
    private int accuracy; //%???
    private int magSize; //патронов в магазине
    private int damage; //урон от выстрела
    private int reloadTime; //время перезарядки*10
    private boolean hasScope;
    
    public WeaponItem(String gameName, String desc, 
            int cost, int weight, boolean canSell, 
            int accuracy, int magSize, int damage, int reloadTime, boolean hasScope) {
        
        super(gameName, desc, cost, weight, canSell);
        
        this.accuracy = accuracy;
        this.magSize = magSize;
        this.damage = damage;
        this.reloadTime = reloadTime;
        
        this.hasScope = hasScope;
    }
}
