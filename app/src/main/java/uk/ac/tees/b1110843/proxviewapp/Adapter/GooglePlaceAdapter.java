package uk.ac.tees.b1110843.proxviewapp.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.tees.b1110843.proxviewapp.GooglePlaceModel;
import uk.ac.tees.b1110843.proxviewapp.NearLocationInterface;
import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.LocationItemLayoutBinding;

public class GooglePlaceAdapter extends RecyclerView.Adapter<GooglePlaceAdapter.ViewHolder> {

    private List<GooglePlaceModel> googlePlaceModels;
    private NearLocationInterface nearLocationInterface;

    public GooglePlaceAdapter(NearLocationInterface nearLocationInterface) {
        this.nearLocationInterface = this.nearLocationInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.location_item_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (googlePlaceModels != null) {
            GooglePlaceModel placeModel = googlePlaceModels.get(position);
            holder.binding.setGooglePlaceModel(placeModel);
            holder.binding.setListener(nearLocationInterface);
        }

    }

    @Override
    public int getItemCount() {
        if (googlePlaceModels != null)
            return googlePlaceModels.size();
        else
            return 0;
    }

    public void setGooglePlaceModels(List<GooglePlaceModel> googlePlaceModels) {
        this.googlePlaceModels = googlePlaceModels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LocationItemLayoutBinding binding;

        public ViewHolder(@NonNull LocationItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
