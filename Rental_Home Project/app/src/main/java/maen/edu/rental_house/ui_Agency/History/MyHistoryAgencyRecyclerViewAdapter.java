package maen.edu.rental_house.ui_Agency.History;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import maen.edu.rental_house.Reserve;
import maen.edu.rental_house.SetOnRecyclerViewClickListener;
import maen.edu.rental_house.databinding.FragmentAgencyHistoryRowBinding;


public class MyHistoryAgencyRecyclerViewAdapter extends RecyclerView.Adapter<MyHistoryAgencyRecyclerViewAdapter.ViewHolder> {
    private final SetOnRecyclerViewClickListener setOnRecyclerViewClickListener;
    private List<Reserve> mValues;


    public MyHistoryAgencyRecyclerViewAdapter(List<Reserve> items, SetOnRecyclerViewClickListener setOnRecyclerViewClickListener) {
        mValues = items;
        this.setOnRecyclerViewClickListener = setOnRecyclerViewClickListener;
    }

    public void setMValues(List<Reserve> mValues) {
        this.mValues = mValues;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentAgencyHistoryRowBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false), setOnRecyclerViewClickListener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCityView.setText(mValues.get(position).getCity());
        holder.mPostalView.setText(String.valueOf(mValues.get(position).getPostal()));
        holder.mDateView.setText(mValues.get(position).getDate());
        holder.mFNameView.setText(mValues.get(position).getFirstName());
        holder.mLNameView.setText(mValues.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
    public Reserve getItem(int position) {
        return mValues.get(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mCityView;
        public final TextView mPostalView;
        public final TextView mDateView;
        public final TextView mFNameView;
        public final TextView mLNameView;

        public Reserve mItem;
        SetOnRecyclerViewClickListener setOnRecyclerViewClickListener;

        public ViewHolder(FragmentAgencyHistoryRowBinding binding, SetOnRecyclerViewClickListener setOnRecyclerViewClickListener) {
            super(binding.getRoot());

            mCityView = binding.HistoryCity;
            mPostalView = binding.HistoryPAddress;
            mDateView = binding.HistoryPeriod;
            mFNameView = binding.HistoryFirstName;
            mLNameView = binding.HistoryLastName;
            this.setOnRecyclerViewClickListener = setOnRecyclerViewClickListener;

            itemView.setOnClickListener(this);
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + mCityView.getText() + "' '" + mPostalView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
           // setOnRecyclerViewClickListener.setOnRecyclerViewListClicked(v, getLayoutPosition());
        }

    }

}