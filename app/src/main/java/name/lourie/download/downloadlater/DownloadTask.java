package name.lourie.download.downloadlater;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by kevin on 6/7/15.
 */
public class DownloadTask extends AsyncTask<String, Void, Object> {
    private final DownloadActivity mDownloadActivity;

    /**
     * Define field bitmap of type Bitmap
     */
    private Bitmap mBitmap = null;

//Makes DownloadTask a public method
    public DownloadTask(DownloadActivity downloadActivity) {
        mDownloadActivity = downloadActivity;
    }

    long startTime = System.currentTimeMillis();

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param o The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //mDownloadActivity is a field
        //A field is a member of a class
        mDownloadActivity.addBitmap(mBitmap);
    }



    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */

    //Catches exceptions
    @Override
    protected Object doInBackground(String... params) {
        InputStream input = null;
        try {
            URL url = new URL(params[0]);
            input = new BufferedInputStream(url.openStream(), 8192);

            // decodeStream makes the compressed input understandable to the computer. Compressed
            // input gets translated to pixels, which is what the bitmap is comprised of. Input is
            // compressed because it makes it download faster. Each pixel has three values, one for
            // each color: red, blue, and green.
            //If the type is there, it is a declaration. Otherwise, it is not.
            mBitmap = BitmapFactory.decodeStream(input);
            input.close();
        } catch (Exception e) {
            Log.e("Problem during download", e.getMessage());
        }
        return null;
    }
}