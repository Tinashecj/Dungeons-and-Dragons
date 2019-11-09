import java.util.*;
import java.io.*;

class Game{
    public static void main(String[] args){
		Difficulty();
    }
	public static void battle(GameCharacter g1, GameCharacter g1enemycounter){
		Random r = new Random();
		Scanner kb = new Scanner(System.in);
		int g1Attack;
		int g1Defence;
		int g1enemycounterAttack;
		int g1enemycounterDefence;
		int deadenemies = 1;
		int items = 0;
		int itemDefence = 0;
		int itemAttack = 0;
		int itemHealth = 0;
		int difficulty = 0;
		Scanner readlevel = null;
		String Capturedifficulty = "";
		int difficultycount = 0;
		String [] weapons = {"Sword","Staff","Bow and Arrows","Katana","Dagger","machete"};
		String [] armor = {"Shield","Armor"};
		int randomweapon = r.nextInt(6);
		int randomarmor = r.nextInt(2);
		String UsedWeapon = weapons[randomweapon];
		String UsedDefence = armor[randomarmor];
		
		while(difficultycount<1){
			try{
				readlevel = new Scanner(new File("Gamelevel.txt"));
					Capturedifficulty = readlevel.nextLine();
					difficulty = Integer.parseInt(Capturedifficulty);
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			readlevel.close();
			difficultycount++;
		}
		
		int playercurrenthealth = g1.getHealth();
		int enemycurrenthealth = g1enemycounter.getHealth();
		
		//generate dice for attacks and defence.
		
		
		while (g1.getHealth()>0&&g1enemycounter.getHealth()>0){
			int diceattack = r.nextInt(5)+1;
		
			//apply the dice to player and enemies attacks and defences.
			int yourAttack = ((g1.getAttack()/5)*diceattack);
			int yourDefence = ((g1.getDefence()/5)*diceattack);
			int enemyattack = ((g1enemycounter.getAttack()/5)*diceattack);
			int enemyDefence = ((g1enemycounter.getDefence()/5)*diceattack);
			//set up values for this round
			g1Attack = yourAttack;
			g1Defence = yourDefence;
			g1enemycounterAttack = enemyattack;
			g1enemycounterDefence = enemyDefence;
			//When the enemy fails to defend against my attacks.
			if(g1.getHealth()>=1){
			   if (g1Attack>g1enemycounterDefence){
				   int damage = g1Attack-g1enemycounterDefence;
				   g1enemycounter.setHealth(g1enemycounter.getHealth() - damage);
				   System.out.println("It's "+g1.getName()+"'s turn to attack:");
				   System.out.println(g1.getName()+" attacked and hit "+ g1enemycounter.getName());
				   System.out.println(g1.getName()+"'s attack value is "+ g1Attack);
				   System.out.println(g1enemycounter.getName()+"'s defence value is "+ g1enemycounterDefence);
				   System.out.println(g1enemycounter.getName()+"'s incurred a damage of "+ damage);
				   g1.setDamage(g1.getDamage() + damage);
				   System.out.println(g1enemycounter.getName()+"'s remaining health is now at "+ g1enemycounter.getHealth()+"\n");
			   }
			    //When the enemy manages to defend my attack.
			   else {
					int damage = g1Attack-g1enemycounterDefence;
					if(g1enemycounterDefence>g1Attack){
					   damage = 0;
				   }
					int life = enemycurrenthealth-damage;
					g1enemycounter.setHealth(g1enemycounter.getHealth() - damage);
					System.out.println("It's "+g1.getName()+"'s turn to attack:");
					System.out.println(g1enemycounter.getName()+" defended your attack");
					System.out.println(g1.getName()+"'s attack value is "+ g1Attack);
					System.out.println(g1enemycounter.getName()+"'s defence value is "+ g1enemycounterDefence);
					System.out.println(g1enemycounter.getName()+"'s incurred no damage");
					System.out.println(g1enemycounter.getName()+"'s health remains at "+life+"\n");
			   }
			}
		   
			//When the enemy successfully lands a hit on me.
			if(g1enemycounter.getHealth()>=1){
				 if (g1enemycounterAttack>g1Defence){
				   int damage = g1enemycounterAttack-g1Defence;
				   g1.setHealth(g1.getHealth() - damage);
				   System.out.println("It's "+g1enemycounter.getName()+"'s turn to attack:");
				   System.out.println("You  were attacked by "+ g1enemycounter.getName());
				   System.out.println(g1enemycounter.getName()+"'s attack value is "+ g1enemycounterAttack);
				   System.out.println("Your defence value is "+ g1Defence);
				   System.out.println("You incurred a damage of "+ damage);
				   System.out.println("Your remaining health is now at "+ (g1.getHealth())+"\n");
				   if (g1.getHealth()>0){
					   System.out.println("Press the enter key to continue..");
					   kb.nextLine();
				   }
			   }
			   //When i manage to defend against the enemy's attack.
			   else {
				   int damage = g1enemycounterAttack-g1Defence;
				   if(g1enemycounterAttack<g1Defence){
					   damage = 0;
				   }
					int life = playercurrenthealth-damage;
				   g1.setHealth(g1.getHealth() - damage);
				   System.out.println("It's "+g1enemycounter.getName()+"'s turn to attack:");
				   System.out.println(g1enemycounter.getName()+" attacked you though you managed to defend yourself");
				   System.out.println(g1enemycounter.getName()+"'s attack value is "+ g1enemycounterAttack);
				   System.out.println("Your defence value is "+ g1Defence);
				   System.out.println("You incurred no damage");
				   System.out.println("Your health remains at "+life+"\n");
				   if (g1.getHealth()>0){
					   System.out.println("Press the enter key to continue..");
					   kb.nextLine();
				   }
			   }
		}
		}
		if (g1.getHealth()<=0){
			System.out.println("Game Over");
			System.out.println("The "+ g1enemycounter.getName()+" killed you\n");	
			highestscore(g1);
		}
		else {
			System.out.println("You have defeated the mighty "+ g1enemycounter.getName()+"\n");
			deadenemies++;
		}
		
		if(difficulty == 1 && deadenemies % 2 == 0 && g1.getHealth()>0){
			g1.setItems(g1.getItems() + 1);
			System.out.println("Defence  = 1     Attack = 2    Health = 3");
			System.out.println("You've happened upon a chest with certain items, though you can only take one. Enter the corresponding number in order to select what hero attribute you wish to enhance for yourself");
			items = kb.nextInt();
			
			if(items == 1){
				itemDefence = r.nextInt(11)+10;
				System.out.println("You have picked up a/some "+UsedDefence+", your defence has been enhanced by "+itemDefence+"\n");
				g1.setDefence(g1.getDefence()+itemDefence);
			}
			else if(items == 2){
				itemAttack = r.nextInt(11)+10;
				System.out.println("You have picked up a "+UsedWeapon+", your defence has been enhanced by "+itemAttack+"\n");
				g1.setAttack(g1.getAttack()+itemAttack);
			}
			else if(items == 3){
				itemHealth = r.nextInt(11)+10;
				System.out.println("You have picked up a health potion, your defence has been enhanced by "+itemHealth+"\n");
				g1.setHealth(g1.getHealth()+itemHealth);
			}
			
		else if(difficulty == 2 && deadenemies % 4 == 0 && g1.getHealth()>0){
			g1.setItems(g1.getItems() + 1);
			System.out.println("Defence  = 1     Attack = 2    Health = 3");
			System.out.println("You've happened upon a chest with certain items, though you can only take one. Enter the corresponding number in order to select what hero attribute you wish to enhance for yourself");
			items = kb.nextInt();
			
			if(items == 1){
				itemDefence = r.nextInt(31)+10;
				System.out.println("You have picked up a/some "+UsedDefence+", your defence has been enhanced by "+itemDefence+"\n");
				g1.setDefence(g1.getDefence()+itemDefence);
			}
			else if(items == 2){
				itemAttack = r.nextInt(31)+10;
				System.out.println("You have picked up a "+UsedWeapon+", your defence has been enhanced by "+itemAttack+"\n");
				g1.setAttack(g1.getAttack()+itemAttack);
			}
			else if(items == 3){
				itemHealth = r.nextInt(31)+10;
				System.out.println("You have picked up a health potion, your defence has been enhanced by "+itemHealth+"\n");
				g1.setHealth(g1.getHealth()+itemHealth);
			}
		}
			
		else if(difficulty == 3 && deadenemies % 10 == 0 && g1.getHealth()>0){
			g1.setItems(g1.getItems() + 1);
			System.out.println("Defence  = 1     Attack = 2    Health = 3");
			System.out.println("You've happened upon a chest with certain items, though you can only take one. Enter the corresponding number in order to select what hero attribute you wish to enhance for yourself");
			items = kb.nextInt();
			
			if(items == 1){
				itemDefence = r.nextInt(51)+10;
				System.out.println("You have picked up a/some "+UsedDefence+", your defence has been enhanced by "+itemDefence+"\n");
				g1.setDefence(g1.getDefence()+itemDefence);
			}
			else if(items == 2){
				itemAttack = r.nextInt(50)+10;
				System.out.println("You have picked up a "+UsedWeapon+", your defence has been enhanced by "+itemAttack+"\n");
				g1.setAttack(g1.getAttack()+itemAttack);
			}
			else if(items == 3){
				itemHealth = r.nextInt(51)+10;
				System.out.println("You have picked up a health potion, your defence has been enhanced by "+itemHealth+"\n");
				g1.setHealth(g1.getHealth()+itemHealth);
			}
		}
		
		}
	}
	public static void Difficulty(){
		Scanner kb = new Scanner (System.in);
		Random r = new Random();
		Scanner names = null;
		PrintWriter intdifficulty = null;	 
		String Enemynames = "";
		int totalrooms = 0;
		int enemycounter = 0;//variable will be concatenated to g1 to make it reusable for more enemies in a loop.
		int roomcounter = 0;
		int repeatquestion = 1;
		String allenemiesArray [] = new String [1001];
		int difficulty = 0;
		int allenemiescounter = 0;
		int loopcounter = 0;
		String Hero = "";
		
		System.out.println("Enter the name of your hero...");
		Hero = kb.nextLine();
		
		GameCharacter g1 = new GameCharacter(Hero, 500, 70, 30, 0, 0);
			
				try{
					System.out.println("Easy = 1      Medium = 2     Hard = 3");
					System.out.println("To select the difficulty you'd like to play on, enter the number next to the corresponding level above.");
					difficulty = kb.nextInt();
					intdifficulty = new PrintWriter(new File("Gamelevel.txt"));	
					if(difficulty == 1||difficulty == 2||difficulty == 3){
						intdifficulty.println(difficulty);
					}
				}
				catch(IOException e){
					System.out.println("Please enter an integer inclusive of or between 1 and 3.");
				}
				intdifficulty.close();
				
			EnteringDungeon();
			
		if(difficulty == 1){
			totalrooms = r.nextInt(4)+3;//Randomly create the number of rooms between 3 and 6.
		}
		
		if(difficulty == 2){
			totalrooms = r.nextInt(5)+7;//Randomly create the number of rooms between 7 and 11.
		}
		
		if(difficulty == 3){
			totalrooms = r.nextInt(9)+12;//Randomly create the number of rooms between 9 and 20.
		}
		
		System.out.println("There are "+totalrooms+" rooms on this level, good luck.\n");
		
		System.out.println("You have entered a room with enemies scattered inside it.");
		Spotted();
		
		for(loopcounter = 0;loopcounter<totalrooms;loopcounter++){
			int totalenemies = r.nextInt(4)+3;//You can only find between 3 and 6 enemies in each room;
			String enemyarray [] = new String [totalenemies];
			try{
				names = new Scanner (new File("Names.txt"));
				for(int counter = 0;counter<totalenemies;counter++){//while there are more enemies...
					enemyarray[counter] = names.nextLine();//read the next name from the text file and put it in an array.
				}
				while(names.hasNextLine() && allenemiescounter!= 1001){
					allenemiesArray[allenemiescounter] = names.nextLine();//read the next name from the text file and put it in an array.
					allenemiescounter++;
				}
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			names.close();
			if (g1.getHealth()>0){
				System.out.println("There are "+totalenemies+" enemies in the room");
			}
			for(int u = 0;u<enemyarray.length;u++){
				int allenemies = r.nextInt(999);//randomly pick a name from the array.
				Enemynames = allenemiesArray[allenemies];//take the from the array name and put it into a string, with each iteration, the name will change.
				 if (g1.getHealth()<=0){
					  break;
					}
					keepmoving();
				System.out.println(Enemynames+" is approaching you, hurry and press enter to fight!!\n");
				kb.nextLine();
				if(difficulty == 1){
					GameCharacter g1enemycounter = new GameCharacter(Enemynames, 250, 50, 50, 0, 0);//All enemies will have these stats and g1enemycounter
					System.out.println(Enemynames+"'s health is at "+g1enemycounter.getHealth());//will go up with each iteration so that i can create a new enemy in the loop. 
					battle(g1, g1enemycounter);//fight the next enemy that we got from the array.
					enemycounter++;//change this variable so that we can continue to create new enemies. 
				}
				else if(difficulty == 2){
					GameCharacter g1enemycounter = new GameCharacter(Enemynames, 280, 55, 50, 0, 0);//the enemies at this level are more and slightly stronger than the ones at the Easy level;
					System.out.println(Enemynames+"'s health is at "+g1enemycounter.getHealth());//will go up with each iteration so that i can create a new enemy in the loop. 
					battle(g1, g1enemycounter);//fight the next enemy that we got from the array.
					enemycounter++;//change this variable so that we can continue to create new enemies. 
				}
				else if(difficulty == 3){
					GameCharacter g1enemycounter = new GameCharacter(Enemynames, 350, 70, 70, 0, 0);//the enemies at this level are more and slightly stronger than the ones at the Medium level;
					System.out.println(Enemynames+"'s health is at "+g1enemycounter.getHealth());//will go up with each iteration so that i can create a new enemy in the loop. 
					battle(g1, g1enemycounter);//fight the next enemy that we got from the array.
					enemycounter++;//change this variable so that we can continue to create new enemies. 
				}
			}
			roomcounter++;
			 if (g1.getHealth()>0){
				System.out.println("You have cleared room number "+roomcounter+"\n");
			 }
		}
		if(loopcounter == totalrooms && g1.getHealth()>0){
			System.out.println("Well done, you win!!\n");
			highestscore(g1);
		}
	}
	//for the 3 methods below, i had to take user input from methods because it didn't work inside the difficulty method.
	public static void EnteringDungeon(){
		Scanner kb = new Scanner (System.in);
		System.out.println("You have now entered the Dungeon, press enter to walk..");
		kb.nextLine();
	}
	public static void Spotted(){
		Scanner kb = new Scanner (System.in);
		System.out.println("You have spotted your first enemy, press the enter on your keyboad to continue..");
		kb.nextLine();
	}
	public static void keepmoving(){
		Scanner kb = new Scanner (System.in);
		System.out.println("Press the enter key on your keyboard to keep moving..");
		kb.nextLine();
	}
	public static void highestscore(GameCharacter g1){
		Scanner kb = new Scanner (System.in);//incomplete method to try and keep score.
		Random r = new Random();
		PrintWriter Scores = null;
		int PlayerDamage = g1.getDamage();
		String temporarynamestore = "";
		
		try{
				Scores = new PrintWriter (new File("Scores.txt"));
				Scores.println(g1.getName()+"   -    "+PlayerDamage);
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			Scores.close();
				
		}
			
	}