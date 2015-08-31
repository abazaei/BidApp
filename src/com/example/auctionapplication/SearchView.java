package com.example.auctionapplication;


import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;

import com.example.auctionapplication.R;
import com.example.auctionapplicationIntermed.CrudModel;
import com.example.auctionapplicationIntermed.CrudModel.Command;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import edu.neumont.csc180.mvc.Model;
import edu.neumont.csc180.mvc.View;

public class SearchView extends LinearLayout implements View<SearchModel>, Model.Listener<SearchModel> {

	private Listener listener;

	private ListView listView;

	private EditText searchQuery;
	private Double searchedItemBid;

	Button addItemButton;
	Button searchButton;

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}



	@Override
	public void update(SearchModel data) {
		if(listView!=null){
			listView.setAdapter(new AuctionAdapter(this.getContext(), data.getItems().values()));

		}

	}


	public void setListener(View.Listener listener) {
		this.listener = (Listener) listener;

	}

	public interface Listener extends View.Listener{
		void searchDisplay(String name);
		void productDisplayBid(double i);
		void itemClicked(AuctionModel model);
		void searchItems(String txt);
		void switchPage(String s);
		void startItemAddPage();
		void sendCrud(Command add, String string);
	}



	protected void 	onFinishInflate(){

		super.onFinishInflate();

		listView = (ListView)findViewById(R.id.listofitems);
//		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


		searchQuery = (EditText)findViewById(R.id.searchspace);
		searchButton = (Button)findViewById(R.id.searchbutton);
		addItemButton = (Button)findViewById(R.id.openAddItemPage);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, android.view.View view
					,int position,long id) {
				listener.itemClicked((AuctionModel)parent.getAdapter().getItem(position));

			}
		});	
		
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(android.view.View v) {
//				Log.w("ERRORONCLICK!!!!", searchQuery.getText().toString());
				
				listener.searchItems(searchQuery.getText().toString());
				listener.sendCrud(CrudModel.Command.READ, searchQuery.getText().toString());
				

			}
		});
		addItemButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(android.view.View v) {
				
				listener.startItemAddPage();
				
			}
		});
		

		


	}
}

