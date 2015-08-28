package com.example.auctionapplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.example.auctionapplicationIntermed.AuctionItem;

import ItemService.ItemServiceClient;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import edu.neumont.csc180.mvc.Controller;
import fileio.BidServer;



public class SearchController extends Controller<SearchModel> implements SearchView.Listener  {

////	AuctionItem mercedes = new AuctionItem(BigDecimal.valueOf(15000), "Mercedes Benz", "car", "A Mercedes Benz", 1 , new Date() , new Date());
//	//	AuctionItem toaster = new AuctionItem(BigDecimal.valueOf(25), "Deluxe Toaster","toaster", "A Toaster", 2);
//	//	AuctionItem kitchenset = new AuctionItem(BigDecimal.valueOf(100), "Kitchen Set","kitchenset", "A kitchen set", 3);
//	//	AuctionItem sodamachine = new AuctionItem(BigDecimal.valueOf(130), "Soda Machine","sodamachine","A Soda Machine", 4);
//
//
//	ArrayList<AuctionItem> allitems = new ArrayList<AuctionItem>(); //removed items, now you must add items
	
	BidServer dbc = new BidServer();
	ItemServiceClient isc = new ItemServiceClient();
	
	@Override
	public void searchDisplay(String searchfield) {

	}


	@Override
	public void onResume(){
		super.onResume();

		searchItems("");

	}

	
	
	@Override
	public void productDisplayBid(double i) {
		model.setSearchedItemPrice(i);

	}
	public void itemClicked(AuctionModel model){
		Intent intent = new Intent(this, AuctionController.class); //new intent, passing in where you are going
		intent.putExtra("AuctionItem", model.getItem()); //the information you want to pass with the intent
		intent.putExtra("ViewName", "auction_controller"); // the view you want to now use
		this.startActivity(intent); //begins the new activity
	}

	public void searchItems(String txt){
		HashMap<Long, AuctionItem> collect = new HashMap<>();

		 //change to model  <<<<<<<<<<<<<<%$!^@$%#@@<<<<<<<@#!@%<<<<<<<<<<<<<<<<<
																		//model get items

		for (AuctionItem i : isc.search(txt)){
			collect.put((long) i.getItemID(),i);
		}

		model.setItems(collect);

	}
	
	

	@Override
	protected void onCreate(Bundle bundle){
		boolean b = true;
		Intent intent = getIntent();
		Bundle extra = this.getIntent().getExtras(); //Gets all the information into this bundle
		this.viewName = "activity_search";
		if(extra != null)
		{
			 b = (boolean) extra.get("firstTime");
			
		}
		try {
			startCheck(b);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		super.onCreate(bundle); 
		setContentView(); //sets the viewname and model, MVC Helper.

	}
	
	private void startCheck(boolean bool) throws NumberFormatException, Exception {
		if(bool){
			
			dbc.readThatLog();
		}
		
	}


	public SearchController(){
		super(new SearchModel(), "activity_search");
		
	}


	@Override
	public void switchPage(String s) {
		// TODO Auto-generated method stub

	}


	@Override
	public void startItemAddPage() {
		Intent intent = new Intent(this, ItemAddController.class);
		intent.putExtra("Items", model.getItems());
		this.startActivity(intent);
	}

}
