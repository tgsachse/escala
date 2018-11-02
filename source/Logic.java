// Part of Escala.
// Written by Jonathan Ponader.

package escala;

import escala.*;
import java.util.*;
import javax.swing.*;
import escala.structures.*;

/*
*	0	17	Asia(China, India Philippines)
*	1	8	Eastern Europe (All of Russia)
*	2	8	Latin/Central America(Mexico, Central Caribbean)
*	3	5	Middle East
*	4	7	North Africa
*	5	13	North America (Canada, US)
*	6	12	Oceania
*	7	10	South Africa
*	8	6	South America
*	9	14	Western Europe
*/

public class Logic
{
	private Game game;
	private int marketShare;
	private int cash = 400;
	private int logistics = 5;
	private int marketing = 5;
	private int product = 5;
	private Calendar cal;
	private static ArrayList<Region> regions;
	private boolean free = true;

	public Logic(Game game)
	{
		cal = Calendar.getInstance();
		cal.set(1000,1,1);
		this.game = game;
		regions = game.getAllRegions();
	}

    //Sets Key Stats for Game
	public void setLogic(int cash, int product, int marketing, int logistics)
	{
		this.cash = cash;
		this.product = product;
		this.marketing = marketing;
		this.logistics = logistics;
	}

	public boolean purchaseRegion(Region r){
		return isValidPurchase(r.getEntryCost(), r.getEfficiencyCost(), r.getMarketingCost(), r.getLogisticsCost());
	}

	//Checks to see if the player can afford the purchase, if so purchases the goods
	public boolean isValidPurchase(int cash, int product, int marketing, int logistics)
	{
		if (!free)
		{
			if((this.cash - cash) < 0)
				return false;

			if((this.product - product) < 0)
				return false;

			if((this.marketing - marketing) < 0)
				return false;

			if((this.logistics - logistics) < 0)
				return false;

			updateStats(cash * -1, product * -1, marketing * -1, logistics * -1);
		}

		free = false;

		return true;
	}

	//updates the stats after purchase or improvements
	public void updateStats(int cash, int product, int marketing, int logistics)
	{
		this.cash += cash;
		this.product += product;
		this.marketing += marketing;
		this.logistics += logistics;
	}

	public String cashToString()
	{
		String string = String.format("$ %06d", cash);
		return string;
	}

	public String shareToString()
	{
		String string = String.format("%02d %%", marketShare);
		return string;
	}

		//Returns Date for Map Label
    public String getDate()
    {
    	String string = String.format("%02d/%02d/%02d", 
    		cal.get(Calendar.MONTH), 
    		cal.get(Calendar.DAY_OF_MONTH), 
    		(cal.get(Calendar.YEAR)%100));
		return string;
    }

	public int getLog(){
		return this.logistics;
	}

	public int getProd(){
		return this.product;
	}

	public int getMark(){
		return this.marketing;
	}

	public int getShare(){
		return marketShare;
	}

	public static void purchaseReg(int region)
	{
		regions.get(region).purchase();
	}

	public void timedUpdate()
	{
		int minDiff =  Math.min(logistics, Math.min(marketing, product)) + 20;
		int log = Math.min(minDiff,this.logistics);
		int mark = Math.min(minDiff,this.marketing);
		int prod = Math.min(minDiff,this.product);
		double total = 0;

		for (Region region : regions) {
            if (region.isPurchased()) {

                 region.incrementMarketShare(.01 * ((.33 * log) + (.33 * mark) + (.33 * prod)));

				if(region.getMarketShare() > 100)
					region.setMarketShare(100);
			}

            total += region.getMarketShare()  * region.getWorldShare();
        }

		this.cal.add(Calendar.DAY_OF_MONTH, 1);
		this.marketShare = (int)total;
		this.cash += (int)((total) * (log + mark + prod) * 20);
		winState();
	}


	// To Add Win Loose Page
	//Ends Game in Win or Loose Condition
	private void winState(){
		if(this.marketShare >= 100){
			JOptionPane.showMessageDialog(null, "You Win, Thank you For Playing!");
			System.exit(0);
		}

		else if((cal.get(Calendar.YEAR)%100) == 10){
			JOptionPane.showMessageDialog(null, "You Loose, Better Luck Next Time");
			System.exit(0);
		}
		else
			return;

		game.stopGame();

	}
}
