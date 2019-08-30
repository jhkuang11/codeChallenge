/*
import java.util.List;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) {
	    double p = 11.25;
	    double e = 20;
		Main.calculateChange(p, e);
	}
	
	static double[] coins;
	static {
    coins = new double[] {5000,2000,1000,500,200,100,50,20,10,5,2,1};
	}
	
	public static void calculateChange(double purchasePrice, double cash) {
	    double change = cash*100 - purchasePrice*100;
	    List<Double> ans = new ArrayList<Double>();
	    Main.helper(change, ans, change);
	    for(double s: ans){
	        s /= 100;
	        System.out.println(s);
	    }
	}
	
	public static void helper(double remains, List<Double> ans, double change) {
	    if (remains == 0) return;
	    for (int i = 0; i < coins.length; i++){
	        if (coins[i] > remains) continue;
	        ans.add(coins[i]);
	        Main.helper(remains-coins[i], ans, change);
	        if (sum(ans) == change) return;
	        ans.remove(ans.size()-1);
	    }
	    return;
	}
	
	public static double sum(List<Double> list) {
	    double sum = 0.0;
	    for (double i: list) {
	        sum += i;
	    }
	    return sum;
	}
  
}
*/

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    String str = "Vodafone,STOCK,10|Google,STOCK,15|Microsoft,BOND,15:Vodafone,STOCK,15|Google,STOCK,10|Microsoft,BOND,15";
	    String[] list = str.split(":");
	    String portfolio = list[0];
		String[] part = portfolio.split(",");
		List<String> process = process_string(part);
		List<Asset> asset = process_asset(process);
		Collections.sort(asset);
		System.out.println("Portfolio: "+asset);
		
		String benchmark = list[1];
		String[] bench = benchmark.split(",");
		List<String> process2 = process_string(bench);
		List<Asset> asset2 = process_asset(process2);
		Collections.sort(asset2);
		System.out.println("Benchmark: "+asset2);
		transact(asset2, asset);
		/*
		if ( asset.get(0).getType().compareTo(asset2.get(1).getType()) != 0 ) {
		    System.out.println("Not equal");
		}
		*/
	}
	
	public static void transact(List<Asset> benchmark, List<Asset> portfolio){
	    for (Asset asset: benchmark){
	        int i = 0;
	        // find the corresponding asset in the portfolio
	        while ( (portfolio.get(i).getName().compareTo(asset.getName()) != 0) || (portfolio.get(i).getType().compareTo(asset.getType()) != 0) ){
	            i++;
	            if (i == portfolio.size()) break;
	        }
	        
	        // if the asset not in the portfolio
	        if (i == portfolio.size()){
	            System.out.print("BUY"+","+asset.getName()+","+asset.getType()+","+asset.getShares());
	            System.out.println();
	            continue;
	        }
	        
	        Integer diff = Integer.parseInt(portfolio.get(i).getShares()) - Integer.parseInt(asset.getShares());
	        if (diff > 0){
	            System.out.print("SELL"+","+asset.getName()+","+asset.getType()+","+Integer.toString(diff));
	            System.out.println();
	        }
	        else if (diff < 0){
	            diff *= -1;
	            System.out.print("BUY"+","+asset.getName()+","+asset.getType()+","+Integer.toString(diff));
	            System.out.println();
	        } else{
	            continue;
	        }
	    }
	    return;
	}
	
	public static class Asset implements Comparable<Asset> {
	    String name;
	    String type;
	    String shares;
	    
	    public Asset(String name, String type, String shares){
	        this.name = name;
	        this.type = type;
	        this.shares = shares;
	    }
	    
	    public String getName(){
	        return name;
	    }
	    
	    public String getType(){
	        return type;
	    }
	    
	    public String getShares(){
	        return shares;
	    }
	    
	    @Override
	    public String toString() { 
	        return (this.getName()+"_"+this.getType()+"_"+this.getShares());
	    }
	    
	    @Override
	    public int compareTo(Asset other) {
	        int i = this.getName().compareTo(other.getName());
	        if (i != 0) return i;
	        return this.getType().compareTo(other.getType());
	    }
	}
	
	public static List<Asset> process_asset(List<String> assets){
	    List<Asset> ans = new ArrayList<Asset>();
	    for (int i = 0; i < assets.size() - 2; i += 3){
	        String name = assets.get(i);
	        String type = assets.get(i+1);
	        String shares = assets.get(i+2);
	        Asset asset = new Asset(name, type, shares);
	        ans.add(asset);
	    }
	    return ans;
	}
	
	public static List<String> process_string(String[] values){
	    List<String> ans = new ArrayList<String>();
	    for (String s: values){
	        if (s.contains("|")){
	            String[] ds = s.split("\\|");
	            for (String ss: ds){
	                ans.add(ss);
	            }
	        }
	        else{
	            ans.add(s);
	        }
	    }
	    return ans;
	}
}