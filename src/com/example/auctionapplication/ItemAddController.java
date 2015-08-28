package com.example.auctionapplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.example.auctionapplicationIntermed.AuctionItem;

import edu.neumont.csc180.mvc.Controller;
import ItemService.ItemServiceClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ItemAddController extends Controller<SearchModel> implements ItemAddView.Listener{

	//ArrayList<AuctionModel> allitems = new ArrayList<AuctionModel>();
	ItemServiceClient isc = new ItemServiceClient();
	int counter = 0;
	
	
	public ItemAddController(){	
		
		super(new SearchModel(), "activity_item_add"); //Model, and Name of View
	}
	
	
	@Override
	public void addItem(AuctionItem newitem) throws IOException {
		
		
		model.addItemToList(newitem); //New Model
		
		isc.addItem(newitem);
		Intent newintent = new Intent(this, SearchController.class);
		newintent.putExtra("Items", model.getItems());
		newintent.putExtra("firstTime", false);

		this.startActivity(newintent);
		
	}

	@Override
	protected void onCreate(Bundle bundle){
		Bundle extra = this.getIntent().getExtras(); //Gets all the information into this bundle
		this.viewName = "activity_item_add";
		if(extra != null)
		{
			this.model = new SearchModel((HashMap<Long, AuctionItem>) extra.get("Items")); //gets the item
		}
	

		super.onCreate(bundle); 
		setContentView(); //sets the viewname and model, MVC Helper.
		
	}

	@Override
	public int idGenerator() {
		
		
		return model.getItems().size()+1;
		
	}

}
