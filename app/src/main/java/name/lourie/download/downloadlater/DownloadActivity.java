package name.lourie.download.downloadlater;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static java.lang.System.currentTimeMillis;

//Sets ActionBarActivity as a public extension of DownloadActivity
public class DownloadActivity extends ActionBarActivity {

    /**
     * This is a declaration
     * It defines field imageView of type ImageView so DownloadTask can display the image
     * when it is done downloading. This field is used by addBitmap defined in this class.
     */

    private LinearLayout linearLayout;
    private boolean setAdjustViewBounds;

    public DownloadActivity() {
        long startTime = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        // Creates a button
        final Button button = (Button) findViewById(R.id.download_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DownloadTask(DownloadActivity.this).execute("http://www.serebii.net/pokedex-bw/evo/143.png");
            }
        });
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout1);
    }

    /**
     * This method displays the bitmap that gets passed in.
     *
     * @param bitmap This is the bitmap that will be displayed.
     */


    //addBitmap is a new method that replaces setBitmap
    public void addBitmap(Bitmap bitmap) {
        ImageView imageView = new ImageView(getApplicationContext());
        setAdjustViewBounds = true;
        linearLayout.addView(imageView);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return true;
    }

    {
        long startTime = 0;
        elapsedTime = currentTimeMillis() - startTime;
    }

    long elapsedTime;
    long timeTillNextDisplayChange = 1000 - (elapsedTime % 1000);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
