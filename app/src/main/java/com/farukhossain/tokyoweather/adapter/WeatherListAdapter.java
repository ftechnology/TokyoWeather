package com.farukhossain.tokyoweather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.farukhossain.tokyoweather.R;

import com.farukhossain.tokyoweather.appfrm.AbsRecycleViewBaseAdapter;
import com.farukhossain.tokyoweather.appfrm.Constants;
import com.farukhossain.tokyoweather.appfrm.Convert;
import com.farukhossain.tokyoweather.appfrm.LogUtil;
import com.farukhossain.tokyoweather.appfrm.NotifyObserver;
import com.farukhossain.tokyoweather.appfrm.ResponseObject;
import com.farukhossain.tokyoweather.appfrm.ViewHolder;
import com.farukhossain.tokyoweather.model.IconMapingModel;
import com.farukhossain.tokyoweather.model.ResponseData;
import com.farukhossain.tokyoweather.model.TemperatureData;
import com.farukhossain.tokyoweather.model.WeatherData;
import com.farukhossain.tokyoweather.model.WeatherDataList;
import com.farukhossain.tokyoweather.webservice.GetWeatherDataService;
import com.farukhossain.tokyoweather.webservice.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherListAdapter extends AbsRecycleViewBaseAdapter {
    private LayoutInflater mInflater;

    private ArrayList<ResponseData> mDataList;
    private Context mContext;

    public static final String STR_TODAY = "Today";
    public static final String STR_TOMORROW = "Tomorrow";


    // Constructor with required parameter
    public WeatherListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    protected void init() {

    }

    public void setmNotifyObserver(NotifyObserver notifyObserver) {
        this.mNotifyObserver = notifyObserver;
    }

    public void setDataList(ArrayList<ResponseData> dataList) {
        this.mDataList = dataList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ResponseData data = mDataList.get(i);

        TemperatureData temperatureData = data.getTemp();
        holder.day.setText(getFormatedDate(i, data));
        holder.temp_avg.setText("" + Convert.toInt(temperatureData.getDay()) + (char) 0x00B0);
        holder.temp_min.setText("" + Convert.toInt(temperatureData.getMin()) + (char) 0x00B0);

        ArrayList<WeatherData> weatherData = data.getWeather();
        if (weatherData != null && weatherData.size() > 0) {
            holder.status.setText(weatherData.get(0).getMain());
            holder.imageView.setImageResource(IconMapingModel.getInstance().getWeatherIcon(weatherData.get(0).getDescription()));
        }

    }

    @Override
    public ResponseObject loadData() {

        GetWeatherDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetWeatherDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<WeatherDataList> call = service.getAllData();

        /*Log the URL called*/
        String REQ_URL = call.request().url().toString();
        LogUtil.d("URL Called", REQ_URL + "");
        if(mNotifyObserver != null)
            mNotifyObserver.update(new ResponseObject(Constants.CODE_SUCCESS, Constants.MSG_LOAD_START, null));
        call.enqueue(new Callback<WeatherDataList>() {
            @Override
            public void onResponse(Call<WeatherDataList> call, Response<WeatherDataList> response) {

                if (response.isSuccessful()) {
                    if(mNotifyObserver != null)
                        mNotifyObserver.update(new ResponseObject(Constants.CODE_SUCCESS, Constants.MSG_LOAD_FINISH, response.body().getWeatherArrayList()));
                    generateAdapterDataList(response.body().getWeatherArrayList());
                } else {
                    if(mNotifyObserver != null)
                        mNotifyObserver.update(new ResponseObject(Constants.CODE_FAIL, "", null));
                    Toast.makeText(mContext, mContext.getString(R.string.STR_FETCH_FAIL), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherDataList> call, Throwable t) {
                if(mNotifyObserver != null)
                    mNotifyObserver.update(new ResponseObject(Constants.CODE_FAIL, "", null));
                Toast.makeText(mContext, mContext.getString(R.string.STR_FETCH_FAIL), Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateAdapterDataList(ArrayList<ResponseData> dataList) {
        dataList.remove(0);
        mDataList = dataList;
        notifyDataSetChanged();
    }

    private String getFormatedDate(int pos, ResponseData data) {
        String date = "";
        if (pos == 0) {
            date = STR_TOMORROW;
        } else if (pos > 0 && pos < 7) {
            date = Convert.getWeekDayName(Convert.toLong(data.getDt()));
        } else {
            date = Convert.getYearDay(Convert.toLong(data.getDt()));
        }

        return date;
    }


    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }


}