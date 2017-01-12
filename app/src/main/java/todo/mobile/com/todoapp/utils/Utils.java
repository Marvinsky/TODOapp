package todo.mobile.com.todoapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by mabisrror on 1/12/17.
 */

public class Utils {

    Context myContext;

    public Utils(Context context) {
        this.myContext = context;
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                myContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
