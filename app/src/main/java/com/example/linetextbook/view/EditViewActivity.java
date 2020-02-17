package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.linetextbook.contract.EditContract;
import com.example.linetextbook.Presenter.EditPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditViewActivity extends AppCompatActivity implements EditContract.view
{
    private MemoEntity memo;
    private EditPresenter presenter;
    @BindView(R.id.edit_title_view) EditText edit_title_view;
    @BindView(R.id.edit_content_view) EditText edit_content_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
        this.presenter = new EditPresenter(this);
        ButterKnife.bind(this);
        getEditMemoInform();
    }

    @Override
    public void backListView() {
        Intent intent = new Intent(this,ListViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void getEditMemoInform() {
        Intent intent = getIntent();
        memo = (MemoEntity) intent.getSerializableExtra("MEMO");
        edit_title_view.setText(memo.getTitle());
        edit_content_view.setText(memo.getContent());
    }

    @Override
    public void editMemo() {
        presenter.requestEditMemo(memo);
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
                memo.setTitle(edit_title_view.getText().toString());
                memo.setContent(edit_content_view.getText().toString());
                editMemo();
                return true;
            case R.id.edit_cancel_btn:
                backListView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
