package com.example.linetextbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Camera를 활용하는 기능에 대한 모든 메서드를 가지고 있는 클래스
 *
 * @author 이윤복
 * @version 1.0
 */

public class CameraFunction {
    private Context context;
    public CameraFunction(Context context) {
        this.context = context;
    }

    /**
     * 이미지 파일 이름과 이미지 저장 경로를 지정한다.
     * @return 지정되어진 File 객체를 리턴한다.
     */
    public File createImageFile() {
        File mImage = null;
        String mTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String mImageFileName = "JPEG_" + mTimeStamp + "_";
        File mStorageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {
            mImage = File.createTempFile(
                    mImageFileName,
                    ".jpg",
                    mStorageDir
            ); }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return mImage;
    }

    /**
     * 수동으로 해당 경로에 대한 파일을 스캔하고 미디어 라이브러리에 파일을 추가하는 메서드
     * @param path 추가할 파일에 대한 경로
     */
    public File galleryAddPic(String path) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);

        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
        return f;
    }
}
