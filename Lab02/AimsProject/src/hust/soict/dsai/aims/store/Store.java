package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;

public class Store {
	public static final int MAX_DVDs = 1000005;
	private ArrayList<Media> itemsInStore = new ArrayList<Media>();
	public static int qtyDVD = 0;
	
	public void addMedia(Media item) {
        if (qtyDVD < MAX_DVDs) {
        	itemsInStore.add(item);
        	qtyDVD++;
            System.out.println("The item has been added to the store.");
        } 
        else {
            System.out.println("The store is full.");
        }
    }
	
	public void removeMedia(Media item) {
		itemsInStore.remove(item);
		qtyDVD--;
		System.out.println("The DVD has been removed from the store.");
    }
	
	public void printStore() {
    	System.out.println("***********************STORE***********************");
    	System.out.println("Store's items:");
    	
    	for (Media item : itemsInStore) {   
    		if(item == null) break;
    		System.out.println("Item - " + item.getTitle() + " - " + item.getCategory() + " - "  + item.getCost() + " $");    		
	    }
    	System.out.println("***************************************************");	
    }
    
    public Media searchStore(String title) {
    	System.out.println("***********************STORE***********************");
    	System.out.println("Searching Items: " + title);
    	Media found = null;
    	for (Media item : itemsInStore) {   
    		if(item == null) {
    			System.out.println("NOT FOUND");
    			return null;
    		}
    		if(item.getTitle().equalsIgnoreCase(title)) {
    			System.out.println("Item - " + item.getTitle() + " - " + item.getCategory() + " - " + item.getCost() + " $");
    			found = item;
    			break;
    		}    			
	    }
    	return found;
    }
    
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
    
    
}
