package xds.courses.a0207.app04;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;

/**
 * Activity with image.
 * @author Mikhail.Malakhov
 */
public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        // Enter and Exit transition
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());
    }

}
