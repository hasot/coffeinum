package com.example.torte.coffeimun2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by torte on 11.11.2017.
 */

public class ImageLoader {

    private final static long BufferSize = 1024 * 1024;

    private static StorageReference storage = FirebaseStorage.getInstance().getReference();

    public static void AddListener(String imageName, final OnSuccessListener<Bitmap> listener)
    {
        StorageReference imageRef = storage.child(imageName + ".png");
        imageRef.getBytes(BufferSize)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap icon = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        listener.onSuccess(icon);
                    }
                });

    }
}
