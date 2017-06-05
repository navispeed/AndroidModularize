# Android Modularize
## Just a basic android app with sub project

### How to : Add a module

1. Create a new Android Library ( File -> New -> New Module -> Android Library  )
2. In the new library build.gradle, in the dependencies block, add this :
```groovy
        compile project(":common")  //Add common library as a dependency == Use Module Interface
```

3. In the root project build.gradle, add your new library as a dependency as follow :
```groovy
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile project(":common")
    compile project(":welcome")
    compile project(":goodbye")
    compile project(":myNewProject") //Add here your library
    compile 'com.android.support:appcompat-v7:24.2.1'
    testCompile 'junit:junit:4.12'
}
```

4. After that, you may declare your library main activity, so create a singleton class as follow :
```java
    package com.navispeed.greg.mymodule;

    import android.app.Activity;

    import com.navispeed.greg.common.Module;

    /**
     * Created by greg on 04/06/2017.
     */

    public class MyModule implements Module {
        private static WelcomeModule ourInstance = new WelcomeModule();

        public static WelcomeModule getInstance() {
            return ourInstance;
        }

        private WelcomeModule() {}

        @Override
        public Class<? extends Activity> getMainActivity() {
            return MyActivity.class;                //Add here your main activity of your module
        }

        @Override
        public String getName() {
            return "Welcome";   //Just the name of your module
        }
    }

```

5. Finally, add, in ModuleRegister.java, your module :

```java
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
        this.moduleList.add(MyModule.getInstance());
    }

    public final List<Module> getModuleList() {
        return moduleList;
    }
}
```

* Congratulations, you just added a new module in this app *