package com.example.excalculator;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ListViewActivity extends Activity {
    ArrayAdapter adapter;
    ListView listView;
    static String query = "select * from exrate " ;
    MyAsyncTask myTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new ArrayAdapter(this,R.layout.simple_list_item_1);

        listView = findViewById(R.id.exChangeList);
        listView.setAdapter(adapter);

        myTask = new MyAsyncTask();
        myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class MyAsyncTask extends AsyncTask<String, Void, ArrayList<String>>
    {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }


        @Override
        protected ArrayList<String> doInBackground( String... params){


            ArrayList<String> list = new ArrayList<String>();



            ResultSet reset = null;
            Connection conn = null;



            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.55.207;databaseName=mobile_project","leehs","12345");
                Statement stmt = conn.createStatement();

                reset = stmt.executeQuery(query);



                while(reset.next()){

                    if ( isCancelled() ) break;

                    final String str = reset.getString(1)+"("+reset.getString(2)+")";
                    list.add(str);


                }
                conn.close();
            }

            catch (Exception e)
            {

            }

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list){

            adapter.clear();
            adapter.addAll(list);
            adapter.notifyDataSetChanged();

        }

        @Override
        protected void onCancelled(){
            super.onCancelled();
        }
    }
}
