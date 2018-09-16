package com.example.sjbyun.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    LinearLayout container;
    ImageView imageView;
    Button copyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.linear);
        copyButton = (Button) findViewById(R.id.copyButton);
        imageView = (ImageView) findViewById(R.id.bottomImage);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setDrawingCacheEnabled(true);

                Bitmap captureBitmap = container.getDrawingCache(); // 캡쳐

                // 캡쳐하기
                screenshot(captureBitmap);

                // 비트맵 불러오기
                try {
                    String imgpath = "data/data/com.example.sjbyun.myapplication/files/CaptureDir/test.png"; // 비트맵 저장 경로
                    Bitmap bm = BitmapFactory.decodeFile(imgpath);
                    imageView.setImageBitmap(bm);
                    Toast.makeText(getApplicationContext(), "load ok", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "load error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void screenshot(Bitmap captureBitmap) {
        FileOutputStream fos;
        File file = new File(getApplicationContext().getFilesDir(), "CaptureDir"); // 폴더 경로

        if (!file.exists()) {  // 해당 폴더 없으면 만들어라
            file.mkdirs();
        }

        String strFilePath = file + "/" + "test" + ".png";
        File fileCacheItem = new File(strFilePath);
        try {
            fos = new FileOutputStream(fileCacheItem);
            captureBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
