package is.loskutov.alliance.system;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import is.loskutov.alliance.R;
import is.loskutov.alliance.model.Category;
import is.loskutov.alliance.view.activities.TestingActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private ArrayList data;

    class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        ViewHolder(View itemView) {
            super(itemView);

            button = (Button) itemView.findViewById(R.id.category_button);
        }
    }

    public CategoryAdapter(ArrayList data) {
        this.data = data;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buttons_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        final Category category = (Category) data.get(position);
        holder.button.setText(category.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(context, TestingActivity.class);

                intent.putExtra("category", (Parcelable) category);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
