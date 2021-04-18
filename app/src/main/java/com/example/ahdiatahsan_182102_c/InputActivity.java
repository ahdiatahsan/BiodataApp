package com.example.ahdiatahsan_182102_c;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    //deklarasi variabel
    Button btnDaftar, btnView, btnEdit, btnHapus, btnCam, btnGalery;
    TextView Nim, Nama, Alamat, Telepon, Tempat, Tgl;
    RadioGroup radioJK;
    RadioButton pilihanJK, rb_LK, rb_PR;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    String JKterpilih;
    SQLiteDatabase db;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final int MY_PERMISSIONS_REQUEST_GALERI = 200;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    ImageView imagecamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        db=openOrCreateDatabase("AHDIATAHSAN_182102_C", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_biodata(nim VARCHAR PRIMARY KEY,nama VARCHAR,alamat VARCHAR,telp VARCHAR,jkel VARCHAR,tmpt VARCHAR,tgl VARCHAR,hobi VARCHAR, foto BLOB);");

        //memberikan nilai/object ke variabel
        btnDaftar = findViewById(R.id.btnDaftar);
        btnView = findViewById(R.id.btnView);
        btnEdit = findViewById(R.id.btnEdit);
        btnHapus = findViewById(R.id.btnHapus);
        Nim = findViewById(R.id.txtNIM);
        Nama = findViewById(R.id.txtNama);
        Alamat = findViewById(R.id.txtAlamat);
        radioJK = findViewById(R.id.radioJK);
        Telepon = findViewById(R.id.txtTelepon);
        Tempat = findViewById(R.id.txtTptLahir);
        Tgl = findViewById(R.id.txtTglLahir);

        cb1 = findViewById(R.id.cMembaca);
        cb2 = findViewById(R.id.cGames);
        cb3 = findViewById(R.id.cSepeda);
        cb4 = findViewById(R.id.cKumpul);
        cb5 = findViewById(R.id.cMendaki);
        cb6 = findViewById(R.id.cBola);

        rb_LK = findViewById(R.id.lakiLaki);
        rb_PR = findViewById(R.id.perempuan);

        imagecamera = findViewById(R.id.imgCam);
        btnCam = findViewById(R.id.btnCam);
        btnGalery = findViewById(R.id.btnGalery);

        btnDaftar.setEnabled(false);
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
        btnCam.setEnabled(false);
        btnGalery.setEnabled(false);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nim = Nim.getText().toString();
                String nama = Nama.getText().toString();
                String alamat = Alamat.getText().toString();
                String tlpn = Telepon.getText().toString();
                String Tptlhr = Tempat.getText().toString();
                String Tglhr = Tgl.getText().toString();
                pilihanJK = findViewById(radioJK.getCheckedRadioButtonId());
                String pilihan = pilihanJK.getText().toString();

                String Hobby = "";
                if (cb1.isChecked()) {
                    Hobby += cb1.getText().toString() + ":";
                }
                if (cb2.isChecked()) {
                    Hobby += cb2.getText().toString() + ":";
                }
                if (cb3.isChecked()) {
                    Hobby += cb3.getText().toString() + ":";
                }
                if (cb4.isChecked()) {
                    Hobby += cb4.getText().toString() + ":";
                }
                if (cb5.isChecked()) {
                    Hobby += cb5.getText().toString() + ":";
                }
                if (cb6.isChecked()) {
                    Hobby += cb6.getText().toString() + ":";
                }

                imagecamera.setDrawingCacheEnabled(true);
                imagecamera.buildDrawingCache();
                Bitmap bitmap = imagecamera.getDrawingCache();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] bytesImage = byteArrayOutputStream.toByteArray();

                ContentValues values = new ContentValues();
                values.put("nim",nim);
                values.put("nama",nama);
                values.put("alamat",alamat);
                values.put("telp",tlpn);
                values.put("jkel",pilihan);
                values.put("tmpt",Tptlhr);
                values.put("tgl",Tglhr);
                values.put("hobi",Hobby);
                values.put("foto",bytesImage);
                db.insert("tbl_biodata", null,values);

                Toast.makeText(getApplicationContext(), "Data " + Nim.getText() + " Berhasil Disimpan !", Toast.LENGTH_SHORT).show();
                clearText();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nim = Nim.getText().toString();
                String nama = Nama.getText().toString();
                String alamat = Alamat.getText().toString();
                String tlpn = Telepon.getText().toString();
                String Tptlhr = Tempat.getText().toString();
                String Tglhr = Tgl.getText().toString();
                pilihanJK = findViewById(radioJK.getCheckedRadioButtonId());
                String pilihan = pilihanJK.getText().toString();

                String Hobby = "";
                if (cb1.isChecked()) {
                    Hobby += cb1.getText().toString() + ":";
                }
                if (cb2.isChecked()) {
                    Hobby += cb2.getText().toString() + ":";
                }
                if (cb3.isChecked()) {
                    Hobby += cb3.getText().toString() + ":";
                }
                if (cb4.isChecked()) {
                    Hobby += cb4.getText().toString() + ":";
                }
                if (cb5.isChecked()) {
                    Hobby += cb5.getText().toString() + ":";
                }
                if (cb6.isChecked()) {
                    Hobby += cb6.getText().toString() + ":";
                }

                imagecamera.setDrawingCacheEnabled(true);
                imagecamera.buildDrawingCache();
                Bitmap bitmap = imagecamera.getDrawingCache();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] bytesImage = byteArrayOutputStream.toByteArray();

                ContentValues values = new ContentValues();
                values.put("nim",nim);
                values.put("nama",nama);
                values.put("alamat",alamat);
                values.put("telp",tlpn);
                values.put("jkel",pilihan);
                values.put("tmpt",Tptlhr);
                values.put("tgl",Tglhr);
                values.put("hobi",Hobby);
                values.put("foto",bytesImage);
                db.update("tbl_biodata", values, "nim="+nim,null);

                Toast.makeText(getApplicationContext(), "Data Mahasiswa " + Nim.getText() + " Berhasil Diperbarui!", Toast.LENGTH_SHORT).show();
                clearText();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM tbl_biodata where nim = '" + Nim.getText() + "' ");
                        Toast.makeText(getApplicationContext(), "Data Mahasiswa NIM " + Nim.getText() + " Berhasil Dihapus!", Toast.LENGTH_SHORT).show();
                clearText();
            }
        });

        btnCam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //intent khusus untuk menangkap foto lewat kamera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, MY_PERMISSIONS_REQUEST_CAMERA);
            }
        });

        btnGalery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //intent khusus untuk menangkap dari galery
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, MY_PERMISSIONS_REQUEST_GALERI);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCam.setEnabled(true);
                btnGalery.setEnabled(true);
                byte[] bytesImage = null;
                Cursor c = db.rawQuery("SELECT * FROM tbl_biodata WHERE nim='" +
                        Nim.getText() + "'", null);
                if (c.moveToFirst()) {
                    Nim.setText(c.getString(0));
                    Nama.setText(c.getString(1));
                    Alamat.setText(c.getString(2));
                    Telepon.setText(c.getString(3));
                    switch (c.getString(4)) {
                        case "Laki-Laki":
                            rb_LK.setChecked(true);
                            JKterpilih = "Laki-Laki";
                            break;
                        case "Perempuan":
                            rb_PR.setChecked(true);
                            JKterpilih = "Perempuan";
                            break;
                    }
                    Tempat.setText(c.getString(5));
                    Tgl.setText(c.getString(6));
                    String[] hb = c.getString(7).split(":");
                    int i = 0;
                    while (i < hb.length) {
                        if (hb[i].equals(cb1.getText().toString())) {
                            cb1.setChecked(true);
                        }
                        if (hb[i].equals(cb2.getText().toString())) {
                            cb2.setChecked(true);
                        }
                        if (hb[i].equals(cb3.getText().toString())) {
                            cb3.setChecked(true);
                        }
                        if (hb[i].equals(cb4.getText().toString())) {
                            cb4.setChecked(true);
                        }
                        if (hb[i].equals(cb5.getText().toString())) {
                            cb5.setChecked(true);
                        }
                        if (hb[i].equals(cb6.getText().toString())) {
                            cb6.setChecked(true);
                        } i++;
                    }

                    bytesImage = c.getBlob(c.getColumnIndex("foto"));;
                    imagecamera.setImageBitmap(BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length));
                    btnDaftar.setEnabled(false);
                    btnEdit.setEnabled(true);
                    btnHapus.setEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(), "NIM " + Nim.getText() + " " +
                            "tidak ditemukan, silahkan input data baru !", Toast.LENGTH_SHORT).show();
                    btnDaftar.setEnabled(true);
                    btnEdit.setEnabled(false);
                    btnHapus.setEnabled(false);
                }
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
                showSettingsAlert();
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this, new
                            String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case(MY_PERMISSIONS_REQUEST_CAMERA) :
                if(resultCode == Activity.RESULT_OK)
                {
                    // result code sama, save gambar ke bitmap
                    Bitmap bitmap;
                    bitmap = (Bitmap) data.getExtras().get("data");
                    imagecamera.setImageBitmap(bitmap);
                }
                break;
            case(MY_PERMISSIONS_REQUEST_GALERI) :
                if(resultCode == Activity.RESULT_OK)
                {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imagecamera.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public static void saveToPreferences(Context context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }
    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF, Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }
    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(InputActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(InputActivity.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                });
        alertDialog.show();
    }
    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(InputActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startInstalledAppDetailsActivity(InputActivity.this);
            }
        });
        alertDialog.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale =

                                ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            saveToPreferences(InputActivity.this,ALLOW_KEY, true);
                        }
                    }
                }
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    public static void startInstalledAppDetailsActivity(final InputActivity context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }

    void clearText(){
        Nama.setText("");
        Alamat.setText("");
        Telepon.setText("");
        Tempat.setText("");
        Tgl.setText("");
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);
        cb5.setChecked(false);
        cb6.setChecked(false);
        radioJK.clearCheck();
        imagecamera.setImageResource(R.drawable.ic_camera);
    }

}