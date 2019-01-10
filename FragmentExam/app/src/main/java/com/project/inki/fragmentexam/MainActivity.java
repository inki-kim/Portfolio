package com.project.inki.fragmentexam;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback{
    MainFragment mainFragment;
    MenuFragment menuFragment;

    ListFragment listFragment;
    ImageFragment imageFragment;

    int[] images={R.drawable.image1,R.drawable.image2,R.drawable.image3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        menuFragment = new MenuFragment();

        listFragment=(ListFragment)getSupportFragmentManager().findFragmentById(R.id.imageTextFragment);
        imageFragment=(ImageFragment)getSupportFragmentManager().findFragmentById(R.id.imageViewFragment);
    }

    public void onFragmentChanged(int num) {
        if (num==0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.m_container,menuFragment).commit();

        } else if (num == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.m_container,mainFragment).commit();

        }
    }

    @Override
    public void onImageSelected(int position){              //이미지 변경을 위한 메소드 파라미터로 클릭한 position 값을 가지고 ImageFragment클래스의 setImage를 통해 이미지 변경
        imageFragment.setImage(images[position]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {         //액션바 생성 menu폴더에 menu_list.xml을 만들고 item을 추가한것을 파라메터로 값을 전달하여 inflate
        getMenuInflater().inflate(R.menu.menu_list,menu);

        View v=menu.findItem(R.id.menu_search).getActionView();
        if(v!=null){
            final EditText editText=v.findViewById(R.id.editText2);

            if(editText!=null){
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Toast.makeText(getApplicationContext(),v.getText().toString(),Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i=item.getItemId();
        switch (i){
            case R.id.menu_refresh :
                Toast.makeText(this,"새로고침",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search :
                Toast.makeText(this,"검색",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting :
                Toast.makeText(this,"설정",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
