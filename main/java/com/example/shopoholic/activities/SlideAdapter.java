package com.example.shopoholic.activities;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shopoholic.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.List;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> {

    private List<SlideIten> slideItems;
    private ViewPager2 viewPager2;

    SlideAdapter(List<SlideIten> slideItems,ViewPager2 viewPager2) {
        this.slideItems=slideItems;
        this.viewPager2 = viewPager2;
    }
    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container,
                parent,
                false
        )
        );
    }
    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
holder.setImage(slideItems.get(position));
    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;

        SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageslide);
        }
        void setImage(SlideIten slideItem){
            imageView.setImageResource(slideItem.getImage());
        }
    }
}
