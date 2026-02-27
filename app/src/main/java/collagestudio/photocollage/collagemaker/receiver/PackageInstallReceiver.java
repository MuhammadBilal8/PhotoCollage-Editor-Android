package collagestudio.photocollage.collagemaker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import collagestudio.photocollage.collagemaker.utils.ALog;
import collagestudio.photocollage.collagemaker.utils.BigDAdsHelper;
import java.util.HashMap;


public class PackageInstallReceiver extends BroadcastReceiver {
    public static String clickedApp = null;
    public static HashMap<String, Boolean> reportedMap = new HashMap<>();

    @Override
    public void onReceive(final Context context, final Intent intent) {
        
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            ALog.d(" PackageInstallReceiver ", "onReceive called "
                    + " PACKAGE_REMOVED, packageName = " + intent.getData());
        } else if (intent.getAction().equals(
                "android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            if (packageName != null && packageName.length() > 0 && packageName.startsWith("package")) {
                packageName = packageName.substring("package:".length());
            }

            ALog.d(" PackageInstallReceiver ", "onReceive called " + "PACKAGE_ADDED, installedPackageName=" + packageName);
            if (packageName != null && packageName.length() > 0
                    && clickedApp != null && reportedMap.get(clickedApp) != Boolean.TRUE
                    && clickedApp.contains(packageName)) {
                BigDAdsHelper.addInstalledApp(packageName);
                reportedMap.put(clickedApp, Boolean.TRUE);
            }
        }
    }
}
