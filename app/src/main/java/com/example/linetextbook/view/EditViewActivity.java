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
import android.widget.TextView;
import android.widget.Toast;

import com.example.linetextbook.CameraFunction;
import com.example.linetextbook.adapter.ImageAdapter;
import com.example.linetextbook.adapter.ImageEditAdapter;
import com.example.linetextbook.contract.EditContract;
import com.example.linetextbook.Presenter.EditPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.converters.ArrayConverters;
import com.example.linetextbook.database.MemoEntity;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 메모를 수정할 수 있도록 UI를 제공하는 액티비티
 * 사용자는 제목, 내용, 이미지를 변경할 수 있다.
 *
 * @author 이윤복
 * @version 1.0
 */

public class EditViewActivity extends AppCompatActivity implements EditContract.view
{
    private MemoEntity memo;
    private List<String> imageList;
    private EditPresenter presenter;
    static final int REQUEST_IMAGE_ALBUM = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    @BindView(R.id.edit_title_edit) EditText edit_title;
    @BindView(R.id.edit_content_edit) EditText edit_content;
    @BindView(R.id.edit_imageList) RecyclerView editRecyclerView;
    @BindView(R.id.edit_album_image_btn) ImageView edit_album_image_btn;
    @BindView(R.id.edit_camera_image_btn) ImageView edit_camera_image_btn;
    @BindView(R.id.edit_url_image_btn) ImageView edit_url_image_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
        this.presenter = new EditPresenter(this);
        ButterKnife.bind(this);
        getEditMemoInform();
    }

    /**
     * 사용자가 수정할 메모의 정보를 출력해주는 메서드
     * Intent를 통해 직렬화되어 넘어온 객체를 역직렬화를 통해 객체 형태로 변환 후 View에 뿌려준다.
     */
    @Override
    public void getEditMemoInform() {
        Intent intent = getIntent();
        memo = (MemoEntity) intent.getSerializableExtra("MEMO");
        edit_title.setText(memo.getTitle());
        edit_content.setText(memo.getContent());
        this.imageList = ArrayConverters.convertArrayToList(memo.getImageList());
        ImageEditAdapter adapter = new ImageEditAdapter(imageList,this);
        editRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        editRecyclerView.setAdapter(adapter);
        editRecyclerView.setItemAnimator(new DefaultItemAnimator());
        editRecyclerView.setNestedScrollingEnabled(false);
    }

    @OnClick(R.id.edit_album_image_btn)
    @Override
    public void addAlbumImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,REQUEST_IMAGE_ALBUM);
    }

    @OnClick(R.id.edit_camera_image_btn)
    @Override
    public void addCameraImage() {
        CameraFunction cameraFunction = new CameraFunction(this);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFIle = null;
            photoFIle = cameraFunction.createImageFile();
            if(photoFIle != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider",photoFIle);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                cameraFunction.galleryAddPic(photoURI.toString());
                changeImageRecyclerView(photoURI.toString());

            }
        }
    }

    @OnClick(R.id.edit_url_image_btn)
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
                if(!Url.contains("http://") || !Url.contains("https://")) {
                    Toast.makeText(getApplicationContext(), "올바르지 않은 URL 입니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                changeImageRecyclerView(Url);
            }
        });

        ad.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_IMAGE_ALBUM) {
            if(data == null) return;
            String path = data.getData().toString();
            changeImageRecyclerView(path);
        }
    }

    /**
     * 사용자가 이미지를 추가했을 경우, 추가된 이미지를 보여주고 있는 RecyclerView를 최신화 시킨다.
     *
     * @param path 추가하고 싶은 이미지의 경로
     */
    @Override
    public void changeImageRecyclerView(String path) {
        imageList.add(path);
        ImageEditAdapter adapter = new ImageEditAdapter(imageList,this);
        editRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        editRecyclerView.setAdapter(adapter);
        editRecyclerView.setItemAnimator(new DefaultItemAnimator());
        editRecyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * EditPresenter에게 한 요청이 완료되었을 때, EditPresenter에게 호출되어지는 콜백메서드
     */
    @Override
    public void backListView() {
        Intent intent = new Intent(this, com.example.linetextbook.view.ListViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * 사용자가 앱 바의 저장 버튼을 클릭했을 경우 호출되는 콜백메서드
     * editPresenter에게 메모 수정 요청을 전송한다.
     */
    @Override
    public void editMemo() {
        presenter.requestEditMemo(memo);
    }

    /**
     * RecyclerView에 대해 삭제 이벤트가 발생했을 경우, 실제 메모를 최신화 시키기 위해 호출되는 콜백 메서드
     *
     * @param imageList 현재 메모가 가지고 있는 이미지의 경로를 담고 있는 리스트
     */
    @Override
    public void notifyDeleteImage(List<String> imageList) {
        this.imageList = imageList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        Intent intent;
        switch (item.getItemId()) {
            case R.id.edit_finish_btn:
                memo.setTitle(edit_title.getText().toString());
                memo.setContent(edit_content.getText().toString());
                memo.setImageList(imageList.toArray(new String[0]));
                editMemo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
