package maen.edu.rental_house.ui_Agency.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import maen.edu.rental_house.House;
import maen.edu.rental_house.R;
import maen.edu.rental_house.SetOnRecyclerViewClickListener;
import maen.edu.rental_house.databinding.FragmentHouseRowBinding;


public class MyHouseRecyclerViewAdapter extends RecyclerView.Adapter<MyHouseRecyclerViewAdapter.ViewHolder> {
    private final SetOnRecyclerViewClickListener setOnRecyclerViewClickListener;
    private List<House> mValues;


    public MyHouseRecyclerViewAdapter(List<House> items, SetOnRecyclerViewClickListener setOnRecyclerViewClickListener) {
        mValues = items;
        this.setOnRecyclerViewClickListener = setOnRecyclerViewClickListener;
    }

    public void setMValues(List<House> mValues) {
        this.mValues = mValues;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentHouseRowBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false), setOnRecyclerViewClickListener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCityView.setText(mValues.get(position).getCity());
        holder.mTypeView.setText(mValues.get(position).getType());
        holder.mYearView.setText(String.valueOf(mValues.get(position).getConstructingYear()));
        holder.mPriceView.setText(String.valueOf(mValues.get(position).getPrice()));

        if (mValues.get(position).isReserved())
            holder.mReservedView.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_baseline_done_24, 0);
        else {
            holder.mReservedView.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_baseline_horizontal_rule_24, 0);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
    public House getItem(int position) {
        return mValues.get(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mCityView;
        public final TextView mTypeView;
        public final TextView mYearView;
        public final TextView mReservedView;
        public final TextView mPriceView;
        public House mItem;
        SetOnRecyclerViewClickListener setOnRecyclerViewClickListener;

        public ViewHolder(FragmentHouseRowBinding binding, SetOnRecyclerViewClickListener setOnRecyclerViewClickListener) {
            super(binding.getRoot());
            mCityView = binding.CityView;
            mPriceView = binding.PriceView;
            mYearView = binding.YearView;
            mTypeView = binding.TypeView;
            mReservedView = binding.ReservedView;
            this.setOnRecyclerViewClickListener = setOnRecyclerViewClickListener;

            itemView.setOnClickListener(this);
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + mCityView.getText() + "' '" + mTypeView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            setOnRecyclerViewClickListener.setOnRecyclerViewListClicked(v, getLayoutPosition());
        }


    }


}