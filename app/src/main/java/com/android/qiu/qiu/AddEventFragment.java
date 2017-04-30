package com.android.qiu.qiu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.baidu.platform.comapi.map.B;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by xemina on 2017/3/29.
 */

public class AddEventFragment extends Fragment {
    private static final String DIALOG_DATE="DialogDate";
    private static final int REQUEST_DATE = 0;  //日期目标



    private byte[] mImageBytes = null;


    private ImageView mAddEventImage;
    private EditText mTopicEditText;
    private Button mTimeEditButton;
    private Button mDateEditButton;
    private EditText mLocEditText;
    private EditText mContentEditText;
    private ImageView mImageView;
    private Button mPublishButton;
    private Date mDate;

    private AVGeoPoint point;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_add, container, false);


        mAddEventImage = (ImageView) view.findViewById(R.id.event_add_Image);
        mTopicEditText = (EditText) view.findViewById(R.id.new_event_title);
        mDateEditButton = (Button) view.findViewById(R.id.new_event_add_date);

        mLocEditText = (EditText) view.findViewById(R.id.event_add_place);
        mContentEditText = (EditText) view.findViewById(R.id.editText3);
        mImageView = (ImageView) view.findViewById(R.id.event_add_Image);
        mPublishButton = (Button) view.findViewById(R.id.button);

       // mDate = (String) DateFormat.format("EEEE,MMM, dd,yyyy",mevent.getDate());


        mDateEditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mDate);
                dialog.setTargetFragment(AddEventFragment.this,REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //select image from phone.
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 42);

            }
        });

        mPublishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PublishActivity

                if ("".equals(mTopicEditText.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(mTimeEditButton.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(mDateEditButton.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(mLocEditText.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入位置信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(mContentEditText.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入内容描述", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mImageBytes == null) {
                    Toast.makeText(getActivity(), "请选择一张照片", Toast.LENGTH_SHORT).show();
                    return;
                }


                AVObject product = new AVObject("Activity");
                product.put("title", mTopicEditText.getText().toString());
                product.put("description", mContentEditText.getText().toString());
                product.put("location_coordinate", point);//test data
                product.put("location_string", mLocEditText.getText().toString());
                product.put("time", mDateEditButton.getText().toString());
                product.put("image", new AVFile("activityPic", mImageBytes));
                product.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            getActivity().finish();
                            Toast.makeText(getActivity(), "恭喜你，活动发布成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println(e.getMessage());
                        }
                    }
                });


            }
        });

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取日期时间
        if(requestCode ==REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mDate = date;
            mDateEditButton.setText(mDate.toString());

        }

        if (requestCode == 42 && resultCode == getActivity().RESULT_OK) {
            try {
                mImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData()));
                mImageBytes = getBytes(getActivity().getContentResolver().openInputStream(data.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if(resultCode == 1){
            //获取回传数据
            //String locationText = intent.getStringExtra("locationText");
            Bundle bundle = new Bundle();
            bundle = data.getBundleExtra("bundle");

            String locationText = bundle.getString("locationText");//位置信息
            double longitude = bundle.getDouble("longitude");//经度
            double latitude = bundle.getDouble("latitude");//纬度


            mLocEditText = (EditText) getActivity().findViewById(R.id.event_add_place);
            mLocEditText.setText(locationText);

            point = new AVGeoPoint(latitude,longitude);
            //System.out.println("经度："+longitude);
            //System.out.println("纬度："+latitude);

        }

    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //Button button = (Button) getActivity().findViewById(R.id.button_addlocation);
        mLocEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("q");
                Intent intent = new Intent();
                intent.setClass(getActivity(),SelectMapActivity.class);

                //getActivity().startActivityForResult(intent,1);
                startActivityForResult(intent,1);
                //getActivity().startActivity(intent);
            }
        });
    }


}
