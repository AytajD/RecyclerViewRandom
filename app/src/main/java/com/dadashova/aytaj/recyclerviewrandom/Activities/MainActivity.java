package com.dadashova.aytaj.recyclerviewrandom.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dadashova.aytaj.recyclerviewrandom.Containers.PaginationScrollListener;
import com.dadashova.aytaj.recyclerviewrandom.Containers.RecyclerAdapter;
import com.dadashova.aytaj.recyclerviewrandom.Containers.RecyclerModel;
import com.dadashova.aytaj.recyclerviewrandom.R;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  {

    private static final String TAG = "MainActivity";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private RecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(this);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter(new ArrayList<RecyclerModel>());
        mRecyclerView.setAdapter(mAdapter);
        preparedListItem();



        mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                preparedListItem();

            }


            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });



    }

    private void preparedListItem() {
        final ArrayList<RecyclerModel> items = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Random random = new Random();
                RecyclerModel postItem = new RecyclerModel();

                for (int i = 0; i < 10; i++) {


                    int randomNum = random.nextInt(2);
                   itemCount++;
                   if (randomNum == 0){

                    postItem.setmName("Mersedes" + itemCount);
                    postItem.setmImgId(R.drawable.a);

                   }else if (randomNum == 1){
                       postItem.setmName("Ferrary" + itemCount);
                       postItem.setmImgId(R.drawable.d);
                       postItem.setRight(true);
                   }

                    Log.d(TAG, "run: " + itemCount);


                    items.add(postItem);

                }
                if (currentPage != PAGE_START) mAdapter.removeLoading();
                mAdapter.addAll(items);
                swipeRefresh.setRefreshing(false);
                if (currentPage < totalPage) mAdapter.addLoading();
                else isLastPage = true;
                isLoading = false;

            }
        }, 1500);
    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        mAdapter.clear();
        preparedListItem();


    }
}
