package edu.niu.cs.z1760203.implicitintent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {


    private Button browserBtn;
    private Button phoneBtn;
    private Button pictureBtn;

    private ImageView pictureTV;

    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        browserBtn = (Button) findViewById(R.id.browserButton);
        phoneBtn = (Button) findViewById(R.id.dialButton);
        pictureBtn = (Button) findViewById(R.id.cameraButton);
        pictureTV = (ImageView) findViewById(R.id.pictureImage);

    }

    public void doBrowser(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cs.niu.edu"));
        startActivity(browserIntent);
    }

    public void doPhone(View view) {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+4014401495"));
        startActivity(phoneIntent);
    }

    public void doCam(View view) {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pictureIntent, REQUEST_CODE);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap image = (Bitmap) bundle.get("data");
            pictureTV.setImageBitmap(image);
        }


    }
}





