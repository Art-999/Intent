package com.example.arturmusayelyan.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

//https://developer.android.com/reference/android/content/Intent.html
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Intent-ov katarvox gorcoxutyunner

    private File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_map).setOnClickListener(this);
        findViewById(R.id.button_market).setOnClickListener(this);
        findViewById(R.id.button_email).setOnClickListener(this);
        findViewById(R.id.button_email).setOnClickListener(this);
        findViewById(R.id.button_send_image).setOnClickListener(this);
        findViewById(R.id.button_do_picture).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = null, chooser = null;

        switch (v.getId()) {
            case R.id.button_map:
                // intent.setData(Uri.parse("https://www.youtube.com/watch?v=iGbMNfv2KxA&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=27"));
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:19.076,72.877"));
                chooser = Intent.createChooser(intent, "Launch Maps");
                startActivity(chooser);
                break;
            case R.id.button_market:
                //intent.setData(Uri.parse("https://www.youtube.com/watch?v=iGbMNfv2KxA&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=27"));
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.vivarobet.am/"));
                chooser = Intent.createChooser(intent, "Web View");
                startActivity(chooser);
                break;
            case R.id.button_email:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"slidenerd@gmail.com", "musayelyan099@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_EMAIL, "hi, this was sent from my app");
                intent.putExtra(Intent.EXTRA_TEXT, "hey whats up,how you doing?");
                intent.setType("message/rfc822");
                chooser = Intent.createChooser(intent, "Launch Email");
                startActivity(chooser);
                break;
            case R.id.button_send_image:
                Uri imageUri = Uri.parse("android.resource:intent/drawable/" + R.drawable.ic_launcher_background);
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                intent.putExtra(Intent.EXTRA_TEXT, "Hey I have attached");
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
                break;
            case R.id.button_do_picture:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "test.jpg");
                Uri tempuri = Uri.fromFile(imageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (imageFile.exists()) {
                        Toast.makeText(this, "The file was saved at " + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "There was an error saving the file ", Toast.LENGTH_LONG).show();
                    }
                    break;
                case Activity.RESULT_CANCELED:

                    break;
                default:
                    break;
            }
        }
    }
}
