package com.mvvmpattern.utils;

/**
 * Created by en_dhiman on 05-October-16.
 * Image related utility methods
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;

public class ImageUtility {
    private Context context;

    public ImageUtility(Context context) {
        this.context = context;
    }

    public Uri CameraGalleryIntent(final int cameraRequestCode, final int galleryRequestCode) {
        final File root = getFile();
        root.mkdirs();
        String filename = getUniqueImageFilename();
        File sdImageMainDirectory = new File(root, filename);
        final Uri outputFileUri = Uri.fromFile(sdImageMainDirectory);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        CharSequence items[] = new CharSequence[]{"Camera", "Gallery"};
        dialog.setItems(items ,new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        ((Activity) context).startActivityForResult(intent, cameraRequestCode);
                        break;
                    case 1:
                        Intent pickIntent = new Intent(Intent.ACTION_PICK);
                        pickIntent.setType("image/* ");
                        ((Activity) context).startActivityForResult(pickIntent, galleryRequestCode);
                        break;
                    default:
                        break;
                }
                }

            });

        dialog.setTitle("Select type");
        if (outputFileUri != null) {
            dialog.show();
        }
        return outputFileUri;
    }

    /**
     * Compresses the image_background
     *
     * @param filePath : Location of image_background file
     * @return
     */
    @SuppressWarnings("deprecation")
    public String compressImage(String filePath) {
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;
        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;
            }
        }

        options.inSampleSize = ImageUtility.calculateInSampleSize(options, actualWidth, actualHeight);
        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(
                Paint.FILTER_BITMAP_FLAG));

        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            Log.e("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.e("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.e("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.e("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(),
                    matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 95, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bmp != null) {
                bmp.recycle();
                bmp = null;
            }
            if (scaledBitmap != null) {
                scaledBitmap.recycle();
            }
        }
        return filename;

    }

    public String getFilename() {

        final File root = getFile();
        root.mkdirs();
        final String filename = getUniqueImageFilename();
        File file = new File(root, filename);
        return file.getAbsolutePath();
    }

    private File getFile() {
        return new File(Environment.getExternalStorageDirectory() + File.separator + "TravellerApp"
                + File.separator);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    public static String getUniqueImageFilename() {
        return "img_" + System.currentTimeMillis() + ".png";
    }

    public String getRealPathFromURI(Uri contentUri) {
        // can post image_background
        String[] proj = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = ((Activity) context).managedQuery(contentUri, proj, // Which
                // columns
                // to
                // return
                null, // WHERE clause; which rows to return (all rows)
                null, // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public void LoadImage(String url, ImageView imageView) {
        LoadImage(url, imageView, false);
    }

    public void LoadImage(String url, ImageView imageView, boolean isRounded) {
        Picasso.with(context).load(url).into(imageView);
    }

    /**
     * Compress image_background and convert to multipart
     *
     * @param filePath path of the file to be converted
     * @return multipart image_background for the path supplied
     */
    public Observable<RequestBody> getCompressedFile(final String filePath, final ImageUtility imageUtility) {
        return Observable.create(new Observable.OnSubscribe<RequestBody>() {
            @Override
            public void call(Subscriber<? super RequestBody> subscriber) {
                try {
                    String newFilePath = getCompressedImage(filePath);
                    subscriber.onNext(imageUtility.getRequestBodyImage(new File(newFilePath)));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * get request body image_background
     */
    public RequestBody getRequestBodyImage(File file) {
        return RequestBody.create(MediaType.parse("image_background/png"), file);
    }

    /**
     * convert image_background to base 64 string
     *
     * @param filePath path of image_background
     * @return base 64 string
     */
    public String getBase64Image(String filePath) {
        filePath = getCompressedImage(filePath);
        Bitmap bm = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    /**
     * get compressed image_background
     */
    private String getCompressedImage(String filePath) {
        String newFilePath;
        int file_size = Integer.parseInt(String.valueOf(new File(filePath).length() >> 10));
        if (file_size >= 120) {
            newFilePath = compressImage(filePath);
        } else {
            newFilePath = filePath;
        }
        return filePath;
    }


    public String DownloadImageToGallery(String url1){

        String filepath = "";
        try
        {
            URL url = new URL(url1);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            File SDCardRoot = Environment.getExternalStorageDirectory().getAbsoluteFile();
            String filename="downloadedFile.png";
            Log.e("Local filename:",""+filename);
            File file = new File(SDCardRoot,filename);
            if(file.createNewFile())
            {
                file.createNewFile();
            }
            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();
            int totalSize = urlConnection.getContentLength();
            int downloadedSize = 0;
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ( (bufferLength = inputStream.read(buffer)) > 0 )
            {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                Log.e("Progress:","downloadedSize:"+downloadedSize+"totalSize:"+ totalSize) ;
            }
            fileOutput.close();
            if(downloadedSize==totalSize) filepath=file.getPath();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            filepath=null;
            e.printStackTrace();
        }
        Log.e("filepath:"," "+filepath) ;
        return filepath;
    }

//    public void loadImage(String url, ImageView imageView, @DrawableRes int placeholder) {
//        if (placeholder != 0) {
//            Glide.with(context).load(url).placeholder(placeholder).error(placeholder).into(imageView);
//        } else {
//            Glide.with(context).load(url).into(imageView);
//        }
//    }




}