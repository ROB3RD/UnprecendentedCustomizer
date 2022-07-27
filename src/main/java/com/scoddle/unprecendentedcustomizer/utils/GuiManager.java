package com.scoddle.unprecendentedcustomizer.utils;

import com.scoddle.unprecendentedcustomizer.gui.player.PlayerCustomizerGui;
import com.scoddle.unprecendentedcustomizer.gui.events.PlayerJoinGui;
import com.scoddle.unprecendentedcustomizer.gui.player.PlayerGamemodeGui;
import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;

import java.util.ArrayList;

public class GuiManager implements IReference {

    public ArrayList<IGUI> guis = new ArrayList<>();

    public GuiManager() {
        init();
    }

    public void init() {
        addGui(new PlayerCustomizerGui());
        addGui(new PlayerJoinGui());
        addGui(new PlayerGamemodeGui());
    }

    private void addGui(IGUI gui) {
        guis.add(gui);
    }

    public ArrayList<IGUI> getGuis() {
        return guis;
    }
}
