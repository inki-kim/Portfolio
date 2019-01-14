package com.project.inki.customviewexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);

        itemAdapter=new ItemAdapter();

        itemAdapter.addItem(new ItemList("김인기","010-9278-9520","25",R.drawable.images));
        itemAdapter.addItem(new ItemList("이성빈","010-9356-2222","25",R.drawable.images2));
        itemAdapter.addItem(new ItemList("손성기","010-4578-1111","25",R.drawable.images));
        itemAdapter.addItem(new ItemList("정성영","010-2184-5846","25",R.drawable.images));
        itemAdapter.addItem(new ItemList("홍성준","010-9275-2568","25",R.drawable.images));
        itemAdapter.addItem(new ItemList("김인기","010-9278-9520","25",R.drawable.images));
        itemAdapter.addItem(new ItemList("이성빈","010-9356-2222","25",R.drawable.images2));
        itemAdapter.addItem(new ItemList("손성기","010-4578-1111","25",R.drawable.images));
        itemAdapter.addItem(new ItemList("정성영","010-2184-5846","25",R.drawable.images));
        itemAdapter.addItem(new ItemList("홍성준","010-9275-2568","25",R.drawable.images));

        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemList itemList=(ItemList)itemAdapter.getItem(position);
                Toast.makeText(getApplicationContext(),itemList.getName()+"고름",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class ItemAdapter extends BaseAdapter {

        ArrayList<ItemList> arrayList=new ArrayList<ItemList>();

        public void addItem(ItemList itemList){
            arrayList.add(itemList);
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemView itemView=new ItemView(getApplicationContext());
            ItemList itemList=arrayList.get(position);

            itemView.setMember_name(itemList.getName());
            itemView.setPhone_number(itemList.getNumber());
            itemView.setList_number(itemList.getListnumber());
            itemView.setImageView(itemList.getRes());

            return itemView;
        }
    }
}

