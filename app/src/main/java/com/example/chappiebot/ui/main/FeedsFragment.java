package com.example.chappiebot.ui.main;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.example.chappiebot.Base;
import com.example.chappiebot.R;
import com.example.chappiebot.base.BaseFragment;
import com.example.chappiebot.common.Constants;
import com.example.chappiebot.common.Utils;
import com.example.chappiebot.model.Feed;
import com.example.chappiebot.router.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedsFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<Feed> feedArrayList;
    private FeedsAdapter customeAdapter;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);

        listView = (ListView) root.findViewById(R.id.lv);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.pushFragments(mActivity.getCurrentTab(), new DetailFragment(), true, true);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void getData() throws IOException, JSONException {

        if (!Utils.isNetworkAvailable(getContext())) {
            Toast.makeText(getContext(), "Internet!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        Utils.showSimpleProgressDialog(getContext());
        new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void[] params) {
                String response = "";
                HashMap<String, String> map = new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest(Constants.URL_MAIN);
                    response = req.prepare(HttpRequest.Method.GET).sendAndReadString();
                } catch (Exception e) {
                    response = e.getMessage();
                }
                return response;
            }

            protected void onPostExecute(String result) {
                //do something with response
                Log.d("newwwss", result);
                onTaskCompleted(result, 200);
            }
        }.execute();
    }

    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response);
        switch (serviceCode) {
            case 200:
                Log.d("luantcq", "response : " + response);
                if (serviceCode == 200) {
                    Utils.removeSimpleProgressDialog();  //remove progress dialog

                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Feed>>() {
                    }.getType();

                    JSONObject resOBJ = null;
                    try {
                        resOBJ = new JSONObject(response);
                        JSONArray array = resOBJ.getJSONArray("items");
                        feedArrayList = gson.fromJson(array.toString(), listType);
                        Log.d("luantcq", "size= + " + feedArrayList.size());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    customeAdapter = new FeedsAdapter(getContext(), feedArrayList);
                    listView.setAdapter(customeAdapter);
                } else {
//                    Toast.makeText(MainActivity.this, getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }
}