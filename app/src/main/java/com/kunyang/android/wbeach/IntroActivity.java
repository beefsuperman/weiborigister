package com.kunyang.android.wbeach;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 坤阳 on 2017/7/24.
 */

public class IntroActivity extends Activity {
    static final private int EXIT_ID = Menu.FIRST;

    private EditText m_search_key;
    private ImageButton m_btn_ok;
    private TextView m_header;
    private ListView m_tweet_list;
    private InputMethodManager im_ctrl;
    private JSONArray jresult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Use res/layout/main.xml as the view of
         * this Activity */
        setContentView(R.layout.search);
        m_search_key = (EditText) findViewById(R.id.search_key);
        m_btn_ok = (ImageButton) findViewById(R.id.btn_ok);
        m_tweet_list = (ListView) findViewById(R.id.tweet_list);
        m_header = (TextView) findViewById(R.id.tweet_header);
        m_btn_ok.setOnClickListener(ok_handler);

        im_ctrl = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        /* the context menu currently has only one option */
        menu.add(0, EXIT_ID, 0, R.string.exit);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case EXIT_ID:
                finish(); /* terminate the application */
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener ok_handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* hide the soft keyboard */
            im_ctrl.hideSoftInputFromWindow(m_search_key.getWindowToken(), 0);

            new TwitterTask().execute(m_search_key.getText().toString());
            /* show a brief message */
            Toast.makeText(getApplicationContext(),
                    getString(R.string.hello) + " " + m_search_key.getText(),
                    Toast.LENGTH_LONG).show();
        }
    };

    private class TwitterTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String query = params[0];
            if (query == null) return "";
            try {
                String result = WeiboHelper.downloadFromServer(query);
                return result;
            } catch (WeiboHelper.ApiException e) {
                e.printStackTrace();
                Log.e("HSD", "Problem making twitter search request");
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            m_tweet_list.setAdapter(new JSONAdapter(getApplicationContext()));
            try {
                JSONObject obj = new JSONObject(result);
                jresult = obj.getJSONArray("results");
                String info = obj.getString("next_page");
                m_header.setText(info);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class JSONAdapter extends BaseAdapter
    {
        private Context mCtx;

        public JSONAdapter (Context c)
        {
            mCtx = c;
        }

        @Override
        public int getCount() {
            return jresult == null ? 0 : jresult.length();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            TextView tv;

            if (convertView == null)
                tv = new TextView(mCtx);
            else
                tv = (TextView) convertView;
            try {
                JSONObject obj = jresult.getJSONObject(pos);
                tv.setText(obj.getString("from_user") + ": " +
                        obj.getString("text"));
            } catch (JSONException e) {
                tv.setText (e.getMessage());
            }
            return tv;
        }

    }
}
