package com.cb.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    TextView mTextView;
    ProgressBar mProgressBar;
    Button mDownloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("cb", "onCreate");

        mTextView = (TextView)findViewById(R.id.tv);
        mProgressBar = (ProgressBar)findViewById(R.id.progress);
        mDownloadBtn = (Button)findViewById(R.id.download);
        mDownloadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyAsyncTask task = new MyAsyncTask();
                task.execute(100);
            }
        });

    }

    class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v("cb", "ready to start");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0;i<=100;i++){
                mProgressBar.setProgress(i);
                publishProgress(i);
                try {
                    Thread.sleep(integers[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "finished";
        }
        
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mTextView.setText(values[0]+"%");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("finished")) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                setTitle("Finished");
            }
        }
    }
}