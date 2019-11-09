class GameCharacter{
	private String name;
	private int health;
	private int attack;
	private int defence;
	private int items;
	private int damage;
	
	//constructor
	public GameCharacter(String n, int h, int a, int d, int it,int dam){
		name = n;
		health = h;
		attack = a;
		defence = d;
		items = it;
		damage = dam;
	}
	public String getName(){
		return name;
		}
	public void setName(String n){
		name = n;
		}
	public int getHealth(){
		return health;
		}
	public void setHealth(int h){
		health = h;
		}
	public int getAttack(){
		return attack;
		}
	public void setAttack(int a){
		attack = a;
		}
	public int getDefence(){
		return defence;
		}
	public void setDefence(int d){
		defence = d;
		}
	public int getItems(){
		return items;
		}
	public void setItems(int it){
		items = it;
		}
	public int getDamage(){
		return damage;
		}
	public void setDamage(int dam){
		damage = dam;
		}
}