package com.scoddle.unprecendentedcustomizer.utils;

import org.bukkit.permissions.Permission;

public class Perm {

    String rperm = "uc.";

    public Permission addPerm(String perm) {
        return new Permission(rperm + perm);
    }

    public String getRperm() {
        return rperm;
    }
}
