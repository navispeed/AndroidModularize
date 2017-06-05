package com.navispeed.greg.androidmodularize.helpers;

import com.navispeed.greg.common.Module;
import com.navispeed.greg.goodbye.GoodbyeModule;
import com.navispeed.greg.welcome.WelcomeModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 04/06/2017.
 */
public class ModuleRegister {

    private static ModuleRegister ourInstance = new ModuleRegister();

    public static ModuleRegister getInstance() {
        return ourInstance;
    }

    private List<Module> moduleList = new ArrayList<>();

    private ModuleRegister() {
        this.moduleList.add(WelcomeModule.getInstance());
        this.moduleList.add(GoodbyeModule.getInstance());
    }

    public final List<Module> getModuleList() {
        return moduleList;
    }
}

