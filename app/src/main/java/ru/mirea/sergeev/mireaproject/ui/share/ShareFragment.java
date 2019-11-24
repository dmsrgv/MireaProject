package ru.mirea.sergeev.mireaproject.ui.share;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import ru.mirea.sergeev.mireaproject.MainActivity;
import ru.mirea.sergeev.mireaproject.R;

import static android.app.Activity.RESULT_OK;

public class ShareFragment extends Fragment implements View.OnClickListener{
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 100;
    final String TAG = MainActivity.class.getSimpleName();
    private ImageView imageView;
    private static final int CAMERA_REQUEST = 0;
    File file;
    FileOutputStream fileoutputstream;
    ByteArrayOutputStream bytearrayoutputstream;
    private boolean isWork = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        imageView = view.findViewById(R.id.imageView2);
        bytearrayoutputstream = new ByteArrayOutputStream();
        int permissionStatus = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_CAMERA);
        }
        imageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
// извлекаем изображение
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            bitmap.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);
            imageView.setImageBitmap(bitmap);
            try

            {
                fileoutputstream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/SampleImage.png");
                fileoutputstream.write(bytearrayoutputstream.toByteArray());
                fileoutputstream.close();
                Log.d(TAG, file.getAbsolutePath());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Toast.makeText(getContext(), "SAVE IMAGE.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {
            Log.d(TAG, String.valueOf(grantResults[0]) + " " + String.valueOf(grantResults[1]));
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
// permission granted
                isWork = true;
            } else {
                isWork = false;
            }
        }

    }

    @Override
    public void onClick(View v) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }
}