package com.example.DeDeforest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.internal.Constants;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import DeDeforest.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
private Button openMapBtn, openOverlayBtn;
private TextView textView;
public String url="https://aff8-213-81-166-67.ngrok.io";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openMapBtn=findViewById(R.id.open_map_btn);
        openOverlayBtn=findViewById(R.id.open_overlay_btn);
        textView=findViewById(R.id.textView);
        OkHttpClient okHttpClient=new OkHttpClient();
        // Instantiate the RequestQueue.

        Request getRequest=new Request.Builder().url(url).build();
        openOverlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent overlayIntent=new Intent();
                overlayIntent.setClass(getApplicationContext(),GroundOverlay1.class);
                startActivity(overlayIntent);

            }
        });

        openMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //your codes here

            okHttpClient.newCall(getRequest).enqueue(new Callback()
             {
                 @Override
                 public void onFailure(@NotNull Call call, @NotNull IOException e)
                 {
                     Log.i("HTTP REQ", "sfsafd");                                                                 }

                 @Override
                 public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException
                 {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ImageView imageView=findViewById(R.id.imageView);
                        ResponseBody in=response.body();
                        InputStream inputStream=in.byteStream();
                        Log.i("inputStream", "value= "+inputStream);
                        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
                        Bitmap bitmap= BitmapFactory.decodeStream(bufferedInputStream);
                        imageView.setImageBitmap(bitmap);

                    }
                });
                     Log.i("HTTP REQ", response.body().string()+"");                                                                 }
             });
             /*   try {
                        Response response = okHttpClient.newCall(getRequest).execute();
                        // System.out.println(response.body().string());
                        Log.i("HTTP REQ", response+"");
                       // textView.setText(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i("HTTP REQ","SKKKKKKKKKKKKKKKKFKFKFK");

                    }*/

                }


               /* Intent intent=new Intent();
                intent.setClass(getApplicationContext(),MapsActivity.class);
                startActivity(intent);*/

            }
        });

    }


}