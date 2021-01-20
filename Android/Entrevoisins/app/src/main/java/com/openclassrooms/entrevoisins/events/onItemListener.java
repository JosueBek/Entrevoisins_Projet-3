package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public interface onItemListener {

     /**
      * that manages the list display
      */
      void onClickItem(Neighbour neighbour);
}

