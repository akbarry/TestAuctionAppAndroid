package com.hubspot.auctionapp.models;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class Item {

  public String key;
  public String name;
  public Integer openbid;
  public String donoremail;
  public String imageurl;
  public Integer qty;
  public String donorname;
  public String description;
  public Integer islive;
  public Map<String, Boolean> bids = new HashMap<>();

  public DatabaseReference itemRef;

  public Item() {
    // Default constructor required for calls to DataSnapshot.getValue(Item.class)
  }

  public String getName() {
    return name;
  }

  public String getImageurl() {
    return this.imageurl == null || this.imageurl.isEmpty() ? "http://cdn2.hubspot.net/hubfs/53/sprocket_web-2.png" : this.imageurl;
  }

  public Integer getQty() {
    return qty;
  }

  public String getDonorname() {
    return donorname;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getIslive() {
    return islive == 1;
  }

  public Integer getNumBids() {
    return bids.size();
  }

  public Boolean getIsBiddingOpen() {

    // Date BIDDING_OPENS = new Date(1481648400000L); // "2016/12/13 12:00"
    Date BIDDING_OPENS = new Date(1481475600000L); // "2016/12/11 12:00"
    Date BIDDING_CLOSES = new Date(1481760000000L); // "2016/12/14 19:00"
    Date LIVE_BIDDING_OPENS = new Date(1481752800000L); //"2016/12/14 17:00"

    Date now = new Date();
    if (now.after(BIDDING_CLOSES)) {
      return false;
    } else if (this.getIslive()) {
      return false;
    } else {
      if (now.before(BIDDING_OPENS)) {
        return false;
      }
    } return true;
  }
}
