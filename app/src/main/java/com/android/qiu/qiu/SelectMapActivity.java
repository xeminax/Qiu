package com.android.qiu.qiu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

public class SelectMapActivity extends AppCompatActivity {

    private MapView mapView;
    private BaiduMap baiduMap;
    private Button selectOK;
    private Button selectCancel;
    private TextView locationText = null;
    private BitmapDescriptor mCurrentMarker;

    private MyLocationConfiguration.LocationMode mCurrentMode;
    private LocationClient mLocationClient = null;
    boolean isFirstLoc = true;
    private GeoCoder geoCoder = null;
    private Bundle bundle = new Bundle();
    private LatLng latLng = null;

    //位置监听器
    private BDLocationListener myListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null)
                return;

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();

            baiduMap.setMyLocationData(locData);    //设置定位数据

           /* LatLng myPoint = new LatLng(location.getLatitude(), location.getLongitude());
            BitmapDescriptor maker = BitmapDescriptorFactory.fromResource(R.drawable.touxiang);
            OverlayOptions option = new MarkerOptions().position(myPoint).icon(maker);
            baiduMap.addOverlay(option);*/


            if (isFirstLoc) {
                isFirstLoc = false;

                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);   //设置地图中心点以及缩放级别
                //MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                baiduMap.animateMapStatus(u);
            }

        }
    };

    private OnMapClickListener mapClickListener = new OnMapClickListener() {

        @Override
        public boolean onMapPoiClick(MapPoi arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onMapClick(LatLng arg0) {
            // TODO Auto-generated method stub
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(arg0));
            latLng = arg0;


        }
    };

    private OnGetGeoCoderResultListener geoListener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                /*Toast.makeText(SelectMapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                        .show();*/
                return;
            }
            /*baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                    .getLocation()));
            String strInfo = String.format("纬度：%f 经度：%f",
                    result.getLocation().latitude, result.getLocation().longitude);
            Toast.makeText(SelectMapActivity.this, strInfo, Toast.LENGTH_LONG).show();*/
            //bundle.putDouble("latitude",result.getLocation().latitude);
            //bundle.putDouble("longitude",result.getLocation().longitude);
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
                Toast.makeText(SelectMapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                        .show();
                return;
            }
            //获取反向地理编码结果
            baiduMap.clear();
            baiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.icon_gcoding)));
            baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                    .getLocation()));
           /* Toast.makeText(SelectMapActivity.this, result.getAddress(),
                    Toast.LENGTH_LONG).show();
            locationText.setText(result.getAddress());*/
            Toast.makeText(SelectMapActivity.this, result.getBusinessCircle(),
                    Toast.LENGTH_LONG).show();
            locationText.setText(result.getAddress());

        }



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////定位
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

        ////地图显示
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //初始化SDK的全局变量
        //SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_select_map);

        //设置定位参数
        initLocation();

        mapView = (MapView) findViewById(R.id.mapView);
        baiduMap = mapView.getMap();

        locationText = (TextView) findViewById(R.id.locationText);
        // 初始化搜索模块，注册事件监听
        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(geoListener);

        baiduMap.setOnMapClickListener(mapClickListener);

        //自定义定位图标
        /*mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.touxiang);
        MyLocationConfiguration configuration = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        baiduMap.setMyLocationConfigeration(configuration);*/

        //普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //开启交通图
        baiduMap.setTrafficEnabled(true);
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);

        //开启定位
        mLocationClient.start();

        selectOK = (Button)findViewById(R.id.selectOK);
        selectCancel = (Button)findViewById(R.id.selectCancel);

        selectOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // System.out.println("w");

                bundle.putString("locationText",locationText.getText().toString());
                bundle.putDouble("latitude",latLng.latitude);
                bundle.putDouble("longitude",latLng.longitude);
                //geoCoder.geocode(new GeoCodeOption().address(locationText.getText().toString()));
                Intent intent = new Intent();
                //intent.putExtra("locationText",locationText.getText());
                intent.putExtra("bundle",bundle);
                setResult(1,intent);
                finish();
            }
        });

        selectCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        mLocationClient.stop();
        super.onDestroy();
        mapView = null;
        //mapView.onDestroy();
        geoCoder.destroy();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

}
