package org.wysaid.databindingdeomo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.wysaid.databindingdeomo.adapter.StudioAdapter;
import org.wysaid.databindingdeomo.databinding.ActivityMainBinding;
import org.wysaid.databindingdeomo.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.SingleScheduler;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ImageModel> listImageModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StudioAdapter studioAdapter = new StudioAdapter();
        ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recyclerView = viewDataBinding.rcView;
//        ImageModel imageModel = new ImageModel("Luanvv++++++");
//        imageModel.setPath("https://androidwave.com/wp-content/uploads/2019/01/profile_pic.jpg");
//        listImageModel.add(imageModel);
//        ImageModel imageModel1 = new ImageModel("hadtt++++++");
//        imageModel1.setPath("https://androidwave.com/wp-content/uploads/2019/01/profile_pic.jpg");
        AppUtil.getImageMedia(this).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ImageModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ImageModel> imageModels) {
                        studioAdapter.setImageModels(imageModels);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        recyclerView.setAdapter(studioAdapter);
        studioAdapter.setListener(new StudioAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });


    }


}
