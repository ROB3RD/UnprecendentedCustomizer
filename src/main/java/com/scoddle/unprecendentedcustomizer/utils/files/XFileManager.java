package com.scoddle.unprecendentedcustomizer.utils.files;

import com.scoddle.unprecendentedcustomizer.utils.files.eventfiles.PlayerJoinEventFile;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;

import java.util.ArrayList;

public class XFileManager implements IReference {

    private ArrayList<XFile> files = new ArrayList<>();

    public XFileManager() {
        init();
    }

    private void init() {
        addFile(new PlayerJoinEventFile());
    }

    private void addFile(XFile file) {
        files.add(file);
    }

    public ArrayList<XFile> getFiles() {
        return files;
    }

    public XFile getFileByClass(Class<? extends XFile> clazz) {
        for(XFile x : getFiles()) {
            if(x.getClass() != clazz) {
                continue;
            }
            return x;
        }

        return null;
    }
}
