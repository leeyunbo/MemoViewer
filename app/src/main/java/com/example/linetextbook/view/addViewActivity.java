package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.linetextbook.adapter.ImageAdapter;
import com.example.linetextbook.adapter.MemoAdapter;
import com.example.linetextbook.contract.AddContract;
import com.example.linetextbook.Presenter.AddPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class addViewActivity extends AppCompatActivity implements AddContract.view {
    private AddPresenter presenter; //presenter
    private List<String> imageList = new ArrayList<>();
    @BindView(R.id.add_content_edit)  EditText contentEditText;
    @BindView(R.id.add_title_edit)  EditText titleEditText;
    @BindView(R.id.add_imageList) RecyclerView addRecyclerView;

    @Override
    public void backListView() {
        Intent intent = new Intent(this, ListViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void addMemo() {
        SimpleDateFormat format2 = new SimpleDateFormat( "yyyy년 MM월dd일 HH시mm분ss초");
        Date date = new Date();
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        String time = format2.format(date);
        MemoEntity memo;
        if(imageList == null) memo = new MemoEntity(title,content,time,null);
        else memo = new MemoEntity(title,content,time,imageList.toArray(new String[0]));
        presenter.requestAddMemo(memo);
    }

    @Override
    public void addImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1) {
            if(data == null) return;
            String path = data.getData().toString();
            System.out.println(path);
            imageList.add(path);
            ImageAdapter adapter = new ImageAdapter(imageList);
            addRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            addRecyclerView.setAdapter(adapter);
            addRecyclerView.setItemAnimator(new DefaultItemAnimator());
            addRecyclerView.setNestedScrollingEnabled(false);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        ButterKnife.bind(this);
        this.presenter = new AddPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_add_btn:
                addMemo();
                return true;
            case R.id.menu_image_add_btn:
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                addImage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
