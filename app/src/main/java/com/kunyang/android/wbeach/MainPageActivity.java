package com.kunyang.android.wbeach;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kunyang.android.wbeach.HttpUtil.WeiboFetchr;
import com.kunyang.android.wbeach.Object.UserInfo;
import com.kunyang.android.wbeach.Object.WeiboItem;
import com.kunyang.android.wbeach.db.UserLab;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class MainPageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<WeiboItem> mWeiboItemList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //UserInfo user =UserLab.get(MainPageActivity.this).getUser("0");
        //String token=user.getToken();
        new WeiboItemsTask().execute();

        mRecyclerView=(RecyclerView)this.findViewById(R.id.last_weibo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        WeiboAdapter adapter=new WeiboAdapter(mWeiboItemList);
        mRecyclerView.setAdapter(adapter);
    }

    private class WeiboItemsTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            new WeiboFetchr().fetchItems();
            return null;
        }
    }

    private class WeiboAdapter extends RecyclerView.Adapter<WeiboAdapter.ViewHolder>{

        private List<WeiboItem> mWeiboItemList;

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView friendImage;
            TextView friendName,dataText,content;

            public ViewHolder(View itemView) {
                super(itemView);
                friendImage=(ImageView)itemView.findViewById(R.id.people_img);
                friendName=(TextView)itemView.findViewById(R.id.name);
                dataText=(TextView)itemView.findViewById(R.id.when_and_where);
                content=(TextView)itemView.findViewById(R.id.content);
            }
        }

        public WeiboAdapter(List<WeiboItem> weiboItemList){
            mWeiboItemList=weiboItemList;
        }

        public WeiboAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.last_weibo_item,parent,false);
            ViewHolder holder=new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(WeiboAdapter.ViewHolder holder, int position) {
            WeiboItem weiboItem=mWeiboItemList.get(position);
            holder.friendImage.setImageDrawable(weiboItem.getFriendImg());
            holder.friendName.setText(weiboItem.getName());
            holder.dataText.setText(weiboItem.getDate());
            holder.content.setText(weiboItem.getContent());
        }

        @Override
        public int getItemCount() {
            return mWeiboItemList.size();
        }
    }
}
