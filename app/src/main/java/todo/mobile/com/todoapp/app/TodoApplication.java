package todo.mobile.com.todoapp.app;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by mabisrror on 1/12/17.
 */

public class TodoApplication extends Application{

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        this.context = this;
    }

    public Context getContext() {
        return context;
    }
}
