package maen.edu.rental_house.ui_Rental.History;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import maen.edu.rental_house.R;
import maen.edu.rental_house.Reserve;
import maen.edu.rental_house.SetOnRecyclerViewClickListener;
import maen.edu.rental_house.databinding.FragmentRentalHistoryRowBinding;


public class MyHistoryRecyclerViewAdapter extends RecyclerView.Adapter<MyHistoryRecyclerViewAdapter.ViewHolder> {
    private final SetOnRecyclerViewClickListener setOnRecyclerViewClickListener;
    private List<Reserve> mValues;


    public MyHistoryRecyclerViewAdapter(List<Reserve> items, SetOnRecyclerViewClickListener setOnRecyclerViewClickListener) {
        mValues = items;
        this.setOnRecyclerViewClickListener = setOnRecyclerViewClickListener;
    }

    public void setMValues(List<Reserve> mValues) {
        this.mValues = mValues;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentRentalHistoryRowBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false), setOnRecyclerViewClickListener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCityView.setText(mValues.get(position).getCity());
        holder.mPostalView.setText(String.valueOf(mValues.get(position).getPostal()));
        holder.mDateView.setText(mValues.get(position).getDate());
        holder.mNameView.setText(mValues.get(position).getAgencyName());
     /*   if (mValues.get(position).isReserved())
            holder.mReservedView.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_baseline_done_24, 0);
        else {
            holder.mReservedView.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_baseline_horizontal_rule_24, 0);
        }*/
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
        public final TextView mNameView;
        public Reserve mItem;
        SetOnRecyclerViewClickListener setOnRecyclerViewClickListener;

        public ViewHolder(FragmentRentalHistoryRowBinding binding, SetOnRecyclerViewClickListener setOnRecyclerViewClickListener) {
            super(binding.getRoot());
            mCityView = binding.HistoryCity;
            mPostalView = binding.HistoryPostal;
            mDateView = binding.HistoryPeriod;
            mNameView = binding.HistoryAName;
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