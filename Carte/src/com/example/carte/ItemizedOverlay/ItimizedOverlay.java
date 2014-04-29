package com.example.carte.ItemizedOverlay;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import android.graphics.drawable.Drawable;


public class ItimizedOverlay extends ItemizedOverlay<OverlayItem> {

	
	private Context context;
	private ArrayList<OverlayItem>  arrayListOverlayItem = new ArrayList<OverlayItem>();
	
	/*permet de definir l'icone du marqueur*/
	 public ItimizedOverlay(Drawable defaultMarker, Context pContext)
	 {
		 /*centre l'image du marqueur*/
	  super(boundCenterBottom(defaultMarker));
	  this.context = pContext;
	 }

	 @Override
	 protected OverlayItem createItem(int i)
	 {
	  return arrayListOverlayItem.get(i);
	 }

	 @Override
	 public int size()
	 {
	  return arrayListOverlayItem.size();
	 }

	 public void addOverlayItem(OverlayItem overlay)
	 {
	  arrayListOverlayItem.add(overlay);
	  /*sert à rajouter les points sur la carte*/
	  populate();

	 }
	
	
	 @Override
	 protected boolean onTap(int index)
	 {
	  OverlayItem item = arrayListOverlayItem.get(index);
	 AlertDialog.Builder dialog = new AlertDialog.Builder(context);
	 dialog.setTitle(item.getTitle());
	 dialog.setMessage(item.getSnippet());
	 dialog.show();
	 return true;
	 }
	 
}
