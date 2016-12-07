package monti.com.firebasePetagram;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Susana on 25/11/2016.
 */

public class NotificationIdTokenService extends FirebaseInstanceIdService {
    private static String TAG = "FIREBASE TOKEN";
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);

    }
    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}
