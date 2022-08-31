package com.scoddle.unprecendentedcustomizer.utils;

import com.scoddle.unprecendentedcustomizer.gui.player.PlayerCustomizerGui;
import com.scoddle.unprecendentedcustomizer.gui.events.PlayerJoinGui;
import com.scoddle.unprecendentedcustomizer.gui.player.PlayerGamemodeGui;
import com.scoddle.unprecendentedcustomizer.gui.player.PlayerHealthGui;
import com.scoddle.unprecendentedcustomizer.gui.test.TestGui;
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
        addGui(new PlayerHealthGui());

        addGui(new TestGui());
    }

    private void addGui(IGUI gui) {
        guis.add(gui);
    }

    public ArrayList<IGUI> getGuis() {
        return guis;
    }
}
