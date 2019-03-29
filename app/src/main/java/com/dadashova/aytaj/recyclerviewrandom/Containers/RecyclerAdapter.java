package com.dadashova.aytaj.recyclerviewrandom.Containers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dadashova.aytaj.recyclerviewrandom.R;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_RIGHT = 1;
    private static final int VIEW_TYPE_LEFT = 2;
    private boolean isLoaderVisible = false;

    private List<RecyclerModel> mItems;

    public RecyclerAdapter(List<RecyclerModel> items) {
        this.mItems = items;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_RIGHT:
                return new RightHolder(
                        LayoutInflater.from(parent
                                .getContext())
                                .inflate(R.layout.item_row_right, parent, false));
            case VIEW_TYPE_LOADING:
                return new FooterHolder(LayoutInflater
                        .from(parent
                                .getContext()).inflate(R.layout.item_loading, parent, false));
            case VIEW_TYPE_LEFT:
                return new LeftHolder(LayoutInflater
                        .from(parent
                                .getContext()).inflate(R.layout.item_row_left, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case VIEW_TYPE_RIGHT:
                RightHolder holder1 = (RightHolder) holder;
                holder1.imageView.setImageResource(R.drawable.a);
                holder1.textViewTitle.setText("Ferrary");
                break;

            case VIEW_TYPE_LEFT:
                LeftHolder holder2 = (LeftHolder) holder;
                holder2.mImg.setImageResource(R.drawable.a);
                holder2.mName.setText("Ferrary");
                break;

            case VIEW_TYPE_LOADING:
                break;
        }
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == mItems.size() - 1 && isLoaderVisible){

            return VIEW_TYPE_LOADING;
        }else if (mItems.get(position).isRight()){
            return VIEW_TYPE_RIGHT;

        }else
            return VIEW_TYPE_LEFT;

    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void add(RecyclerModel response) {
        mItems.add(response);
        notifyItemInserted(mItems.size() - 1);
    }

    public void addAll(List<RecyclerModel> items) {
        for (RecyclerModel response : items) {
            add(response);
        }
    }


    private void remove(RecyclerModel items) {
        int position = mItems.indexOf(items);
        if (position > -1) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        add(new RecyclerModel());
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = mItems.size() - 1;
        RecyclerModel item = getItem(position);
        if (item != null) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    RecyclerModel getItem(int position) {
        return mItems.get(position);
    }



    class RightHolder extends BaseViewHolder {
        @BindView(R.id.txt_name)
        TextView textViewTitle;
        @BindView(R.id.img_profile)
        ImageView imageView;


        RightHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            RecyclerModel item = mItems.get(position);



            textViewTitle.setText(item.getmName());
            imageView.setImageResource(item.getmImgId());
        }

}


    public class FooterHolder extends BaseViewHolder {

        @BindView(R.id.progress_bar)
        ProgressBar mProgressBar;


        FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

    }


    public class LeftHolder extends BaseViewHolder {

        @BindView(R.id.text)
        TextView mName;
        @BindView(R.id.img_profile)
        ImageView mImg;


        LeftHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

    }









}