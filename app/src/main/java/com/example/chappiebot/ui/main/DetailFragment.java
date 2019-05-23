package com.example.chappiebot.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chappiebot.R;
import com.example.chappiebot.base.BaseFragment;
import com.example.chappiebot.common.Constants;
import com.example.chappiebot.common.Utils;
import com.example.chappiebot.model.Detail;
import com.example.chappiebot.model.Feed;
import com.example.chappiebot.model.Section;
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
public class DetailFragment extends BaseFragment {
    TextView tvTitle, tvDes;
    ImageView imageView;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        tvTitle = root.findViewById(R.id.tvDetailTitle);
        tvDes = root.findViewById(R.id.tvDetailDes);
        imageView = root.findViewById(R.id.imageView);
        linearLayout = root.findViewById(R.id.lnDetailContent);
        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                    HttpRequest req = new HttpRequest(Constants.URL_DETAIL);
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
                    Detail m = gson.fromJson(response, Detail.class);
                    showDataDetail(m);
                } else {
//                    Toast.makeText(MainActivity.this, getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showDataDetail(Detail m) {
        if (m == null)
            return;
        mActivity.runOnUiThread(() -> {
            tvTitle.setText(m.getTitle());
            tvDes.setText(m.getDescription());
            if (null != m.getSections() && !m.getSections().isEmpty())
                for (Section obj : m.getSections()) {
                    LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    TextView tv = new TextView(mActivity);
                    tv.setLayoutParams(lparams);
                    tv.setText(!TextUtils.isEmpty(obj.getContent().getCaption()) ? obj.getContent().getCaption() : obj.getContent().getText());
                    tv.setTextColor(!TextUtils.isEmpty(obj.getContent().getCaption()) ? Color.GREEN : Color.BLACK);
                    tv.setTextSize(!TextUtils.isEmpty(obj.getContent().getCaption()) ? 16f : 14f);
                    this.linearLayout.addView(tv);
                }
        });
    }
}