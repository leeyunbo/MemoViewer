package com.example.linetextbook.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.linetextbook.CameraFunction;
import com.example.linetextbook.adapter.ImageAdapter;
import com.example.linetextbook.contract.AddContract;
import com.example.linetextbook.Presenter.AddPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * 사용자가 추가할 메모를 작성할 수 있도록 UI를 제공하는 액티비티
 * 사용자가 입력을 다한 후, 추가 버튼에 대한 이벤트가 발생했을 경우, addPresenter에게 DB 추가 요청을 한다.
 * ButterKnife 라이브러리 사용 https://github.com/JakeWharton/butterknife
 * RecyclerView 라이브러리 사용
 *
 * @author 이윤복
 * @version 1.0
 */

public class AddViewActivity extends AppCompatActivity implements AddContract.view {
    private AddPresenter mPresenter; //mPresenter
    private List<String> mImageList = new ArrayList<>();
    private Uri mPhotoURI;
    static final int REQUEST_IMAGE_ALBUM = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    @BindView(R.id.add_content_edit)  EditText contentEditText;
    @BindView(R.id.add_title_edit)  EditText titleEditText;
    @BindView(R.id.add_imageList) RecyclerView addRecyclerView;
    @BindView(R.id.add_album_image_btn) ImageView add_album_image_btn;
    @BindView(R.id.add_camera_image_btn) ImageView add_camera_image_btn;
    @BindView(R.id.add_url_image_btn) ImageView add_url_image_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        ButterKnife.bind(this);
        this.mPresenter = new AddPresenter(this);
    }

    /**
     * presenter에게 메모 추가를 요청하는 메서드
     */
    @Override
    public void addMemo() {
        SimpleDateFormat format2 = new SimpleDateFormat( "yyyy년 MM월dd일 HH시mm분ss초");
        Date date = new Date();
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        String time = format2.format(date);
        MemoEntity memo;
        if(mImageList == null) memo = new MemoEntity(title,content,time,null);
        else memo = new MemoEntity(title,content,time, mImageList.toArray(new String[0]));
        mPresenter.requestAddMemo(memo);
    }

    /**
     * addPresenter에게 한 요청이 완료되었을 때, addPresenter에게 호출되어지는 콜백메서드
     */
    @Override
    public void backListView() {
        Intent intent = new Intent(this, com.example.linetextbook.view.ListViewActivity.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * 사용자가 add_album_image_btn을 클릭한 경우 호출되는 이벤트 콜백메서드
     * 앨범에서 이미지를 받아온다.
     */
    @OnClick(R.id.add_album_image_btn)
    @Override
    public void addAlbumImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,REQUEST_IMAGE_ALBUM);
    }

    /**
     * 사용자가 add_camera_image_btn을 클릭한 경우 호출되는 이벤트 콜백메서드
     * 카메라 촬영을 통해 이미지를 받아온다.
     */
    @OnClick(R.id.add_camera_image_btn)
    @Override
    public void addCameraImage() {
        CameraFunction cameraFunction = new CameraFunction(this);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFIle = null;
            photoFIle = cameraFunction.createImageFile();
            if (photoFIle != null) {
                mPhotoURI =
                        FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFIle);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                cameraFunction.galleryAddPic(mPhotoURI.toString());
            }
        }
    }

    /**
     * 사용자가 add_url_image_btn을 클릭한 경우 호출되는 이벤트 콜백메서드
     * 사용자가 입력한 URL을 통해 이미지를 받아온다.
     */
    @OnClick(R.id.add_url_image_btn)
    @Override
    public void addUrlImage() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("URL 입력");
        ad.setMessage("http://www.url.com 형식으로 입력해주세요.");

        final EditText et = new EditText(this);
        ad.setView(et);

        ad.setPositiveButton("입력", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Url = et.getText().toString();
                if(!Url.contains("http://") && !Url.contains("https://")) {
                    Toast.makeText(getApplicationContext(), "올바르지 않은 URL 입니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                changeImageRecyclerView(Url);
            }
        });

        ad.show();
    }

    /**
     * 사용자가 이미지를 추가했을 경우, 추가된 이미지를 보여주고 있는 RecyclerView를 최신화 시킨다.
     *
     * @param path 추가하고 싶은 이미지의 경로
     */
    @Override
    public void changeImageRecyclerView(String path) {
        mImageList.add(path);
        ImageAdapter adapter = new ImageAdapter(mImageList,this);
        addRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        addRecyclerView.setAdapter(adapter);
        addRecyclerView.setItemAnimator(new DefaultItemAnimator());
        addRecyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * RecyclerView에 대해 삭제 이벤트가 발생했을 경우, 실제 메모를 최신화 시키기 위해 호출되는 콜백 메서드
     *
     * @param imageList 현재 메모가 가지고 있는 이미지의 경로를 담고 있는 리스트
     */
    @Override
    public void notifyDeleteImage(List<String> imageList) {
        this.mImageList = imageList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_btn:
                addMemo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_IMAGE_ALBUM) {
            if(data == null) return;
            String path = data.getData().toString();
            changeImageRecyclerView(path);
        }
        else if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if(mPhotoURI == null) return;
            changeImageRecyclerView(mPhotoURI.toString());
            mPhotoURI = null;
        }
    }


}
