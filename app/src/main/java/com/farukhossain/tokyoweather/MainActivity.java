package com.farukhossain.tokyoweather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farukhossain.tokyoweather.adapter.WeatherListAdapter;
import com.farukhossain.tokyoweather.appfrm.BaseActivity;
import com.farukhossain.tokyoweather.appfrm.Constants;
import com.farukhossain.tokyoweather.appfrm.Convert;
import com.farukhossain.tokyoweather.appfrm.NetworkUtill;
import com.farukhossain.tokyoweather.appfrm.ResponseObject;
import com.farukhossain.tokyoweather.model.IconMapingModel;
import com.farukhossain.tokyoweather.model.TemperatureData;
import com.farukhossain.tokyoweather.model.WeatherData;
import com.farukhossain.tokyoweather.model.ResponseData;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WeatherListAdapter mAdapter;

    private TextView tv_today, tv_temp_avg, tv_min, tv_status;
    private ImageView iv_weather;
    private ProgressDialog mProgressDoalog;


    @Override
    protected void createView(Bundle savedInstanceState) {
        this.setContentView(R.layout.activity_main);
        initUI();
        setupRecycleView();

    }

    @Override
    protected void createAdapter() {
        mAdapter = new WeatherListAdapter(this);
        mAdapter.setmNotifyObserver(this);
    }

    @Override
    protected void loadData() {
        if (!NetworkUtill.isNetworkAvailable(this)) {
            Toast.makeText(this, getString(R.string.STR_NO_INTERNET), Toast.LENGTH_LONG).show();
            return;
        }
        mAdapter.loadData();
    }

    @Override
    public void doUpdateRequest(ResponseObject response) {
        if (response != null) {
            int code = response.getResponseCode();
            if (code == Constants.CODE_SUCCESS) {
                String message = response.getResponseMsg();

                if (message.equals(Constants.MSG_LOAD_START)) {
                    showProgressDialog();
                }

                if (message.equals(Constants.MSG_LOAD_FINISH)) {
                    hideProgressDialog();
                    generateCollapseViewData((ArrayList<ResponseData>) response.getDataObject());
                }
            }

            if (code == Constants.CODE_FAIL) {
                hideProgressDialog();
            }
        }

    }

    private void initUI() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");

        mRecyclerView = (RecyclerView) findViewById(R.id.weather_recycler_view);
        mRecyclerView.setAdapter(mAdapter);

        tv_today = (TextView) findViewById(R.id.tv_today);
        tv_temp_avg = (TextView) findViewById(R.id.tv_avg_temp);
        tv_min = (TextView) findViewById(R.id.tv_min_temp);
        tv_status = (TextView) findViewById(R.id.tv_today_status);
        iv_weather = (ImageView) findViewById(R.id.iv_today_status);
    }

    private void setupRecycleView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void generateCollapseViewData(ArrayList<ResponseData> dataList) {
        if (dataList != null && dataList.size() > 0) {
            ResponseData data = dataList.get(0);
            TemperatureData temperatureData = data.getTemp();
            tv_today.setText(Convert.getTodayDate(Convert.toLong(data.getDt())));
            tv_temp_avg.setText("" + Convert.toInt(temperatureData.getDay()) + (char) 0x00B0);
            tv_min.setText("" + Convert.toInt(temperatureData.getMin()) + (char) 0x00B0);

            ArrayList<WeatherData> weatherData = data.getWeather();
            if (weatherData != null && weatherData.size() > 0) {
                tv_status.setText(weatherData.get(0).getMain());
                iv_weather.setImageResource(IconMapingModel.getInstance().getArtWeatherIcon(weatherData.get(0).getDescription()));
            }

        }
    }

    private void showProgressDialog() {
        mProgressDoalog = new ProgressDialog(MainActivity.this);
        mProgressDoalog.setMessage(getString(R.string.STR_LOADING));
        mProgressDoalog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDoalog != null && mProgressDoalog.isShowing()) {
            mProgressDoalog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
}
